package com.example.zsk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zsk.entity.KnowledgeBase;
import com.example.zsk.service.KnowledgeBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge")
@Tag(name = "知识库", description = "知识库的增删改查接口")
public class KnowledgeBaseController {

    @Autowired
    private KnowledgeBaseService service;

    @GetMapping
    @Operation(summary = "查询知识列表")
    public List<KnowledgeBase> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "按ID查询知识")
    public KnowledgeBase getById(@PathVariable @Parameter(description = "知识ID") Long id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "新增知识")
    public boolean save(@RequestBody KnowledgeBase entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改知识")
    public boolean update(@PathVariable @Parameter(description = "知识ID") Long id,
                          @RequestBody KnowledgeBase entity) {
        entity.setId(id);
        return service.updateById(entity);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除知识")
    public boolean delete(@PathVariable @Parameter(description = "知识ID") Long id) {
        return service.removeById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "按标题模糊搜索")
    public List<KnowledgeBase> search(@RequestParam @Parameter(description = "关键字") String keyword) {
        QueryWrapper<KnowledgeBase> qw = new QueryWrapper<>();
        qw.like("title", keyword);
        qw.orderByDesc("create_date");
        return service.list(qw);
    }
}
