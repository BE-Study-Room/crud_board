package study.crudboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 2, max = 20, message = "아이디는 2자 이상, 20자 이하로 입력해야 합니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 2, max = 20, message = "비밀번호는 2자 이상, 20자 이하로 입력해야 합니다.")
    private String loginPw;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;
}
