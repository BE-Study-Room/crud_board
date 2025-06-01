package study.crud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import study.crud.entity.User;

@Getter
@Builder
@AllArgsConstructor
public class BoardSaveForm {

    //@NotNull//빈칸이어도 그냥 성공됨
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private User writer;
}
