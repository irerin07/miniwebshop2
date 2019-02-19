package com.tistory.irerin07.miniwebshop.service;
import com.tistory.irerin07.miniwebshop.domain.Role;
import com.tistory.irerin07.miniwebshop.domain.User;
import com.tistory.irerin07.miniwebshop.repository.RoleRepository;
import com.tistory.irerin07.miniwebshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAccountAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User join(User user) {

        Role role = roleRepository.getRoleByName("USER");
        user.addRole(role);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteAccount(Long id) {
        userRepository.deleteById(id);
    }
}