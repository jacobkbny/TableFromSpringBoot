package com.tistory.jacobcloud.table.persistence;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.jacobcloud.model.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>, QuerydslPredicateExecutor<Memo> 
{
		//ORM
		List<Memo> findByTitle(String title);
	
		//직접 쿼리를 작성하는 방법
		@Transactional
		@Modifying
		@Query("update Memo m set m.title = :title , m.content = :content where m.gno = :gno")
			int updateMemo(@Param("title") String title,
					@Param("content") String content,
					@Param("gno") Long gno);
}

