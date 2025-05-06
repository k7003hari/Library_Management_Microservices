package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.MemberNotFoundException;
import com.cts.model.Member;
import com.cts.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
 
    @Autowired
    private MemberRepository memberRepository;
 
    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }
 
    @Override
    public Member updateMember(Long id, Member member) {
        memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + id));
        member.setMemberId(id);
        return memberRepository.save(member);
    }
 
    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
 
    @Override
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException("Member not found: " + memberId));
    }
 
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
 
    @Override
    public Member updateMembershipStatus(Long memberId, String status) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException("Member not found: " + memberId));
        member.setMembershipStatus(status);
        return memberRepository.save(member);
    }
}