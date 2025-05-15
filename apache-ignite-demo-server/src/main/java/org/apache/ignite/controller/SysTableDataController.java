package org.apache.ignite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.ignite.service.SysTableDataService;
import org.apache.ignite.util.ResultUtil;
import org.apache.ignite.vo.ResultEntity;
import org.apache.ignite.vo.SysTableDataParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 表数据管理
 *
 * @author weiqiang
 * @date 2025/5/15 09:54
 */
@RestController
@RequestMapping("/sysTableDataApi")
@Tag(name = "表数据管理", description = "表数据管理，查询所有表、表元数据信息")
public class SysTableDataController {

    /**
     * 表数据管理服务
     */
    @Autowired
    public SysTableDataService sysTableDataService;

    /**
     * 获取表数据
     *
     * @param paramVO 参数
     * @return {@link ResultEntity}
     */
    @Operation(summary = "获取表数据", description = "返回获取表数据")
    @PostMapping("/getTableData")
    public ResultEntity getTableData(@Valid @RequestBody SysTableDataParamVO paramVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return sysTableDataService.getTableData(paramVO);
    }
}
