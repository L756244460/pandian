package cn.datasset.yipandian.client.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.datasset.yipandian.client.code.ErrorCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import lombok.Data;

/**
 * @author lzl
 */
@Data
public class ResultVO {

    private Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean success;

    private String message;

    private Object data;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long total;

    private static ResultVO SUCCESS = new ResultVO();

    public static ResultVO EXCEPTION = new ResultVO(ErrorCode.RUNTIME_EXCEPTION);

    private static ResultVO FAIL = new ResultVO();

    public ResultVO() {
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMessage();
    }

    public ResultVO(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ResultVO success() {
        return SUCCESS;
    }

    public static ResultVO success(ErrorCode errorCode, String... str) {
        String msg = String.format(errorCode.getMessage(), str);
        return success(errorCode.getCode(), msg);
    }

    public static ResultVO success(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static ResultVO success(Long total) {
        ResultVO resultVO = new ResultVO();
        resultVO.setTotal(total);
        return resultVO;
    }

    public static ResultVO success(ErrorCode code) {
        return new ResultVO(code);
    }

    public static ResultVO success(Object data, long total) {
        ResultVO resultVO = new ResultVO();
        Map<String, Object> result = new HashMap<>(3);
        result.put("list", data!=null ?  data : new ArrayList<>());
        result.put("total",total);
        resultVO.setData(result);
        return resultVO;
    }

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        Map<String, Object> result = new HashMap<>(3);
        if (data instanceof Page) {
            result.put("list", ((Page) data).getRecords());
            result.put("total", ((Page) data).getTotal());
            resultVO.setData(result);
        } else if (data instanceof PageInfo) {
            result.put("list", ((PageInfo) data).getList());
            result.put("total", ((PageInfo) data).getTotal());
            resultVO.setData(result);
        } else {
            resultVO.setData(data);
        }
        return resultVO;
    }

    public static ResultVO success(Object data, boolean success) {
        ResultVO resultVO = new ResultVO();
        if (data instanceof Page) {
            resultVO.setData(((Page) data).getRecords());
            resultVO.setTotal(((Page) data).getTotal());
        } else {
            resultVO.setData(data);
        }
        resultVO.setSuccess(success);

        return resultVO;
    }

    public static ResultVO fail(ErrorCode errorCode) {
        return fail(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultVO fail() {
        return fail(ErrorCode.FAIL.getCode(), ErrorCode.FAIL.getMessage());
    }

    public static ResultVO fail(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }

    public static ResultVO fail(String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ErrorCode.FAIL.getCode());
        resultVO.setMessage(msg);
        return resultVO;
    }

    @Override
    public String toString() {
        return "code=" + code + ",message=" + message;
    }
}
