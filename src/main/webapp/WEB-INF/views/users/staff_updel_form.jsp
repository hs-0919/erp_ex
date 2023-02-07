<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 부트스트랩 -->
<jsp:include page="/WEB-INF/views/func/BS.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<div>
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
									<input class="form-check-input" type="radio" value="남"${ us.gender eq '남' ? 'checked' : '' } name="gender">
				  					<label class="form-check-label" for="gender">남</label>
				  					<input class="form-check-input" type="radio" value="여"${ us.gender eq '여' ? 'checked' : '' } name="gender">
				  					<label class="form-check-label" for="gender">여</label>
								</th>
							</tr>
							<tr>
								<th>학력</th>
								<th>
				  					<label class="form-check-label" for="school_code">고졸</label>
									<input class="form-check-input" type="radio" value="1"${ us.school_code eq 1 ? 'checked' : '' } name="school_code">
				  					<label class="form-check-label" for="school_code">전문대졸</label>
				  					<input class="form-check-input" type="radio" value="2"${ us.school_code eq 2 ? 'checked' : '' } name="school_code">
				  					<label class="form-check-label" for="school_code">일반대졸</label>
				  					<input class="form-check-input" type="radio" value="3"${ us.school_code eq 3 ? 'checked' : '' } name="school_code">
								</th>
								<th>기술</th>
									<th>
					  					<label class="form-check-label" for="skill_code">JAVA</label>
										<input class="form-check-input" type="checkbox" id ="1" value="1" name="skill_code">
					  					<label class="form-check-label" for="skill_code">JSP</label>
					  					<input class="form-check-input" type="checkbox" id ="2" value="2" name="skill_code">
					  					<label class="form-check-label" for="skill_code">ASP</label>
					  					<input class="form-check-input" type="checkbox" id ="3" value="3" name="skill_code">
					  					<label class="form-check-label" for="skill_code">PHP</label>
					  					<input class="form-check-input" type="checkbox" id ="4" value="4" name="skill_code">
					  					<label class="form-check-label" for="skill_code">Delphi</label>
					  					<input class="form-check-input" type="checkbox" id ="5" value="5" name="skill_code">
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
									<input type="month" id="graduate_day" name="graduate_day" value="${fn:trim(us.graduate_day)}" min="1950-01" max="2024-12"/>
								</th>
							</tr>
						</tbody>
					</c:forEach>
			</table>
		</form>
		<div>
			<button class="btn" type="button" id="upmit">수정</button>
			<button class="btn" type="button" id="delmit">삭제</button>
		</div>
	</div>
	
	<a href="${pageContext.request.contextPath}/users/staff_search_form.do">뒤로가기</a>
</div>
</body>
<script>
$(function(){
	var skillList = []; // skillCode를 담을 변수
	var skill_Code = [1,2,3,4,5]; // 비교할 skillCode
	
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

		if(confirm('수정 하시겠습니까?')== true){
			$("#myform").attr("method", "POST").attr("action", "${pageContext.request.contextPath}/users/update.do").submit();
		}
			
	});
	$('#delmit').on("click",function(){
		if(confirm('삭제 하시겠습니까?')== true){
			$("#myform").attr("method", "POST").attr("action", "${pageContext.request.contextPath}/users/delete.do").submit();
		}
	});
});
</script>
</html>