package com.gura.erp.users.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gura.erp.users.dto.SearchDto;
import com.gura.erp.users.dto.UsersDto;



public interface UsersDao {
	//사원정보 조회
	public List<UsersDto> getData(int staff_no);
	public List<UsersDto> getSkillList(int staff_no);
	//사원정보 전체 조회
	public List<UsersDto> getList(UsersDto dto);
	public List<SearchDto> getSearchList(SearchDto dto);
	//기타 등등 리스트 조회
	public List<UsersDto> schoolList();
	public List<UsersDto> skillList();
	//row갯수
	public int getCount(UsersDto dto);
	public int getCount(SearchDto dto);
	//사원 번호 가져오기
	public int getUserNo(int staff_no);
	//사원 정보를 DB에 저장
	public int insert(Map<Object, Object> map);
	//사원 스킬을 DB에 저장
	public int insertSkill(Map<Object, Object> map1);
	//사원 정보 수정
	public int update(UsersDto dto);
	//사원 스킬 정보 수정
	public int updateSkill(Map<Object, Object> updsk);
	//사원 정보 삭제
	public int delete(int staff_no);
	//사원 스킬 삭제
	public int deleteSkill(int staff_no);
	
}
