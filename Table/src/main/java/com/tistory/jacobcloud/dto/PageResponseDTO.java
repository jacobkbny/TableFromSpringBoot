package com.tistory.jacobcloud.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;

//재활용을 위해서 템플릿을 적용
//DTO는 변환할 DTO 클래스의 이름 (어떤 DTO가 들어와도 상관 없음)
//EN은 Entity 와 DTO 를 서로 변환을 시켜줄 메서드(함수)
//EN은 바꿔주는 함수
//미지정 자료형 
//java 에서는 이 미지정 자료형을 Generic
//객체 지향 프로그럼애서는 템플릿 프로그램 
@Getter
public class PageResponseDTO<DTO,EN> {
		//DTO의 list
	private List<DTO> dtoList;
		// 전체 페이지 개수 
	private int totalPage;
	
		//출력할 페이지 번호 개수
	private int page;
	private int size;
	
		//이전 페이지 목록 여부
	private boolean prev;
	
		// 다음 페이지 목록 여부
	private boolean next;
	
	private int start;
		
	private int end;
	
	//출력할 페이지 번호 목록
	private List<Integer> pageList;
	//출력할 페이지 번호를 계산하는 메서드
	
	private void makePageList(Pageable pageable) {
			this.page = pageable.getPageNumber()+1;
			this.size = pageable.getPageSize(); 
			
			int tempEnd = (int)(Math.ceil(page/10.0))*10;
			start = tempEnd -9;
			prev = start >1;
			end = totalPage > tempEnd ? tempEnd : totalPage;
			next = totalPage > tempEnd;
			pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			
			}
	
	//page 객체 와 함수를 적용해서 List로 변환해주는 메서드
	public PageResponseDTO(Page<EN> result , Function<EN, DTO>fn) {
				//출력할 데이터 목록 생성
			dtoList = result.stream().map(fn).collect(Collectors.toList());
				//페이지 번호 목록 생성
			totalPage = result.getTotalPages();
			makePageList(result.getPageable());
	}
}
