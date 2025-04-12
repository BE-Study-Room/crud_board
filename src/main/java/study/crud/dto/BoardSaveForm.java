package study.crud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardSaveForm {

    //@NotNull//빈칸이어도 그냥 성공됨
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String writer;

}
