package com.cts.service;

import java.util.List;

import com.cts.model.Member;

public interface MemberService {

	Member registerMember(Member member);

	Member updateMember(Long memberId, Member member);

	Member getMember(Long memberId, String requesterEmail);
	
	Member getMemberById(Long memberId);

	List<Member> getAllMember();
	
	
	     
}