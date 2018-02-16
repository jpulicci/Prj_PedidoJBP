package br.com.impacta.prj_010_dbase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.impacta.prj_010_dbase.banco.Dao;
import br.com.impacta.prj_010_dbase.banco.HMAux;
import br.com.impacta.prj_010_dbase.model.Item;
import br.com.impacta.prj_010_dbase.model.ItemPedido;

/**
 * Created by nalmir on 05/02/2018.
 */

public class ItemDao extends Dao {
    public static final String TABELA = "Item";
    public static final String ID_ITEM = "id_item";
    public static final String COD_ITEM = "cod_item";
    public static final String DESC_ITEM = "desc_item";
    public static final String VLR_PRECOITEM = "vlr_precoitem";

    public ItemDao(Context context) {
        super(context);
    }

    public void inserirItem(Item item) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        cv.put(ID_ITEM, item.getId_item());
        cv.put(COD_ITEM, item.getCod_item());
        cv.put(DESC_ITEM, item.getDesc_item());
        cv.put(VLR_PRECOITEM, item.getVlr_precoitem());
        //
        db.insert(TABELA, null, cv);
        //
        fecharBanco();
    }

    public void atualizarItem(Item item) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        String filtro = ID_ITEM + " = ? ";
        String[] argumentos = {String.valueOf(item.getId_item())};
        //
        cv.put(COD_ITEM, item.getCod_item());
        cv.put(DESC_ITEM, item.getDesc_item());
        cv.put(VLR_PRECOITEM, item.getVlr_precoitem());
        //
        db.update(TABELA, cv, filtro, argumentos);
        //
        fecharBanco();
    }

    public void apagarItem(long idcontato) {
        abrirBanco();
        //
        String filtro = ID_ITEM + " = ? ";
        String[] argumentos = {String.valueOf(idcontato)};
        //
        db.delete(TABELA, filtro, argumentos);
        //
        fecharBanco();
    }

    public Item obterItemByID(long idcontato) {
        Item cAux = null;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select * from " + TABELA + " where id_item = ? ";
            String[] argumentos = {String.valueOf(idcontato)};

            cursor = db.rawQuery(comando, argumentos);

            while (cursor.moveToNext()) {
                cAux = new Item();
                cAux.setId_item(cursor.getLong(cursor.getColumnIndex(ID_ITEM)));
                cAux.setCod_item(cursor.getString(cursor.getColumnIndex(COD_ITEM)));
                cAux.setDesc_item(cursor.getString(cursor.getColumnIndex(DESC_ITEM)));
                cAux.setVlr_precoitem(cursor.getInt(cursor.getColumnIndex(VLR_PRECOITEM)));
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return cAux;
    }

    public ArrayList<HMAux> obterListaItem() {
        ArrayList<HMAux> item = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder comando = new StringBuilder();
            comando
                    .append(" select ")
                    .append(ID_ITEM)
                    .append(",")
                    .append(COD_ITEM)
                    .append(",")
                    .append(DESC_ITEM)
                    .append(" from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(COD_ITEM);

            cursor = db.rawQuery(comando.toString(), null);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(ID_ITEM,
                        cursor.getString(cursor.getColumnIndex(ID_ITEM))
                );
                //
                aux.put(COD_ITEM,
                        cursor.getString(cursor.getColumnIndex(COD_ITEM))
                );
                //
                aux.put(DESC_ITEM,
                        cursor.getString(cursor.getColumnIndex(DESC_ITEM))
                );
                //
                item.add(aux);
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return item;
    }

    public long proximoID() {
        long proID = 1L;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select max(id_item) + 1 as id from " + TABELA;

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
