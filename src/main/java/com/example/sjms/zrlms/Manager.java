package com.example.sjms.zrlms;

/**
 * 具体审批人：经理（额度 5000 元以内）
 */
public class Manager extends Approver {

    @Override
    protected int maxAmount() {
        return 5000;
    }

    @Override
    protected String name() {
        return "经理";
    }
}
