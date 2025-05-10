package com.cts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service")
public interface MemberClient {
   @GetMapping("/members/exists/{id}")
   boolean isMemberExist(@PathVariable Long id);

   @GetMapping("/members/active/{id}")
   boolean isMemberActive(@PathVariable Long id);
}