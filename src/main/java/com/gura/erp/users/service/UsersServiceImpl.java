package com.gura.erp.users.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.erp.users.dto.SearchDto;
import com.gura.erp.users.dto.UsersDto;
import com.gura.erp.users.dao.UsersDao;

@Service("UsersServiceImpl")
public class UsersServiceImpl implements UsersService{
	//의존객체 주입 받기
	@Autowired
	private UsersDao dao;

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
	
	@Override
	public List<UsersDto> getSchoolList() {
		return dao.schoolList();
	}
	@Override
	public List<UsersDto> getSkillList() {
		return dao.skillList();
	}
	
	@Override
	public void getListUser(HttpServletRequest request) {
		
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=6;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;
		
		//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int pageNum=1;
		//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum = request.getParameter("pageNum");
		//만일 페이지 번호가 파라미터로 넘어 온다면
		if(strPageNum != null){
			//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum-1) * PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;
		
		//UsersDto 객체에 startRowNum과 endRowNum을  담는다.
		UsersDto dto = new UsersDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//전체 row 의 갯수
		List<UsersDto> list=dao.getList(dto);
		/* mView.addObject("list", list); */
		
		int totalRow = dao.getCount(dto);
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		
		//전체 페이지의 갯수 구하기
		int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum = totalPageCount; //보정해 준다. 
		}
		
		//request 영역에 담아주기
		request.setAttribute("pageNum", pageNum);	//현재 페이지 번호
		request.setAttribute("startPageNum", startPageNum);	//시작 페이지 번호
		request.setAttribute("endPageNum", endPageNum);	//끝 페이지 번호
		//request.setAttribute("search_d", search_d);
		//request.setAttribute("keyword", keyword);
		//request.setAttribute("encodedK", encodedK);
		request.setAttribute("totalPageCount", totalPageCount);	//모든 페이지 count
		request.setAttribute("list", list);	//리뷰 list
		request.setAttribute("totalRow", totalRow);
		
		
	}
	
	@Override
	public void searchListUser(HttpServletRequest request) {
		
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=6;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;
		
		//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int pageNum=1;
		//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum = request.getParameter("pageNum");
		//만일 페이지 번호가 파라미터로 넘어 온다면
		if(strPageNum != null){
			//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum-1) * PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;
		
		//UsersDto 객체에 startRowNum과 endRowNum을  담는다.
		SearchDto dto = new SearchDto(); 
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//전체 row 의 갯수
		List<SearchDto> list= dao.getSearchList(dto);
		/* mView.addObject("list", list); */
		
		int totalRow = dao.getCount(dto);
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		
		//전체 페이지의 갯수 구하기
		int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum = totalPageCount; //보정해 준다. 
		}
		
		//request 영역에 담아주기
		request.setAttribute("pageNum", pageNum);	//현재 페이지 번호
		request.setAttribute("startPageNum", startPageNum);	//시작 페이지 번호
		request.setAttribute("endPageNum", endPageNum);	//끝 페이지 번호
		request.setAttribute("totalPageCount", totalPageCount);	//모든 페이지 count
		request.setAttribute("list", list);	//리뷰 list
		request.setAttribute("totalRow", totalRow);
		
		
	}

	@Override
	public int insertUser(Map<Object, Object> map) {
		return dao.insert(map); 
	}
	
	@Override 
	public int insertSk(Map<Object, Object> map1) { 
		System.out.println("ser"+map1.get("skill_code"));
		System.out.println("ser"+map1.get("staff_no"));
		return dao.insertSkill(map1); 
	}
	
	@Override
	public int updateUser(UsersDto dto) {
		System.out.println("ser1"+dto.getStaff_no());
		return dao.update(dto);
	}
	
	@Override 
	public int updateSk(Map<Object, Object> updsk) { 
		System.out.println("ser2"+updsk.get("skill_code"));
		return dao.updateSkill(updsk); 
	}

	@Override
	public int deleteUser(int staff_no) {
		
		return dao.delete(staff_no);
	}
	
	@Override
	public int deleteSk(int staff_no) {
		
		return dao.deleteSkill(staff_no);
	}

	
	@Override
	public int getUserNo(int staff_no) {
		
		return dao.getUserNo(staff_no);
	}

}
