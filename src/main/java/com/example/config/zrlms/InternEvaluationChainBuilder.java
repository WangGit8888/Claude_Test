package com.example.config.zrlms;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
public class InternEvaluationChainBuilder {

    @Autowired
    private List<InternEvaluationHandler> handlers;  // Spring自动注入所有处理器

    @PostConstruct
    public void buildChain() {
        // 按@Order排序
        handlers.sort(Comparator.comparingInt(
                handler -> handler.getClass().getAnnotation(Order.class).value()
        ));

        // 组装链
        for (int i = 0; i < handlers.size() - 1; i++) {
            handlers.get(i).setNext(handlers.get(i + 1));
        }

        log.info("责任链构建完成，共{}个处理器", handlers.size());
    }

    // 获取链头
    public InternEvaluationHandler getChainHead() {
        return handlers.isEmpty() ? null : handlers.get(0);
    }
}
