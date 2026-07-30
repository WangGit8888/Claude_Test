package com.example.config.zrlms.handle;

import com.example.config.zrlms.InternEvaluationHandler;
import com.example.config.zrlms.InternEvaluationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class AttendanceValidationHandler extends InternEvaluationHandler {

    private static final Logger log = LoggerFactory.getLogger(AttendanceValidationHandler.class);

    // 最低出勤天数要求
    private static final int MIN_ATTENDANCE_DAYS = 20;

    @Override
    public void handle(InternEvaluationRequest request) {
        // 如果前面已经有错误，停止执行
        if (request.getErrorMessages() != null && !request.getErrorMessages().isEmpty()) {
            return;
        }

        log.info("【责任链-第2环】开始考勤数据校验...");

        List<String> errors = new ArrayList<>();

        // 校验出勤天数
        if (request.getAttendanceDays() == null) {
            errors.add("出勤天数不能为空");
        } else if (request.getAttendanceDays() < 0) {
            errors.add("出勤天数不能为负数");
        } else if (request.getAttendanceDays() > 31) {
            errors.add("出勤天数不能超过31天");
        } else if (request.getAttendanceDays() < MIN_ATTENDANCE_DAYS) {
            errors.add(String.format(
                    "出勤天数不足（实际%d天，要求最少%d天）",
                    request.getAttendanceDays(), MIN_ATTENDANCE_DAYS
            ));
        }

        // 校验实习月数
        if (request.getInternshipMonths() == null) {
            errors.add("实习月数不能为空");
        } else if (request.getInternshipMonths() < 0) {
            errors.add("实习月数不能为负数");
        } else if (request.getInternshipMonths() > 12) {
            errors.add("实习月数不能超过12个月");
        }

        if (!errors.isEmpty()) {
            request.setErrorMessages(errors);
            log.error("考勤数据校验失败：{}", errors);
            return;
        }

        log.info("考勤数据校验通过：出勤{}天，实习{}个月",
                request.getAttendanceDays(), request.getInternshipMonths());

        next(request);
    }
}
