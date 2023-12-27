package net.fatdog.kafkaregulator.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessageResolver {
    private static final Map<Integer, String> ERROR_MESSAGES = new HashMap<>();

    static {
        ERROR_MESSAGES.put(1001, "Kafka JMX 포트에 연결을 실패하였습니다.");
        ERROR_MESSAGES.put(1002, "Kafka metric 을 가져오는 것에 실패하였습니다.");
        ERROR_MESSAGES.put(1003, "요청한 metric 이 없거나 잘못되었습니다.");
        ERROR_MESSAGES.put(9999, "오류가 발생하였습니다.");
        
    }

    public static String getErrorMessage(int errorCode) {
        return ERROR_MESSAGES.getOrDefault(errorCode, "알 수 없는 에러");
    }
}
