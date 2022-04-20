package com.travel.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: ResResult 返回对象
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */

@Data
public class ResResult<T> implements Serializable {

    private static final long serialVersionUID = 3468352004150968551L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回对象
     */
    private T data;

    public ResResult() {
        super();
    }

    public ResResult(Integer code) {
        super();
        this.code = code;
    }

    public ResResult(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResResult(Integer code, Throwable throwable) {
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ResResult(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ResResult(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResResult<?> other = (ResResult<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }

    /**
     * 通用状态码
     * <p>
     * Description:
     */
    public class CodeStatus {
        /**
         * 请求成功
         */
        public static final int OK = 20000;

        /**
         * 请求失败
         */
        public static final int FAIL = 20002;

        /**
         * 熔断请求
         */
        public static final int BREAKING = 20004;

        /**
         * 非法请求
         */
        public static final int ILLEGAL_REQUEST = 50000;

        /**
         * 非法请求
         */
        public static final int LOGIN_FAIL = 50001;


    }
}
