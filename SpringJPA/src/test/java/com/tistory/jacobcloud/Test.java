package com.tistory.jacobcloud;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.jacobcloud.entity.Memo;
import com.tistory.jacobcloud.entity.MemoRepository;
import com.tistory.jacobcloud.entity.QMemo;

import lombok.ToString;

//스프링 부트 테스트 클래스를 의미하는 어노테이션
@SpringBootTest
public class Test {
	@Autowired
	
	MemoRepository memoRepository;
	
	@org.junit.jupiter.api.Test
	public void testDependency() {
		System.out.println(memoRepository);
		
	}
	
	//@org.junit.jupiter.api.Test
	public void testInsert() {
		//정수 스트림(여러 개의 데이터를 순회하면서 사용할 수 있도록 만든 데이터의 통로) 생성
		//forEach는 데이터를 순회하면서 작업을 수행하는 메서드
		//forEach의 매개변수는 매개변수가 1개이고 리턴이 없는 람다가 매개변수
		IntStream.rangeClosed(1, 100).forEach(i->{
				//builder()는 매개변수가 없는 생성자를 이용해서 객체를 생성하고
				//속성이름에 값을 바로 대입해서 객체를 생성해주는 메서드
			Memo memo = Memo.builder().memoText("sample..."+i).build();
			memoRepository.save(memo);
		});
	}
	
//	@org.junit.jupiter.api.Test
	public void testAllSelect() {
			//테이블의 전체 데이터를 가져오기
		List<Memo>list=memoRepository.findAll();
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	//@org.junit.jupiter.api.Test
	public void testselectOne() {
		Optional<Memo> memo = memoRepository.findById(1L);
		System.out.println(memo);
		
		memo = memoRepository.findById(86L);
			
			System.out.println(memo);
			//ifpresent() 사용해보기
			memo.ifPresent((a)->{System.out.println("비어있는 데이터가 아닙니다");}
			);
			//orElse() 사용해보기
			
		Optional<Memo> memo2 = memoRepository.findById(300L);
			Memo memo3 = new Memo();
				memo3.setMemoText("비어있을떄 orElse()를 사용하기 위한 디폴트 입니다.");
				memo3.setMno(900L);
				System.out.println("memo.orElse() 시작");
				System.out.println(memo2.orElse(memo3));
				
		
	}
//	@org.junit.jupiter.api.Test
	public void testUpdate() {
		//mno가 100이고 mem0Text가 updated text인 인스턴스 생성
		Memo memo = Memo.builder().mno(100L).memoText("updated text").build();
		
		System.out.println(memoRepository.save(memo));
	}
	
	//@org.junit.jupiter.api.Test
	public void testDelete() {
		Memo memo = Memo.builder().mno(101L).build();
		//memoRepository.delete(memo);
		memoRepository.deleteById(101L); // 기본키 가지고 데이터 삭제
	}
//	@org.junit.jupiter.api.Test
	public void testPaging() {
		Pageable page = PageRequest.of(0, 10);
		Page<Memo> list = memoRepository.findAll(page);
			for(Memo m : list) {
				System.out.println(m);
			}
		
	}
//	@org.junit.jupiter.api.Test
	public void testSort() {
			Sort sort = Sort.by("mno").descending(); // 내림차순
//			Sort sort = Sort.by("mno").ascending(); // 오름차순
			
		Pageable page = PageRequest.of(0, 10);
		Page<Memo> list = memoRepository.findAll(page);
		for(Memo m : list) {
			System.out.println(m);
		}
		
	}
	
	//@org.junit.jupiter.api.Test
	public void testQueryMethod() {
		List<Memo>list = memoRepository.findByMnoBetween(70L, 80L);
			for(Memo m : list) {
				System.out.println("70 부터 80 사이에 있는것들"+m);
			}
	}
	
//	@org.junit.jupiter.api.Test
	public void testQueryMethodDesc() {
		List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
		
				System.out.println("70부터 80 사이 내림차순"+list);
//			for(Memo m : list) {
//				System.out.println("70부터 80 사이 내림차순"+m);
//			}
			
	}
	
//	@org.junit.jupiter.api.Test
	public void betweenPage() {
			//Mno의 내림차순 정렬 후 2페이지부터 10개를 가져오는 Pageable 객체 생성
		Pageable page = PageRequest.of(1, 10, Sort.by("mno").descending());
		List<Memo> list = memoRepository.findByMnoBetween(10L, 50L, page);
		for(Memo m : list) {
			System.out.println("betweenPage is executed"+m);
		}
		
	}
//	@org.junit.jupiter.api.Test
	@Commit
	@Transactional
	public void deleteLessthan() {
		memoRepository.deleteByMnoLessThan(10L);
	}
	
//	@org.junit.jupiter.api.Test
	public void testUpdateQuert() {
		System.out.println(memoRepository.updateMemoText(11L,"반갑습니다."));
		System.out.println(memoRepository.updateMemoText(Memo.builder().mno(12L).memoText("어서오세요").build()));
	}
//	@org.junit.jupiter.api.Test
//	public void testSelectQuery() {
//		Pageable pageable = PageRequest.of(0, 10,Sort.by("mno").descending());
//		Page<Memo>page = memoRepository.ListwithPaging(50L, pageable);
//		for(Memo m : page) {
//			System.out.println(m);
//		}
		
	//}
//	@org.junit.jupiter.api.Test
	public void testSelectObjectQuery() {
		Pageable pageable = PageRequest.of(0, 10,Sort.by("mno").descending());
		Page<Object[]>page = memoRepository.ListwithPaging(50L, pageable);
		for(Object[] obj : page) {
			System.out.println(Arrays.toString(obj));
		}
		
	}
//	@org.junit.jupiter.api.Test
	public void testNativeSQL() {
		List<Object[]> list = memoRepository.getNativeResult();
		for(Object[] obj : list) {
			System.out.println(Arrays.toString(obj));
		}
		
	}
	@PersistenceContext
	EntityManager em;
	@org.junit.jupiter.api.Test
	public void queryDslTest() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
			//Q뒤에 Entity이름이 안만들어지면 컴파일이 제대로 안만들어짐
			//entity 클래스 이름 앞에 Q 가 붙는 클래스를 추가로 생성해서 쿼리를 생성
			QMemo qMemo = QMemo.memo;
			//mno 가 12인 데이터를 조회하는 query 생성
			JPAQuery<Memo> query = queryFactory.selectFrom(qMemo).where(qMemo.mno.eq(12L));
			//쿼리
			List<Memo> list = query.fetch();
			for(Memo memo : list) {
				System.out.println(memo);
			}
	}
	
	
	
}
