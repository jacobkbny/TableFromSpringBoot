package com.tistory.jacobcloud.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.tistory.jacobcloud.dto.MemoDTO;
import com.tistory.jacobcloud.dto.PageRequestDTO;
import com.tistory.jacobcloud.dto.PageResponseDTO;
import com.tistory.jacobcloud.model.Memo;
import com.tistory.jacobcloud.model.QMemo;
import com.tistory.jacobcloud.table.persistence.MemoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
//로그 기록을 위한 어노테이션 -log.level(메세지)를 이용해서 로그를 출력하는 것이 가능 
@Log4j2
@Service
//생성자를 이용해서 주입받기 위한 어노테이션
//@autowired를 이용해서 주입 받으면 주입 받는 시점을 예측하기 어려움. 언제나 사람이 예측하기 어려운건 쓰지 않는게 좋음
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
		//주입 받기 위한 Repository -생성자에서 주입받기 위해서는 final로 만들어져야 함.
		//autowired로 주입 받으면 생성자를 이용할떄 에러가 날수있음 왜나면 아직 주입되지 않았는데 생성자를 불렀기떄문에.
		//생성자를 통해 주입 받으면, 생성자를 생성할떄 주입이 된다는걸 알기 떄문에 예측을 할수 있고 그에 따라서 작업을 진행할수 있음
		private final MemoRepository memoRepository;

		@Override
		public Long insertMemo(MemoDTO dto) {
			log.info("데이터 삽입 ---------------------------");
			log.info(dto);
			
			//Dto를 entity로 변환
			Memo memo = dtoToEntity(dto);
			
			//데이터 저장
			//데이터 저장하고 저장한 내용을 memo에 다시 기록
			memoRepository.save(memo);
			return memo.getGno();
		}

		@Override
		public PageResponseDTO<MemoDTO, Memo> getList(PageRequestDTO requestDTO) {
				Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
				Page<Memo> result = memoRepository.findAll(pageable);
				//Entity를 DTO를 변환해주는 함수 설정
				Function<Memo,MemoDTO> fn = (entity -> entitytoDTO(entity));
					//fn 에는 Memo entity를 DTO로 바뀐 형태로 들어가있음
			return new PageResponseDTO<>(result, fn);
			
		}

		@Override
		public MemoDTO read(Long gno) {
				//기본키
					Optional<Memo> memo=memoRepository.findById(gno);
			return memo.isPresent()?entitytoDTO(memo.get()):null;
		}

		@Override
		public BooleanBuilder getSearch(PageRequestDTO reqeustDTO) {
			
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			
			String type = reqeustDTO.getType();
			QMemo qMemo = QMemo.memo;
			String keyword=reqeustDTO.getKeyword();
			BooleanExpression expression = qMemo.gno.gt(0L);
			booleanBuilder.and(expression);
			
			//검색 조건이 없을 떄
			if(type== null || type.trim().length() == 0) {
				
					return booleanBuilder;
			}
			BooleanBuilder conditionBuilder = new BooleanBuilder();
			
			if(type.contains("t")) {
				conditionBuilder.or(qMemo.title.contains(keyword));
			}
			if(type.contains("c")) {
				conditionBuilder.or(qMemo.content.contains(keyword));
			}
	
			if(type.contains("w")) {
				conditionBuilder.or(qMemo.writer.contains(keyword));
			}
			
			booleanBuilder.and(conditionBuilder);
			
			return booleanBuilder;
		}
		
		
		
	
	
	
}
