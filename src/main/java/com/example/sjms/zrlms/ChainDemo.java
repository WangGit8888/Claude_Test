package com.example.sjms.zrlms;

/**
 * 责任链模式演示：报销金额按权限逐级转交
 * 与旧版只传一个 int 不同：整条链共享同一个 ApprovalRequest（单据），
 * 每个审批人把自己的审批结果追加到单据上，链结束后统一查看完整审批轨迹
 */
public class ChainDemo {

    public static void main(String[] args) {
        // 串联责任链：组长 -> 经理 -> 总经理
        Approver groupLeader = new GroupLeader();
        groupLeader.setNext(new Manager()).setNext(new CEO());

        // 每个请求都是独立的共享上下文
        evaluate(groupLeader, new ApprovalRequest(500, "张三"));
        evaluate(groupLeader, new ApprovalRequest(3000, "李四"));
        evaluate(groupLeader, new ApprovalRequest(20000, "王五"));
    }

    private static void evaluate(Approver head, ApprovalRequest req) {
        System.out.println("===== " + req.getApplicant() + " 报销 " + req.getAmount() + " 元 =====");
        head.approve(req);
        // 链结束后统一"消费"共享上下文：打印单据上累积的完整审批记录
        System.out.println("—— 单据上的完整审批记录 ——");
        for (String record : req.getRecords()) {
            System.out.println("  " + record);
        }
        System.out.println();
    }
}
