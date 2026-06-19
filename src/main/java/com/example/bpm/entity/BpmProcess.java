package com.example.bpm.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.model.BaseClass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bpm_process")
@Component
public class BpmProcess extends BaseClass {

    @ExcelProperty("流程名称")
    private String processName;

    @ExcelProperty("流程编号")
    private String processNo;

    @ExcelProperty("年度")
    private String year;

    @ExcelProperty("子工程")
    private String subProject;

    @ExcelProperty("文件类型")
    private String fileType;

    @ExcelProperty("报送文件类型")
    private String submitFileType;

    @ExcelProperty("发起日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date initiateDate;

    @ExcelProperty("发起人")
    private String initiator;

    @ExcelProperty("当前执行人")
    private String currentExecutor;

    @ExcelProperty("流程状态")
    private String processStatus;
}
