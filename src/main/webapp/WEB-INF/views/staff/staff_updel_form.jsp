<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var GDate = new Date();
	var GYear = GDate.getFullYear();

	$("#graduateYear").append("<option value=''></option>");
	for (var i = 1950; i <= GYear; i++) {
		$("#graduateYear").append(
				"<option value='" + i + "'>" + i + "</option>");
	}
});

$(function(){
    var yearList = [];
    var GDate = new Date();
    var GYear = GDate.getFullYear();
	var Year = $("#year").val();
	
	yearList.push();
	for(var i = 1950; i <= GYear; i++) {
		yearList.push(i);
	};
	
    for(var i = 0; i <= yearList.length; i++){
        if(yearList[i] == Year){
            $("#graduateYear option:eq(" + (i+1) + ")").attr("selected", true);
        }
    }
});


$(function() {
	$("#graduateYear, #graduateMonth").on('change', function(){
		var selectGraduateYear = $("#graduateYear option:selected").val();
		var selectGraduateMonth = $("#graduateMonth option:selected").val();

		if(selectGraduateYear != "" && selectGraduateYear != null 
	       && selectGraduateMonth != "" && selectGraduateMonth != null) {
			$("#graduateYear").val();
			$("#graduateMonth").val();
		} 
	});
});
// 사원 정보 수정시 사원의 스킬정보를 보여주는 javascript
$(function(){
	var skillList = []; // skillCode를 담을 변수
	var skill_Code = [1,2,3,4,5,6,7]; // 비교할 skillCode
	
	<c:forEach items="${skList}" var="skList" varStatus="status">
    	skillList.push(${skList.skill_code });
 	</c:forEach> 
 	console.log(skillList);
 	
 	for(var i = 0; i < skill_Code.length; i++) {
 		for(var j = 0; j < skillList.length; j++) {
 			if(skill_Code[i] == skillList[j]) { // 해당 사원의 skillCode와 일치하는 skill만 체크
 				$("#" + (i + 1)).attr("checked", true);
 			}
 			else {
 				continue;
 			}
 		}
 	}
 	
});

$(function(){
	$('#upmit').on("click",function(){
		var staff_no = $("#staff_no").val();
		var staff_name = $("#staff_name").val();
		var jumin_no = parseInt($("#jumin_no").val());
		var jumin_no2 = parseInt($("#jumin_no2").val());
		var graduate_day = $("#graduate_day").val();
		var graduateMonth = $("#graduateMonth").val();
		var graduateYear = $("#graduateYear").val();
		
		var gender = $("input:radio[name='gender']:checked").val();
		var school_code = parseInt($("input:radio[name='school_code']:checked").val());
		var department_code = $("#department_code").val();
		
		var skill_arr = []; //배열 선언
		$('input[name="skill_code"]:checked').each(function(i){//체크된 리스트 저장
			skill_arr.push($(this).val());
        });
		
		
		var data1 = {
			"staff_name":staff_name, "jumin_no":jumin_no, "jumin_no2":jumin_no2, "gender":gender,
			"school_code":school_code, "skill_code":skill_arr, "department_code":department_code,
			"graduate_day":graduate_day
		};
		
		console.log(data1);
		
		//정규식 - 주민번호
		var fmt = /\d{2}([0][1-9]|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])/; // 앞자리
		var Chk = /[1-4]\d{6}/; // 뒷자리
		
		if($("input[name='staff_name']").val() == ''){
			alert("이름을 입력해주세요");
			return;
		}
		else if($("#jumin_no").val() == '') {
			alert("주민번호를 입력해주세요");
			return;
		}
		else if(!fmt.test($("#jumin_no").val())) {
			alert("주민등록번호 형식에 맞에 입력해 주세요.");
			return;
		}
		else if($("#jumin_no2").val() == '') {
			alert("주민번호를 입력해주세요");
			return;
		}
		else if(!Chk.test($("#jumin_no2").val())) {
			alert("주민등록번호 형식에 맞에 입력해 주세요.");
			return;
		}
		else if($("select[name='department_code'] option:selected").val() == '0') {
			alert("부서를 선택해주세요");
			return;
		}
		else if($("input:radio[name='gender']:checked").length < 1) {
			alert("성별을 선택해주세요");
			return;
		}
		else if($("input:radio[name='school_code']:checked").length < 1) {
			alert("학력을 선택해주세요");
			return;
		}
		else if($("input:checkbox[name='skill_code']:checked").length < 1) {
			alert("기술을 선택해주세요");
			return;
		}
		else if($("select[name='graduateYear'] option:selected").val() == '') {
			alert("졸업날짜(년)를 선택해주세요");
			return;
		}
		else if($("select[name='graduateMonth'] option:selected").val() == '') {
			alert("졸업날짜(월)를 선택해주세요");
			return;
		}
		if(confirm('수정 하시겠습니까?')== true){
			$("#myform").attr("method", "POST").attr("action", "${pageContext.request.contextPath}/staff/update.do").submit();
		}
			
	});
	$('#delmit').on("click",function(){
		if(confirm('삭제 하시겠습니까?')== true){
			$("#myform").attr("method", "POST").attr("action", "${pageContext.request.contextPath}/staff/delete.do").submit();
		}
	});
});
</script>

