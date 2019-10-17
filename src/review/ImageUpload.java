package review;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImageUpload {

   public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         // 파일 정보
         String sFileInfo = "";
         // 파일명을 받는다 - 일반 원본 파일명
         String filename = req.getHeader("file-name");
         //파일 기본경로 
         String dftFilePath = req.getServletContext().getRealPath("/");
//         System.out.println("dftFilePath : " + dftFilePath);
         // 파일 기본경로 _ 상세경로
//         String filePath = dftFilePath + "WebContent" + File.separator + "pro_image" + File.separator;
         String filePath = dftFilePath + "upload" + File.separator;
  //       System.out.println("filePath : " + filePath);
         File file = new File(filePath);
         if(!file.exists()) {
            file.mkdirs();
         }
   //      System.out.println("filePath : " + filePath);
         String realFileNm = "";
         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
         String today = formatter.format(new java.util.Date());
         realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
         String rlFileNm = filePath + realFileNm;
         
         // 서버에 파일 쓰기 //////
         InputStream is = req.getInputStream();
         OutputStream os = new FileOutputStream(rlFileNm);
         int numRead;
         byte b[] = new byte[Integer.parseInt(req.getHeader("file-size"))];
         while((numRead = is.read(b, 0, b.length))!= -1) {
            os.write(b, 0, numRead);
         }
         if(is != null)
            is.close();
         
         os.flush();
         os.close();
         
         ///////////////////////
         
         // 정보 출력
         sFileInfo += "&bNewLine=true";
         // img 태그의 title 속성을 원본 파일명으로 적용시켜주기 위함
         sFileInfo += "&sFileName="+filename;
         sFileInfo += "&sFileURL="+"/semiRecipe/upload/"+realFileNm;
         PrintWriter out = resp.getWriter();
         out.print(sFileInfo);
         out.flush();
         out.close();
         
         
      }catch(Exception e) {
         System.out.println("Error발생 : " + e.getMessage());
      }
   }

}