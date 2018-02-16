package br.com.impacta.prj_010_dbase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.impacta.prj_010_dbase.banco.Dao;
import br.com.impacta.prj_010_dbase.banco.HMAux;
import br.com.impacta.prj_010_dbase.model.Contato;
import br.com.impacta.prj_010_dbase.model.Pedido;

/**
 * Created by nalmir on 05/02/2018.
 */

public class PedidoDao extends Dao {
    public static final String TABELA = "Pedido";
    public static final String ID_PEDIDO = "idcontato";
    public static final String COD_PEDIDO = "cod_pedido";
    public static final String IDCONTATO = "id_contato";
     public static final String DT_DATAPEDIDO = "dt_datapedido";
    public static final String PEDIDOINNER = " PEDIDO P INNER JOIN ItemPedido IP on P.id_pedido = IP.id_pedido and " +
                                                        "INNER JOIN Item I on P.id_item = IP.id_item "+
                                                        "INNER JOIN Contasto on C.id_contato = P.id_contato";
    public static final String CAMPOSPEDIDOINNER = " P.cod_pedido, C.cod_contato, C.desc_contato, I.cod_item, I.desc_item, P.dt_datapedido";

    public PedidoDao(Context context) {
        super(context);
    }

    public void inserirPedido(Pedido pedido) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        cv.put(ID_PEDIDO, pedido.getId_pedido());
        cv.put(COD_PEDIDO, pedido.getCod_pedido());
        cv.put(IDCONTATO, pedido.getId_contato());
        cv.put(DT_DATAPEDIDO, pedido.getDt_datapedido());
        //
        db.insert(TABELA, null, cv);
        //
        fecharBanco();
    }

    public void atualizarPedido(Pedido pedido) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        String filtro = ID_PEDIDO + " = ? ";
        String[] argumentos = {String.valueOf(pedido.getId_pedido())};
        //
        cv.put(COD_PEDIDO, pedido.getCod_pedido());
        cv.put(IDCONTATO, pedido.getId_contato());
        cv.put(DT_DATAPEDIDO, pedido.getDt_datapedido());
        //
        db.update(TABELA, cv, filtro, argumentos);
        //
        fecharBanco();
    }

    public void apagarPedido(long id_pedido) {
        abrirBanco();
        //
        String filtro = ID_PEDIDO + " = ? ";
        String[] argumentos = {String.valueOf(id_pedido)};
        //
        db.delete(TABELA, filtro, argumentos);
        //
        fecharBanco();
    }

    public Pedido obterPedidoByID(long id_pedido) {
        Pedido cAux = null;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select * from " + TABELA + " where id_pedido = ? ";
            String[] argumentos = {String.valueOf(id_pedido)};

            cursor = db.rawQuery(comando, argumentos);

            while (cursor.moveToNext()) {
                cAux = new Pedido();
                cAux.setId_pedido(cursor.getLong(cursor.getColumnIndex(ID_PEDIDO)));
                cAux.setCod_pedido(cursor.getString(cursor.getColumnIndex(COD_PEDIDO)));
                cAux.setId_contato(cursor.getLong(cursor.getColumnIndex(IDCONTATO)));
                cAux.setDt_datapedido(cursor.getString(cursor.getColumnIndex(DT_DATAPEDIDO)));
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return cAux;
    }

    public Pedido obterPedidoCompleto(long id_pedido) {
        Pedido cAux = null;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select " + CAMPOSPEDIDOINNER + " from " + PEDIDOINNER + " where id_pedido = ? ";
            String[] argumentos = {String.valueOf(id_pedido)};

            cursor = db.rawQuery(comando, argumentos);

            while (cursor.moveToNext()) {
                cAux = new Pedido();
                cAux.setId_pedido(cursor.getLong(cursor.getColumnIndex(ID_PEDIDO)));
                cAux.setCod_pedido(cursor.getString(cursor.getColumnIndex(COD_PEDIDO)));
                cAux.setId_contato(cursor.getLong(cursor.getColumnIndex(IDCONTATO)));
                cAux.setDt_datapedido(cursor.getString(cursor.getColumnIndex(DT_DATAPEDIDO)));
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return cAux;
    }

    public ArrayList<HMAux> obterListaPedido() {
        ArrayList<HMAux> pedidos = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder comando = new StringBuilder();
            comando
                    .append(" select ")
                    .append(ID_PEDIDO)
                    .append(",")
                    .append(COD_PEDIDO)
                    .append(",")
                    .append(IDCONTATO)
                    .append(",")
                    .append(DT_DATAPEDIDO)
                    .append(" from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(COD_PEDIDO);

            cursor = db.rawQuery(comando.toString(), null);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(ID_PEDIDO,
                        cursor.getString(cursor.getColumnIndex(ID_PEDIDO))
                );
                //
                aux.put(COD_PEDIDO,
                        cursor.getString(cursor.getColumnIndex(COD_PEDIDO))
                );
                //
                aux.put(IDCONTATO,
                        cursor.getString(cursor.getColumnIndex(IDCONTATO))
                );
                //
                aux.put(DT_DATAPEDIDO,
                        cursor.getString(cursor.getColumnIndex(DT_DATAPEDIDO))
                );
                //
                pedidos.add(aux);
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return pedidos;
    }

    public long proximoID() {
        long proID = 1L;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select max(id_pedido) + 1 as id from " + TABELA;

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
