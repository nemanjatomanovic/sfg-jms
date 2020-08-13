package nemanja.data.sfgjms.sender;

import lombok.RequiredArgsConstructor;
import nemanja.data.sfgjms.config.JmsConfig;
import nemanja.data.sfgjms.model.HelloWorldMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;


    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("Sending message");
        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello world")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        System.out.println("Message sent");
    }
}
