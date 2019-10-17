<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.UUID"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.File"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
	<%
		try {
			// 파일 정보
			String sFileInfo = "";
			// 파일명을 받는다 - 일반 원본 파일명
			String filename = request.getHeader("file-name");
			//파일 기본경로 
			String dftFilePath = request.getServletContext().getRealPath("/");
			out.println("dftFilePath : " + dftFilePath);
			// 파일 기본경로 _ 상세경로
			//        String filePath = dftFilePath + "WebContent" + File.separator + "pro_image" + File.separator;
			String filePath = dftFilePath + "img/upload" + File.separator;
			out.println("filePath : " + filePath);
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			out.println("filePath : " + filePath);
			String realFileNm = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = formatter.format(new java.util.Date());
			realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
			String rlFileNm = filePath + realFileNm;

			// 서버에 파일 쓰기 //////
			InputStream is = request.getInputStream();
			OutputStream os = new FileOutputStream(rlFileNm);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while ((numRead = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, numRead);
			}
			if (is != null)
				is.close();

			os.flush();
			os.close();

			///////////////////////

			// 정보 출력
			sFileInfo += "&bNewLine=true";
			// img 태그의 title 속성을 원본 파일명으로 적용시켜주기 위함
			sFileInfo += "&sFileName=" + filename;
			sFileInfo += "&sFileURL=" + "/semiRecipe/smartEditor/img/upload/" + realFileNm;
			PrintWriter o = response.getWriter();
			o.print(sFileInfo);
			o.flush();
			o.close();

		} catch (Exception e) {
			out.println("Error발생 : " + e.getMessage());
		}
	%>
</body>
</html>