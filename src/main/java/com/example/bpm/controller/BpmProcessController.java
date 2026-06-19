package com.example.bpm.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bpm/process")
@Tag(name = "流程管理", description = "流程信息的增删改查接口")
public class BpmProcessController {

    @GetMapping("/test")
    public String test() {
        return "sucess";
    }
}
