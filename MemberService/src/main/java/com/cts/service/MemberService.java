package com.cts.service;

import java.util.List;

import com.cts.dto.MemberDTO;

public interface MemberService {
	    MemberDTO addMember(MemberDTO memberDTO);
	    MemberDTO updateMember(Long id, MemberDTO memberDTO);
	    void deleteMember(Long id);
	    MemberDTO getMemberById(Long id);
	    List<MemberDTO> getAllMembers();
	    List<MemberDTO> searchByName(String name);
	    boolean isMemberExist(Long id);
	    boolean isMemberActive(Long id);
	}