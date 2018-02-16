package br.com.impacta.prj_010_dbase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.impacta.prj_010_dbase.banco.Dao;
import br.com.impacta.prj_010_dbase.banco.HMAux;
import br.com.impacta.prj_010_dbase.model.ItemPedido;
import br.com.impacta.prj_010_dbase.model.Pedido;

/**
 * Created by nalmir on 05/02/2018.
 */

public class ItemPedidoDao extends Dao {
    public static final String TABELA = "ItemPedido";
    public static final String ID_ITEMPEDIDO = "id_itempedido";
    public static final String ID_PEDIDO = "id_pedido";
    public static final String ID_ITEM = "id_item";
    public static final String QTD_ITEM = "qtd_item";
    public static final String VLR_PRECOITEM = "vlr_precoitem";
    public static final String VLR_PRECOTOTAL = "vlr_precototal";

    public ItemPedidoDao(Context context) {
        super(context);
    }

    public void inserirItemPedido(ItemPedido itempedido) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        cv.put(ID_ITEMPEDIDO, itempedido.getId_itempedido());
        cv.put(ID_PEDIDO, itempedido.getId_pedido());
        cv.put(ID_ITEM, itempedido.getId_item());
        cv.put(QTD_ITEM, itempedido.getQtd_item());
        cv.put(VLR_PRECOITEM, itempedido.getVlr_precoitem());
        cv.put(VLR_PRECOTOTAL, itempedido.getVlr_precototal());
        //
        db.insert(TABELA, null, cv);
        //
        fecharBanco();
    }

    public void atualizarItemPedido(ItemPedido itempedido) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        String filtro = ID_ITEMPEDIDO + " = ? ";
        String[] argumentos = {String.valueOf(itempedido.getId_itempedido())};
        //
        cv.put(ID_ITEM, itempedido.getId_item());
        cv.put(ID_PEDIDO, itempedido.getId_pedido());
        cv.put(QTD_ITEM, itempedido.getQtd_item());
        cv.put(VLR_PRECOTOTAL, itempedido.getVlr_precototal());
        cv.put(VLR_PRECOITEM, itempedido.getVlr_precoitem());
        //
        db.update(TABELA, cv, filtro, argumentos);
        //
        fecharBanco();
    }

    public void apagarItemPedido(long idcontato) {
        abrirBanco();
        //
        String filtro = ID_ITEMPEDIDO + " = ? ";
        String[] argumentos = {String.valueOf(idcontato)};
        //
        db.delete(TABELA, filtro, argumentos);
        //
        fecharBanco();
    }

    public ItemPedido obterItemPEdidoByID(long idItemPedido) {
        ItemPedido cAux = null;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select * from " + TABELA + " where id_itempedido = ? ";
            String[] argumentos = {String.valueOf(idItemPedido)};

            cursor = db.rawQuery(comando, argumentos);

            while (cursor.moveToNext()) {
                cAux = new ItemPedido();
                cAux.setId_itempedido(cursor.getLong(cursor.getColumnIndex(ID_ITEMPEDIDO)));
                cAux.setId_item(cursor.getLong(cursor.getColumnIndex(ID_ITEM)));
                cAux.setId_pedido(cursor.getLong(cursor.getColumnIndex(ID_PEDIDO)));
                cAux.setQtd_item(cursor.getLong(cursor.getColumnIndex(QTD_ITEM)));
                cAux.setVlr_precototal(cursor.getLong(cursor.getColumnIndex(VLR_PRECOTOTAL)));
                cAux.setVlr_precoitem(cursor.getLong(cursor.getColumnIndex(VLR_PRECOITEM)));
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return cAux;
    }

    public ArrayList<HMAux> obterListaItemPedido() {
        ArrayList<HMAux> itempedidos = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder comando = new StringBuilder();
            comando
                    .append(" select ")
                    .append(ID_ITEMPEDIDO)
                    .append(",")
                    .append(ID_ITEM)
                    .append(",")
                    .append(ID_PEDIDO)
                    .append(",")
                    .append(QTD_ITEM)
                    .append(",")
                    .append(VLR_PRECOTOTAL)
                    .append(",")
                    .append(VLR_PRECOITEM)
                    .append(" from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(ID_ITEM);

            cursor = db.rawQuery(comando.toString(), null);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(ID_ITEMPEDIDO,
                        cursor.getString(cursor.getColumnIndex(ID_ITEMPEDIDO))
                );
                //
                aux.put(ID_ITEM,
                        cursor.getString(cursor.getColumnIndex(ID_ITEM))
                );
                //
                aux.put(ID_PEDIDO,
                        cursor.getString(cursor.getColumnIndex(ID_PEDIDO))
                );
                //
                itempedidos.add(aux);
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return itempedidos;
    }

    public long proximoID() {
        long proID = 1L;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select max(id_itempedido) + 1 as id from " + TABELA;

            cursor = db.rawQuery(comando, null);

            while (cursor.moveToNext()) {
                proID = cursor.getLong(cursor.getColumnIndex("id"));
            }

            if (proID == 0){
                proID = 1;
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return proID;
    }
}
