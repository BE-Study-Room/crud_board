package study.crudboard.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    private String loginId;
    private String loginPw;
    private String name;
}
