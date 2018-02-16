package br.com.impacta.prj_010_dbase.model;

/**
 * Created by nalmir on 05/02/2018.
 */

public class Item {
    public long getId_item() {
        return id_item;
    }

    public void setId_item(long id_item) {
        this.id_item = id_item;
    }

    public String getCod_item() {
        return cod_item;
    }

    public void setCod_item(String cod_item) {
        this.cod_item = cod_item;
    }

    public String getDesc_item() {
        return desc_item;
    }

    public void setDesc_item(String desc_item) {
        this.desc_item = desc_item;
    }

    public long getVlr_precoitem() {
        return vlr_precoitem;
    }

    public void setVlr_precoitem(long vlr_precoitem) {
        this.vlr_precoitem = vlr_precoitem;
    }

    private long id_item;
    private String cod_item;
    private String desc_item;
    private long vlr_precoitem;

    public Item() {
        this.id_item = -1;
        this.cod_item = "sem item";
        this.desc_item = "sem descrição";
        this.vlr_precoitem = -1;
    }


}
