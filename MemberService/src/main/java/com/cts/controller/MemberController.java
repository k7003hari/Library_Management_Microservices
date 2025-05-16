package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Member;
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

    @Autowired
    private MemberService memberService;

    @PostMapping("/regMember")
    public Member registerMember(@RequestBody Member member) {
        log.info("POST /members");
        return memberService.registerMember(member);
    }

    @PutMapping("update/{id}")
    public Member updateMember(@PathVariable Long id, @Valid @RequestBody Member member) {
        log.info("PUT /members/{}", id);
        return memberService.updateMember(id, member);
    }

    @GetMapping("/getMember/{id}/{Email}")
    public Member getMember(@PathVariable("id") Long id, @PathVariable("Email") String email) {
        log.info("GET /members/{}", id);
        return memberService.getMember(id,email);
    }
    
    @GetMapping("/getallMember")
	public List<Member> getAllMember() {
		return memberService.getAllMember();
	}


    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        log.info("GET /members/{}", memberId);
        return memberService.getMemberById(memberId);
    }
}
