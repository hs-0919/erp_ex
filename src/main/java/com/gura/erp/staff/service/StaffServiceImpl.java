package com.gura.erp.staff.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.erp.staff.dao.StaffDao;
import com.gura.erp.staff.dto.UsersDto;
import com.gura.erp.staff.dto.Criteria;
import com.gura.erp.staff.dto.SearchDto;

@Service("StaffServiceImpl")
public class StaffServiceImpl implements StaffService{

	//의존객체 주입 받기
	@Autowired
	private StaffDao dao;
	
	//사원 한사람
	@Override
	public List<UsersDto> getUser(int staff_no) {
		System.out.println("서비스 넘어온 no : " + staff_no);
		return dao.getData(staff_no);
	}
	@Override
	public List<UsersDto> getSkillList(int staff_no) {
		System.out.println("서비스 넘어온 스킬 : " + staff_no);
		return dao.getSkillList(staff_no);
	}
	//전체 사원
	@Override
	public List<UsersDto> getListUser(Map<Object, Object> page, UsersDto dto) {
		return dao.getList(page, dto);
	}
	@Override
	public int getCount() {
		return dao.getCount();
	}
	
	//사원 검색
	@Override
	public List<SearchDto> getSearchList(Map<Object, Object> search, SearchDto ser) {
		System.out.println("넘어온 스킬"+search.get("skill_code"));
		return dao.getSearchList(search, ser);
	}
	
	@Override
	public int getSearchCount(Map<Object, Object> search) {
		return dao.getSearchCount(search);
	}

	// 기타 등등 리스트 조회
	@Override
	public List<UsersDto> getSchoolList() {
		return dao.schoolList();
	}
	@Override
	public List<UsersDto> getSkillList() {
		return dao.skillList();
	}
	@Override
	public List<UsersDto> getDepartList() {
		return dao.departList();
	}
	
	//등록
	@Override
	public int insertUser(Map<Object, Object> map) {
		return dao.insert(map); 
	}
	
	@Override 
	public int insertSk(Map<Object, Object> map1) { 
		//System.out.println("ser"+map1.get("skill_code"));
		//System.out.println("ser"+map1.get("staff_no"));
		return dao.insertSkill(map1); 
	}
	//수정
	@Override
	public int updateUser(UsersDto dto) {
		System.out.println("ser1"+dto.getStaff_no());
		return dao.update(dto);
	}
	//수정
	@Override 
	public int updateSk(Map<Object, Object> updsk) { 
		System.out.println("ser2"+updsk.get("skill_code"));
		return dao.updateSkill(updsk); 
	}
	//삭제
	@Override
	public int deleteUser(int staff_no) {
		
		return dao.delete(staff_no);
	}
	
	@Override
	public int deleteSk(int staff_no) {
		
		return dao.deleteSkill(staff_no);
	}

	//사원 번호 가져오기
	@Override
	public int getUserNo(int staff_no) {
		
		return dao.getUserNo(staff_no);
	}
}
