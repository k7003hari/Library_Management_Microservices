package com.cts.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.MemberDTO;
import com.cts.exception.MemberNotFoundException;
import com.cts.model.Member;
import com.cts.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
 

    private MemberRepository memberRepository;
 
    @Override
    public MemberDTO registerMember(MemberDTO dto) {
        log.info("Registering new member: {}", dto.getEmail());
 
        Member member = new Member();
        BeanUtils.copyProperties(dto, member);
        Member saved = memberRepository.save(member);
 
        MemberDTO result = new MemberDTO();
        BeanUtils.copyProperties(saved, result);
        return result;
    }
 
    @Override
    public MemberDTO updateMember(Long memberId, MemberDTO dto) {
        log.info("Updating member: {}", memberId);
 
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException("Member not found"));
 
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setAddress(dto.getAddress());
        member.setMembershipStatus(dto.getMembershipStatus());
        Member updated = memberRepository.save(member);
 
        MemberDTO result = new MemberDTO();
        BeanUtils.copyProperties(updated, result);
        return result;
    }
 
    @Override
    public MemberDTO getMember(Long memberId, String requesterEmail) {
        log.info("Fetching member {} for requester {}", memberId, requesterEmail);
 
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException("Member not found"));
 
        if (!member.getEmail().equals(requesterEmail)) {
            throw new MemberNotFoundException("Access Denied: Not your profile");
        }
 
        MemberDTO dto = new MemberDTO();
        BeanUtils.copyProperties(member, dto);
        return dto;
    }
 
    @Override
    public MemberDTO updateMembershipStatus(Long memberId, String membershipStatus, String requesterEmail) {
        log.info("Updating membership status for member {} to {}", memberId, membershipStatus);
 
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException("Member not found"));
 
        if (!member.getEmail().equals(requesterEmail)) {
            throw new MemberNotFoundException("Access Denied: Not your profile");
        }
 
        member.setMembershipStatus(membershipStatus);
        Member updated = memberRepository.save(member);
 
        MemberDTO result = new MemberDTO();
        BeanUtils.copyProperties(updated, result);
        return result;
    }
}
 