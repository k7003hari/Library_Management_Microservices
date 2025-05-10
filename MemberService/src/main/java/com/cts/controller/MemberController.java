package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.MemberDTO;
import com.cts.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/addmember")
	public MemberDTO addMember(@RequestBody @Valid MemberDTO memberDTO) {
		log.info("Received request to add member");
		return memberService.addMember(memberDTO);
	}

	@PutMapping("/update/{id}")
	public MemberDTO updateMember(@PathVariable Long id, @RequestBody @Valid MemberDTO memberDTO) {
		log.info("Received request to update member with ID: {}", id);
		return memberService.updateMember(id, memberDTO);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteMember(@PathVariable Long id) {
		log.info("Received request to delete member with ID: {}", id);
		memberService.deleteMember(id);
		return "Member deleted successfully.";
	}

	@GetMapping("/getMemberById/{id}")
	public MemberDTO getMemberById(@PathVariable Long id) {
		log.info("Received request to fetch member by ID: {}", id);
		return memberService.getMemberById(id);
	}

	@GetMapping("/getAllMembers/all")
	public List<MemberDTO> getAllMembers() {
		log.info("Received request to fetch all members");
		return memberService.getAllMembers();
	}

	@GetMapping("/search/name/{name}")
	public List<MemberDTO> searchByName(@PathVariable String name) {
		log.info("Received request to search members by name: {}", name);
		return memberService.searchByName(name);
	}

	@GetMapping("/exists/{id}")
	public boolean isMemberExist(@PathVariable Long id) {
		log.info("Received request to check if member exists: ID = {}", id);
		return memberService.isMemberExist(id);
	}

	@GetMapping("/active/{id}")
	public boolean isMemberActive(@PathVariable Long id) {
		log.info("Received request to check if member is active: ID = {}", id);
		return memberService.isMemberActive(id);
	}
}