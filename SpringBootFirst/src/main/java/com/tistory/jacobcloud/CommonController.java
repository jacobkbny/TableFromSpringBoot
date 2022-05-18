package com.tistory.jacobcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 기본 패키지 안에 있으면 인스턴스를 생성

//controller오와 rescontroller 는 매칭되는 url이 있으면 메서드를 호출

//controller는 view 이름을 이용해서 출력을 만들고

//restcontroller는 문자열이나 json 문자열을 출력


@RestController
public class CommonController {
	
		@GetMapping("/")
		public String home() {
			return "Hello Spring boots";
		}
	
}
