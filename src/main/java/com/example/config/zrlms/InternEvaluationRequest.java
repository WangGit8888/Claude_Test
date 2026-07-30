package com.example.config.zrlms;

import lombok.Data;

import java.util.List;

// 实习生考核请求对象
@Data
public class InternEvaluationRequest {
    // 基本信息
    private String name;              // 姓名
    private String employeeId;        // 工号
    private String department;        // 部门

    // 考勤数据
    private Integer attendanceDays;   // 出勤天数（月）
    private Integer internshipMonths; // 实习月数

    // 考核数据
    private Double examScore;         // 笔试成绩（0-100）
    private Double interviewScore;    // 面试成绩（0-100）
    private Double dailyScore;        // 日常表现分（0-100）

    // 计算后的总分
    private Double totalScore;

    // 奖金信息（由责任链填充）
    private String bonusLevel;        // 奖金等级：S/A/B/C/D
    private Double bonusAmount;       // 奖金金额
    private Boolean needSpecialApproval; // 是否需要特殊审批
    private List<String> errorMessages;  // 校验错误信息
}
