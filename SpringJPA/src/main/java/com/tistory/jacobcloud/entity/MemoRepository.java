package com.tistory.jacobcloud.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
		
//	4)Memo Entity 에서 mno 값이 70에서 80 사이 인 데이터를 조회
	
	List<Memo> findByMnoBetween(Long from , Long to);
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from , Long to);
	// 페이징 처리를 할떄
	List<Memo> findByMnoBetween( Long from , Long to,Pageable pageable);
	//mno가 매개변수보다 작은 데이터 삭제
	void deleteByMnoLessThan(Long num);
	//mno을 가지고 데이터를 찾아서 memoText를 수정하는 메서드
	@Query("update Memo m set m.memoText =:#{#memoText} where m.mno = :#{#mno}")
		// @query에서 수행하는 문장이 select 가 아닐떄 원본에 반영하도록 하기 위해서 설정
	@Modifying
	//이 메소드 안에서 이루어지는 모든 작업은 하나의 트랜잭션으로 수행 되도록 해주는 annotation
	//Repository에 적용하지 않고 서비스나 컨트로럴에서함
	@Transactional
	int updateMemoText(@Param("mno") Long mno ,@Param("memoText")String memoText);
	
	@Query("update Memo m set m.memoText =:#{#param.memoText} where m.mno = :#{#param.mno}")
	@Modifying
	@Transactional
	int updateMemoText(@Param("param") Memo memo);
	// 이렇게 작성했을떄 에러가 발생하면(SpringJPA 하위 버전을 사용하는 경우
//	@Query("select m from Memo m where m.mno > :mno")
	@Query(value ="select m.mno,m.memoText,CURRENT_DATE from Memo m where m.mno > :mno", countQuery="select count(m) from Memo m where m.mno >:mno")
	Page<Object[]>ListwithPaging(@Param("mno") Long mno , Pageable pageable);
	
	//nativeQuery 사용
	@Query(value="select * from tbl_memo where mno>0",nativeQuery=true)
	List<Object[]> getNativeResult();
	
	
	
}
