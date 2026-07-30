package com.example.config.zrlms.run;

import com.example.config.clms.BusinessException;
import com.example.config.zrlms.InternEvaluationChainBuilder;
import com.example.config.zrlms.InternEvaluationHandler;
import com.example.config.zrlms.InternEvaluationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InternEvaluationService {

    @Autowired
    private InternEvaluationChainBuilder chainBuilder;

    /**
     * 处理实习生考核（使用责任链）
     */
    public InternEvaluationRequest evaluate(InternEvaluationRequest request) throws BusinessException {
        log.info("========== 开始实习生考核评估 ==========");
        log.info("实习生：{}，工号：{}", request.getName(), request.getEmployeeId());

        // 获取责任链头
        InternEvaluationHandler chainHead = chainBuilder.getChainHead();

        if (chainHead == null) {
            throw new BusinessException("责任链未正确构建");
        }

        // 执行责任链
        chainHead.handle(request);

        // 检查是否有错误
        if (request.getErrorMessages() != null && !request.getErrorMessages().isEmpty()) {
            log.warn("考核评估失败，错误信息：{}", request.getErrorMessages());
            throw new BusinessException("考核数据校验失败：" + String.join("；", request.getErrorMessages()));
        }

        log.info("========== 考核评估完成 ==========");
        log.info("最终结果：等级={}，奖金={}元，需审批={}",
                request.getBonusLevel(), request.getBonusAmount(), request.getNeedSpecialApproval());

        return request;
    }
}
