package net.katrinka.sb2ongcp.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Configuration
class MessageService {
    private final String topic;
    private final PubSubTemplate template;

    public MessageService(@Value("${sbmessage.topic:firstTopic}") String topic,
                          PubSubTemplate template) {
        this.topic = topic;
        this.template = template;
    }

    void sendMessage(String message) {
        log.info("Got message: {}", message);
        template.publish(topic, message);
    }

    @Bean
    SubscribableChannel outgoing() {
        return MessageChannels.direct().get();
    }

    @Bean
    PubSubMessageHandler pubSubMessageHandler() {
        return new PubSubMessageHandler(template, topic);
    }

    @Bean
    IntegrationFlow publisherFlow() {
        return IntegrationFlows.from(this.outgoing())
                .handle(this.pubSubMessageHandler())
                .get();
    }
}
