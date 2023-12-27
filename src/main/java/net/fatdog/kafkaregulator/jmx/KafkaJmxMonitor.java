package net.fatdog.kafkaregulator.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.fatdog.kafkaregulator.exception.CustomException;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

@Lazy
@Slf4j
@Component
public class KafkaJmxMonitor {

    @Autowired
    private JmxConnectionManager jmxConnectionManager;

    /**
     * Topic 별 초당 인입되는 데이터량에 대한 metric 을 가져오는 메서드
     *
     * @param topicName Topic 이름.
     * @return 초당 인입되는 데이터량. Object 타입.
     * @throws CustomException 1002
    */
    public Object getBytesInPerSecMetric(String topicName) throws CustomException {
        
        Object bytesInPerSec = null;
        Object messageInPerSec = null;
        Object bytesOutPerSec = null;
        Object incomingByteRate = null;

        try {
            
            // Kafka 토픽의 BytesInPerSec 메트릭 ObjectName
            ObjectName objectName = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec,topic=" + topicName);
           
            // 메트릭 값 조회
            bytesInPerSec = jmxConnectionManager.getConnection().getAttribute(objectName, "MeanRate");
            log.info("BytesInPerSec for topic " + topicName + ": " + bytesInPerSec);

            objectName = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec,topic=" + topicName);

            messageInPerSec = jmxConnectionManager.getConnection().getAttribute(objectName, "MeanRate");
            log.info("MessagesInPerSec for topic " + topicName + ": " + messageInPerSec);
            
            objectName = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec,topic=" + topicName);

            bytesOutPerSec = jmxConnectionManager.getConnection().getAttribute(objectName, "MeanRate");
            log.info("BytesOutPerSec for topic " + topicName + ": " + bytesOutPerSec);
            
            // objectName = new ObjectName("kafka.producer:type=producer-metrics,client-id=test-client");

            // incomingByteRate = jmxConnectionManager.getConnection().getAttribute(objectName,"MeanRate");
            // log.info("incomingByteRate : " + incomingByteRate);
            
            return bytesInPerSec;

        } catch(IOException e) {
            log.error(e.toString());
            throw new CustomException(1002);

        } catch(AttributeNotFoundException | InstanceNotFoundException | MBeanException | ReflectionException | MalformedObjectNameException e) {
            log.error(e.toString());
            // e.printStackTrace();
    
            throw new CustomException(1003);
        }

        
    }
}
