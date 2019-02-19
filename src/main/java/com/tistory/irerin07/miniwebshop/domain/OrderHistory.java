package com.tistory.irerin07.miniwebshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="order_history")
@Getter
@Setter
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가.
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
