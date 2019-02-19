package com.tistory.irerin07.miniwebshop.repository.custom;

import com.tistory.irerin07.miniwebshop.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
    public List<User> getUsers(String searchKind, String searchStr);
    public Long getUserCount(String searchKind, String searchStr);
}
