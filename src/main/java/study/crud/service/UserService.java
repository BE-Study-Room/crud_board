package study.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crud.dto.UserSaveForm;
import study.crud.dto.UserUpdateForm;
import study.crud.entity.User;
import study.crud.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(UserSaveForm form) {
        User user=User.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .email(form.getEmail())
                .build();
        User saved = userRepository.save(user);
        return saved;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new NoSuchElementException("관련된 사용자가 없습니다."));
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(()->new NoSuchElementException("관련된 사용자가 없습니다."));
    }

    public User update(UUID id,UserUpdateForm form) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("관련된 사용자가 없습니다."));
        user.updateUser(form);
        return user;
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
