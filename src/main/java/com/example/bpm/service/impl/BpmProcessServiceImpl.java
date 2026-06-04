package com.example.bpm.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bpm.entity.BpmProcess;
import com.example.bpm.mapper.BpmProcessMapper;
import com.example.bpm.service.BpmProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class BpmProcessServiceImpl
        extends ServiceImpl<BpmProcessMapper, BpmProcess>
        implements BpmProcessService {

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("流程信息", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName + ".xlsx");

        // 查询全部数据
        List<BpmProcess> list = this.list();

        // 写入 Excel
        EasyExcel.write(response.getOutputStream(), BpmProcess.class)
                .sheet("流程信息")
                .doWrite(list);
    }

    @Override
    @Transactional
    public int importExcel(MultipartFile file) throws IOException {
        List<BpmProcess> list = EasyExcel.read(file.getInputStream(), BpmProcess.class, null)
                .sheet()
                .doReadSync();

        int count = 0;
        for (BpmProcess entity : list) {
            boolean ok = this.saveOrUpdate(entity);
            if (ok) count++;
        }
        return count;
    }
}
