package org.apache.ignite.service;

import org.apache.ignite.vo.ResultEntity;
import org.apache.ignite.vo.SysTableDataParamVO;

/**
 * 系统表数据Service
 *
 * @author weiqiang
 * @date 2025/5/15 09:56
 */
public interface SysTableDataService {

    /**
     * 获取表数据
     *
     * @param paramVO 参数
     * @return 结果
     */
    ResultEntity getTableData(SysTableDataParamVO paramVO);
}
