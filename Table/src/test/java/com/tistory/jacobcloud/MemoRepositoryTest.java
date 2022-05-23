package com.tistory.jacobcloud;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.tistory.jacobcloud.model.Memo;
import com.tistory.jacobcloud.model.QMemo;
import com.tistory.jacobcloud.table.persistence.MemoRepository;

@SpringBootTest
public class MemoRepositoryTest {
	@Autowired
	private MemoRepository memoRepository;
	
//	@Test
	public void insertMemo() {
		IntStream.rangeClosed(1, 300).forEach(i ->{
				Memo memo = Memo.builder().title("Sample_"+i).content("nothig is coming_"+i).writer("admin_"+i).build();
				memoRepository.save(memo);
		});
		
	}
	
//	@Test
	public void modifyMemo() {
		Optional<Memo> result = memoRepository.findById(111L);
					
				if(result.isPresent()) {
						Memo memo = result.get();
						memo.changeTitle("title changed");
						memo.changeContent("content changed");
						memoRepository.save(memo);
				}else {
					System.out.println("데이터가 존재하지 않습니다.");
				}
				
	}
	
//	@Test
	public void deleteMemo() {
		Optional<Memo> result = memoRepository.findById(111L);	
			if(result.isPresent()) {
				Memo memo = result.get();
					memoRepository.delete(memo);
					
			}else {
				System.out.println("삭제할 데이터가 없습니다.");
			}
	}
	
//	@Test
	public void findByTitle() {
		List<Memo> list = memoRepository.findByTitle("Sample_2");
			System.out.println(list);
		}
	
//	@Test
	public void changeMemo() {
		int result = memoRepository.updateMemo("bang bang", "skit skit", 177L);
			System.out.println("result입니다-----"+result);
		
	}
	
	//title에 1이 포함된 데이터를 조회
//	@Test
	public void testQuery1() {
		Pageable pageable = PageRequest.of(0 , 10, Sort.by("gno").descending());
		
		QMemo qMemo = QMemo.memo;
		String title = "1";
		String content = "nothing";	
		BooleanBuilder builder = new BooleanBuilder();
		BooleanBuilder builder_title = new BooleanBuilder();
		BooleanExpression expression = qMemo.content.contains(content);
		BooleanExpression expression_title = qMemo.title.contains(title);		
		builder.and(expression);
		builder_title.and(expression_title);
		
		Page<Memo> result = memoRepository.findAll(builder,pageable);
		Page<Memo> result_title = memoRepository.findAll(builder_title,pageable);
			System.out.println("content 내용 검색입니다");
		for(Memo memo : result) {
			System.out.println(memo);
		}
		System.out.println("title 검색입니다");
		for(Memo m : result_title) {
			System.out.println(m);
		}
		
	}
	//title 또는 content에 1이 포함되어 있는 것 찾기
	
	public void findlike() {
		Pageable pageable = PageRequest.of(0 , 10, Sort.by("gno").descending());
		
		QMemo qMemo = QMemo.memo;

		BooleanBuilder builder = new BooleanBuilder();
		
		//키워드를 생성
		String keyword = "1";
		
		//title에 포함된거 
		BooleanExpression exr_title = qMemo.title.contains(keyword);
		BooleanExpression exr_content = qMemo.content.contains(keyword);
		//2개의 조건을 or로 묶음
		BooleanExpression exAll = exr_title.or(exr_content);
		
			builder.and(exAll);
			//gno가 0보다 큰 조건을 and로 묶어주기
			builder.and(qMemo.gno.gt(200L));
			Page<Memo> result = memoRepository.findAll(builder,pageable);
				System.out.println("title 혹은 content 에 1이 들어가는것 뽑기");
			for(Memo m : result) {
				System.out.println(m);
			}
		
	}
	
}
