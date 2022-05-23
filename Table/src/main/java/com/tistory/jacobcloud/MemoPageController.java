package com.tistory.jacobcloud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tistory.jacobcloud.dto.MemoDTO;
import com.tistory.jacobcloud.dto.PageRequestDTO;
import com.tistory.jacobcloud.service.DeleteService;
import com.tistory.jacobcloud.service.MemoService;
import com.tistory.jacobcloud.service.UpdateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//로그 기록을 편리하게 할 수 잇도록 해주눈 어노테이션
@Log4j2
@Controller
// 인스턴수 변수의 주입을 생성자에서 자동으로 처리하도록 해주는 어노테이션
@RequiredArgsConstructor
		@RequestMapping("/memo/")
public class MemoPageController {
			private final MemoService memoService;
			private final UpdateService updateService;
			private final DeleteService deleteService;
		@GetMapping("/")
		public String main() {
				//redirect 할 떄는 view의 이름을 적것이 아니라 요청을 적어야함
			return "redirect:/memo/list";
		}

		//목록보기 요청을 처리
		@GetMapping("list")
		public void list(PageRequestDTO pageRequestDTO , Model model) {
				log.info("목록 보기");
				log.info("getlist 리턴값"+memoService.getList(pageRequestDTO).getDtoList());
				model.addAttribute("result", memoService.getList(pageRequestDTO));
		}
		
		//삽입 화면으로 이동하는 요청을 처리
		@GetMapping("insert")
		public void insert() {
			log.info("데이터 삽입 화면");
			
		}
		
		@PostMapping("insert")
		public String insert(MemoDTO dto , Model model) {
			log.info("데이터 삽입 처리 ");
				//파라미터가 제대로 넘어 오지 않으면 요청 url 과 view 이름을 확인
				//form의 경우라면 입력 요소의 name을 확인 
			log.info("파라미터:",dto);
			Long gno = memoService.insertMemo(dto);
			return "redirect:/memo/list";
					}
		@GetMapping("read")
		public void read(Long gno,Model model,@ModelAttribute("requestDTO")PageRequestDTO requestDTO) {
			MemoDTO memo = memoService.read(gno);
				System.out.println("메모 입니다-----"+memo);
			model.addAttribute("dto",memo);
		}
		
		@GetMapping("update")
		
		public void update(Long gno, Model model,@ModelAttribute("requestDTO")PageRequestDTO requestDTO) {
			MemoDTO memo = memoService.read(gno);
			System.out.println("메모 입니다-----"+memo);
		model.addAttribute("dto",memo);
		}
		
		@PostMapping("update")
		
		public String update(MemoDTO dto,@ModelAttribute("requestDTO")PageRequestDTO requestDTO,RedirectAttributes rttr) {
			int result = updateService.update(dto);
			rttr.addFlashAttribute("result",result);
			return "redirect:/memo/read?gno="+dto.getGno()+"&page="+requestDTO.getPage();
		}
		
		@PostMapping("delete")
		
		public String remove(Long gno,RedirectAttributes rttr) {
				
				log.info("gno:"+gno);
				
				deleteService.remove(gno);
				rttr.addAttribute("msg",gno+"삭제");
				return "redirect:/memo/list";
		}
		
		
		
}
