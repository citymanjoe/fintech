package com.zenpay.fintech.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zenpay.fintech.model.dto.enumv.AccountTypeEnum;
import com.zenpay.fintech.model.dto.enumv.CurrencyEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ToString(exclude = {"user"})
@Table(name = "m_account")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "balance")
    private BigDecimal accountBalance;

    @Column(unique = true, nullable = false)
    private String accountNo;

    @Column(unique = true, nullable = false)
    private String accountName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;

    @Column(nullable = false)
    private Date createdDate;

    private Date lastTransactionDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyEnum accountCurrency;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cifId")
    private Users user;

    public Accounts(BigDecimal accountBalance, String accountNo, String accountName, AccountTypeEnum accountType, Date createdDate, CurrencyEnum accountCurrency) {
        this.accountBalance = accountBalance;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.accountType = accountType;
        this.createdDate = createdDate;
        this.accountCurrency = accountCurrency;
    }
}
