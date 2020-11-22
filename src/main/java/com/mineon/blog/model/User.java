package com.mineon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가 Mysql에 테이블이 생성됨
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 30)
	private String username;
	
	@Column(nullable = false,length = 100)
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다.
	
	@CreationTimestamp
	private Timestamp crateDate;
}
