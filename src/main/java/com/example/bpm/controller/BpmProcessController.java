package com.example.bpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bpm.entity.BpmProcess;
import com.example.bpm.service.BpmProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/bpm/process")
@Tag(name = "流程管理", description = "流程信息的增删改查接口")
public class BpmProcessController {

    @Autowired
    private BpmProcessService service;

    @GetMapping
    @Operation(summary = "查询流程列表")
    public List<BpmProcess> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "按ID查询流程")
    public BpmProcess getById(@PathVariable @Parameter(description = "流程ID") Long id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "新增或修改流程（有id则修改，无id则新增）")
    public boolean saveOrUpdate(@RequestBody BpmProcess entity) {
        return service.saveOrUpdate(entity);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除流程")
    public boolean delete(@PathVariable @Parameter(description = "流程ID") Long id) {
        return service.removeById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "按流程名称模糊搜索")
    public List<BpmProcess> search(@RequestParam @Parameter(description = "关键字") String keyword) {
        QueryWrapper<BpmProcess> qw = new QueryWrapper<>();
        qw.like("process_name", keyword);
        qw.orderByDesc("create_date");
        return service.list(qw);
    }

    @GetMapping("/export")
    @Operation(summary = "导出Excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        service.exportExcel(response);
    }

    @PostMapping("/import")
    @Operation(summary = "导入Excel")
    public String importExcel(@RequestParam @Parameter(description = "Excel文件") MultipartFile file) throws IOException {
        int count = service.importExcel(file);
        return "导入成功，共处理 " + count + " 条记录";
    }
}
