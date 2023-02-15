<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
/*
$(function(){
	const sch = location.search;
	const params = new URLSearchParams(sch);
	const num = params.get('pageNum');
	params.set('pageNum','${i}');
	url = params.toString();
	
	$(".page-item a").on("click", function(e){ // class=page-item의 a태그를 클릭하면 
		//e.preventDefault();//이벤트를 막고 
		$(".page-item a").removeAttr("href"); // href속성을 지우고 
		$(".page-item a").attr("href",
				"${pageContext.request.contextPath}/staff/staff_search.do?"+url);
	
	});
});
*/
$(function() {
	var GDate = new Date();
	var GYear = GDate.getFullYear();

	$("#graduateYear, #startYear").append("<option value=''></option>");
	for (var i = 1950; i <= GYear; i++) {
		$("#graduateYear, #startYear").append(
				"<option value='" + i + "'>" + i + "</option>");
	}
});

$(function() {
	$("#startYear, #startMonth, #graduateYear, #graduateMonth").on('change',function() {
		var selectStartYear = $("#startYear option:selected").val();
		var selectStartMonth = $("#startMonth option:selected").val();
		var selectGraduateYear = $("#graduateYear option:selected").val();
		var selectGraduateMonth = $("#graduateMonth option:selected").val();

		if (selectStartYear != "" && selectStartYear != null
		    && selectStartMonth != "" && selectStartMonth != null
			&& selectGraduateYear != "" && selectGraduateYear != null
			&& selectGraduateMonth != ""	&& selectGraduateMonth != null) {
			
			
			if (selectStartYear > selectGraduateYear) {
				alert("졸업 날짜보다 클 수 없습니다. 날짜를 다시 입력해주세요.");
				$("#startYear").val('');
				$("#startMonth").val('');
				$("#graduateYear").val('');
				$("#graduateMonth").val('');
			}else if (selectStartYear == selectGraduateYear && selectStartMonth >= selectGraduateMonth) {
				alert("졸업 날짜와 같거나 클 수 없습니다. 날짜를 다시 입력해주세요.");
				$("#startYear").val('');
				$("#startMonth").val('');
				$("#graduateYear").val('');
				$("#graduateMonth").val('');
			}
		}
	});
});


$(document).ready(function(){
  $('#alltable th').each(function (column) {
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

      var rec = $('#alltable').find('tbody>tr').get();

      rec.sort(function (a, b) {
        var val1 = $(a).children('td').eq(column).text().toUpperCase();
        var val2 = $(b).children('td').eq(column).text().toUpperCase();
        return (val1 < val2)?-sortdir:(val1>val2)?sortdir:0;
      });

      $.each(rec, function(index, row) {
          $('#alltable tbody').append(row);
      });
    });
 });
});

$(function(){
	$('#search').on("click",function(){
		if(confirm('검색 하시겠습니까?')== true){
			$("#listForm").attr("method", "POST").attr("action", "${pageContext.request.contextPath}/staff/staff_search.do").submit();
		}
	})
});

