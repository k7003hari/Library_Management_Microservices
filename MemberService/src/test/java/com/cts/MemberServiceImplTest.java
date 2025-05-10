package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.controller.MemberController;
import com.cts.dto.MemberDTO;
import com.cts.repository.MemberRepository;
import com.cts.service.MemberService;

class MemberControllerTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	@InjectMocks
	private MemberController memberController;

	private AutoCloseable closeable;

	private MemberDTO member;

	@BeforeEach
	void setUp() {
		closeable = MockitoAnnotations.openMocks(this);
		member = new MemberDTO(1L, "John", "john@example.com", null, null, true);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void testAddMember() {
		when(memberService.addMember(member)).thenReturn(member);
		assertEquals("John", memberController.addMember(member).getName());
	}

	@Test
	void testGetMemberById() {
		when(memberService.getMemberById(1L)).thenReturn(member);
		assertEquals(1L, memberController.getMemberById(1L).getId());
	}

	@Test
	void testIsMemberExist() {
		when(memberService.isMemberExist(1L)).thenReturn(true);
		assertTrue(memberController.isMemberExist(1L));
	}

	@Test
	void testIsMemberActive() {
		when(memberService.isMemberActive(1L)).thenReturn(true);
		assertTrue(memberController.isMemberActive(1L));
	}
}