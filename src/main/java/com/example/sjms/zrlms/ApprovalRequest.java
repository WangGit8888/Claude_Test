package com.example.sjms.zrlms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 责任链的共享上下文：一张报销单据
 * 整条链传递同一个实例（引用传递），每个审批人都能读、能改，
 * 下游节点看到的就是上游节点改完的状态
 */
public class ApprovalRequest {

    private final int amount;          // 报销金额
    private final String applicant;    // 申请人
    private final List<String> records = new ArrayList<>();  // 共享的审批记录

    public ApprovalRequest(int amount, String applicant) {
        this.amount = amount;
        this.applicant = applicant;
    }

    public int getAmount() {
        return amount;
    }

    public String getApplicant() {
        return applicant;
    }

    /** 追加一条审批记录（共享数据的写入入口） */
    public void addRecord(String msg) {
        records.add(LocalDateTime.now() + " " + msg);
    }

    /** 查看完整审批记录（链结束后统一消费） */
    public List<String> getRecords() {
        return List.copyOf(records);
    }
}
