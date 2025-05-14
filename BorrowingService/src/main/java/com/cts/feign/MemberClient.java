package com.cts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.dto.MemberDTO;

@FeignClient(name = "MEMBER-SERVICE")
public interface MemberClient {
    @GetMapping("/members/{id}")
    Object getMember(@PathVariable Long id);
}