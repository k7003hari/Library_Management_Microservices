package com.cts;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.exception.MemberNotFoundException;
import com.cts.model.Member;
import com.cts.repository.MemberRepository;
import com.cts.service.MemberService;

import jakarta.transaction.Transactional;
//
//@SpringBootTest
//@Transactional // ensures test data is rolled back after each test
//class MemberServiceTests {
// 
//    @Autowired
//    private MemberService memberService;
// 
//    @Autowired
//    private MemberRepository memberRepository;
// 
//    @Test
//    void testRegisterMember() {
//        MemberDTO dto = new MemberDTO();
//        dto.setName("Alice");
//        dto.setEmail("alice@example.com");
//        dto.setPhone("9999999999");
//        dto.setAddress("Bangalore");
//        dto.setMembershipStatus("ACTIVE");
// 
//        MemberDTO saved = memberService.registerMember(dto);
// 
//        Assertions.assertNotNull(saved.getMemberId());
//        Assertions.assertEquals("alice@example.com", saved.getEmail());
//    }
// 
//    @Test
//    void testUpdateMember() {
//        Member member = new Member(null, "Bob", "bob@example.com", "8888888888", "Chennai", "ACTIVE");
//        memberRepository.save(member);
// 
//        MemberDTO dto = new MemberDTO();
//        dto.setName("Bob Updated");
//        dto.setEmail("bob@example.com");
//        dto.setPhone("8888888888");
//        dto.setAddress("Updated Address");
//        dto.setMembershipStatus("INACTIVE");
// 
//        MemberDTO updated = memberService.updateMember(member.getMemberId(), dto);
//        Assertions.assertEquals("Bob Updated", updated.getName());
//    }
// 
//    @Test
//    void testUpdateMembershipStatus() {
//        Member member = new Member(null, "Carol", "carol@example.com", "7777777777", "Hyderabad", "ACTIVE");
//        memberRepository.save(member);
// 
//        MemberDTO updated = memberService.updateMembershipStatus(member.getMemberId(), "INACTIVE", "carol@example.com");
//        Assertions.assertEquals("INACTIVE", updated.getMembershipStatus());
//    }
// 
//    @Test
//    void testAccessDenied() {
//        Member member = new Member(null, "David", "david@example.com", "6666666666", "Delhi", "ACTIVE");
//        memberRepository.save(member);
// 
//        Assertions.assertThrows(MemberNotFoundException.class, () ->
//                memberService.getMember(member.getMemberId(), "unauthorized@example.com"));
//    }
//}