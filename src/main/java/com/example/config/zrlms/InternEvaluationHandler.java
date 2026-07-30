package com.example.config.zrlms;

/**
 * 实习生考核处理器（责任链模式）
 * 每个处理器负责一项校验或计算任务
 */
public abstract class InternEvaluationHandler {

    // 下一个处理器
    protected InternEvaluationHandler nextHandler;

    // 设置下一个处理器（链式调用）
    public InternEvaluationHandler setNext(InternEvaluationHandler handler) {
        this.nextHandler = handler;
        return handler;  // 返回下一个处理器，方便链式调用
    }

    // 处理方法：由子类实现具体逻辑
    public abstract void handle(InternEvaluationRequest request);

    // 调用下一个处理器
    protected void next(InternEvaluationRequest request) {
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
