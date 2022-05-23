package com.tistory.jacobcloud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="memo")
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memo extends BaseEntity {
	// 자바가 랜덤한 문자열을 넣어줌 아이디를 만들떄 (primary key 이기 떄문에 겹치면 안되기때문에)	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GenericGenerator(name="system-uuid",strategy = "uuid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gno;
	
	@Column(length=100 , nullable = false)
	//제목
	private String title;
	@Column(length=1500 , nullable = false)
	//내용
	private String content;
	@Column(length=100 , nullable = false)
	//작성자
	private String writer ;
	
	//title을 변경해주는 메서드
	
	public void changeTitle(String title) {
		this.title = title.trim();
		
	}
	public void changeContent(String content) {
		this.content=content.trim();
	}
			
}
