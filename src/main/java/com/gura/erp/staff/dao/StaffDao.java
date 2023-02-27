package com.gura.erp.staff.dao;

import java.util.List;
import java.util.Map;

import com.gura.erp.staff.dto.Criteria;
import com.gura.erp.staff.dto.SearchDto;
import com.gura.erp.staff.dto.UsersDto;

public interface StaffDao {
	//사원정보 조회
	public List<UsersDto> getData(int staff_no);
	public List<UsersDto> getSkillList(int staff_no);
	
	//사원정보 전체 조회
	public List<UsersDto> getList(Map<Object, Object> page, UsersDto dto);
	public int getCount();
	
	//검색 사원정보 조회
	public List<SearchDto> getSearchList(Map<Object, Object> search, SearchDto ser);
	public int getSearchCount(Map<Object, Object> search);

	//기타 등등 리스트 조회
	public List<UsersDto> schoolList();
	public List<UsersDto> skillList();
	public List<UsersDto> departList();
	
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
