package org.apache.ignite.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ignite.enums.GlobalEnum;
import org.apache.ignite.service.SysTableDataService;
import org.apache.ignite.util.ResultUtil;
import org.apache.ignite.vo.ResultEntity;
import org.apache.ignite.vo.SysTableDataParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.apache.ignite.global.GlobalConstants.ORDER_ASC;

/**
 * 系统表数据ServiceImpl
 *
 * @author weiqiang
 * @date 2025/5/15 10:17
 */
@Slf4j
@Service("sysTableDataService")
public class SysTableDataServiceImpl implements SysTableDataService {

    /**
     * jdbcTemplate
     */
    @Autowired
    public JdbcTemplate jdbcTemplate;

    /**
     * 获取表数据
     *
     * @param paramVO 参数
     * @return 结果
     */
    @Override
    public ResultEntity getTableData(SysTableDataParamVO paramVO) {
        final String schema = paramVO.getSchema(), tableName = paramVO.getTableName();
        final Integer pageNumber = paramVO.getPageNumber(), pageSize = paramVO.getPageSize();
        final String orderByColumn = paramVO.getOrderByColumn(), isAsc = StringUtils.defaultIfBlank(paramVO.getIsAsc(), ORDER_ASC);
        if (StringUtils.isBlank(schema)) {
            return ResultUtil.error("架构不能为空");
        }
        if (StringUtils.isBlank(tableName)) {
            return ResultUtil.error("表名不能为空");
        }
        if (pageNumber == null || pageNumber < 1) {
            return ResultUtil.error("页码不能小于1");
        }
        if (Objects.isNull(pageSize) || pageSize < 1) {
            return ResultUtil.error("每页显示条数不能小于1");
        }
        final Map<String, Object> whereMap = paramVO.getWhereMap();
        try {
            String sql = "SELECT * FROM " + schema + "." + tableName;
            AtomicReference<String> whereSql = new AtomicReference<>(" WHERE 1 = 1 ");
            if (Objects.nonNull(whereMap) && !whereMap.isEmpty()) {
                whereMap.forEach((key, value) -> {
                    if (Objects.nonNull(value)) {
                        final String tempWhere = whereSql.get();
                        //如果value为数字时，使用数字查询，否则使用字符串查询
                        if (StringUtils.isNumeric(value.toString())) {
                            whereSql.set(tempWhere + " AND " + key + " = " + value);
                        } else {
                            whereSql.set(tempWhere + " AND " + key + " = '" + value + "'");
                        }
                    }
                });
            }
            sql += whereSql.get();
            //获取表数据条数
            Long total = jdbcTemplate.queryForObject("SELECT count(1) from (" + sql + ") temp", Long.class);
            if (Objects.isNull(total)) {
                return ResultUtil.error("获取表数据失败");
            }
            if (total < 1) {
                return ResultUtil.success(GlobalEnum.DataEmpty);
            }
            if (StringUtils.isNotBlank(orderByColumn)) {
                sql += " ORDER BY " + orderByColumn + " " + isAsc;
            }
            sql += " LIMIT " + pageSize + " OFFSET " + (pageNumber - 1) * pageSize;
            log.info("获取表数据sql:{}", sql);
            final ResultEntity resultEntity = ResultUtil.success(jdbcTemplate.queryForList(sql));
            resultEntity.setTotal(total);
            return resultEntity;
        } catch (Exception e) {
            log.error("获取表数据失败", e);
            return ResultUtil.error("获取表数据失败");
        }
    }
}
