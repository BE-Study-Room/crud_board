package study.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BoardUpdateForm {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

}
