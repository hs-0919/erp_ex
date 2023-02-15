package com.gura.erp.staff.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gura.erp.staff.dto.UsersDto;
import com.gura.erp.staff.service.StaffService;

import oracle.jdbc.logging.annotations.Log;

import com.gura.erp.staff.dto.Criteria;
import com.gura.erp.staff.dto.SearchDto;

@Controller
public class StaffController {
	
	@Autowired
	private StaffService service;
	
	
	//사원 전체 리스트 
	@RequestMapping(value="/staff/staff_search_form.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String staffList(@ModelAttribute("UsersDto") UsersDto dto, Model model,
			@RequestParam(value="pageNum", defaultValue = "1") int pageNum,
			@RequestParam(required=false, defaultValue = "staff_no") String col
			) {
		
		System.out.println("전체검색 pageNum > " + pageNum);
		//list정보 보내기
		List<UsersDto> schoolList = service.getSchoolList();
		List<UsersDto> skillList = service.getSkillList();
		List<UsersDto> departList = service.getDepartList();
		
		
		model.addAttribute("schoolList",schoolList);
		model.addAttribute("skillList",skillList);
		model.addAttribute("departList",departList);
		
		// 총 개수 모델에 담기
		// 페이징
		int displayCount = 5; // 페이징바 최대 개수
		int pageCount = 10;    // 게시글 최대 개수
		int allCount;       // 게시글 총 개수
		
		allCount = service.getCount();
		model.addAttribute("allCount", allCount);
		
		// 페이지 최대 개수
		int maxPage = (int) Math.ceil((double) allCount / pageCount);
		// 시작 페이지
		int startRowNum = ((pageNum - 1) / displayCount) * displayCount + 1;
		// 마지막 페이지
		int endRowNum = startRowNum + displayCount - 1;
		
		if(endRowNum > maxPage) {
			endRowNum=maxPage;
		}
		// 모델에 담기
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("startRowNum", startRowNum);
		model.addAttribute("endRowNum", endRowNum);
		
		dto =new UsersDto(); 
		dto.setPageNum(pageNum);
		dto.setTotalCount(allCount);
		dto.setDisplayCount(displayCount);
		dto.setPageCount(pageCount);
		dto.setMaxPage(maxPage);
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		Map<Object, Object> page = new HashMap<>();
		page.put("col", col);
		
		List<UsersDto> boardList = service.getListUser(page, dto);
		model.addAttribute("boardList",boardList);
		
		
		
		return "staff/staff_search_form";
	}
	
