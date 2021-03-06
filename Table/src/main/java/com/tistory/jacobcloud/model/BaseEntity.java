package com.tistory.jacobcloud.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
//테이블로 생성할 필요가 없음
@MappedSuperclass
//데이터베이스 작업을 감시
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
abstract public class BaseEntity {
	//생성한 시간을 저장하는데 컬럼 이름은 regdated 이고 수정할 수 없도록 생성
	@CreatedDate
	@Column(name="regdate", updatable=false)
	private LocalDateTime regdate;
	//수정한 시간을 저장하는데 컬럼 이름은 moddate이고 수정할 수 없도록 생성
	@LastModifiedDate
	@Column(name="moddate", updatable=false)
	private LocalDateTime moddate;
	
}
