package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.MemberDTO;
import com.cts.exception.MemberNotFoundException;
import com.cts.model.Member;
import com.cts.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Member addMember(MemberDTO dto) {
		Member member = new Member();
		member.setName(dto.getName());
		member.setEmail(dto.getEmail());
		member.setPhone(dto.getPhone());
		member.setAddress(dto.getAddress());
		member.setMembershipStatus(dto.getMembershipStatus());
		return memberRepository.save(member);
	}

	@Override
	public Member updateMember(Long memberId, MemberDTO dto) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new MemberNotFoundException("Member not found"));

		member.setName(dto.getName());
		member.setEmail(dto.getEmail());
		member.setPhone(dto.getPhone());
		member.setAddress(dto.getAddress());
		member.setMembershipStatus(dto.getMembershipStatus());
		return memberRepository.save(member);
	}

	@Override
	public void deleteMember(Long memberId) {
		if (!memberRepository.existsById(memberId)) {
			throw new MemberNotFoundException("Member not found");
		}
		memberRepository.deleteById(memberId);
	}

	@Override
	public Member getMemberById(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("Member not found"));
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	@Override
	public boolean isMemberExists(Long memberId) {
		return memberRepository.existsById(memberId);
	}

	@Override
	public Member changeMembershipStatus(Long memberId, String newStatus) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new MemberNotFoundException("Member not found"));
		member.setMembershipStatus(newStatus);
		return memberRepository.save(member);
	}
}