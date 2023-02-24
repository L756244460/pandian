package cn.datasset.yipandian.client.exception;

import cn.datasset.yipandian.client.code.ErrorCode;
import lombok.Getter;

/**
 * 服务异常类
 */
@Getter
public class MessageException extends RuntimeException {

    private int code;
    private String message;
    private Object data;

    public MessageException(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public MessageException(String message) {
        this.code = 400;
        this.message = message;
    }

    public MessageException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MessageException(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}