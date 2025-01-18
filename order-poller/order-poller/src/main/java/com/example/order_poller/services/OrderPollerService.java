package com.example.order_poller.services;

import com.example.order_poller.entities.Outbox;
import com.example.order_poller.publisher.MessagePublisher;
import com.example.order_poller.repositories.OutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
@Slf4j
public class OrderPollerService {

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private MessagePublisher messagePublisher;

    @Scheduled(fixedRate = 60000)
    public void pollOutboxMessagesAndPublish()
    {
        //fetch unprocessed record
        //publish record to kafka/queue
        List<Outbox> outboxes=outboxRepository.findByProcessedFalse();
        log.info("unprocessed record count : {} ",outboxes.size());

        outboxes.forEach(outbox -> {
            try{
                    messagePublisher.publish(outbox.getPayload());
                    //setting the processed variable to true for not processing the record again
                    outbox.setProcessed(true);
                    outboxRepository.save(outbox);
            }
            catch (Exception e)
            {

            }
        });


    }
}
