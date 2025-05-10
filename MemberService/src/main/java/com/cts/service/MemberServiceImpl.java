package com.cts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.dto.MemberDTO;
import com.cts.exception.DuplicateMemberException;
import com.cts.exception.MemberNotFoundException;
import com.cts.model.Member;
import com.cts.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	// Helper method to convert Entity to DTO
	private MemberDTO convertToDTO(Member member) {
		MemberDTO dto = new MemberDTO();
		dto.setId(member.getId());
		dto.setName(member.getName());
		dto.setEmail(member.getEmail());
		dto.setPhone(member.getPhone());
		dto.setMembershipType(member.getMembershipType());
		dto.setActive(member.isActive());
		return dto;
	}

	// Helper method to convert DTO to Entity
	private Member convertToEntity(MemberDTO dto) {
		Member member = new Member();
		member.setId(dto.getId());
		member.setName(dto.getName());
		member.setEmail(dto.getEmail());
		member.setPhone(dto.getPhone());
		member.setMembershipType(dto.getMembershipType());
		member.setActive(dto.getActive());
		return member;
	}

	@Override
	public MemberDTO addMember(MemberDTO memberDTO) {
		log.info("Attempting to add new member with email: {}", memberDTO.getEmail());

		if (memberRepository.existsByEmail(memberDTO.getEmail())) {
			throw new DuplicateMemberException("Member already exists with email: " + memberDTO.getEmail());
		}

		Member member = convertToEntity(memberDTO);
		Member saved = memberRepository.save(member);

		log.info("Member added successfully: ID = {}", saved.getId());
		return convertToDTO(saved);
	}

	@Override
	public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
		log.info("Attempting to update member with ID: {}", id);

		Member existing = memberRepository.findById(id)
				.orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + id));

		existing.setName(memberDTO.getName());
		existing.setPhone(memberDTO.getPhone());
		existing.setMembershipType(memberDTO.getMembershipType());
		existing.setActive(memberDTO.getActive());

		Member updated = memberRepository.save(existing);
		log.info("Member updated successfully: ID = {}", updated.getId());
		return convertToDTO(updated);
	}

	@Override
	public void deleteMember(Long id) {
		log.info("Attempting to delete member with ID: {}", id);

		if (!memberRepository.existsById(id)) {
			throw new MemberNotFoundException("Cannot delete. Member not found with ID: " + id);
		}

		memberRepository.deleteById(id);
		log.info("Member deleted successfully: ID = {}", id);
	}

	@Override
	public MemberDTO getMemberById(Long id) {
		log.info("Fetching member by ID: {}", id);

		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + id));

		return convertToDTO(member);
	}

	@Override
	public List<MemberDTO> getAllMembers() {
		log.info("Fetching all members");

		return memberRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<MemberDTO> searchByName(String name) {
		log.info("Searching members by name: {}", name);

		return memberRepository.findByNameContainingIgnoreCase(name).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isMemberExist(Long id) {
		log.info("Checking if member exists: ID = {}", id);
		return memberRepository.existsById(id);
	}

	@Override
	public boolean isMemberActive(Long id) {
		log.info("Checking if member is active: ID = {}", id);

		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + id));
		return member.isActive();
	}
}