package br.com.impacta.prj_010_dbase.model;

/**
 * Created by nalmir on 05/02/2018.
 */

public class ItemPedido {
    private long id_itempedido;
    private long id_pedido;
    private long id_item;
    private long qtd_item;
    private long vlr_precoitem;
    private long vlr_precototal;

    public ItemPedido() {
        this.id_itempedido = -1;
        this.id_pedido = -1;
        this.id_item = -1;
        this.qtd_item = -1;
    }


    public long getId_itempedido() {
        return id_itempedido;
    }

    public void setId_itempedido(long id_itempedido) {
        this.id_itempedido = id_itempedido;
    }

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public long getId_item() {
        return id_item;
    }

    public void setId_item(long id_item) {
        this.id_item = id_item;
    }

    public long getQtd_item() {
        return qtd_item;
    }

    public void setQtd_item(long qtd_item) {
        this.qtd_item = qtd_item;
    }

    public long getVlr_precoitem() {
        return vlr_precoitem;
    }

    public void setVlr_precoitem(long vlr_precoitem) {
        this.vlr_precoitem = vlr_precoitem;
    }

    public long getVlr_precototal() {
        return vlr_precototal;
    }

    public void setVlr_precototal(long vlr_precototal) {
        this.vlr_precototal = vlr_precototal;
    }
}
