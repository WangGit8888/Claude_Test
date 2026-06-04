```sql
CREATE TABLE knowledge_base (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT  COMMENT '主键ID',
    title           VARCHAR(256)  NOT NULL             COMMENT '知识标题',
    description     TEXT                              COMMENT '知识描述',
    file_count      INT           NOT NULL DEFAULT 0   COMMENT '文件总数',
    delete_flag     INT           NOT NULL DEFAULT 0   COMMENT '删除标记 0未删除 1已删除',
    create_by       BIGINT(20)    DEFAULT NULL         COMMENT '创建人',
    create_date     DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by       BIGINT(20)    DEFAULT NULL         COMMENT '修改人',
    update_date     DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    INDEX idx_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识库';
```
