package com.zenpay.fintech.model.entity;

import com.zenpay.fintech.model.dto.enumv.TranTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "m_trans", uniqueConstraints = {@UniqueConstraint(name = "UniqueTranIdAccountNoTranDateTranPart",
columnNames = {"tran_id","account_no","tran_date","tran_part_id"})})
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(nullable = false, name = "tranType")
    @Enumerated(EnumType.STRING)
    private TranTypeEnum transactionType;

    @Column(nullable = false, name = "tranAmt")
    private BigDecimal transactionAmount;

    @Column(nullable = false, name = "tranDate")
    private Date transactionDate;

    @Column(nullable = false)
    private String accountNo;

    @Column(nullable = false)
    private boolean postedFlag;

    @Column(nullable = false, name = "tranNarration")
    private String transactionNarration;

    @Column(nullable = false, name = "tranId")
    private String transactionId;

    @Column(nullable = false, name = "tranCrncy")
    private String transactionCurrency;

    @Column(name = "refNo")
    private String transactionReferenceNo;

    @Column(nullable = false, name = "tranPartId")
    private int transactionPartitionId;

    @Column(nullable = false)
    private Date createdDate;

    public Transactions(TranTypeEnum transactionType, BigDecimal transactionAmount, Date transactionDate, String accountNo, boolean postedFlag, String transactionNarration, String transactionId, String transactionCurrency, String transactionReferenceNo, int transactionPartitionId, Date createdDate) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.accountNo = accountNo;
        this.postedFlag = postedFlag;
        this.transactionNarration = transactionNarration;
        this.transactionId = transactionId;
        this.transactionCurrency = transactionCurrency;
        this.transactionReferenceNo = transactionReferenceNo;
        this.transactionPartitionId = transactionPartitionId;
        this.createdDate = createdDate;
    }
}