	//사원 검색
	@RequestMapping(value="/staff/staff_search.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String staff_search(Model model,
			@RequestParam(value="pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value="staff_no",required=false, defaultValue = "staff_no") String col,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="keyword_type", defaultValue = "AND") String keyword_type,
			@RequestParam(value="staff_name", required=false) String staff_name,
			@RequestParam(value="gender", required=false) List<String> gender,
			@RequestParam(value="department_code",required=false, defaultValue = "0") int department_code,
			@RequestParam(value="school_code", required=false, defaultValue = "") List<Integer> school_code,
			@RequestParam(value="skill_code", required=false) List<Integer> skill_code,
			@RequestParam(value="startYear", required=false) String startYear,
			@RequestParam(value="startMonth", required=false) String startMonth,
			@RequestParam(value="graduateYear", required=false) String graduateYear,
			@RequestParam(value="graduateMonth", required=false) String graduateMonth
			) throws Exception{
		
		//list정보 보내기
		List<UsersDto> schoolList = service.getSchoolList();
		List<UsersDto> skillList = service.getSkillList();
		List<UsersDto> departList = service.getDepartList();
		
		model.addAttribute("schoolList",schoolList);
		model.addAttribute("skillList",skillList);
		model.addAttribute("departList",departList);
		
		//졸업 날짜 concat
		String graduateDayStart = "";
		String graduateDayEnd = "";
		
		if(startYear != "" && startYear != null	&& startMonth != "" && startMonth != null
		   && graduateYear != "" && graduateYear != null && graduateMonth != "" && graduateMonth != null) {
			graduateDayStart = startYear.concat(startMonth);
			graduateDayEnd = graduateYear.concat(graduateMonth);
		}
		System.out.println("gra++"+graduateDayStart+", "+graduateDayEnd);
		
		//map에 담기
		Map<Object, Object> search = new HashMap<>();
		search.put("keyword", keyword);
		search.put("col", col);
		search.put("keyword_type", keyword_type);
		search.put("staff_name", staff_name);
		search.put("gender", gender);
		search.put("department_code", department_code);
		search.put("school_code", school_code);
		search.put("skill_code", skill_code);
		search.put("graduateDayStart", graduateDayStart);
		search.put("graduateDayEnd", graduateDayEnd);
		
		System.out.println("all+"+keyword+", "+keyword_type+", "+staff_name+", "+gender+", "+department_code+", "+school_code+", "+
				skill_code+", "+graduateDayStart+", "+graduateDayEnd);
		System.out.println("검색 pageNum > " + pageNum);
		
		// 페이징
		int displayCount = 5; // 페이징바 최대 개수
		int pageCount = 8;    // 게시글 최대 개수
		int totalCount;       // 게시글 총 개수
		
		// 총 개수 모델에 담기
		totalCount = service.getSearchCount(search);
		model.addAttribute("totalCount", totalCount);
		
		// 페이지 최대 개수
		int maxPage = (int) Math.ceil((double) totalCount / pageCount);
		// 시작 페이지
		int startRowNum = ((pageNum - 1) / displayCount) * displayCount + 1;
		// 마지막 페이지
		int endRowNum = startRowNum + displayCount - 1;
		
		if(endRowNum > maxPage) {
			endRowNum=maxPage;
		}
		// 모델에 담기
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("startRowNum", startRowNum);
		model.addAttribute("endRowNum", endRowNum);
		
		SearchDto ser =new SearchDto(); 
		ser.setPageNum(pageNum);
		ser.setTotalCount(totalCount);
		ser.setDisplayCount(displayCount);
		ser.setPageCount(pageCount);
		ser.setMaxPage(maxPage);
		ser.setStartRowNum(startRowNum);
		ser.setEndRowNum(endRowNum);
		
		
		//사원 정보 보내기
		List<SearchDto> data = service.getSearchList(search, ser);
		/*
		 * for(int i = 0; i < list1.size(); i++) { switch(list1.get(i).getGender()) {
		 * case "1" : list1.get(i).setGender("남"); break; case "2" :
		 * list1.get(i).setGender("여"); break; } }
		 */
		
		model.addAttribute("data",data);
		model.addAttribute("li",data);
		System.out.println(data.toString());
		model.addAttribute("keyword",keyword);
		model.addAttribute("col",col);
		
		

		

		return "staff/staff_search";
		
	}
	
	//사원 정보 등록 폼
	@RequestMapping(value="/staff/staff_input_form.do")
	public ModelAndView staff_input_form(HttpServletRequest request, ModelAndView mView) {
		mView.setViewName("staff/staff_input_form");
		return mView;
	}
	
	//사원 정보 등록
	@RequestMapping(value="/staff/insert.do" , method = RequestMethod.POST )
	public String insert(
			UsersDto dto,
			@RequestParam(value="staff_name", required=false) String staff_name, 
			@RequestParam(value="jumin_no", required=false) String jumin_no,
			@RequestParam(value="jumin_no2", required=false) Integer jumin_no2,
			@RequestParam(value="gender", required=false) String gender, 
			@RequestParam(value="school_code", required=false) Integer school_code,
			@RequestParam(value="skill_code", required=false) List<String> skill_code, 
			@RequestParam(value="department_code", required=false) Integer department_code,
			@RequestParam(value="graduateYear", required=false) String graduateYear,
			@RequestParam(value="graduateMonth", required=false) String graduateMonth
			) {
		System.out.println(staff_name);
		
		String b = graduateYear.concat(graduateMonth);
		String graduateDayS = b.replaceAll("\\s", ""); //공백 제거
		
		Map<Object, Object> map = new HashMap<>();
		// 정보 담기
		map.put("staff_name",staff_name);
		map.put("jumin_no",jumin_no);
		map.put("jumin_no2",jumin_no2);
		map.put("school_code",school_code);
		map.put("department_code",department_code);
		map.put("graduateDayS",graduateDayS);
		map.put("graduateMonth",graduateMonth);
		map.put("gender",gender);
		
		System.out.println(map.put("staff_name",staff_name));
		// Service를 이용하여  map에 회원정보 담기
		service.insertUser(map); 
		
		System.out.println("전"+skill_code);
		Map<Object, Object> map1 = new HashMap<>();
		
		// 따로 skill_code만 가져와 map1에 담기
		map1.put("skill_code",skill_code);
		
		System.out.println("후"+skill_code);
		// Service를 이용하여 map1에 회원정보 담기
		service.insertSk(map1);

		return("redirect:/staff/staff_search_form.do");
	}
	
	// 사원 수정 폼
	@RequestMapping(value="/staff/staff_updel_form.do")
	public String staff_updel_form(Model model,int staff_no, String graduateMonth,UsersDto dto) {
		
		model.addAttribute("staff_no", staff_no);
		System.out.println("logala+++"+service.getUserNo(staff_no));
		
		List<UsersDto> user = service.getUser(staff_no);
		model.addAttribute("user",user);
		model.addAttribute("graduateMonth", user);
		System.out.println("logala++"+graduateMonth);
		
		List<UsersDto> skList = service.getSkillList(staff_no);
		model.addAttribute("skList",skList);
		
		return "staff/staff_updel_form";
	}
	
	//사원 수정
	@RequestMapping(value="/staff/update.do" , method = RequestMethod.POST )
	public String update(@ModelAttribute UsersDto dto,	
			@RequestParam(value="staff_no", required=true) int staff_no,
			@RequestParam(value="skill_code", required=false) List<String> skill_code,
			@RequestParam(value="graduateYear", required=false) String graduateYear,
			@RequestParam(value="graduateMonth", required=false) String graduateMonth,
			@RequestParam(value="graduateMonth", required=false) String graduateDayS
			) {

		//사원 번호 가져오기
		System.out.println("전"+skill_code);
		System.out.println("전"+staff_no);
		System.out.println("logala2+++"+service.getUserNo(staff_no));
		
		String b = graduateYear.concat(graduateMonth);
		graduateDayS = b.replaceAll("\\s", "");//공백 제거
		dto.setGraduate_day(graduateDayS); //dto에 담기
		
		//Map을 만들어서
		Map<Object, Object> updsk = new HashMap<>();
		// 따로 skill_code만 가져와 updsk에 담기
		updsk.put("skill_code",skill_code);
		updsk.put("graduateDayS",graduateDayS);
		
		//Service를 이용하여 사원정보 수정
		service.updateUser(dto); 
		
		//기존 사원 스킬정보를 삭제하고
		service.deleteSk(staff_no);
		System.out.println("후"+skill_code);
		
		// Service를 이용하여 updsk에 회원정보 담기
		service.updateSk(updsk);

		return("redirect:/staff/staff_search_form.do");
	}
	
	//사원 삭제
	@RequestMapping(value="/staff/delete.do")
	public String delete(@RequestParam(value="staff_no", required=true) int staff_no, @ModelAttribute UsersDto dto) {
		System.out.println("넘어온 del no : " + staff_no);
		//사원 번호 가져오기
		System.out.println("넘어온 del no : " + staff_no);
		//Service 객체를 이용해서 사원 스킬 정보 삭제하기
		service.deleteSk(staff_no);
		//Service 객체를 이용해서 사원 정보 삭제하기
		service.deleteUser(staff_no);

		/* /staff/staff_search_form.do  요청을 다시 하도록 리다일렉트 이동 시킨다
		      리다일렉트 이동은 특정경로로 요청을 다시 하라고 강요하는 응답이다.
		      
		   "redirect: context 경로를 제외한 요청을 다시할 절대경로 " 
		*/
		return ("redirect:/staff/staff_search_form.do");
	}
	
}
