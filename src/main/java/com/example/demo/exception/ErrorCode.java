package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //400  BAD_REQUEST : 잘못된 요청
    DUPLICATED_ROOM_NAME(HttpStatus.BAD_REQUEST,"400_1", " 방이름이 이미 존재합니다."),
    INPUT_ROOM_NAME(HttpStatus.BAD_REQUEST,"400_2", "방 이름을 입력해 주세요."),
    ALREADY_ENTER_ROOM(HttpStatus.BAD_REQUEST, "400_3", "이미 입장한 방입니다."),

    //403 FORBIDDEN : 권한 없음
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "403", "요청에 대한 권한이 없습니다."),

    //404 NOT_FOUND : Resource 를 찾을 수 없음
    NOT_FOUND_USER(HttpStatus.NOT_FOUND,"404_1", "해당 사용자가 존재하지 않습니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "404_2", "해당 게시글이 존재하지 않습니다."),
    NOT_FOUND_ROOM(HttpStatus.NOT_FOUND, "404_3", " 해당 룸이 존재하지 않습니다."),

    //500 INTERNAL_SERVER_ERROR : 서버 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 에러");

    private final HttpStatus httpStatus;
    private final  String  errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus,String errorCode,String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
