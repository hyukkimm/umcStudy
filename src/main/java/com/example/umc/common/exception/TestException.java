package com.example.umc.common.exception;


import com.example.umc.common.apiPayload.code.BaseErrorCode;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}
