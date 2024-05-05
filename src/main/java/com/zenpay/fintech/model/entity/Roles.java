package com.zenpay.fintech.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "m_role")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String desc;
    public Roles(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
