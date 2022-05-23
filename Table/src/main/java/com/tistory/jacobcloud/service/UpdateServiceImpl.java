package com.tistory.jacobcloud.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tistory.jacobcloud.dto.MemoDTO;
import com.tistory.jacobcloud.model.Memo;
import com.tistory.jacobcloud.table.persistence.MemoRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UpdateServiceImpl implements UpdateService {
	private final MemoRepository memoRepository;

	@Override
	public MemoDTO read(Long gno) {
		Optional<Memo> memo=memoRepository.findById(gno);
		return memo.isPresent()?entitytoDTO(memo.get()):null;
	}

	@Override
	public int update(MemoDTO dto) {
		int result=	memoRepository.updateMemo(dto.getTitle(), dto.getContent(), dto.getGno());
		return result;
	}
	
	

	


	
	
	
	
}
