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
@Order(3)
public class ScoreValidationHandler extends InternEvaluationHandler {

    private static final Logger log = LoggerFactory.getLogger(ScoreValidationHandler.class);

    @Override
    public void handle(InternEvaluationRequest request) {
        if (hasError(request)) return;

        log.info("【责任链-第3环】开始考核分数校验...");

        List<String> errors = new ArrayList<>();

        // 校验笔试成绩
        if (request.getExamScore() == null) {
            errors.add("笔试成绩不能为空");
        } else if (request.getExamScore() < 0 || request.getExamScore() > 100) {
            errors.add("笔试成绩必须在0-100之间");
        }

        // 校验面试成绩
        if (request.getInterviewScore() == null) {
            errors.add("面试成绩不能为空");
        } else if (request.getInterviewScore() < 0 || request.getInterviewScore() > 100) {
            errors.add("面试成绩必须在0-100之间");
        }

        // 校验日常表现分
        if (request.getDailyScore() == null) {
            errors.add("日常表现分不能为空");
        } else if (request.getDailyScore() < 0 || request.getDailyScore() > 100) {
            errors.add("日常表现分必须在0-100之间");
        }

        if (!errors.isEmpty()) {
            request.setErrorMessages(errors);
            log.error("考核分数校验失败：{}", errors);
            return;
        }

        // 计算总分（加权：笔试40% + 面试40% + 日常20%）
        Double totalScore = request.getExamScore() * 0.4
                + request.getInterviewScore() * 0.4
                + request.getDailyScore() * 0.2;
        request.setTotalScore(Math.round(totalScore * 100) / 100.0);  // 保留2位小数

        log.info("考核分数校验通过：总分={}", request.getTotalScore());

        next(request);
    }

    private boolean hasError(InternEvaluationRequest request) {
        return request.getErrorMessages() != null && !request.getErrorMessages().isEmpty();
    }
}
