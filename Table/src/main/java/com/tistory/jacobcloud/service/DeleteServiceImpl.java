package com.tistory.jacobcloud.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tistory.jacobcloud.model.Memo;
import com.tistory.jacobcloud.table.persistence.MemoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteServiceImpl implements DeleteService {
	public final MemoRepository memoRepository;

	@Override
	public void remove(Long gno) {
		
			Optional<Memo> result = memoRepository.findById(gno);
			
			if(result.isPresent()) {
				memoRepository.deleteById(gno);
			}
			
			
			
			
			
		
	}
	
	
	
}
