package com.example.config.zrlms.handle;

import com.example.config.zrlms.InternEvaluationHandler;
import com.example.config.zrlms.InternEvaluationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(5)
public class BonusLevelHandler extends InternEvaluationHandler {

    private static final Logger log = LoggerFactory.getLogger(BonusLevelHandler.class);

    @Override
    public void handle(InternEvaluationRequest request) {
        if (hasError(request)) return;

        log.info("【责任链-第5环】开始奖金区间判断...");

        Double score = request.getTotalScore();
        String level;
        Double amount;
        boolean needApproval = false;

        // 奖金区间判断
        if (score >= 95) {
            level = "S";
            amount = 5000.0;
            needApproval = true;  // S级需要领导审批
            log.info("🎉 S级实习生：总分{}，奖金5000元（需领导审批）", score);

        } else if (score >= 85) {
            level = "A";
            amount = 3000.0;
            log.info("🌟 A级实习生：总分{}，奖金3000元", score);

        } else if (score >= 75) {
            level = "B";
            amount = 2000.0;
            log.info("👍 B级实习生：总分{}，奖金2000元", score);

        } else if (score >= 60) {
            level = "C";
            amount = 1000.0;
            log.info("📘 C级实习生：总分{}，奖金1000元", score);

        } else {
            level = "D";
            amount = 0.0;
            log.warn("📕 D级实习生：总分{}，无奖金", score);
        }

        // 设置奖金信息
        request.setBonusLevel(level);
        request.setBonusAmount(amount);
        request.setNeedSpecialApproval(needApproval);

        log.info("奖金区间判断完成：等级={}，金额={}元", level, amount);

        next(request);
    }

    private boolean hasError(InternEvaluationRequest request) {
        return request.getErrorMessages() != null && !request.getErrorMessages().isEmpty();
    }
}
