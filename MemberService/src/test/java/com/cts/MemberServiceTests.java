package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.model.Member;
import com.cts.service.MemberService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional // ensures test data is rolled back after each test
class MemberServiceTests {
 
    @Autowired
    private MemberService memberService;
 
    
    private Member sampleMember; 
    
     @BeforeEach
    void init() {
        sampleMember = Member.builder().name("Rahul").email("rahul.gmail.com").phone("9177444233").address("789-chennai").membershipStatus("Active").build();
    }
     @Test
     void testAddMember()
     {
    	 Member saved=memberService.registerMember(sampleMember);
    	 assertNotNull(saved.getMemberId());
         assertEquals("Clean Code", saved.getName());
     }
   
}