$(function(){
	let pageNum = $("#pageNum").val();
	let keyword = $("#keyword").val();
	let staff_no = $("#staff_no").val();
	let keyword_type = $("input[name=keyword_type]:checked").val();
	let staff_name = $("#staff_name").val();
	
	let gender = [];
	if($("input[type=checkbox][name=gender]:checked").val()=='남'){
		gender.push('남');
	}else if($("input[type=checkbox][name=gender]:checked").val()=='여'){
		gender.push('여');
	}else if(gender==""){
		gender.push('남');
		gender.push('여');
	}
	
	let department_code=[];
	if($("#department_code").val()==""){
		for(var i =1; i<=7;i++){
			department_code.push(i);
		}
	}else{
		department_code.push( $("#department_code").val());
	}
	
	let school_code = [];
	$("input[type=checkbox][name=school_code]:checked").each(function(){
		school_code.push(this.value);
	});
	if(school_code==""){
		for(var i =1;i<=3;i++){
			school_code.push(i);
		}
	}
	
	let skill_code = [];
	$("input[type=checkbox][name=skill_code]:checked").each(function(){
		skill_code.push(this.value);
	});
	if(skill_code==""){
		for(var i=1;i<=7;i++){
			skill_code.push(i);
		}
		
	}
	var startYear = $("input[type=checkbox][name=startYear]:checked").val();
	if( startYear==""){
		startYear='1950';
	}
	var startMonth = $("input[type=checkbox][name=startMonth]:checked").val();
	if( startMonth==""){
		startMonth='02';
	}
	var graduateYear = $("input[type=checkbox][name=graduateYear]:checked").val();
	if( graduateYear==""){
		graduateYear='2023';
	}
	var graduateMonth = $("input[type=checkbox][name=graduateMonth]:checked").val();
	if( graduateMonth==""){
		graduateMonth='08';
	}
	
	let url = "{pageContext.request.contextPath}/staff/staff_search.do?pageNum="+pageNum+"&staff_name="+staff_name+"&gender="+gender+
	   "&department_code="+department_code+"&school_code="+school_code+"&skill_code="+skill_code+"&startYear="+startYear+
	   "&startMonth="+startMonth+"&graduateYear="+graduateYear+"&graduateMonth="+graduateMonth+"&keyword="+keyword+"&keyword_type="+keyword_type;
	/*  
	$(".page-item a").on("click", function(e){ // class=page-item의 a태그를 클릭하면 
		
		//e.preventDefault();//이벤트를 막고 
		//$(".page-item a").removeAttr("href"); // href속성을 지우고 
		$(".page-item a").attr("href",url);
		
		$.ajax({
			   url:"{pageContext.request.contextPath}/staff/staff_search.do?pageNum="+pageNum+"&staff_name="+staff_name+"&gender="+gender+
					   "&department_code="+department_code+"&school_code="+school_code+"&skill_code="+skill_code+"&startYear="+startYear+
					   "&startMonth="+startMonth+"&graduateYear="+graduateYear+"&graduateMonth="+graduateMonth+"&keyword="+keyword+"&keyword_type="+keyword_type,
			   type:"get",
			   datatype:"html",
			   success:function(data){
			 	  $(".page-item a").html(data);
		       }	
	    });
	});*/
});


</script>

<%@include file="../includes/header.jsp" %>


