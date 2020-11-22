package com.mineon.blog.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mineon.blog.model.RoleType;
import com.mineon.blog.model.User;
import com.mineon.blog.repository.UserRepository;

@RestController
public class DummyController {
	
	@Autowired //의존성주입
	private UserRepository userRepository;
	
	
	@PostMapping("/dummy/join")
	public String join (User user) {
		
		System.out.println("DummyController: username="+user.getUsername());
		System.out.println("DummyController: password="+user.getPassword());
		System.out.println("DummyController: email="+user.getEmail());
		System.out.println("DummyController: role="+user.getRole());
		System.out.println("DummyController: crateDate="+user.getCrateDate());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입이 되었습니다. ";
	}
	
	@GetMapping("/dummy/list")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user/page")
	public Page<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		//Page<User> user = userRepository.findAll(pageable);
		return null;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//			@Override
//			public IllegalArgumentException get() {
//				// TODO Auto-generated method stub
//				return new IllegalArgumentException();
//			}
//		});
		
		//람다식 
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException();
		});
		
		//스프링부트 = MessageConverter가 응답시에 
		//자바오브젝트를 리턴하면 user오브젝트를 json오브젝트로 변환함
		return user;
	}
	
	@PutMapping("/dummy/user/{id}")
	@Transactional
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		System.out.println("id = "+ id);
		System.out.println("Password = "+ reqUser.getPassword());
		System.out.println("Email = "+ reqUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		//userRepository.save(user);
		
		return user;
		
	}
	
	@DeleteMapping("/dummy/delete/{id}")
	public String deleteUser(@PathVariable int id) {
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("삭제 실패했습니다.");
//		});
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e1) {
			return "삭제에 실패하였습니다. id = "+ id;
		}
		return id+ " 삭제되었습니다.";
	}
	
	
	
}
