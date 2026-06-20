package com.example.bpm.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/bpm/process")
@Tag(name = "流程管理", description = "流程信息的增删改查接口")
public class BpmProcessController {

    @GetMapping("/test")
    public String test() {
        Integer i = 200;
        for (int j = 0; j < i; j++) {
             log.info("发送日志!!!" + j);
        }
        return "sucess";
    }
}
