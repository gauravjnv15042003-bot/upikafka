package com.example.upikafka.repository;
import com.example.upikafka.entity.UpiTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpiTransactionRepository extends JpaRepository<UpiTransaction, Long> {
}

