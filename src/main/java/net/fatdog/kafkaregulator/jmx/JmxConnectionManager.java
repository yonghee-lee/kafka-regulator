package net.fatdog.kafkaregulator.jmx;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.fatdog.kafkaregulator.exception.CustomException;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.MBeanServerConnection;
import java.io.IOException;

@Slf4j
@Component
public class JmxConnectionManager {

    private JMXConnector jmxConnector;
    private String jmxUrl = "service:jmx:rmi:///jndi/rmi://my-kafka-broker-0.my-kafka-broker-headless.default.svc.cluster.local:9999/jmxrmi";
    // private String jmxUrl = "service:jmx:rmi:///jndi/rmi://my-kafka:9999/jmxrmi";

    public MBeanServerConnection getConnection() throws CustomException {
        try {
            if (jmxConnector == null) {
                
                connect();
            }
            if(!isConnected()) {
                closeConnection();
                connect();
            }
            return jmxConnector.getMBeanServerConnection();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Getting connection failed.");
            throw new CustomException(1001);
        }
    }

    private void connect() throws IOException {
        JMXServiceURL serviceUrl = new JMXServiceURL(jmxUrl);
        jmxConnector = JMXConnectorFactory.connect(serviceUrl, null);
    }

    private boolean isConnected() {
        try {
            if (jmxConnector == null) {
                return false;
            }
            jmxConnector.getMBeanServerConnection().getDefaultDomain();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // 필요한 경우 연결을 닫는 메서드
    public void closeConnection() throws IOException {
        if (jmxConnector != null) {
            jmxConnector.close();
            jmxConnector = null;
        }
    }
}
