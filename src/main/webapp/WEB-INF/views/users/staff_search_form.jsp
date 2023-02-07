<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 검색</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 부트스트랩 -->
<jsp:include page="/WEB-INF/views/func/BS.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<form id="myform" name="myform">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th colspan='6' style="text-align:center;">사원 정보 검색</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>이름</th>
					<th>
						<input type="text" name="staff_name" id="staff_name"/>
					</th>
					<th>성별</th>
					<th>
						<input class="form-check-input" type="checkbox" value="남" id="gender" name="gender">
	  					<label class="form-check-label" for="gender">남</label>
	  					<input class="form-check-input" type="checkbox" value="여" id="gender1" name="gender">
	  					<label class="form-check-label" for="gender">여</label>
					</th>
					<th colspan="">부서</th>
					<th>
						<select name="department_code" id="department_code" class="form-select">
							<option selected>부서 선택</option>
							<c:forEach var="depart" items="${list }">
					        	<option value="${depart.department_code}">${depart.department_name}</option>
							</c:forEach>
						</select>
					</th>
				</tr>
				<tr>
					<th>학력</th>
					<th>
						<c:forEach var="school" items="${schoolList }">
		                    <input type="checkbox" name="school_code" value="${school.school_code }" id="school_${school.school_code }">
		                    <label for="school_code">${school.school_name}</label>
		                </c:forEach>
					</th>
					<th>기술</th>
					<th colspan="3">
	  					<c:forEach var="skill" items="${skillList }">
		                    <input type="checkbox" name="skill_code" value="${skill.skill_code }" id="skill_${skill.skill_code }">
		                    <label for="skill_${skill.skill_code }">${skill.skill_name }</label>
		                </c:forEach>
					</th>
				</tr>
				<tr>
					<th>졸업일</th>
					<th colspan="6">
						<input type="month" id="graduate_day" name="graduate_day" min="1980-01" max="2024-12"/> ~ 
						<input type="month" id="graduate_day_e" name="graduate_day_e" min="1980-01" max="2024-12"/>
					</th>
				</tr>
				<tr>
					<th>상세검색</th>
					<td>
						<input type="text" name="keyword"/>
						<input type="radio" id="and" name="keyword_type" value="and"/>
						<label for="and">and</label>
						<input type="radio" id="or" name="keyword_type" value="or"/>
						<label for="or">or</label>
						
					</td>
				</tr>
			</tbody>
		</table>
		<input type="button" class="btn" id="search" value="검색"/>
		<input type="button" class="btn" id="allSearch" value="전부 검색" />
		<input type="reset"  class="btn" value="초기화"/>
		<input type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/users/staff_input_form.do'" value="등록 하기" />
	</form>
</div>
<div>
	<strong>${totalRow }</strong> 개의 글이 검색 되었습니다.
</div>
<div class="container" style="display:hidden;" id="erp">
	<table class="table table-bordered" id="alltable" >
			<thead>
				<tr style="text-align:center;">
					<th>번호<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th>이름<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th>성별<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th>부서<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th>졸업일<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${ list }">
					<tr>
						<td>${s.staff_no }</td>
						<td>${s.staff_name }</td>
						<td>${s.gender }</td>
						<td>${s.department_name}</td>
						<td>${s.graduate_day }</td>
						<td><a href="staff_updel_form.do?staff_no=${s.staff_no }">수정/삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
	</table>
	<nav>
		<ul class="pagination justify-content-center">
			<c:if test="${startPageNum ne 1 }">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/users/staff_search_form.do?pageNum=${startPageNum - 1}">Prev</a>
				</li>
			</c:if>
			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
				<li class="page-item">
					<c:choose>
						<c:when test="${pageNum eq i }">
							<a class="page-link"
								href="redirect:${pageContext.request.contextPath}/users/staff_search_form.do?pageNum=${i}">${i }
							</a>
						</c:when>
					    <c:otherwise>
							<a class="page-link"
								href="${pageContext.request.contextPath}/users/staff_search_form.do?pageNum=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</li>
			</c:forEach>
			<c:if test="${endPageNum lt totalPageCount }">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/users/staff_search_form.do?pageNum=${endPageNum + 1}">Next</a>
				</li>
			</c:if>
		</ul>
	</nav>
