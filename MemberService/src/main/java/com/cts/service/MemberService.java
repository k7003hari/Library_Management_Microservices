package com.cts.service;

import com.cts.dto.MemberDTO;

public interface MemberService {

	MemberDTO registerMember(MemberDTO memberDTO);

	MemberDTO updateMember(Long memberId, MemberDTO memberDTO);

	MemberDTO getMember(Long memberId, String requesterEmail);

	MemberDTO updateMembershipStatus(Long memberId, String membershipStatus, String requesterEmail);
}