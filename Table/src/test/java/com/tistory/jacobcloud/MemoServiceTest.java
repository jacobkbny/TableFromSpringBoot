package com.tistory.jacobcloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tistory.jacobcloud.dto.MemoDTO;
import com.tistory.jacobcloud.dto.PageRequestDTO;
import com.tistory.jacobcloud.dto.PageResponseDTO;
import com.tistory.jacobcloud.model.Memo;
import com.tistory.jacobcloud.service.MemoService;

@SpringBootTest
public class MemoServiceTest {
	@Autowired
	private MemoService memoService;
	
//	@Test
	public void testInsert() {
		
		MemoDTO dto = MemoDTO.builder().title("데이터 삽입 테스트").content("삽입 테스트중").writer("tester").build();
				
			System.out.println(memoService.insertMemo(dto));
	}
	
	// 목록 보기 테스트 - 목록 테스트
//		@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
			PageResponseDTO<MemoDTO, Memo> resultDTO = memoService.getList(pageRequestDTO);
			for(MemoDTO memo : resultDTO.getDtoList()) {
				System.out.println(memo);
			}
	}
		// 목록 과 페이지 번호 테스트
	@Test
	public void testPageList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResponseDTO<MemoDTO, Memo> resultDTO = memoService.getList(pageRequestDTO);
		for(MemoDTO memo : resultDTO.getDtoList()) {
			System.out.println(memo);
		}
			System.out.println("이전"+resultDTO.isPrev());
			System.out.println("다음"+resultDTO.isNext());
			
			//전체 페이지 개수
			System.out.println("전체 페이지 개수"+resultDTO.getTotalPage());
			//페이지 번호 목록
			System.out.println(resultDTO.getPageList());
	}
	
}
