package com.tistory.irerin07.miniwebshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String nickName;

    @Column(length = 255)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(name = "regdate")
    private Date regdate;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String contactNumber;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<OrderHistory> orderHistoryList;

    @OneToMany(mappedBy = "user")
    private List<Cart> cartList;;


    public User(){
        regdate = new Date();
        roles = new HashSet<>();

    }

    public void addRole(Role role) {
        if(roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }

}
