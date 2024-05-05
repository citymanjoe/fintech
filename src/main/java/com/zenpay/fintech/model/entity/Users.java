package com.zenpay.fintech.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "m_user", uniqueConstraints = {@UniqueConstraint(name = "UniqueEmailMobileNo",
        columnNames = {"email","mobileNo"})})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String mobileNo;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(unique = true, nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String address;

    @Column(unique = true, name = "bvn")
    private String bankVerificationNo;

    @Column(unique = true, name = "nin")
    private String nationalIdentificationNo;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private String encryptPassword;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cifId")
    private List<Accounts> account;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "m_user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    Collection<Roles> roles;

    public Users(String email, String mobileNo, Date dateOfBirth, String fullName, String address, String bankVerificationNo, String nationalIdentificationNo, boolean enabled, Date createdDate, String encryptPassword, List<Accounts> account, Collection<Roles> roles) {
        this.email = email;
        this.mobileNo = mobileNo;
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
        this.address = address;
        this.bankVerificationNo = bankVerificationNo;
        this.nationalIdentificationNo = nationalIdentificationNo;
        this.enabled = enabled;
        this.createdDate = createdDate;
        this.encryptPassword = encryptPassword;
        this.account = account;
        this.roles = roles;
    }
}
