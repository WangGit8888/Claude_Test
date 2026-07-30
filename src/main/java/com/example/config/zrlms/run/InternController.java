package com.example.config.zrlms.run;

import com.example.config.clms.BusinessException;
import com.example.config.zrlms.InternEvaluationRequest;
import com.example.config.zrlms.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/intern")
@Slf4j
public class InternController {

    @Autowired
    private InternEvaluationService evaluationService;

    /**
     * 实习生考核评估接口
     */
    @PostMapping("/evaluate")
    public Result<InternEvaluationRequest> evaluate(@RequestBody InternEvaluationRequest request) {
        try {
            InternEvaluationRequest result = evaluationService.evaluate(request);
            return Result.success(result);
        } catch (BusinessException e) {
            log.error("考核评估失败：{}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
