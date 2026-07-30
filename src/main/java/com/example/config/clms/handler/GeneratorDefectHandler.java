package com.example.config.clms.handler;

import com.example.config.clms.Defect;
import com.example.config.clms.DefectHandler;
import com.example.config.clms.EquipmentType;
import com.example.config.clms.impl.GeneratorService;
import com.example.config.clms.impl.SmsService;
import com.example.config.clms.impl.WorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GeneratorDefectHandler implements DefectHandler {

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private GeneratorService generatorService;
    @Autowired
    private SmsService smsService;

    @Override
    public Integer getEquipmentType() {
        return EquipmentType.GENERATOR;
    }

    @Override
    public String determineLevel(Defect defect) {
        // 发电机：温度或振动异常升URGENT
        String desc = defect.getDefectDesc();
        if (desc.contains("温度") || desc.contains("振动")) {
            return "URGENT";
        }
        return defect.getDefectLevel();
    }

    @Override
    public String createWorkOrder(Defect defect) {
        // 发电机工单需要附带运行参数曲线
//        return workOrderService.createGeneratorWorkOrder(defect);
        return null;
    }

    @Override
    public void execute(Defect defect) {
        String level = defect.getDefectLevel();

        if ("URGENT".equals(level)) {
            log.info("【发电机紧急缺陷】位置：{}，描述：{}", defect.getLocation(), defect.getDefectDesc());

            // 降50%负荷 + 申请停机检修
//            generatorService.reduceLoad(defect.getLocation(), 50);
//            generatorService.applyShutdown(defect);
//            smsService.sendToTechnicians("【古田溪】发电机故障，24小时内安排检修");

        } else {
            log.info("【发电机常规缺陷】位置：{}，等级：{}", defect.getLocation(), level);

            // 录入计划检修队列
//            generatorService.addToMaintenanceQueue(defect);
        }
    }

    @Override
    public boolean needReportToProvince(Defect defect) {
        // 发电机故障不直接上报省调（只报给厂内）
        return false;
    }
}
