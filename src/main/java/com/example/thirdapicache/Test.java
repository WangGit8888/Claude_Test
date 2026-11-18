package com.example.thirdapicache;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("thirdApi")
@RequiredArgsConstructor
public class Test {

  private final ThirdApiService thirdApiService;

    @GetMapping("getValue")
    public String getValue(@Param("key") String key) {
       return thirdApiService.queryEnterprise(key);
    }
}