<%@include file="../includes/header.jsp" %>


<!-- Begin Page Content -->
<div class="container-fluid">

	    <!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">사원 정보 수정 삭제</h1>
	<p class="mb-4">사원 정보 수정 삭제 테이블입니다. 
	
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
	    <div class="card-header py-3">
	        <h6 class="m-0 font-weight-bold text-primary">ERP TABLE</h6>
	    </div>
	    <div class="card-header py-3">
		   	<form id="myform" name="form"> 
				<table class="table table-bordered">
					    <c:forEach var="us" items="${ user }">
							<thead>
								<tr>
									<th colspan='6' style="text-align:center;">사원 정보 수정, 삭제</th>
								</tr>
							</thead>
							<tbody>
								<tr>
								
									<th>이름</th>
									<th>
										<input type="hidden" name="staff_no" id="staff_no" value="${staff_no }">
										<input type="text" name="staff_name" id="staff_name" value="${us.staff_name }"/>
									</th>
									<th>주민 번호</th>
									<th>
										<input type="text" name="jumin_no" id="jumin_no" value="${us.jumin_no }" style="width: 120;"/> - <input type="password" name="jumin_no2" id="jumin_no2" value="${us.jumin_no2 }" maxlength="7" style="width: 120;"/>
									</th>
									<th>성별</th>
									<th>
					  					<label  for="gender">남</label>
										<input  type="radio" value="남"${ us.gender eq '남' ? 'checked' : '' } name="gender">
					  					<label  for="gender">여</label>
					  					<input  type="radio" value="여"${ us.gender eq '여' ? 'checked' : '' } name="gender">
									</th>
								</tr>
								<tr>
									<th>학력</th>
									<th>
										<input  type="radio" value="1"${ us.school_code eq 1 ? 'checked' : '' } name="school_code">
					  					<label  for="school_code">고졸</label>
					  					<input  type="radio" value="2"${ us.school_code eq 2 ? 'checked' : '' } name="school_code">
					  					<label  for="school_code">전문대졸</label>
					  					<input  type="radio" value="3"${ us.school_code eq 3 ? 'checked' : '' } name="school_code">
					  					<label  for="school_code">일반대졸</label>
									</th>
									<th>기술</th>
										<th>
											<input  type="checkbox" id ="1" value="1" name="skill_code">
						  					<label  for="skill_code">JAVA</label>
						  					<input  type="checkbox" id ="2" value="2" name="skill_code">
						  					<label  for="skill_code">JSP</label>
						  					<input  type="checkbox" id ="3" value="3" name="skill_code">
						  					<label  for="skill_code">ASP</label>
						  					<input  type="checkbox" id ="4" value="4" name="skill_code">
						  					<label  for="skill_code">PHP</label>
						  					<input  type="checkbox" id ="5" value="5" name="skill_code">
						  					<label  for="skill_code">Delphi</label>
						  					<input  type="checkbox" id ="6" value="6" name="skill_code">
						  					<label  for="skill_code">Python</label>
						  					<input  type="checkbox" id ="7" value="7" name="skill_code">
						  					<label  for="skill_code">Nexacro</label>
										</th>
									<th>부서</th>
									<th>
										<select name="department_code" id="department_code" class="form-select">
											<option selected>부서 선택</option>
											<option value="1"${ us.department_code eq 1 ? "selected=true" : "" }>컨설팅사업부</option>
					  						<option value="2"${ us.department_code eq 2 ? "selected=true" : "" }>하이테크사업부</option>
					  						<option value="3"${ us.department_code eq 3 ? "selected=true" : "" }>SI사업부</option>
					  						<option value="4"${ us.department_code eq 4 ? "selected=true" : "" }>반도체사업부</option>
					  						<option value="5"${ us.department_code eq 5 ? "selected=true" : "" }>기업부설연구소</option>
					  						<option value="6"${ us.department_code eq 6 ? "selected=true" : "" }>전략기획팀</option>
					  						<option value="7"${ us.department_code eq 7 ? "selected=true" : "" }>경영지원팀</option>
										</select>
									</th>
								</tr>
								<tr>
									<th>졸업일</th>
									<th colspan="6">
										 <input type="hidden" id="graduateDayS" value="${ us.graduateDayS }">
										 <input type="hidden" id="year" value="${ us.graduateYear }">
										 <select name="graduateYear" id="graduateYear" style="width: 100px;"></select>년 &ensp;&ensp;
										 <select name="graduateMonth" id="graduateMonth" style="width: 50px;">
										 	<option value="02" ${ us.graduateMonth eq "02" ? "selected=true" : "" }>02</option>
										 	<option value="08" ${ us.graduateMonth eq "08" ? "selected=true" : "" }>08</option>
										 </select>월
									 </th>
								</tr>
							</tbody>
						</c:forEach>
				</table>
			</form>
			<div>
				<button class="btn btn-success" type="button" id="upmit">수정</button>
				<button class="btn btn-danger" type="button" id="delmit">삭제</button>
			</div>
</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<!-- Footer -->
<footer class="sticky-footer bg-white">
    <div class="container my-auto">
        <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2020</span>
        </div>
    </div>
</footer>
<!-- End of Footer -->


<%@include file="../includes/footer.jsp" %>
			
			
			
        