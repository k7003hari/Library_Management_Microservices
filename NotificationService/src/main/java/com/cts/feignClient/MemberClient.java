package com.cts.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "memberService")
public interface MemberClient {

	@GetMapping("/members/exists/{id}")
	boolean isMemberExist(@PathVariable("id") Long memberId);
}