<!-- Begin Page Content -->
<div class="container-fluid">

	    <!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">사원 정보 검색</h1>
	<p class="mb-4">사원 정보 검색 테이블입니다. 
	
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
	    <div class="card-header py-3">
	        <h6 class="m-0 font-weight-bold text-primary">ERP TABLE</h6>
	    </div>
	    <div class="card-header py-3">
	   	<form id="listForm" name="listForm">
			
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
							
							<input type="text" name="staff_name" id="staff_name" placeholder="사원이름을 입력하세요.."/>
						</th>
						<th>성별</th>
						<th>
							<input type="checkbox" value="남" id="gender" name="gender">
		  					<label for="gender">남</label>
		  					<input type="checkbox" value="여" id="gender1" name="gender">
		  					<label for="gender">여</label>
						</th>
						<th colspan="">부서</th>
						<th>
							<select name="department_code" id="department_code" class="form-select">
								<option value=""></option>
								<option value="1">컨설팅사업부</option>
								<option value="2">하이테크사업부</option>
								<option value="3">SI사업부</option>
								<option value="4">반도체사업부</option>
								<option value="5">기업부설연구소</option>
								<option value="6">전략기획팀</option>
								<option value="7">경영지원팀</option>
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
							 <select name="startYear" id="startYear" style="width: 100px;"></select>년 &ensp;&ensp;
							 <select name="startMonth" id="startMonth" style="width: 50px;">
							 	<option value=""></option>
							 	<option value="02">02</option>
							 	<option value="08">08</option>
							 </select>월 &ensp; ~ &ensp;
							 <select name="graduateYear" id="graduateYear" style="width: 100px;"></select>년 &ensp;&ensp;
							 <select name="graduateMonth" id="graduateMonth" style="width: 50px;">
							 	<option value=""></option>
							 	<option value="02">02</option>
							 	<option value="08">08</option>
							 </select>월
						</th>
					</tr>
					<tr>
						<th>상세검색</th>
						<th colspan="6">
							<input type="text" id="keyword" name="keyword" value="${keyword}" placeholder="기술을 입력하세요."/>
							<input type="radio" id="and" name="keyword_type" value="and"/>
							<label for="and">and</label>
							<input type="radio" id="or" name="keyword_type" value="or"/>
							<label for="or">or</label>
							
						</th>
					</tr>
				</tbody>
			</table>
			<div>
				<input type="button" class="btn btn-primary" id="search" value="검색"/>
				<input type="button" class="btn btn-primary" id="allSearch" onclick="location.href='${pageContext.request.contextPath}/staff/staff_search_form.do'" value="전부 검색" />
				<input type="reset"  class="btn btn-warning" value="초기화"/>
				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/staff/staff_input_form.do'" value="등록 하기" />
			</div>
			</form>
			<form id="reform" method="GET">
				 <input type='hidden' name='pageNum' value='<c:out value="${SearchDto.pageNum}"/>' />
				 <input type='hidden' name='staff_no' value='<c:out value="${SearchDto.staff_no}"/>' />
				 <input type='hidden' name='keyword' value='<c:out value="${SearchDto.keyword}"/>' />
				 <input type='hidden' name='keyword_type' value='<c:out value="${SearchDto.keyword_type}"/>' />
			</form>
	
		</div>
	    <div class="card-body">
	        <div class="table-responsive">
	        	<div style="text-align: end;">
	        		<strong>${totalCount }</strong> 명의 사원이 검색 되었습니다.
	        	</div>
	            <table class="table table-bordered" id="alltable" width="100%" cellspacing="0" style="margin-top: 20px">
	                <thead>
	                    <tr>
	                        <th>번호<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
							<th>이름<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
							<th>성별<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
							<th>부서<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
							<th>졸업일<img src="${pageContext.request.contextPath}/resources/img/up-and-down.png"></th>
	                        <th></th>
	                    </tr>
	                </thead>
	                <tbody>
	               		<c:forEach var="data" items="${data}">
							<tr>
								<td>${data.staff_no}</td>
								<td>${data.staff_name }</td>
								<td>${data.gender }</td>
								<td>${data.department_name}</td>
								<td>${data.graduate_day}</td>
								<td>
									<input type="hidden" name="pageNum" id="pageNum" value="${data.pageNum}" />
									<input type="hidden" name="staff_no" id="staff_no" value="${data.staff_no}">
									<a href="staff_updel_form.do?staff_no=${data.staff_no }">수정/삭제</a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
	            </table>
	        </div>
	        <nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${pageNum ne 1}">
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath}/staff/staff_search.do?pageNum=${pageNum - 1}">이전</a>
						</li>
					</c:if>
					<c:forEach begin="${startRowNum}" end="${endRowNum}" var="i">
						<li class="page-item ${pageNum == i ? "active":"" }">
							<c:choose>
								<c:when test="${i == pageNum}">
									<a class="page-link" href="${pageContext.request.contextPath}/staff/staff_search.do?pageNum=${i}">${i} </a>
								</c:when>
								<c:when test="${i != pageNum }">
									<a class="page-link" href="${pageContext.request.contextPath}/staff/staff_search.do?pageNum=${i}&">${i} </a>
								</c:when>
							</c:choose>
						</li>
					</c:forEach>
					<c:if test="${pageNum ne maxPage && !empty list}">
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath}/staff/staff_search.do?pageNum=${pageNum + 1}">다음</a>
						</li>
					</c:if>
				</ul>
			</nav>
	    </div>
	   
</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<!-- Footer -->
<footer class="sticky-footer bg-white">
    <div class="container my-auto">
        <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2023</span>
        </div>
    </div>
</footer>
<!-- End of Footer -->


<%@include file="../includes/footer.jsp" %>