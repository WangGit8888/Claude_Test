package com.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@RestController("/mvc")
@Controller
public class MvcController {

    @RequestMapping("/debug/springmvc")
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ok");//对应到/WEB-INF/page/ok.jsp
        modelAndView.addObject("name", "老韩");//在model中放入数据
        return modelAndView;
    }
}
