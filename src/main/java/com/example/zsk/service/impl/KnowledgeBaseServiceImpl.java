package com.example.zsk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zsk.entity.KnowledgeBase;
import com.example.zsk.mapper.KnowledgeBaseMapper;
import com.example.zsk.service.KnowledgeBaseService;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeBaseServiceImpl
        extends ServiceImpl<KnowledgeBaseMapper, KnowledgeBase>
        implements KnowledgeBaseService {
}
