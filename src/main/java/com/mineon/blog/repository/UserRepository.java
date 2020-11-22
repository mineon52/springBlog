package com.mineon.blog.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mineon.blog.model.User;

//DAO
//자동으로 Bean등록이된다.
//@Repository 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{


}
