package com.example.sjms.zrlms;

/**
 * 具体审批人：组长（额度 1000 元以内）
 */
public class GroupLeader extends Approver {

    @Override
    protected int maxAmount() {
        return 1000;
    }

    @Override
    protected String name() {
        return "组长";
    }
}
