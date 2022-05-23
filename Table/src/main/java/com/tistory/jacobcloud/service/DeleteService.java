package com.tistory.jacobcloud.service;

import com.tistory.jacobcloud.dto.MemoDTO;
import com.tistory.jacobcloud.model.Memo;

public interface DeleteService {
	
	public void remove(Long gno);
	
	
	
	
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
