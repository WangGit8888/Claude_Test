package com.example.sjms.zrlms;

/**
 * 具体审批人：总经理（不限额）
 */
public class CEO extends Approver {

    @Override
    protected int maxAmount() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected String name() {
        return "总经理";
    }
}
