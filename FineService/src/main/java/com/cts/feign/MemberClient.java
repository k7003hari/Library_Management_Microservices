package com.cts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.MemberDTO;

@FeignClient(name = "member-service")
public interface MemberClient {

	@GetMapping("/members/{memberId}")
	MemberDTO getMemberById(@PathVariable("memberId") Long memberId);
}