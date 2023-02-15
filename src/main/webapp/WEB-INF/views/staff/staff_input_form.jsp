<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<%@include file="../includes/header.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">사원 등록</h1>
<p class="mb-4">사원 등록 테이블입니다. 

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">ERP TABLE</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
			<form id="myform" name="form"> 
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th colspan='6' style="text-align:center;">사원 정보 등록</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>이름</th>
							<th>
								<input type="text" name="staff_name" id="staff_name"/>
							</th>
							<th>주민 번호</th>
							<th>
								<input type="text" name="jumin_no" id="jumin_no" maxlength="6" style="width: 120;"/> - 
								<input type="password" name="jumin_no2" id="jumin_no2" maxlength="7" style="width: 120;"/>
							</th>
							<th>성별</th>
							<th>
								<input  type="radio" value="남" id="gender" name="gender">
			  					<label  for="gender">남</label>
			  					<input  type="radio" value="여" id="gender1" name="gender">
			  					<label  for="gender">여</label>
							</th>
						</tr>
						<tr>
							<th>학력</th>
							<th>
								<input  type="radio" value="1" name="school_code">
			  					<label  for="school_code">고졸</label>
			  					<input  type="radio" value="2" name="school_code">
			  					<label  for="school_code">전문대졸</label>
			  					<input  type="radio" value="3" name="school_code">
			  					<label  for="school_code">일반대졸</label>
							</th>
							<th>기술</th>
								<th>
									<input  type="checkbox" value="1" name="skill_code">
				  					<label  for="skill_code">JAVA</label>
				  					<input  type="checkbox" value="2" name="skill_code">
				  					<label  for="skill_code">JSP</label>
				  					<input  type="checkbox" value="3" name="skill_code">
				  					<label  for="skill_code">ASP</label>
				  					<input  type="checkbox" value="4" name="skill_code">
				  					<label  for="skill_code">PHP</label>
				  					<input  type="checkbox" value="5" name="skill_code">
				  					<label  for="skill_code">Delphi</label>
				  					<input  type="checkbox" value="6" name="skill_code">
				  					<label  for="skill_code">Python</label>
				  					<input  type="checkbox" value="7" name="skill_code">
				  					<label  for="skill_code">Nexacro</label>
								</th>
							<th>부서</th>
							<th>
								<select name="department_code" id="department_code" class="form-select">
									<option value="0" selected>부서 선택</option>
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
							<th>졸업일</th>
							<th colspan="6">
								 <select name="graduateYear" id="graduateYear" style="width: 100px;"></select>년 &ensp;&ensp;
								 <select name="graduateMonth" id="graduateMonth" style="width: 50px;">
								 	<option value=""></option>
								 	<option value="02">02</option>
								 	<option value="08">08</option>
								 </select>월
							 </th>
						</tr>
					</tbody>
				</table>
			</form>
			<div>
				<button class="btn btn-sm btn-primary" type="button" id="submit">등록</button>
				<button class="btn btn-sm btn-danger" id="cancel">취소</button>
			</div>
		</div>
    </div>
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
<script>
//졸업년도 selectbox
$(function() {
	var GDate = new Date();
	var GYear = GDate.getFullYear();

	$("#graduateYear").append("<option value=''></option>");
	for (var i = 1950; i <= GYear; i++) {
		$("#graduateYear").append("<option value='" + i + "'>" + i + "</option>");
	}
});
//졸업일 검사식
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


$(function(){
	$('#submit').on("click",function(){
		
		var staff_name = $("#staff_name").val();
		var jumin_no = parseInt($("#jumin_no").val());
		var jumin_no2 = parseInt($("#jumin_no2").val());
		var graduate_day = $.trim($("#graduate_day").val());
		var graduateYear = parseInt($("#graduateYear").val());
		var graduateMonth = parseInt($("#graduateMonth").val());
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
		
		if(confirm('저장 하시겠습니까?')== true){
			$("#myform").attr("method", "POST").attr("action", "${pageContext.request.contextPath}/staff/insert.do").submit();
		}
		
	});
});
//취소버튼 누를시
$(function(){
	$('#cancel').on("click",function(){
		if(confirm('취소 하시겠습니까?')== true){
			location.href="${pageContext.request.contextPath}/staff/staff_search_form.do";
		}
	})
});

</script>

<%@include file="../includes/footer.jsp" %>
			
			
			
        