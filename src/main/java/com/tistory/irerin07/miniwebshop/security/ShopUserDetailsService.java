package com.tistory.irerin07.miniwebshop.security;

import com.tistory.irerin07.miniwebshop.domain.Role;
import com.tistory.irerin07.miniwebshop.domain.User;
import com.tistory.irerin07.miniwebshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component //클래스 명은 Service이지만 실제로 이 클래스가 작동하는 곳은 Filter쪽이다. 즉, Bean으로 등록하기 위해서는 Service가 아닌 Componenet를 사용해야한다.
@RequiredArgsConstructor //final이 붙은 필드를 초기화 해주는 생성자를 자동으로 생성해준다. 생성자 주입시 사용한다.
public class ShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = false)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        User user = userRepository.getUserByEmail(email);
        if(user == null) {
            System.out.println("null User");
            throw new UsernameNotFoundException(email + "에 해당하는 사용자 없어!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for(Role role : roles){
            System.out.println(role.getName());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        // 리턴한 값은 세션에 저장된다.
        ShopSecurityUser shopSecurityUser = new ShopSecurityUser(email, user.getPassword(), authorities);
        shopSecurityUser.setId(user.getId());
        shopSecurityUser.setName(user.getName());
        shopSecurityUser.setNickName(user.getNickName());
        System.out.println(shopSecurityUser);
        return shopSecurityUser;
    }
}
