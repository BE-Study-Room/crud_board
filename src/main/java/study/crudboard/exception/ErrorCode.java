package study.crudboard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-001", "사용자를 찾을 수 없습니다."),
    HAS_EMAIL(HttpStatus.BAD_REQUEST, "ACCOUNT-002", "존재하는 ID입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "ACCOUNT-003", "비밀번호가 일치하지 않습니다."),
    ARTICLE_TITLE_REQUIRED(HttpStatus.BAD_REQUEST, "ARTICLE-001", "제목은 필수입니다."),
    ARTICLE_BODY_REQUIRED(HttpStatus.BAD_REQUEST, "ARTICLE-002", "내용은 필수입니다."),
    ARTICLE_TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, "ARTICLE-003", "제목은 100자 이하이어야 합니다."),
    UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "COMMON-001", "권한이 없습니다. 본인만 수정/삭제할 수 있습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
