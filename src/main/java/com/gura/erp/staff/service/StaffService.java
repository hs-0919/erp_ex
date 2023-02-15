package com.gura.erp.staff.service;

import java.util.List;
import java.util.Map;

import com.gura.erp.staff.dto.Criteria;
import com.gura.erp.staff.dto.SearchDto;
import com.gura.erp.staff.dto.UsersDto;

public interface StaffService {
	
	//사원 전체의 정보를 가져오는 메소드
	public List<UsersDto> getListUser(Map<Object, Object> page, UsersDto dto);
	public int getCount();// 총 사원수
	
	//사원 한사람의 정보와 스킬리스트 가져오는 메소드
	public List<UsersDto> getUser(int staff_no);
	public List<UsersDto> getSkillList(int staff_no);
	
	//사원 검색
	public List<SearchDto> getSearchList(Map<Object, Object> search, SearchDto ser);
	public int getSearchCount(Map<Object, Object> search); //검색 사원 수
	
	//기타 등등 리스트 가져오기
	public List<UsersDto> getSchoolList();
	public List<UsersDto> getSkillList();
	public List<UsersDto> getDepartList();
	
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
	//사원 번호 가져오는 메소드
	public int getUserNo(int staff_no);
		
}
