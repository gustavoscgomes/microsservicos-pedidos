package br.com.gustavoscgomes.pedidos.notificacao.listener;

import br.com.gustavoscgomes.pedidos.notificacao.entity.Pedido;
import br.com.gustavoscgomes.pedidos.notificacao.service.Emailservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final Logger logger = LoggerFactory.getLogger(PedidoListener.class);

    @Autowired
    private Emailservice emailservice;

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-notificacao")
    public void enviarNotificacao(Pedido pedido) {
        emailservice.enviarEmail(pedido);
        logger.info("Notificacao gerada: {}", pedido.toString());
    }
}
