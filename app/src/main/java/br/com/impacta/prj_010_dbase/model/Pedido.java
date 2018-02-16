package br.com.impacta.prj_010_dbase.model;

/**
 * Created by nalmir on 05/02/2018.
 */

public class Pedido {
    private long id_pedido;
    private String cod_pedido;
    private long id_contato;
    private String dt_datapedido;

    public Pedido() {
        this.id_pedido = -1;
        this.cod_pedido = "sem codigo";
        this.id_contato = -1;
        this.dt_datapedido = "Sem data";
    }


    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getCod_pedido() {
        return cod_pedido;
    }

    public void setCod_pedido(String cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    public long getId_contato() {
        return id_contato;
    }

    public void setId_contato(long id_contato) {
        this.id_contato = id_contato;
    }

    public String getDt_datapedido() {
        return dt_datapedido;
    }

    public void setDt_datapedido(String dt_datapedido) {
        this.dt_datapedido = dt_datapedido;
    }
}
