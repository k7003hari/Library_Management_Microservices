package com.cts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.MemberDTO;

@FeignClient(name = "MEMBERSERVICE", path = "/members")
public interface MemberClient {

    @GetMapping("/{memberId}")
    MemberDTO getMemberById(@PathVariable("memberId") Long memberId);

    @GetMapping("/getMember/{id}/{Email}")
    MemberDTO getMember(@PathVariable("id") Long id, @PathVariable("Email") String email);
}
