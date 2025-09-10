package com.example.upikafka.controller;
import com.example.upikafka.entity.UpiTransaction;
import com.example.upikafka.service.UpiTransactionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class UpiTransactionController {

    private final UpiTransactionService service;

    public UpiTransactionController(UpiTransactionService service) {
        this.service = service;
    }

    @PostMapping
    public UpiTransaction create(@RequestBody UpiTransaction tx) {
        return service.create(tx);
    }

    @GetMapping
    public List<UpiTransaction> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public UpiTransaction update(@PathVariable Long id, @RequestBody UpiTransaction tx) {
        return service.update(id, tx);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
