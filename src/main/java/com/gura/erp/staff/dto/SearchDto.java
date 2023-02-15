package com.gura.erp.staff.dto;

import java.util.List;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchDto {
	//staff
	private int staff_no;
	private String staff_name;
	//gender
	private String gender;
	//jumin_no
	private String jumin_no;
	private int jumin_no2;
	//school
	private List<Integer> school_code;
	private String school_name;
	//department
	private int department_code;
	private String department_name;
	//skill
	private List<Integer> skill_code;
	private String skill_name;
	//graduate_day
	private int graduate_day;
	private int graduate_day_e;
	private String startYear;
	private String startMonth;
	private String graduateYear;
	private String graduateMonth;
	private String graduateDayStart;
	private String graduateDayEnd;
	//검색 키워드 
	private String keyword;
	private String keyword_type;
	
	//paging
	private int pageCount;   // 한 페이지 몇개 표시
	private int displayCount;// 하단 페이징바 몇개 표시
	private int pageNum;     // 현재 페이지 
	private int totalCount;  // 게시글 총개수
	private int maxPage;     // 페이지 최대개수
	private int startRowNum; // 시작 페이지
	private int endRowNum;   // 마지막 페이지 
	private int prevNum;
	private int nextNum;

	public SearchDto() {};
	
	public SearchDto(int staff_no, String staff_name, String gender, String jumin_no, int jumin_no2,
			List<Integer> school_code, String school_name, int department_code, String department_name,
			List<Integer> skill_code, String skill_name, int graduate_day, int graduate_day_e, String keyword,
			String keyword_type,int startRowNum, int endRowNum, int prevNum, int nextNum, String startYear, String startMonth,
			String graduateYear, String graduateMonth, String graduateDayStart, String graduateDayEnd, int pageCount,
			int displayCount, int pageNum, int totalCount, int maxPage) {
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
		this.startYear = startYear;
		this.startMonth = startMonth;
		this.graduateYear = graduateYear;
		this.graduateMonth = graduateMonth;
		this.graduateDayStart = graduateDayStart;
		this.graduateDayEnd = graduateDayEnd;
		this.keyword = keyword;
		this.keyword_type = keyword_type;
		this.pageCount = pageCount;
		this.displayCount = displayCount;
		this.pageNum = pageNum;
		this.totalCount = totalCount;
		this.maxPage = maxPage;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
		this.prevNum = prevNum;
		this.nextNum = nextNum;
	}
	// p 349
	  // 가장 편리한 점 한글 처리에 신경쓰지 않아도 된다.
	  // 쿼리스트링(queryString)
	  // UriComponentsBuilder 클래스 라이브러리 사용해서 자동으로
	  // ?pageNum=2&amount=10&keyword=kenik&type=W  이렇게 뒤에 붙는다. (jsp에서 지저분하게 안해도 됨)   
	  public String getListLink() {
	     String path = "";
	     UriComponentsBuilder builder  = 
	           UriComponentsBuilder.fromPath(path)
	           .queryParam("pageNum", this.pageNum)
	           .queryParam("amount", this.amount)
	           .queryParam("type", this.getType())
	           .queryParam("keyword", this.keyword) 
	           ;      
	     return builder.toUriString();
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


	public List<Integer> getSchool_code() {
		return school_code;
	}


	public void setSchool_code(List<Integer> school_code) {
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


	public List<Integer> getSkill_code() {
		return skill_code;
	}


	public void setSkill_code(List<Integer> skill_code) {
		this.skill_code = skill_code;
	}


	public String getSkill_name() {
		return skill_name;
	}


	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}


	public int getGraduate_day() {
		return graduate_day;
	}


	public void setGraduate_day(int graduate_day) {
		this.graduate_day = graduate_day;
	}


	public int getGraduate_day_e() {
		return graduate_day_e;
	}


	public void setGraduate_day_e(int graduate_day_e) {
		this.graduate_day_e = graduate_day_e;
	}
	
	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	
	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	
	public String getGraduateYear() {
		return graduateYear;
	}

	public void setGraduateYear(String graduateYear) {
		this.graduateYear = graduateYear;
	}
	
	public String getGraduateDayStart() {
		return graduateDayStart;
	}

	public void setGraduateDayStart(String graduateDayStart) {
		this.graduateDayStart = graduateDayStart;
	}
	
	public String getGraduateDayEnd() {
		return graduateDayEnd;
	}

	public void setGraduateDayEnd(String graduateDayEnd) {
		this.graduateDayEnd = graduateDayEnd;
	}
	
	public String getGraduateMonth() {
		return graduateMonth;
	}

	public void setGraduateMonth(String graduateMonth) {
		this.graduateMonth = graduateMonth;
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
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getDisplayCount() {
		return displayCount;
	}

	public void setDisplayCount(int displayCount) {
		this.displayCount = displayCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
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
