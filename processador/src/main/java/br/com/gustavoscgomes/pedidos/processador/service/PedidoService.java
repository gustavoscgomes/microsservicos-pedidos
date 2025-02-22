package br.com.gustavoscgomes.pedidos.processador.service;

import br.com.gustavoscgomes.pedidos.processador.entity.ItemPedido;
import br.com.gustavoscgomes.pedidos.processador.entity.Pedido;
import br.com.gustavoscgomes.pedidos.processador.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemPedidoService itemPedidoService;

    public void save(Pedido pedido) {

        // salvar produto
        produtoService.save(pedido.getItens());

        // salvar item do pedido
        List<ItemPedido> itensPedido = itemPedidoService.save(pedido.getItens());

        // salvar pedido
        repository.save(pedido);

        // atualiza o item pedido definindo o pedido ao qual ele faz parte
        itemPedidoService.updateItemPedido(itensPedido, pedido);

        logger.info("pedido salvo: {}", pedido.toString());
    }
}
