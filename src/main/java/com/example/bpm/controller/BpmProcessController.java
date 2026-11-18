package com.example.bpm.controller;


import com.example.utils.PortHolder;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/bpm/process")
@Tag(name = "流程管理", description = "流程信息的增删改查接口")
public class BpmProcessController {

    @Autowired
    private PortHolder portHolder;

    @GetMapping("/test")
    public String test() {
        Integer i = 200;

        System.out.println("当前实例端口: " + portHolder.getPort());

        return "sucess";
    }
}