</div>
<div class="container" style="display:none;" id="search">
	<strong>${totalRow }</strong> 개의 글이 검색 되었습니다.
	<table class="table table-bordered" id="searchTable" >
			<thead>
				<tr style="text-align:center;">
					<th>번호<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th>이름<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th>성별<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th>부서<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th>졸업일<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${ list }">
					<tr>
						<td>${s.staff_no }</td>
						<td>${s.staff_name }</td>
						<td>${s.gender }</td>
						<td>${s.department_name}</td>
						<td>${s.graduate_day }</td>
						<td><a href="staff_updel_form.do?staff_no=${s.staff_no }">수정/삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
	</table>
	<nav>
		<ul class="pagination justify-content-center">
			<c:if test="${startPageNum ne 1 }">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/users/staff_search_form.do?pageNum=${startPageNum - 1}">Prev</a>
				</li>
			</c:if>
			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
				<li class="page-item">
					<c:choose>
						<c:when test="${pageNum eq i }">
							<a class="page-link"
								href="redirect:${pageContext.request.contextPath}/users/staff_search_form.do?pageNum=${i}">${i }
							</a>
						</c:when>
					    <c:otherwise>
							<a class="page-link"
								href="${pageContext.request.contextPath}/users/staff_search_form.do?pageNum=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</li>
			</c:forEach>
			<c:if test="${endPageNum lt totalPageCount }">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/users/staff_search_form.do?pageNum=${endPageNum + 1}">Next</a>
				</li>
			</c:if>
		</ul>
	</nav>
</div>
</body>
<script>
$(document).ready(function(){
	  $('#searchTable th').each(function (column) {
	    $(this).click(function() {
	      if($(this).is('.asc')) {     // 현재 오름차순인 경우
	        $(this).removeClass('asc');
	        $(this).addClass('desc');  // 내림차순으로 변경
	        $(this).children().attr('src', "${pageContext.request.contextPath}/resources/img/up-and-down.png");	// 이미지 src 수정
	        sortdir=-1;

	      } else { // 현재 오름차순 아닌 경우
	        $(this).addClass('asc');  // 오름차순으로 변경
	        $(this).removeClass('desc'); sortdir=1;
	        $(this).children().attr('src', "${pageContext.request.contextPath}/resources/img/up-and-down.png");	// 이미지 src 수정
	      }

	      $(this).siblings().removeClass('asc');
	      $(this).siblings().removeClass('desc');

	      var rec = $('#searchTable').find('tbody>tr').get();

	      rec.sort(function (a, b) {
	        var val1 = $(a).children('td').eq(column).text().toUpperCase();
	        var val2 = $(b).children('td').eq(column).text().toUpperCase();
	        return (val1 < val2)?-sortdir:(val1>val2)?sortdir:0;
	      });

	      $.each(rec, function(index, row) {
	          $('#searchTable tbody').append(row);
	      });
	    });
	 });
	});

//사원 리스트 테이블을 '전부 검색' 누를때 보이게
$("#allSearch").on("click", function(){
	$("#erp").css("display","block");
	const click= document.getElementByClass('page-link');
	
	if(click.style.display !== 'block'){
		click.style.display == 'block';
	}
	else{
		click.style.display == 'none';
	}
	
	
});
$(function(){
	$('#search').on("click",function(){
		if(confirm('검색 하시겠습니까?')== true){
			$("#myform").attr("method", "POST").attr("action", "${pageContext.request.contextPath}/users/staff_search.do").submit();
		}
	})
})
	
</script>
</html>