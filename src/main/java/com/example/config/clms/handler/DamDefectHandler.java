package com.example.config.clms.handler;

import com.example.config.clms.Defect;
import com.example.config.clms.DefectHandler;
import com.example.config.clms.EquipmentType;
import com.example.config.clms.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DamDefectHandler implements DefectHandler {

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private DamService damService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private DispatchService dispatchService;

    @Override
    public Integer getEquipmentType() {
        return EquipmentType.DAM;
    }

    @Override
    public String determineLevel(Defect defect) {
        // 大坝：裂缝/渗漏直接升为URGENT
        String desc = defect.getDefectDesc();
        if (desc.contains("裂缝") || desc.contains("渗漏")) {
            return "URGENT";
        }
        // 否则保持原等级
        return defect.getDefectLevel();
    }

    @Override
    public String createWorkOrder(Defect defect) {
        // 大坝工单需要附带地质勘察报告
//        return workOrderService.createDamWorkOrder(defect);
        return null;
    }

    @Override
    public void execute(Defect defect) {
        String level = defect.getDefectLevel();

        if ("URGENT".equals(level)) {
            // 紧急：停机关闸 + 上报省调 + 启动应急预案
            log.info("【大坝紧急缺陷】位置：{}，描述：{}", defect.getLocation(), defect.getDefectDesc());

//            damService.stopGate(defect.getLocation());
//            dispatchService.reportToProvince("大坝裂缝", defect);
//            alertService.startEmergencyPlan("DAM_CRACK");
//            smsService.sendToManagers("【古田溪】大坝出现裂缝，已紧急停机，请立即到场！");

        } else {
            // 非紧急：加强监测 + 录入台账
            log.info("【大坝常规缺陷】位置：{}，等级：{}", defect.getLocation(), level);

//            damService.addMonitorPoint(defect.getLocation());
//            damService.recordToLedger(defect);
        }
    }

    @Override
    public boolean needReportToProvince(Defect defect) {
        // 只有URGENT级别才上报省调
        return "URGENT".equals(defect.getDefectLevel());
    }
}
