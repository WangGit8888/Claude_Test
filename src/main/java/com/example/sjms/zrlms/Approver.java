package com.example.sjms.zrlms;

/**
 * 责任链模式：抽象审批人
 * 每个审批人有自己的权限额度，超限则转交上级
 * 整条链传递同一个 {@link ApprovalRequest}（共享上下文），
 * 每个节点既能读也能写，下游能看到上游的修改
 */
public abstract class Approver {

    protected Approver next;

    // 设置下一级，返回 next 便于链式串联
    public Approver setNext(Approver next) {
        this.next = next;
        return next;
    }

    // 审批入口：金额在权限内则通过（并往共享单据上追加记录），否则转交上级
    public final void approve(ApprovalRequest req) {
        int amount = req.getAmount();
        if (amount <= maxAmount()) {
            req.addRecord(name() + " 审批通过：" + amount + " 元");
            System.out.println(name() + " 审批通过：" + amount + " 元");
        } else if (next != null) {
            req.addRecord(name() + " 权限不足，转交上级");   // ★ 中间节点也往共享单据上写记录
            System.out.println(name() + " 权限不足，转交上级");
            next.approve(req);   // ★ 同一个 req 实例继续往下传
        } else {
            req.addRecord("无人能审批：" + amount + " 元");
            System.out.println("无人能审批：" + amount + " 元");
        }
    }

    protected abstract int maxAmount();

    protected abstract String name();
}
