package net.fatdog.kafkaregulator.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.fatdog.kafkaregulator.exception.CustomException;
import net.fatdog.kafkaregulator.jmx.KafkaJmxMonitor;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    KafkaJmxMonitor kafkaJmxMonitor;

    @Scheduled(fixedRate = 5000)
    public void someScheduledTask() throws CustomException {

        log.info("Periodical Task...");
        // 여기에 주기적으로 실행할 로직을 구현
        try {

            log.info("Input Bytes per Second: " + kafkaJmxMonitor.getBytesInPerSecMetric("test-topic"));

        } catch (CustomException e) {
            // TODO Auto-generated catch block
            log.error(e.getErrorMessage());
            log.info("Not yet.");
            
        }
        
    }
}