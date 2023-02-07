package com.gura.erp.users.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.erp.users.dto.SearchDto;
import com.gura.erp.users.dto.UsersDto;

public interface UsersService {
	//사원정보 가져오기
	public List<UsersDto> getUser(int staff_no);
	public List<UsersDto> getSkillList(int staff_no);
	//기타 등등 리스트 가져오기
	public List<UsersDto> getSchoolList();
	public List<UsersDto> getSkillList();
	//사원 전체의 정보를 인자로 전달한 ModelAndView 객체에 담아주는 메소드
	public void getListUser(HttpServletRequest request);
	public void searchListUser(HttpServletRequest request);
	//public List<SearchDto> getSearchUser(int staff_no);
	//사원 정보를 추가 해 주는 메소드
	public int insertUser(Map<Object, Object> map);
	//사원 스킬을 추가 해 주는 메소드
	public int insertSk(Map<Object, Object> map1);
	//사원 정보를 수정 해 주는 메소드 
	public int updateUser(UsersDto dto);
	//사원 정보 스킬을 수정 해 주는 메소드
	public int updateSk(Map<Object, Object> updsk);
	//사원 정보를 삭제 해 주는 메소드 
	public int deleteUser(int staff_no);
	//사원 스킬을 삭제 해 주는 메소드 
	public int deleteSk(int staff_no);
	//
	public int getUserNo(int staff_no);
}
