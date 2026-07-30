package com.example.config.zrlms.handle;

import com.example.config.zrlms.ApprovalService;
import com.example.config.zrlms.InternEvaluationHandler;
import com.example.config.zrlms.InternEvaluationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class SpecialApprovalHandler extends InternEvaluationHandler {

    private static final Logger log = LoggerFactory.getLogger(SpecialApprovalHandler.class);

    @Autowired
    private ApprovalService approvalService;  // 审批服务

    @Override
    public void handle(InternEvaluationRequest request) {
        if (hasError(request)) return;

        log.info("【责任链-第6环】开始特殊审批判断...");

        // 如果奖金等级是S级，需要特殊审批
        if (request.getNeedSpecialApproval() != null && request.getNeedSpecialApproval()) {
            log.info("⚠️ S级奖金需要特殊审批，正在发起审批流程...");

            // 发起审批
            String approvalId = approvalService.startApproval(
                    request.getEmployeeId(),
                    request.getName(),
                    "S级实习生奖金审批",
                    request.getBonusAmount()
            );

            request.setNeedSpecialApproval(true);
            log.info("✅ 审批流程已发起，审批编号：{}", approvalId);
        } else {
            log.info("无需特殊审批，奖金自动发放");
        }

        log.info("【责任链处理完成】实习生：{}，奖金等级：{}，金额：{}元",
                request.getName(), request.getBonusLevel(), request.getBonusAmount());

        // 这是最后一个环节，不再调用next
    }

    private boolean hasError(InternEvaluationRequest request) {
        return request.getErrorMessages() != null && !request.getErrorMessages().isEmpty();
    }
}
