package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.MemberDTO;
import com.cts.model.Member;
import com.cts.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
 
    @Autowired
    private MemberService memberService;
 
    @PostMapping("/addmember")
    public ResponseEntity<Member> addMember(@RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.addMember(dto));
    }
 
    @PutMapping("/upadate/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.updateMember(id, dto));
    }
 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
 
    @GetMapping("/getmemberbyid/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }
 
    @GetMapping("/getallmember")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }
 
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> isMemberExists(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.isMemberExists(id));
    }
 
    @PatchMapping("/status/{id}")
    public ResponseEntity<Member> changeMembershipStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(memberService.changeMembershipStatus(id, status));
    }
}