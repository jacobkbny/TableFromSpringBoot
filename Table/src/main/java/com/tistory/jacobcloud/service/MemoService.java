package com.tistory.jacobcloud.service;

import com.querydsl.core.BooleanBuilder;
import com.tistory.jacobcloud.dto.MemoDTO;
import com.tistory.jacobcloud.dto.PageRequestDTO;
import com.tistory.jacobcloud.dto.PageResponseDTO;
import com.tistory.jacobcloud.model.Memo;

public interface MemoService {
	//데이터 삽입을 위한 메서드
	//삽입된 메모의 gno 값을 리턴 
	public Long insertMemo(MemoDTO dto);
	
	public PageResponseDTO<MemoDTO , Memo> getList(PageRequestDTO requestDTO);
	
	public MemoDTO read(Long gno);
	
	public BooleanBuilder getSearch(PageRequestDTO reqeustDTO);
		
	
	
	//DTO 객체를 Entity로 변환해주는 메서드 
	//이 메서드는 클라이언트 정보를 가지고 데이터베이스에 변환을 수행하기 위해서
	
	default Memo dtoToEntity(MemoDTO dto) {
			Memo memo = Memo.builder().gno(dto.getGno())
					.content(dto.getContent())
					.title(dto.getTitle())
					.writer(dto.getWriter())
					.build();
			return memo;
	}
	
	//Entity 객체를 dto 객체로 변환해주는 메서드
	// 주로 조회를 할 떄 사용함 
	
	default MemoDTO entitytoDTO(Memo memo) {
		MemoDTO dto = MemoDTO.builder().gno(memo.getGno())
				.title(memo.getTitle())
				.content(memo.getContent())
				.writer(memo.getWriter())
				.regdate(memo.getRegdate())
				.moddate(memo.getModdate())
				.build();
			return dto;
	}
	
	
}
