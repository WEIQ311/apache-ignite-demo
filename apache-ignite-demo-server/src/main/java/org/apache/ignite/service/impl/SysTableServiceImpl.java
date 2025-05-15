package org.apache.ignite.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.service.SysTableService;
import org.apache.ignite.util.ResultUtil;
import org.apache.ignite.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 系统表ServiceImpl
 *
 * @author weiqiang
 * @date 2025/5/15 09:56
 */
@Slf4j
@Service("sysTableService")
public class SysTableServiceImpl implements SysTableService {

    /**
     * jdbcTemplate
     */
    @Autowired
    public JdbcTemplate jdbcTemplate;

    /**
     * 获取所有表
     *
     * @return {@link ResultEntity}
     */
    @Override
    public ResultEntity getAllTable() {
        try {
            String sql = "SELECT * FROM SYSTEM.TABLES";
            return ResultUtil.success(jdbcTemplate.queryForList(sql));
        } catch (Exception e) {
            log.error("获取所有表失败", e);
            return ResultUtil.error("获取所有表失败");
        }
    }

    /**
     * 获取所有视图
     *
     * @return {@link ResultEntity}
     */
    @Override
    public ResultEntity getAllView() {
        try {
            String sql = "SELECT * FROM SYSTEM.SYSTEM_VIEWS ";
            return ResultUtil.success(jdbcTemplate.queryForList(sql));
        } catch (Exception e) {
            log.error("获取所有视图失败", e);
        }
        return ResultUtil.error("获取所有视图失败");
    }

    /**
     * 获取表元数据
     *
     * @param schema    架构
     * @param tableName 表名
     * @return {@link ResultEntity}
     */
    @Override
    public ResultEntity getTableMetaData(String schema, String tableName) {
        try {
//            String sql = "SELECT * FROM SYSTEM.SYSTEM_VIEWS WHERE SCHEMA = ? AND NAME = ?";
            String sql = "SELECT * FROM SYSTEM.TABLE_COLUMNS WHERE SCHEMA = ? AND TABLE_NAME = ?";
            return ResultUtil.success(jdbcTemplate.queryForList(sql, schema, tableName));
        } catch (Exception e) {
            log.error("获取表元数据失败", e);
        }
        return ResultUtil.error("获取表元数据失败");
    }
}
