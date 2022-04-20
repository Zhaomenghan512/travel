package com.travel.auth.config.exception;


import com.travel.common.exception.BaseException;

/**
 * 描述：
 */
public class TokenException extends BaseException {

    private static final long serialVersionUID = 1L;

    public TokenException(String message) {
        super(message);
    }
}
