package org.apache.ignite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ignite.service.SysTableService;
import org.apache.ignite.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统表管理
 *
 * @author weiqiang
 * @date 2025/5/15 09:54
 */
@RestController
@RequestMapping("/sysTableApi")
@Tag(name = "系统表管理", description = "系统表管理，查询所有表、表元数据信息")
public class SysTableController {

    /**
     * 系统表服务
     */
    @Autowired
    public SysTableService sysTableService;

    /**
     * 获取所有表
     *
     * @return {@link ResultEntity}
     */
    @Operation(summary = "获取表结果", description = "返回所有获取表结果")
    @GetMapping("/getAllTable")
    public ResultEntity getAllTable() {
        return sysTableService.getAllTable();
    }

    /**
     * 获取表元数据
     *
     * @param schema    架构
     * @param tableName 表名称
     * @return
     */
    @Operation(summary = "获取表元数据", description = "返回所有获取表元数据")
    @GetMapping("/getTableMetaData/{schema}/{tableName}")
    public ResultEntity getTableMetaData(@PathVariable @Parameter(name = "schema", description = "架构") String schema, @PathVariable @Parameter(name = "tableName", description = "表名称", required = true) String tableName) {
        return sysTableService.getTableMetaData(schema, tableName);
    }
}
