package org.apache.ignite.util;

import org.apache.ignite.enums.GlobalEnum;
import org.apache.ignite.vo.ResultEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.apache.ignite.aspect.CommonAspect.BEGIN_TIME;


/**
 * 返回结果封装工具类
 *
 * @author weiQiang
 * @date 2020-01-10
 */
public class ResultUtil {

    /**
     * 失败方法
     *
     * @param globalEnum 信息
     * @return ResultEntity
     */
    public static ResultEntity error(GlobalEnum globalEnum) {
        return error(globalEnum.getMessage());
    }

    /**
     * 失败方法
     *
     * @param globalEnum 信息
     * @param totalTime  耗时
     * @return ResultEntity
     */
    public static ResultEntity error(GlobalEnum globalEnum, Long totalTime) {
        return error(globalEnum.getMessage(), totalTime);
    }

    /**
     * 失败方法
     *
     * @param message 数据
     * @return ResultEntity
     */
    public static ResultEntity error(String message) {
        return error(message, List.of());
    }

    /**
     * 失败方法
     *
     * @param message   数据
     * @param totalTime 耗时
     * @return ResultEntity
     */
    public static ResultEntity error(String message, Long totalTime) {
        return error(message, null, totalTime);
    }


    /**
     * 失败方法
     *
     * @param message 信息
     * @param data    数据
     * @return ResultEntity
     */
    public static ResultEntity error(String message, List data) {
        return error(message, data, totalTime());
    }

    /**
     * 失败方法
     *
     * @param message   信息
     * @param data      数据
     * @param totalTime 耗时
     * @return ResultEntity
     */
    public static ResultEntity error(String message, List data, Long totalTime) {
        return ResultEntity.builder().success(false).message(message).data(data).totalTime(totalTime).build();
    }


    /**
     * 操作成功
     *
     * @return ResultEntity
     */
    public static ResultEntity msg() {
        return ResultEntity.builder().success(true).message(GlobalEnum.MsgOperationSuccess.getMessage()).totalTime(totalTime()).build();
    }

    /**
     * 操作成功与失败
     *
     * @param flag 标识
     * @return ResultEntity
     */
    public static ResultEntity booleanFlag(boolean flag) {
        return booleanFlag(flag, flag ? GlobalEnum.MsgOperationSuccess.getMessage() : GlobalEnum.MsgOperationFailed.getMessage());
    }

    /**
     * 操作成功与失败
     *
     * @param flag    标识
     * @param message 提示信息
     * @return ResultEntity
     */
    public static ResultEntity booleanFlag(boolean flag, String message) {
        return ResultEntity.builder().success(flag).message(message).build();
    }

    /**
     * 操作成功、失败
     *
     * @param total 数据
     * @param count 数量
     * @return ResultEntity
     */
    public static ResultEntity msg(Object[] total, int count) {
        if (total.length == count) {
            return ResultEntity.builder().success(true).message(GlobalEnum.MsgOperationSuccess.getMessage()).build();
        } else {
            return msg(count);
        }
    }

    /**
     * 操作成功、失败
     *
     * @param count 数量
     * @return ResultEntity
     */
    public static ResultEntity msg(Integer count) {
        if (isIntThanZero(count)) {
            return ResultEntity.builder().success(true).message(GlobalEnum.MsgOperationSuccess.getMessage()).build();
        }
        return ResultEntity.builder().success(false).message(GlobalEnum.MsgOperationFailed.getMessage()).build();
    }

    /**
     * 判断整数是否大于零
     *
     * @param number
     * @return boolean
     */
    public static boolean isIntThanZero(int number) {
        return number > 0;
    }


    /**
     * 成功返回分页数据和总页数
     *
     * @param globalEnum 信息
     * @param resultList 数据
     * @param total      总数据
     * @return ResultEntity
     */
    public static ResultEntity success(GlobalEnum globalEnum, List resultList, Long total) {
        return success(globalEnum.getMessage(), resultList, total);
    }

    /**
     * 成功返回分页数据和总页数
     *
     * @param message    信息
     * @param resultList 数据
     * @param total      总数据
     * @return ResultEntity
     */
    public static ResultEntity success(String message, List resultList, Long total) {
        return ResultEntity.builder().message(message).success(true).data(resultList).total(total).build();
    }

