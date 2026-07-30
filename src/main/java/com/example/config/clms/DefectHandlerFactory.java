package com.example.config.clms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DefectHandlerFactory {

    private final Map<Integer, DefectHandler> handlerMap;

    @Autowired
    public DefectHandlerFactory(List<DefectHandler> handlers) {
        this.handlerMap = handlers.stream()
                .collect(Collectors.toMap(DefectHandler::getEquipmentType, Function.identity()));
    }

    public DefectHandler getHandler(Integer equipmentType) throws BusinessException {
        DefectHandler handler = handlerMap.get(equipmentType);
        if (handler == null) {
            throw new BusinessException("不支持的设备类型: " + equipmentType);
        }
        return handler;
    }
}