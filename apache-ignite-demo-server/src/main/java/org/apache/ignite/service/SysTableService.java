package org.apache.ignite.service;

import org.apache.ignite.vo.ResultEntity;

/**
 * 系统表Service
 *
 * @author weiqiang
 * @date 2025/5/15 09:56
 */
public interface SysTableService {
    /**
     * 获取所有表
     *
     * @return {@link ResultEntity}
     */
    ResultEntity getAllTable();

    /**
     * 获取所有视图
     *
     * @return {@link ResultEntity}
     */
    ResultEntity getAllView();

    /**
     * 获取表元数据
     *
     * @param schema    架构
     * @param tableName 表名
     * @return {@link ResultEntity}
     */
    ResultEntity getTableMetaData(String schema, String tableName);
}
