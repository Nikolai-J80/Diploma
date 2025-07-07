package ru.nikola.diploma.cloudstorageapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nikola.diploma.cloudstorageapplication.entity.User;
import ru.nikola.diploma.cloudstorageapplication.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(("Пользователь не найден")));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUserName;
    }
}
