package com.cts.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
@FeignClient(name = "member-service", url = "http://localhost:8082/api/members")
public interface MemberServiceClient {
 
    @GetMapping("/{memberId}/exists")
    boolean memberExists(@PathVariable("memberId") Long memberId);
}