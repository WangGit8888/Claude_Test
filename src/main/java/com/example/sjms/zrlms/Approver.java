package com.example.sjms.zrlms;

/**
 * 责任链模式：抽象审批人
 * 每个审批人有自己的权限额度，超限则转交上级
 */
public abstract class Approver {

    protected Approver next;

    // 设置下一级，返回 next 便于链式串联
    public Approver setNext(Approver next) {
        this.next = next;
        return next;
    }

    // 审批入口：金额在权限内则通过，否则转交上级
    public final void approve(int amount) {
        if (amount <= maxAmount()) {
            System.out.println(name() + " 审批通过：" + amount + " 元");
        } else if (next != null) {
            System.out.println(name() + " 权限不足，转交上级");
            next.approve(amount);
        } else {
            System.out.println("无人能审批：" + amount + " 元");
        }
    }

    protected abstract int maxAmount();

    protected abstract String name();
}
