package com.example.dmstodo.exception;


public class UserNotFoundException extends BusinessException{

    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
