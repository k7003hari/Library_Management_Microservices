package com.cts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.dto.MemberDTO;

@FeignClient(name = "member-service") // name is the name of the member service
public interface MemberClient {

	// This method will call the Member Service's endpoint to get member by email
	@GetMapping("/members/email")
	MemberDTO getMemberByEmail(@RequestParam("email") String email);
}
