package org.instagram.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service")
public interface UserServiceClient {
    @GetMapping(value="/auth/getid/{username}")
    public long getId(@PathVariable String username);
}
