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
@Order(4)
public class InternshipDurationHandler extends InternEvaluationHandler {

    private static final Logger log = LoggerFactory.getLogger(InternshipDurationHandler.class);

    // 最低实习月数要求
    private static final int MIN_MONTHS = 3;

    @Override
    public void handle(InternEvaluationRequest request) {
        if (hasError(request)) return;

        log.info("【责任链-第4环】开始实习时长校验...");

        List<String> errors = new ArrayList<>();

        if (request.getInternshipMonths() < MIN_MONTHS) {
            errors.add(String.format(
                    "实习未满%d个月（实际%d个月），不满足奖金发放条件",
                    MIN_MONTHS, request.getInternshipMonths()
            ));
        }

        if (!errors.isEmpty()) {
            request.setErrorMessages(errors);
            log.error("实习时长校验失败：{}", errors);
            return;
        }

        log.info("实习时长校验通过：已实习{}个月", request.getInternshipMonths());

        next(request);
    }

    private boolean hasError(InternEvaluationRequest request) {
        return request.getErrorMessages() != null && !request.getErrorMessages().isEmpty();
    }
}
