package com.example.config.zrlms.handle;

import com.alibaba.excel.util.StringUtils;
import com.example.config.zrlms.InternEvaluationHandler;
import com.example.config.zrlms.InternEvaluationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)  // 标记执行顺序
public class BasicInfoValidationHandler extends InternEvaluationHandler {

    private static final Logger log = LoggerFactory.getLogger(BasicInfoValidationHandler.class);

    @Override
    public void handle(InternEvaluationRequest request) {
        log.info("【责任链-第1环】开始基础信息校验...");

        List<String> errors = new ArrayList<>();

        // 校验姓名
        if (StringUtils.isBlank(request.getName())) {
            errors.add("实习生姓名不能为空");
        }

        // 校验工号
        if (StringUtils.isBlank(request.getEmployeeId())) {
            errors.add("实习生工号不能为空");
        } else if (!request.getEmployeeId().matches("^SX\\d{6}$")) {
            errors.add("实习生工号格式错误，应为 SX + 6位数字（如 SX202401）");
        }

        // 校验部门
        if (StringUtils.isBlank(request.getDepartment())) {
            errors.add("实习生部门不能为空");
        } else {
            // 校验部门是否属于水电厂
            List<String> validDepts = Arrays.asList("运行部", "检修部", "水工部", "安监部", "综合部");
            if (!validDepts.contains(request.getDepartment())) {
                errors.add("部门必须为：运行部/检修部/水工部/安监部/综合部");
            }
        }

        // 如果有错误，记录并终止流程
        if (!errors.isEmpty()) {
            request.setErrorMessages(errors);
            log.error("基础信息校验失败：{}", errors);
            return;  // 不再调用下一个处理器
        }

        log.info("基础信息校验通过：{}（{}）", request.getName(), request.getEmployeeId());

        // 调用下一个处理器
        next(request);
    }
}
