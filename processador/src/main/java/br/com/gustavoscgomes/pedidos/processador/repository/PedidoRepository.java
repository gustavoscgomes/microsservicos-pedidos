package br.com.gustavoscgomes.pedidos.processador.repository;

import br.com.gustavoscgomes.pedidos.processador.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