    /**
     * 成功返回分页数据和总页数
     *
     * @param message    信息
     * @param resultData 数据
     * @return ResultEntity
     */
    public static ResultEntity success(String message, Object resultData) {
        List resultList = new ArrayList();
        if (Objects.nonNull(resultData)) {
            if (resultData instanceof ArrayList) {
                resultList = (ArrayList) resultData;
            } else {
                resultList.add(resultData);
            }
        }
        return ResultEntity.builder().message(message).success(true).data(resultList).total((long) resultList.size()).build();
    }

    /**
     * 成功返回分页数据和总页数
     *
     * @param globalEnum 信息
     * @param resultList 数据
     * @param totalTime  总耗时
     * @return ResultEntity
     */
    public static ResultEntity successTotalTime(GlobalEnum globalEnum, List resultList, Long totalTime) {
        return ResultEntity.builder().message(globalEnum.getMessage()).success(true).data(resultList).totalTime(totalTime).build();
    }

    /**
     * 成功返回数据
     *
     * @param resultList 数据
     * @return ResultEntity
     */
    public static ResultEntity success(List resultList) {
        return success(GlobalEnum.QuerySuccess, resultList);
    }

    /**
     * 成功返回数据
     *
     * @param globalEnum 信息
     * @param resultList 数据
     * @return ResultEntity
     */
    public static ResultEntity success(GlobalEnum globalEnum, List resultList) {
        Long total = 0L;
        if (Objects.nonNull(resultList)) {
            total = (long) resultList.size();
        }
        return ResultEntity.builder().message(globalEnum.getMessage()).success(true).data(resultList).total(total).build();
    }

    /**
     * 成功方法
     *
     * @param message 信息
     * @return ResultEntity
     */
    public static ResultEntity success(String message) {
        return success(message, List.of());
    }

    /**
     * 成功方法
     *
     * @param globalEnum 信息
     * @return ResultEntity
     */
    public static ResultEntity success(GlobalEnum globalEnum) {
        return success(globalEnum.getMessage(), List.of());
    }


    /**
     * 成功方法
     *
     * @param message 信息
     * @param data    数据
     * @return ResultEntity
     */
    public static ResultEntity success(String message, List data) {
        return ResultEntity.builder().success(true).message(message).data(data).build();
    }


    /**
     * 成功方法
     *
     * @param globalEnum 信息
     * @param dataMap    数据
     * @return ResultEntity
     */
    public static ResultEntity success(GlobalEnum globalEnum, Map<String, Object> dataMap) {
        return ResultEntity.builder().success(true).message(globalEnum.getMessage()).data(List.of(dataMap)).build();
    }

    /**
     * 成功方法
     *
     * @param dataMap 数据
     * @return ResultEntity
     */
    public static ResultEntity success(Map<?, ?> dataMap) {
        return ResultEntity.builder().success(true).message(GlobalEnum.QuerySuccess.getMessage()).data(List.of(dataMap)).total(1L).build();
    }


    /**
     * 获取耗时
     *
     * @return long
     */
    public static long totalTime() {
        long beginTime = Objects.isNull(BEGIN_TIME.get()) ? 0 : BEGIN_TIME.get();
        return System.currentTimeMillis() - beginTime;
    }

    /**
     * 将聚合数据结果添加到data中
     *
     * @param resultEntity 请求结果
     * @param moreData     聚合数据
     * @return ResultEntity
     */
    public static ResultEntity addMoreDataToList(ResultEntity resultEntity, Map<String, Object> moreData) {
        if (Objects.nonNull(moreData) && !moreData.isEmpty()) {
            if (Objects.isNull(resultEntity)) {
                resultEntity = ResultUtil.success(GlobalEnum.QuerySuccess);
            }
            List entityData = resultEntity.getData();
            if (Objects.isNull(entityData) || entityData.isEmpty()) {
                resultEntity.setData(List.of(moreData));
            } else {
                entityData.add(moreData);
                resultEntity.setData(entityData);
            }
            resultEntity.setSuccess(true);
        }
        if (CollectionUtils.isEmpty(resultEntity.getData()) && Objects.equals(resultEntity.getMessage(), GlobalEnum.DataEmpty)) {
            resultEntity.setMessage(GlobalEnum.QuerySuccess.getMessage());
        }
        return resultEntity;
    }
}
