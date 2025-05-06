package com.cts.service;

import java.util.List;

import com.cts.dto.MemberDTO;
import com.cts.model.Member;

public interface MemberService {
    Member addMember(MemberDTO dto);
    Member updateMember(Long memberId, MemberDTO dto);
    void deleteMember(Long memberId);
    Member getMemberById(Long memberId);
    List<Member> getAllMembers();
 
    boolean isMemberExists(Long memberId);
    Member changeMembershipStatus(Long memberId, String newStatus);
}