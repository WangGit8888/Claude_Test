package com.example.bpm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bpm.entity.BpmProcess;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BpmProcessService extends IService<BpmProcess> {

    /**
     * 导出 Excel
     */
    void exportExcel(HttpServletResponse response) throws IOException;

    /**
     * 导入 Excel，返回成功导入条数
     */
    int importExcel(MultipartFile file) throws IOException;
}
