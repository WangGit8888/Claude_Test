package com.example.bpm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bpm.entity.BpmProcess;
import com.example.common.dto.Condition;
import com.example.common.dto.RequestDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface BpmProcessService extends IService<BpmProcess> {

    /**
     * 分页查询
     */
    Page<BpmProcess> page(RequestDTO requestDTO);

    /**
     * 批量删除
     */
    boolean delete(List<Long> ids);

    /**
     * 导出 Excel
     */
    void exportExcel(HttpServletResponse response, Condition condition) throws IOException;

    /**
     * 导入 Excel，返回成功导入条数
     */
    int importExcel(MultipartFile file) throws IOException;
}
