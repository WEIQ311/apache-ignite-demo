package org.apache.ignite.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 请求返回实体类
 *
 * @author weiQiang
 * @date 2020-01-10
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@Builder
@Schema(description = "请求返回实体类")
public class ResultEntity implements Serializable {

    private static final long serialVersionUID = -5159330866402406443L;
    /**
     * 返回结果
     */
    @Schema(description = "返回结果数据")
    private List data;
    /**
     * 返回信息
     */
    @Schema(description = "返回信息")
    private String message;
    /**
     * 是否分页
     */
    @JsonIgnore
    @Schema(description = "是否分页", hidden = true)
    private boolean pageable;
    /**
     * 返回成功与否
     */
    @Schema(description = "返回结果状态", example = "true")
    private boolean success;
    /**
     * 数据条数
     */
    @Builder.Default
    @Schema(description = "返回结果数据条数", example = "0")
    private Long total = 0L;
    /**
     * 转换返回结果
     */
    @Schema(description = "转换返回结果")
    private List convertData;
    /**
     * 耗时
     */
    @Builder.Default
    @Schema(description = "请求耗时,单位ms", example = "0")
    private Long totalTime = 0L;

}
