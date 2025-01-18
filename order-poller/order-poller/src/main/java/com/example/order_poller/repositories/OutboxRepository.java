package com.example.order_poller.repositories;

import com.example.order_poller.entities.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<Outbox,Long> {
    List<Outbox> findByProcessedFalse();
}
