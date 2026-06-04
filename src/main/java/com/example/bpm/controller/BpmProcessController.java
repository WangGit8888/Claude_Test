package com.example.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bpm.entity.BpmProcess;
import com.example.bpm.service.BpmProcessService;
import com.example.common.dto.Condition;
import com.example.common.dto.RequestDTO;
import com.example.common.model.ApiResponseBody;
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
    @Operation(summary = "分页查询流程")
    public ApiResponseBody<Page<BpmProcess>> page(RequestDTO requestDTO) {
        return ApiResponseBody.success(service.page(requestDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "按ID查询流程")
    public ApiResponseBody<BpmProcess> getById(@PathVariable @Parameter(description = "流程ID") Long id) {
        return ApiResponseBody.success(service.getById(id));
    }

    @PostMapping
    @Operation(summary = "新增或修改流程（有id则修改，无id则新增）")
    public ApiResponseBody<Boolean> saveOrUpdate(@RequestBody BpmProcess entity) {
        return ApiResponseBody.success(service.saveOrUpdate(entity));
    }

    @DeleteMapping
    @Operation(summary = "批量删除流程")
    public ApiResponseBody<Boolean> delete(@RequestBody @Parameter(description = "ID列表") List<Long> ids) {
        return ApiResponseBody.success(service.delete(ids));
    }

    @GetMapping("/export")
    @Operation(summary = "导出Excel")
    public void exportExcel(HttpServletResponse response,@RequestBody Condition condition) throws IOException {
        service.exportExcel(response,condition);
    }

    @PostMapping("/import")
    @Operation(summary = "导入Excel")
    public ApiResponseBody<String> importExcel(@RequestParam @Parameter(description = "Excel文件") MultipartFile file) throws IOException {
        int count = service.importExcel(file);
        return ApiResponseBody.success("导入成功，共处理 " + count + " 条记录");
    }
}
