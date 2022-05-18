package com.tistory.jacobcloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//데이터베이스의 테이블과 연동
@Entity
//테이블 이름 설정
@Table(name="tbl_memo")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//build 라는 메서드를 이용해서 매개변수가 없는 생성자(default constructor)를 이용해서
//인스턴스를 생성하고 속성을 호출해서 값을 설정해주도록 해주는 설정
@Builder
public class Memo {
	@Id
	
	//기본키는 설정하는 데이터베이스 옵션에 따라 자동생성
	//Mysql 연결하면 auto_increment , oracle 연결하면 sequence 사용
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mno;
	@Column(length=200,nullable = false)
	private String memoText;
	
}
