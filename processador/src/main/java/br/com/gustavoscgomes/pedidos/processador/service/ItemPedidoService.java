package br.com.gustavoscgomes.pedidos.processador.service;

import br.com.gustavoscgomes.pedidos.processador.entity.ItemPedido;
import br.com.gustavoscgomes.pedidos.processador.entity.Pedido;
import br.com.gustavoscgomes.pedidos.processador.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    public List<ItemPedido> save(List<ItemPedido> itens) {
        return repository.saveAll(itens);
    }

    public void save(ItemPedido item) {
        repository.save(item);
    }

    public void updateItemPedido(List<ItemPedido> itens, Pedido pedido) {
        itens.forEach(item -> {
            item.setPedido(pedido); // informamos ao item o seu pedido
            this.save(item);
        });
    }
}
