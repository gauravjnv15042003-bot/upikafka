package com.example.upikafka.service;
import com.example.upikafka.entity.UpiTransaction;
import com.example.upikafka.repository.UpiTransactionRepository;
import com.example.upikafka.service.producer.TransactionProducer;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UpiTransactionService {
    private final UpiTransactionRepository repo;
    private final TransactionProducer producer;

    public UpiTransactionService(UpiTransactionRepository repo, TransactionProducer producer) {
        this.repo = repo;
        this.producer = producer;
    }

    public UpiTransaction create(UpiTransaction tx) {
        tx.setStatus("SUCCESS");
        UpiTransaction saved = repo.save(tx);
        producer.sendTransactionEvent("New Transaction: " + saved.getId() + " Amount: " + saved.getAmount());
        return saved;
    }

    public List<UpiTransaction> getAll() {
        return repo.findAll();
    }

    public UpiTransaction update(Long id, UpiTransaction tx) {
        // fetch existing
        UpiTransaction existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id " + id));

        // update only fields you want
        existing.setSenderUpi(tx.getSenderUpi());
        existing.setReceiverUpi(tx.getReceiverUpi());
        existing.setAmount(tx.getAmount());       // ⚠ make sure this line exists
        existing.setStatus(tx.getStatus());

        // save back
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
        producer.sendTransactionEvent("Deleted Transaction: " + id);
    }
}

