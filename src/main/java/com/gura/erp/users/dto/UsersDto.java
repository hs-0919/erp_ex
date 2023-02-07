package com.gura.erp.users.dto;


public class UsersDto {
	//staff
	private int staff_no;
	private String staff_name;
	//gender
	private String gender;
	//jumin
	private String jumin_no;
	private int jumin_no2;
	//school
	private int school_code;
	private String school_name;
	//department
	private int department_code;
	private String department_name;
	//graduate_day
	private String graduate_day;
	//skill
	private int skill_code;
	private String skill_name;
	//paging
	private int startRowNum;
	private int endRowNum;
	private int prevNum;
	private int nextNum;
	
	public UsersDto() {}
	
	public UsersDto(int staff_no, String staff_name, String gender, String jumin_no, int jumin_no2, int school_code, int skill_code,
			int department_code, String graduate_day, String department_name, String school_name, String skill_name, int startRowNum,
			int endRowNum, int prevNum, int nextNum) {
		super();
		this.staff_no = staff_no;
		this.staff_name = staff_name;
		this.gender = gender;
		this.jumin_no = jumin_no;
		this.jumin_no2 = jumin_no2;
		this.school_code = school_code;
		this.skill_code = skill_code;
		this.department_code = department_code;
		this.graduate_day = graduate_day;
		this.department_name = department_name;
		this.school_name = school_name;
		this.skill_name = skill_name;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
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

	public int getSchool_code() {
		return school_code;
	}

	public void setSchool_code(int school_code) {
		this.school_code = school_code;
	}
	
	public int getSkill_code() {
		return skill_code;
	}
	
	public void setSkill_code(int skill_code) {
		this.skill_code = skill_code;
	}
	
	public int getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(int department_code) {
		this.department_code = department_code;
	}

	public String getGraduate_day() {
		return graduate_day;
	}

	public void setGraduate_day(String graduate_day) {
		this.graduate_day = graduate_day;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
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
