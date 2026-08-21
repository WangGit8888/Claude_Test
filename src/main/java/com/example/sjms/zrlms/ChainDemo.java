package com.example.sjms.zrlms;

/**
 * 责任链模式演示：报销金额按权限逐级转交
 */
public class ChainDemo {

    public static void main(String[] args) {
        // 串联责任链：组长 -> 经理 -> 总经理
        Approver groupLeader = new GroupLeader();
        groupLeader.setNext(new Manager()).setNext(new CEO());

        groupLeader.approve(500);   // 组长审批
        groupLeader.approve(3000);  // 经理审批
        groupLeader.approve(20000); // 总经理审批
    }
}
