package br.com.gustavoscgomes.pedidos.api.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    /**
     * Define um Exchange do tipo Fanout no RabbitMQ.
     * Um Fanout Exchange distribui mensagens para todas as filas que estão vinculadas a ele.
     */
    @Bean
    public Exchange pedidosExchange() {
        return new FanoutExchange(exchangeName);
    }

    /**
     * Cria e configura um RabbitAdmin, que gerencia automaticamente a criação e configuração de exchanges, filas e bindings.
     * O RabbitAdmin usa a ConnectionFactory fornecida para se conectar ao RabbitMQ.
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * Configura um conversor de mensagens que transforma objetos Java em JSON e vice-versa.
     * Isso permite enviar e receber mensagens no formato JSON automaticamente.
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Cria um RabbitTemplate, que é usado para enviar mensagens ao RabbitMQ.
     * O RabbitTemplate utiliza a ConnectionFactory para se conectar ao broker e o MessageConverter para converter mensagens.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    /**
     * Define um listener que será acionado quando a aplicação estiver pronta.
     * Quando o evento ApplicationReadyEvent ocorre, o RabbitAdmin é inicializado automaticamente,
     * garantindo que as filas e exchanges sejam configuradas corretamente no RabbitMQ.
     */
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
//        return new ApplicationListener<ApplicationReadyEvent>() {
//            @Override
//            public void onApplicationEvent(ApplicationReadyEvent event) {
//                rabbitAdmin.initialize();
//            }
//        };
        return event -> rabbitAdmin.initialize();
    }
}
