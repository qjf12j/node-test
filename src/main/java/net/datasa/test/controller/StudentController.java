package net.datasa.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.test.domain.dto.StudentDTO;
import net.datasa.test.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Data
@Controller
@Slf4j
public class StudentController {

	private final StudentService service;

	/**
	 * 입력 페이지로 넘어가는 기능 제공
	 */
	@GetMapping("save")
	public String save() {
		return "save";
	}

	/**
	 * 입력한 정보를 DB에 저장하는 기능 제공
	 * 
	 * @param DTO 입력한 객체
	 */
	@PostMapping("save")
	public String save(@ModelAttribute StudentDTO dto) {
		service.save(dto);
		log.debug("서버에 전송된 정보 :{}", dto);
		return "home";
	}

	/**
	 * 전체정보 조회
	 * 
	 */

	@GetMapping("serchall")
	public String serchall(Model model) {

		List<StudentDTO> dtolist = service.serchall();

		model.addAttribute("dtolist", dtolist);

		return "serchall";
	}
	/**
	 *  정보 삭제 
	 * @param sid  학번
	 */
@PostMapping("delete")
public String delete(@RequestParam ("sid") String sid , Model model) {
  
	boolean result = service.delete(sid);
	
	model.addAttribute("result",result);
    model.addAttribute("sid", sid);
	
    return "redirect:/";
}
@GetMapping("delete")
public String delete(Model model,@RequestParam ("sid") String sid  ) {
	
	boolean result = service.delete(sid);
	
	model.addAttribute("result",result);
	model.addAttribute("sid", sid);
	
	return "redirect:/";
}
/**
 *  정보 수정페이지로 이동
 *  @param sid 학번
 *  @param dto 입력정보(점수들)
 * 
 */
@GetMapping("update")
public String update(@RequestParam("sid") String sid , Model model) {
   
	StudentDTO dto = service.selet(sid);
	
	model.addAttribute("sid", sid);
	model.addAttribute("dto", dto);
	
	return "updateForm";
}
@PostMapping("update")
public String postMethodName(@ModelAttribute StudentDTO dto) {
	log.debug("전달된값{}" , dto );
	service.update(dto);
    
	return "redirect:/";
}


}