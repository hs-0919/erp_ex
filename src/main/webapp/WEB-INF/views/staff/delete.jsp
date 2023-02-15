<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 알림</title>
</head>
<body>
<div class="container">
	<h1>알림</h1>
	<p>
		<strong>${param.STAFF_NAME}</strong>님의 정보를 삭제 했습니다.
	</p>
	<a href="${pageContext.request.contextPath}/users/staff_search_form.do">확인</a><a href="${pageContext.request.contextPath}/users/staff_updel_form.do">취소</a>
</div>
</body>
</html>