package com.cts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.exception.MemberNotFoundException;
import com.cts.model.Member;
import com.cts.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public Member registerMember(Member member) {
		log.info("Registering new member: {}", member.getEmail());
		Member saved = memberRepository.save(member);
		log.info("Member registered successfully with ID: {}", saved.getMemberId());
		return saved;
	}

	@Override
	public Member updateMember(Long memberId, Member member) {
		log.info("Updating member: {}", memberId);
		Member existingMember = memberRepository.findById(memberId).orElseThrow(() -> {
			log.error("Update failed: Member not found with ID: {}", memberId);
			return new MemberNotFoundException("Member not found with ID: " + memberId);
		});
		existingMember.setName(member.getName());
		existingMember.setEmail(member.getEmail());
		existingMember.setPhone(member.getPhone());
		existingMember.setAddress(member.getAddress());
		existingMember.setMembershipStatus(member.getMembershipStatus());
		memberRepository.save(existingMember);
		log.info("Member updated successfully with ID: {}", memberId);
		return existingMember;
	}
	
	@Override
	public List<Member> getAllMember() {
	    log.debug("Fetching all Member");
	    return memberRepository.findAll();
	}

	@Override
	public Member getMember(Long memberId, String requesterEmail) {
		log.info("Fetching member {} for requester {}", memberId, requesterEmail);
		Member member = memberRepository.findById(memberId).orElseThrow(() -> {
			log.error("Member not found with ID: {}", memberId);
			return new MemberNotFoundException("Member not found with ID: " + memberId);
		});
		if (!member.getEmail().equals(requesterEmail)) {
			throw new MemberNotFoundException("Access Denied: Not your profile");
		}
		return member;
	}

	@Override
	public Member getMemberById(Long memberId) {
		log.info("Fetching member by ID {}", memberId);
		return memberRepository.findById(memberId).orElseThrow(() -> {
			log.error("Member not found with ID: {}", memberId);
			return new MemberNotFoundException("Member not found with ID: " + memberId);
		});
	}
}
