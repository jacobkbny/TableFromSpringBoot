package com.tistory.jacobcloud.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
public class PageRequestDTO {
	//페이지 갯수를 저장할 변수
	private int page;
	//한 페이지에 출력될 데이터 개수 
	private int size;

	//검색 항목을 저장할 변수
	
	private String type;
	
	private String keyword;

	// 생성자 - 인스턴스 변수 초기화

	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
		//Pageable(Spring boot JPA에서 Page 단위 검색을 위한 클래스) 객체를 생성해주는 메서드
	public Pageable getPageable(Sort sort) {
			return PageRequest.of(page-1,size,sort);
	}
	
}