package org.apache.ignite.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * 表数据查询参数
 *
 * @author weiqiang
 * @date 2025/5/15 10:22
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "表数据查询参数")
public class SysTableDataParamVO implements Serializable {

    private static final long serialVersionUID = -5159330866402406995L;
    /**
     * 页码
     */
    @Builder.Default
    @Min(value = 1, message = "页码不能小于1")
    @Schema(description = "页码")
    private Integer pageNumber = 1;

    /**
     * 每页显示条数
     */
    @Builder.Default
    @Min(value = 1, message = "每页显示条数不能小于1")
    @Schema(description = "每页显示条数")
    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private String orderByColumn;

    /**
     * 排序方式
     */
    @Schema(description = "排序方式")
    private String isAsc;

    /**
     * 表名
     */
    @NotBlank(message = "表名不能为空")
    @Schema(description = "表名")
    private String tableName;

    /**
     * 架构
     */
    @NotBlank(message = "架构不能为空")
    @Schema(description = "架构")
    private String schema;

    /**
     * 查询条件
     */
    @Schema(description = "查询条件")
    private Map<String, Object> whereMap;
}
