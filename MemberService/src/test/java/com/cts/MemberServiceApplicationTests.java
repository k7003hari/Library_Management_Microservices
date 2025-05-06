package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.exception.MemberNotFoundException;
import com.cts.model.Member;
import com.cts.repository.MemberRepository;
import com.cts.service.MemberService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MemberServiceApplicationTests {
 
    @Mock
    private MemberRepository memberRepository;
 
    @InjectMocks
    private MemberService memberService;
 
    @Test
    public void testAddMember() {
        Member member = new Member(null, "John", "john@email.com", "123", "Address", "Active");
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        Member result = memberService.addMember(member);
        assertEquals("John", result.getName());
    }
 
    @Test
    public void testUpdateMember_NotFound() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());
        Member member = new Member();
        assertThrows(MemberNotFoundException.class, () -> memberService.updateMember(1L, member));
    }
}