package com.cts.service;

import java.util.List;

import com.cts.model.Member;

public interface MemberService {
    Member addMember(Member member);
    Member updateMember(Long memberId, Member member);
    void deleteMember(Long memberId);
    Member getMemberById(Long memberId);
    List<Member> getAllMembers();
    Member updateMembershipStatus(Long memberId, String status);
}