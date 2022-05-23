package com.tistory.jacobcloud;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tistory.jacobcloud.domain.PersonVO;

@Controller
public class PageController {

		@GetMapping("/")
		public String main(Model model) {
			String name = "jacob";
			PersonVO person = PersonVO.builder().num(1L).name("pkb").asset(20000L).debt(1000L).properties("house,vehicle,crypto,stock") .nickname("jay").birthtime(LocalDateTime.now()).build();
			//mapToObj(객체로 변환 - 매개변수가 1개이고 1개의 객체를 리턴하는 람다를 매개변수로 사용)
			List<PersonVO>list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i->{
				PersonVO temp =PersonVO.builder().num(i).name("unknown_"+i).nickname("Alias_"+i)
							.birthtime(LocalDateTime.now()).build();
						return temp;
				}).collect(Collectors.toList());
					model.addAttribute("name",name);
					model.addAttribute("person",person);
					model.addAttribute("list",list);
					
			return "main";
		}
		
		@GetMapping("/sample")
		public String exformat(Model model,RedirectAttributes rttr, HttpSession session , HttpServletRequest request){
						// 전송할 데이터를 생성 - Service의 메서드를 호출해서 생성하는 경우가 대부분
				List<PersonVO> list = new ArrayList<>();
				for(long i = 1; i<=10; i++) {
						Random r = new Random();
							Long a =r.nextLong(100000);
							Long b = r.nextLong(1000);
					PersonVO person = PersonVO.builder().num(i).name("name_"+i).nickname("Alias_"+i).asset(a).debt(b).birthtime(LocalDateTime.now()).build();
							list.add(person);
				}
//					request.setAttribute("list", list);
					model.addAttribute("list",list);
				
					//데이터 저장
					//model 이나 request에 저장하면 포워딩 하는 경우에는 데이터가 전될됨.
					//redirect 하면 model 이나 request에 저장된 데이터는 소멸
					//redirect 할 떄도 데이터를 전달하고자 하는 경우에는 session이나 redirect.addattribute를 이용 
					//차이점은 세션에서 강제로 삭제하지 않는한 유지 되지만 ,rttr은 한번 사용하면 사라짐
					return "sample";
}
					@GetMapping("/include")
				public void include() {
						
					}
		
}
