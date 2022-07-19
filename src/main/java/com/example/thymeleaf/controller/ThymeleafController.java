package com.example.thymeleaf.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
	
	Logger logger = LoggerFactory.getLogger(ThymeleafController.class);
	
	@GetMapping("/text-basic")
	public String testBasic(Model model) {
		logger.info("text-basic");
		model.addAttribute("data", "Hello Spring");
		return "/text-basic";
	}
	@GetMapping("/text-unescaped")
	public String textUnescaped(Model model) {
		logger.info("text-unescaped");
		model.addAttribute("data", "Hello <b>Spring</b>");
		return "/text-unescaped";
	}
	@GetMapping("/variable")
	public String variable(Model model) {
		logger.info("variable");
		Member member1 = new Member("길동", 25);
		Member member2 = new Member("순희", 30);
		List<Member> list = new ArrayList<>();
		list.add(member1);
		list.add(member2);
		Map<String, Member>memberMap = new HashMap<>();
		memberMap.put("member1", member1);
		memberMap.put("member2", member2);
		model.addAttribute("member1", member1);
		model.addAttribute("members", list);
		model.addAttribute("memberMap", memberMap);
		return "/variable";
	}
	@GetMapping("/basic-object")
	public String basicObject(HttpSession session) {
		session.setAttribute("sessionData", "Hello Spring");
		return "/basic-object";
	}
	@GetMapping("/date")
	public String date(Model model) {
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "/date";
	}
	
	@GetMapping("/link")
	public String link(Model model) {
		model.addAttribute("data1", "data1");
		model.addAttribute("data2", "data2");
		return "/link";
	}
	
	@GetMapping("/each")
	public String each(Model model) {
		List<Member> members = new ArrayList<>();
		members.add(new Member("홍길동", 30));
		members.add(new Member("이영희", 25));
		members.add(new Member("김순희", 35));
		members.add(new Member("강철수", 22));
		model.addAttribute("members", members);
		return "/each";
	}
	
	@GetMapping("/literal")
	public String literal(Model model) {
		model.addAttribute("data","Spring!");
		return "/literal";
	}
	
	@GetMapping("/operation")
	public String operation() {
		return "/operation";
	}
	
	static class Member {
		private String name;
		private int age;
		
		public Member(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
		public int getAge() {return age;}
		public void setAge(int age) {this.age = age;}

		@Override
		public String toString() {
			return "Member [name=" + name + ", age=" + age + "]";
		}
	}
}
