package com.tistory.irerin07.miniwebshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ship_info")
@Getter
@Setter
public class ShipInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contactNumber;
    private String shipStatus;
    private Date shipDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private OrderHistory orderHistory;

    public ShipInfo(){
        this.shipDate = new Date();
    }


}
