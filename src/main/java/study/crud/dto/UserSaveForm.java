package study.crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserSaveForm {

    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    //비밀번호: 영문 대/소문자와 숫자, 특수문자를 1개 이상 포함한 8-15자의 비밀번호를 입력해야한다.
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~@#$%^&+=!])(?=\\S+$).{8,15}$",
            message = "대/소문자, 숫자, 특수문자를 1개 이상 포함한 8-15자여야합니다.")
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private String email;
}
