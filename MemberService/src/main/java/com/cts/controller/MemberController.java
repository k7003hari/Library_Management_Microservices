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

import com.cts.model.Member;
import com.cts.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
 
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
 
    @PostMapping("addmember")
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }
 
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }
 
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
