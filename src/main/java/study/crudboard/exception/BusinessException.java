package study.crudboard.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String redirectUrl;

    public BusinessException(ErrorCode errorCode, String redirectUrl) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.redirectUrl = redirectUrl;
    }
}
