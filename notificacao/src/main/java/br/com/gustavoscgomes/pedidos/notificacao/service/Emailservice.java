package br.com.gustavoscgomes.pedidos.notificacao.service;

import br.com.gustavoscgomes.pedidos.notificacao.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Emailservice {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("pedidos-api@company.com");
        simpleMailMessage.setFrom(pedido.getEmailNotificacao());
        simpleMailMessage.setSubject("Pedido de compra");
        simpleMailMessage.setText(this.gerarMensagem(pedido));
        mailSender.send(simpleMailMessage);
    }

    private String gerarMensagem(Pedido pedido) {
        String pedidoId = pedido.getId().toString();
        String cliente = pedido.getCliente();
        String valor = String.valueOf(pedido.getValorTotal());
        String status = pedido.getStatus().name();
        return String.format("Ola %s, seu pedido de n %s no valor %s, foi realizado com sucesso. \nStatus: %s", cliente,
                pedidoId, valor, status);
    }
}
