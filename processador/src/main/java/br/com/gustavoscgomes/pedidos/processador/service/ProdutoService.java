package br.com.gustavoscgomes.pedidos.processador.service;

import br.com.gustavoscgomes.pedidos.processador.entity.ItemPedido;
import br.com.gustavoscgomes.pedidos.processador.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void save(List<ItemPedido> itens) {
        itens.forEach(item -> {
            repository.save(item.getProduto());
        });
    }
}
