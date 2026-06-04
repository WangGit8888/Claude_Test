package com.example.bpm.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bpm.entity.BpmProcess;
import com.example.bpm.mapper.BpmProcessMapper;
import com.example.bpm.service.BpmProcessService;
import com.example.common.dto.Condition;
import com.example.common.dto.RequestDTO;
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
    public Page<BpmProcess> page(RequestDTO requestDTO) {
        Page<BpmProcess> p = new Page<>(requestDTO.getPageNum(), requestDTO.getPageSize());
        LambdaQueryWrapper<BpmProcess> queryWrapper = buildLambdaQueryWrapper(requestDTO.getCondition());
        QueryWrapper<BpmProcess> qw = new QueryWrapper<>();
        queryWrapper.orderByDesc(BpmProcess::getUpdateTime);
        return this.page(p, qw);
    }

    private LambdaQueryWrapper<BpmProcess> buildLambdaQueryWrapper(Condition condition) {
        LambdaQueryWrapper<BpmProcess> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }


    @Override
    @Transactional
    public boolean delete(List<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public void exportExcel(HttpServletResponse response,Condition condition) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("流程信息", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName + ".xlsx");
        LambdaQueryWrapper<BpmProcess> queryWrapper = buildLambdaQueryWrapper(condition);
        List<BpmProcess> list = this.list(queryWrapper);
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
