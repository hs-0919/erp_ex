package com.gura.erp.users.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gura.erp.users.dto.SearchDto;
import com.gura.erp.users.dto.UsersDto;
import com.gura.erp.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	
	@RequestMapping(value="/users/staff_search_form.do")
	public String staff_search_form(HttpServletRequest request,Model model){
		//list정보 보내기
		List<UsersDto> schoolList = service.getSchoolList();
		List<UsersDto> skillList = service.getSkillList();
		
		model.addAttribute("schoolList",schoolList);
		model.addAttribute("skillList",skillList);
		
		
		//사원 정보 보내기
		service.getListUser(request);

		return "users/staff_search_form";
		
	}
	
	@RequestMapping(value="/users/staff_search.do")
	public String staff_search(@ModelAttribute("myform") SearchDto dto, HttpServletRequest request,Model model){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dto", dto);
		
		
		List<UsersDto> schoolList = service.getSchoolList();
		
		
		//list정보 보내기
		List<UsersDto> skillList = service.getSkillList();
		
		model.addAttribute("schoolList",schoolList);
		model.addAttribute("skillList",skillList);
		model.addAttribute("myform",dto);
		
		
		//사원 정보 보내기
		service.searchListUser(request);

		return "users/staff_search_form";
		
	}
	
	
	
	@RequestMapping(value="/users/staff_input_form.do")
	public ModelAndView staff_input_form(HttpServletRequest request, ModelAndView mView) {
		mView.setViewName("users/staff_input_form");
		return mView;
	}
	
	@RequestMapping(value="/users/insert.do" , method = RequestMethod.POST )
	@DateTimeFormat(pattern="yyyy-MM")
	public String insert(
			UsersDto dto,
			@RequestParam(value="staff_name", required=false) String staff_name, 
			@RequestParam(value="jumin_no", required=false) String jumin_no,
			@RequestParam(value="jumin_no2", required=false) Integer jumin_no2,
			@RequestParam(value="gender", required=false) String gender, 
			@RequestParam(value="school_code", required=false) Integer school_code,
			@RequestParam(value="skill_code", required=false) List<String> skill_code, 
			@RequestParam(value="department_code", required=false) Integer department_code,
			@RequestParam(value="graduate_day", required=false) String graduate_day
			) {
		System.out.println(staff_name);
		
		graduate_day = graduate_day.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
		//날짜 가공 먼저 하기 
		Map<Object, Object> map = new HashMap<>();
		// 정보 담기
		map.put("staff_name",staff_name);
		map.put("jumin_no",jumin_no);
		map.put("jumin_no2",jumin_no2);
		map.put("school_code",school_code);
		map.put("department_code",department_code);
		map.put("graduate_day",graduate_day);
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

		return("redirect:/users/staff_search_form.do");
	}

	@RequestMapping(value="/users/staff_updel_form.do")
	@DateTimeFormat(pattern="yyyy-MM")
	public String staff_updel_form(Model model,int staff_no, UsersDto dto) {
		
		model.addAttribute("staff_no", staff_no);
		
		System.out.println("logala+++"+service.getUserNo(staff_no));
		List<UsersDto> user = service.getUser(staff_no);
		model.addAttribute("user",user);
		List<UsersDto> skList = service.getSkillList(staff_no);
		model.addAttribute("skList",skList);
		
		return "users/staff_updel_form";
	}
	
	@RequestMapping(value="/users/update.do" , method = RequestMethod.POST )
	@DateTimeFormat(pattern="yyyy-MM")
	public String update(@ModelAttribute UsersDto dto,	
			@RequestParam(value="staff_no", required=true) int staff_no,
			@RequestParam(value="skill_code", required=false) List<String> skill_code 
			) {

		//사원 번호 가져오기
		System.out.println("전"+skill_code);
		System.out.println("전"+staff_no);
		System.out.println("logala2+++"+service.getUserNo(staff_no));
		//Map을 만들어서
		Map<Object, Object> updsk = new HashMap<>();
		// 따로 skill_code만 가져와 updsk에 담기
		updsk.put("skill_code",skill_code);
		//Service를 이용하여 사원정보 수정
		service.updateUser(dto); 
		//기존 사원 스킬정보를 삭제하고
		service.deleteSk(staff_no);
		
		System.out.println("후"+skill_code);
		
		// Service를 이용하여 updsk에 회원정보 담기
		service.updateSk(updsk);

		return("redirect:/users/staff_search_form.do");
	}
	
		
	@RequestMapping(value="/users/delete.do")
	public String delete(@RequestParam(value="staff_no", required=true) int staff_no, @ModelAttribute UsersDto dto) {
		System.out.println("넘어온 del no : " + staff_no);
		//사원 번호 가져오기
		System.out.println("넘어온 del no : " + staff_no);
		//Service 객체를 이용해서 사원 스킬 정보 삭제하기
		service.deleteSk(staff_no);
		//Service 객체를 이용해서 사원 정보 삭제하기
		service.deleteUser(staff_no);

		/* /users/staff_search_form.do  요청을 다시 하도록 리다일렉트 이동 시킨다
		      리다일렉트 이동은 특정경로로 요청을 다시 하라고 강요하는 응답이다.
		      
		   "redirect: context 경로를 제외한 요청을 다시할 절대경로 " 
		*/
		return ("redirect:/users/staff_search_form.do");
	}
		
		
		
	
}
