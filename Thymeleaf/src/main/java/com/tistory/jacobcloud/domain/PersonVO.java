package com.tistory.jacobcloud.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonVO {
	private Long num;
	private String name;
	private String ssn;
	private Long asset;
	private Long debt;
	private String properties;
	private String nickname;
	private LocalDateTime birthtime;
	
}
