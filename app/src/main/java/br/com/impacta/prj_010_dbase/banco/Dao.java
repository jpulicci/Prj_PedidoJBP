package br.com.impacta.prj_010_dbase.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nalmir on 05/02/2018.
 */

public class Dao {

    private Context context;
    protected SQLiteDatabase db;

    public Dao(Context context) {
        this.context = context;
    }

    public void abrirBanco(){
        SQLiteHelper criarbanco = new SQLiteHelper(
                context,
                Constantes.BANCO,
                null,
                Constantes.VERSAO
        );

        this.db = criarbanco.getWritableDatabase();
    }

    public void fecharBanco(){
        if (db != null){
            db.close();
        }
    }
}
