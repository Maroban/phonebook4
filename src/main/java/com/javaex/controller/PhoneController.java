package com.javaex.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 필드
	@Autowired
	private PhoneDao phoneDao;

	// 생성자

	// 메소드 - GS

	// 메소드 - 일반

	/*** 리스트 ***/
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[PhoneController.list]");

		// new PhoneDao()
		// PhoneDao phoneDao = new PhoneDao();

		// PhoneList 가져오기
		List<PersonVo> personList = phoneDao.getPersonList();

		// model에 담기
		model.addAttribute("pList", personList);

		// Front Controller ==> list.jsp 포워드
		return "/WEB-INF/views/list.jsp";
	}

	/*** 등록 폼 ***/
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[PhoneController.writeForm]");

		return "/WEB-INF/views/writeForm.jsp";
	}

	/*** 등록 ***/
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("[PhoneController.write]");

		// insert() 메소드 사용
		phoneDao.insert(personVo);

		// 리다이렉트
		return "redirect:/list";
	}

	/*** 삭제 ***/
	@RequestMapping(value = "/delete/{person_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("person_id") int person_id) {
		System.out.println("[PhoneController.delete]");

		// delete() 메소드 사용
		phoneDao.delete(person_id);

		// 리다이렉트
		return "redirect:/list";
	}

	/*** 수정 폼 ***/
	@RequestMapping(value = "/modifyForm/{person_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@PathVariable("person_id") int person_id, Model model) {

		// 한사람 정보 가져오기
		PersonVo onePerson = phoneDao.getPerson(person_id);

		// model에 담기
		model.addAttribute("onePerson", onePerson);

		// modifyForm.jsp 포워드
		return "/WEB-INF/views/modifyForm.jsp";
	}

	/*** 수정 ***/
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute PersonVo personVo) {

		// update() 메소드 사용
		phoneDao.update(personVo);

		// 리다이렉트
		return "redirect:/list";
	}

}
