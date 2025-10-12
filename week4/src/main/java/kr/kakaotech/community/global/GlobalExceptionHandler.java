package kr.kakaotech.community.global;

import kr.kakaotech.community.exception.CustomException;
import kr.kakaotech.community.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        return new ResponseEntity<>(errorCode.getMessage(), errorCode.getStatus());
    }
}
