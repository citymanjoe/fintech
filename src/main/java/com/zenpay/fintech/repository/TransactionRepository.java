package com.zenpay.fintech.repository;

import com.zenpay.fintech.model.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    //@Query(value = "Select max(tran_id) from m_tran)")
    //int tranCount();
}
