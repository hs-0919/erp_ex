package com.gura.erp.users.dto;

import java.util.List;

public class SearchDto {
	private int staff_no;
	private String staff_name;
	private List<String> gender;
	private String jumin_no;
	private int jumin_no2;
	private List<String> school_code;
	private String school_name;
	private int department_code;
	private String department_name;
	private List<String> skill_code;
	private String skill_name;
	private String graduate_day;
	private String graduate_day_e;
	
	private String keyword;
	private String keyword_type;
	
	//paging
	private int startRowNum;
	private int endRowNum;
	private int prevNum;
	private int nextNum;

	public SearchDto() {}
	
	public SearchDto(int staff_no, String staff_name, List<String> gender, String jumin_no, int jumin_no2,
			List<String> school_code, String school_name, int department_code, String department_name,
			List<String> skill_code, String skill_name, String graduate_day, String graduate_day_e, String keyword,
			String keyword_type,int startRowNum, int endRowNum, int prevNum, int nextNum) {
		super();
		this.staff_no = staff_no;
		this.staff_name = staff_name;
		this.gender = gender;
		this.jumin_no = jumin_no;
		this.jumin_no2 = jumin_no2;
		this.school_code = school_code;
		this.school_name = school_name;
		this.department_code = department_code;
		this.department_name = department_name;
		this.skill_code = skill_code;
		this.skill_name = skill_name;
		this.graduate_day = graduate_day;
		this.graduate_day_e = graduate_day_e;
		this.keyword = keyword;
		this.keyword_type = keyword_type;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
		this.prevNum = prevNum;
		this.nextNum = nextNum;
	}




	public int getStaff_no() {
		return staff_no;
	}


	public void setStaff_no(int staff_no) {
		this.staff_no = staff_no;
	}


	public String getStaff_name() {
		return staff_name;
	}


	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}


	public List<String> getGender() {
		return gender;
	}


	public void setGender(List<String> gender) {
		this.gender = gender;
	}


	public String getJumin_no() {
		return jumin_no;
	}


	public void setJumin_no(String jumin_no) {
		this.jumin_no = jumin_no;
	}


	public int getJumin_no2() {
		return jumin_no2;
	}


	public void setJumin_no2(int jumin_no2) {
		this.jumin_no2 = jumin_no2;
	}


	public List<String> getSchool_code() {
		return school_code;
	}


	public void setSchool_code(List<String> school_code) {
		this.school_code = school_code;
	}


	public String getSchool_name() {
		return school_name;
	}


	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}


	public int getDepartment_code() {
		return department_code;
	}


	public void setDepartment_code(int department_code) {
		this.department_code = department_code;
	}


	public String getDepartment_name() {
		return department_name;
	}


	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}


	public List<String> getSkill_code() {
		return skill_code;
	}


	public void setSkill_code(List<String> skill_code) {
		this.skill_code = skill_code;
	}


	public String getSkill_name() {
		return skill_name;
	}


	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}


	public String getGraduate_day() {
		return graduate_day;
	}


	public void setGraduate_day(String graduate_day) {
		this.graduate_day = graduate_day;
	}


	public String getGraduate_day_e() {
		return graduate_day_e;
	}


	public void setGraduate_day_e(String graduate_day_e) {
		this.graduate_day_e = graduate_day_e;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getKeyword_type() {
		return keyword_type;
	}


	public void setKeyword_type(String keyword_type) {
		this.keyword_type = keyword_type;
	}
	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	
	public int getPrevNum() {
		return prevNum;
	}

	public void setPrevNum(int prevNum) {
		this.prevNum = prevNum;
	}
	
	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}
	
	public int getNextNum() {
		return nextNum;
	}

	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}
	
}
