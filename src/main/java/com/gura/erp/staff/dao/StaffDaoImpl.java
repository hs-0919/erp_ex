package com.gura.erp.staff.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.erp.staff.dto.Criteria;
import com.gura.erp.staff.dto.SearchDto;
import com.gura.erp.staff.dto.UsersDto;
import com.gura.erp.staff.dao.StaffDao;

//StaffDaoImpl 객체도 spring bean container 에서 관리가 되도록한다.
@Repository
public class StaffDaoImpl implements StaffDao{
	//spring bean container 에서 SqlSession type 객체를 찾아서 주입해(DI) 주세요 라는 의미 
	@Autowired
	private SqlSession session;
	
	//사원정보 조회
	@Override
	public List<UsersDto> getData(int staff_no) {
		/* 
		 *  Mapper's namespace : users
		 *  sql's id : getData
		 *  pararmeterType : Int
		 *  resultType : UsersDto  
		 */
		return session.selectList("users.getData", staff_no);
	}
	@Override
	public List<UsersDto> getSkillList(int staff_no) {
		
		return session.selectList("users.getSkillList", staff_no);
	}
	
	//전체 사원정보 조회
	@Override
	public List<UsersDto> getList(Map<Object, Object> page, UsersDto dto) {
		// 페이지의 시작 -맵퍼에 넣기
		int startRow = (dto.getPageNum() - 1) * dto.getPageCount() + 1;
		// 페이지의 끝 -맵퍼에 넣기
		int endRow = startRow + dto.getPageCount() - 1;
		
		page.put("startRow", startRow);
		page.put("endRow", endRow);
		return session.selectList("users.getList", page);
	}
	@Override
	public int getCount() {
		return session.selectOne("users.getCount");
	}
	
	//검색 사원정보 조회
	
	@Override 
	public List<SearchDto> getSearchList(Map<Object, Object> search, SearchDto ser) { 
		// 페이지의 시작 -맵퍼에 넣기
		int startRow = (ser.getPageNum() - 1) * ser.getPageCount() + 1;
		// 페이지의 끝 -맵퍼에 넣기
		int endRow = startRow + ser.getPageCount() - 1;
	 
		search.put("startRow", startRow); 
		search.put("endRow", endRow); 
		
	 	return	session.selectList("search.getSearchList", search); 
	}
	
	@Override
	public int getSearchCount(Map<Object, Object> search) {
		return session.selectOne("search.getSearchCount", search);
	}
	
	//기타 등등 리스트 조회
	@Override
	public List<UsersDto> schoolList() {
		return session.selectList("users.schoolList");
	}
	@Override
	public List<UsersDto> skillList() {
		return session.selectList("users.skillList");
	}
	@Override
	public List<UsersDto> departList() {
		return session.selectList("users.departList");
	}
	//사원 정보 저장
	@Override
	public int insert(Map<Object, Object> map) {
		return session.insert("users.insert", map);
	}
	@Override 
	public int insertSkill(Map<Object, Object> map1) {
		return session.insert("users.insertSkill", map1);
	}
	 
	//사원정보 수정
	@Override
	public int update(UsersDto dto) {
		return session.update("users.update", dto);
	}
	@Override 
	public int updateSkill(Map<Object, Object> updsk) {
		return session.insert("users.insertSkill", updsk);
	}
	
	//사원정보 삭제
	@Override
	public int delete(int staff_no) {
		return session.delete("users.delete", staff_no);
	}
	@Override
	public int deleteSkill(int staff_no) {
		return session.delete("users.deleteSkill", staff_no);
	}
	
	//사원 번호 가져오기
	@Override
	public int getUserNo(int staff_no) {
		return session.selectOne("users.getUserNo", staff_no);
	}
}
