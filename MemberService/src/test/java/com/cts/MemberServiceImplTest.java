package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.dto.MemberDTO;
import com.cts.exception.MemberNotFoundException;
import com.cts.model.Member;
import com.cts.repository.MemberRepository;
import com.cts.service.MemberServiceImpl;

public class MemberServiceImplTest {

   @Mock
   private MemberRepository memberRepository;

   @InjectMocks
   private MemberServiceImpl memberService;

   private Member member;

   @BeforeEach
   public void setUp() {
       MockitoAnnotations.openMocks(this);
       member = new Member();
       member.setMemberId(1L);
       member.setName("Alice");
       member.setEmail("alice@example.com");
       member.setPhone("1234567890");
       member.setAddress("Street 1");
       member.setMembershipStatus("Active");
   }

   @Test
   public void testAddMember() {
       MemberDTO dto = new MemberDTO();
       dto.setName("Alice");
       dto.setEmail("alice@example.com");
       dto.setPhone("1234567890");
       dto.setAddress("Street 1");
       dto.setMembershipStatus("Active");

       when(memberRepository.save(any(Member.class))).thenReturn(member);
       Member saved = memberService.addMember(dto);

       assertEquals("Alice", saved.getName());
       verify(memberRepository, times(1)).save(any(Member.class));
   }

   @Test
   public void testGetMemberById_Found() {
       when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
       Member found = memberService.getMemberById(1L);

       assertEquals(1L, found.getMemberId());
       assertEquals("Alice", found.getName());
   }

   @Test
   public void testGetMemberById_NotFound() {
       when(memberRepository.findById(2L)).thenReturn(Optional.empty());
       assertThrows(MemberNotFoundException.class, () -> memberService.getMemberById(2L));
   }

   @Test
   public void testDeleteMember() {
       when(memberRepository.existsById(1L)).thenReturn(true);
       doNothing().when(memberRepository).deleteById(1L);

       memberService.deleteMember(1L);
       verify(memberRepository, times(1)).deleteById(1L);
   }

   @Test
   public void testChangeMembershipStatus() {
       when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
       member.setMembershipStatus("Inactive");

       Member updated = memberService.changeMembershipStatus(1L, "Inactive");

       assertEquals("Inactive", updated.getMembershipStatus());
   }

   @Test
   public void testIsMemberExists() {
       when(memberRepository.existsById(1L)).thenReturn(true);
       assertTrue(memberService.isMemberExists(1L));
   }

   @Test
   public void testGetAllMembers() {
       List<Member> list = Arrays.asList(member);
       when(memberRepository.findAll()).thenReturn(list);

       List<Member> members = memberService.getAllMembers();
       assertEquals(1, members.size());
       assertEquals("Alice", members.get(0).getName());
   }
}