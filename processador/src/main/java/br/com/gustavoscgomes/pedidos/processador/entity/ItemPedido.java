package br.com.gustavoscgomes.pedidos.processador.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemPedido(UUID id, Produto produto, Integer quantidade, Pedido pedido) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.pedido = pedido;
    }

    public ItemPedido() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", pedido=" + pedido +
                '}';
    }
}
