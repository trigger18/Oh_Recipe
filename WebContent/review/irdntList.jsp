<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table id="irdntsTable">
<tr>
	<td colspan ="2" align ="center" height="70px">재료 중요도 선택 <br/>미 선택시 필수 재료로 등록됩니다.</td>

</tr>

<c:forEach items="${requestScope.irdntMain }" var="dto" varStatus="status">
<tr>
	<td width = "150px">
	<li class="irdnts" id="${dto.IRDNT_SN}" style="font-size:15px;">${dto.IRDNT_NM } </li>
	</td>
	<td>
		<select name="importance_${dto.IRDNT_SN}" id="${dto.IRDNT_SN}">
			<option value="4" selected="selected">필수</option>
			<option value="3">상</option>
			<option value="2">중</option>
			<option value="1">하</option>
		</select>
	</td>

</tr>

</c:forEach>
<tr>
   <td colspan ="2" align ="center" height="30px"> 부재료 </td>
</tr>
<c:forEach items="${requestScope.irdntSub }" var="dto" varStatus="status">
<tr>

	<td width = "150px">
	<li class="irdnts" id="${dto.IRDNT_SN}" style="font-size:15px;">${dto.IRDNT_NM }</li>
	</td>
	<td>
	<select name="importance_${dto.IRDNT_SN}" id="${dto.IRDNT_SN}">
		<option value="4" selected="selected">필수</option>
		<option value="3">상</option>
		<option value="2">중</option>
		<option value="1">하</option>
	</select>
	</td>
</c:forEach>
</table>
