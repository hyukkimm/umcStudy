package com.example.umc.common.exception;

import com.example.umc.common.apiPayload.code.BaseErrorCode;

public class UserException extends GeneralException {
    public UserException(BaseErrorCode code) {
        super(code);
    }
}