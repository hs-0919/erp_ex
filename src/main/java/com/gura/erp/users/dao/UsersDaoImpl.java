package com.gura.erp.users.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.erp.users.dto.SearchDto;
import com.gura.erp.users.dto.UsersDto;



//UsersDaoImpl 객체도 spring bean container 에서 관리가 되도록한다.
@Repository
public class UsersDaoImpl implements UsersDao{
	
	//spring bean container 에서 SqlSession type 객체를 찾아서 주입해(DI) 주세요 라는 의미 
	@Autowired
	private SqlSession session;
	
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
	
	
	@Override
	public List<UsersDto> getList(UsersDto dto) {
		
		return session.selectList("users.getList", dto);
		
	}
	
	@Override
	public List<SearchDto> getSearchList(SearchDto dto) {
		
		return session.selectList("search.getSearchList", dto);
		
	}
	
	@Override
	public List<UsersDto> schoolList() {
		return session.selectList("users.schoolList");
	}
	@Override
	public List<UsersDto> skillList() {
		return session.selectList("users.skillList");
	}
	
	
	//row 의 총 개수 구하기
	@Override
	public int getCount(UsersDto dto) {
		return session.selectOne("users.getCount", dto);
	}
	@Override
	public int getCount(SearchDto dto) {
		return session.selectOne("search.getCount", dto);
	}
	
	@Override
	public int insert(Map<Object, Object> map) {

		return session.insert("users.insert", map);

	}
	
	
	@Override 
	public int insertSkill(Map<Object, Object> map1) {
		
		return session.insert("users.insertSkill", map1);
		
	}
	 
	
	@Override
	public int update(UsersDto dto) {
		return session.update("users.update", dto);
	}

	@Override 
	public int updateSkill(Map<Object, Object> updsk) {
		
		return session.insert("users.insertSkill", updsk);
	}
	
	@Override
	public int delete(int staff_no) {
		return session.delete("users.delete", staff_no);

	}
	
	@Override
	public int deleteSkill(int staff_no) {
		return session.delete("users.deleteSkill", staff_no);

	}
	
	@Override
	public int getUserNo(int staff_no) {
		
		return session.selectOne("users.getUserNo", staff_no);
	}
}
