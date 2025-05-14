package com.cts.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@Validated

public class MemberController {

	private MemberService memberService;

	@PostMapping
	public MemberDTO registerMember(@Valid @RequestBody MemberDTO memberDTO) {
		log.info("POST /api/members");
		return memberService.registerMember(memberDTO);
	}

	@PutMapping("/{id}")
	public MemberDTO updateMember(@PathVariable Long id, @Valid @RequestBody MemberDTO memberDTO,
			@RequestHeader("X-Email") String email) {
		log.info("PUT /api/members/{}", id);
		return memberService.updateMember(id, memberDTO);
	}

	@GetMapping("/{id}")
	public MemberDTO getMember(@PathVariable Long id, @RequestHeader("X-Email") String email) {
		log.info("GET /api/members/{}", id);
		return memberService.getMember(id, email);
	}

	@PutMapping("/{id}/status")
	public MemberDTO updateMembershipStatus(@PathVariable Long id, @RequestParam String membershipStatus,
			@RequestHeader("X-Email") String email) {
		log.info("PUT /api/members/{}/status", id);
		return memberService.updateMembershipStatus(id, membershipStatus, email);
	}
}