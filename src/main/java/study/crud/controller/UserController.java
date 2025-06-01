package study.crud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.crud.dto.UserSaveForm;
import study.crud.dto.UserUpdateForm;
import study.crud.entity.User;
import study.crud.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") UserSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            log.error("error발생: {}", bindingResult);
            return "userForm";
        }
        User user = userService.create(form);
        redirectAttributes.addAttribute("userId",user.getId());
        return "redirect:/user/{userId}";
    }

    @GetMapping("/{userId}/edit")
    public String update(@PathVariable UUID userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "userEditForm";
    }

    @PostMapping("/{userId}/edit")
    public String update(@PathVariable UUID userId, @Validated @ModelAttribute("user") UserUpdateForm userParam, BindingResult bindingResult
    , RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            log.error("error발생: {}", bindingResult);
            return "userEditForm";
        }

        userService.update(userId, userParam);
        redirectAttributes.addAttribute("userId",userId);
        return "redirect:/user";
    }

    @GetMapping("/{userId}")
    public String findById(@PathVariable UUID userId,Model model) {
        User result = userService.findById(userId);
        model.addAttribute("board", result);
        return "userDetail";
    }

    @PostMapping("/{userId}/delete")
    public String delete(@PathVariable UUID userId) {
        userService.delete(userId);
        return "redirect:/user";
    }

}
