package com.tistory.irerin07.miniwebshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_info")
@Getter
@Setter
public class paymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;
    private String method;
    private String paymentStatus;
    private Date orderDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private OrderHistory orderHistory;

    public paymentInfo(){
        this.orderDate = new Date();
    }
}
