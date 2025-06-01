package study.crud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserUpdateForm {
    @NotBlank(message = "이름은 필수입니다.")
    private String Username;

}
