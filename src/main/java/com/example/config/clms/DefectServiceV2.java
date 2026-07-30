package com.example.config.clms;

import com.example.config.clms.impl.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DefectServiceV2 {

    @Autowired
    private DefectMapper defectMapper;
    @Autowired
    private DefectHandlerFactory handlerFactory;
    @Autowired
    private LogService logService;
    @Autowired
    private DispatchService dispatchService;  // 省调

    @Transactional
    public void handleDefect(Defect defect) throws BusinessException {

        // 1. 根据设备类型获取处理器（策略模式）
        DefectHandler handler = handlerFactory.getHandler(defect.getEquipmentType());

        // 2. 等级判定（由具体策略执行）
        String level = handler.determineLevel(defect);
        defect.setDefectLevel(level);
        log.info("缺陷等级判定：设备类型={}，等级={}", defect.getEquipmentType(), level);

        // 3. 生成工单（由具体策略执行）
        String workOrderNo = handler.createWorkOrder(defect);
//        defect.setWorkOrderNo(workOrderNo);

        // 4. 执行处理（由具体策略执行）
        handler.execute(defect);

        // 5. 插入缺陷记录（共同逻辑）
//        defect.setHandleTime(new Date());
//        defectMapper.insert(defect);

        // 6. 是否需要上报省调（由具体策略判断）
        if (handler.needReportToProvince(defect)) {
//            dispatchService.reportToProvince("设备缺陷", defect);
            log.info("已上报省调：工单号={}", workOrderNo);
        }

        // 7. 记录操作日志（共同逻辑）
//        logService.record(defect.getReporterId(), "处理缺陷", defect.getId());
    }
}
