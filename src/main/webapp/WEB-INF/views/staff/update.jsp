<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림</title>
</head>
<body>
<div class="container">
	<h1>알림</h1>
	<p>
		<strong>${tmp.staff_no }</strong> 번 회원의 정보를 수정 했습니다.
		<a href="${pageContext.request.contextPath }/users/staff_search_form.do">목록보기</a>
	</p>
</div>
</body>
</html>