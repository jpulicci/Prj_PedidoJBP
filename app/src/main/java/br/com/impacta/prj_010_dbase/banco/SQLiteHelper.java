package br.com.impacta.prj_010_dbase.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nalmir on 05/02/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            StringBuilder sb = new StringBuilder();
            //
            sb.append("CREATE TABLE IF NOT EXISTS [contatos] (\n" +
                    "  [idcontato] INT, \n" +
                    "  [nome] TEXT NOT NULL, \n" +
                    "  [telefone] TEXT NOT NULL, \n" +
                    "  [idade] INT NOT NULL, \n" +
                    "  CONSTRAINT [] PRIMARY KEY ([idcontato]));");
            //
            String[] comandos = sb.toString().split(";");
            //
            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }

            sb.delete(0,comandos.length);

            //
            sb.append("CREATE TABLE IF NOT EXISTS [item] (\n" +
                    "  [id_item] INT, \n" +
                    "  [cod_item] TEXT NOT NULL, \n" +
                    "  [desc_item] TEXT NOT NULL, \n" +
                    "  [vlr_precoitem] REAL NOT NULL, \n" +
                    "  CONSTRAINT [] PRIMARY KEY ([id_item]));");
            //
            comandos = sb.toString().split(";");
            //
            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }

            sb.delete(0,comandos.length);

            //
            sb.append("CREATE TABLE IF NOT EXISTS [Pedido] (\n" +
                    "  [id_pedido] INT, \n" +
                    "  [cod_pedido] TEXT NOT NULL, \n" +
                    "  [id_contato] INT NOT NULL, \n" +
                    "  [dt_datapedido] TEXT NOT NULL, \n" +
                    "  CONSTRAINT [] PRIMARY KEY ([id_pedido]));");
            //
            comandos = sb.toString().split(";");
            //
            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }

            sb.append("CREATE TABLE IF NOT EXISTS [ItemPedido] (\n" +
                    "  [id_itempedido] INT, \n" +
                    "  [id_pedido] INT NOT NULL, \n" +
                    "  [id_item] INT, \n"+
                    "  [qtd_item] REAL NOT NULL, \n" +
                    "  [vlr_precoitem] REAL NOT NULL, \n" +
                    "  [vlr_precototal] REAL NOT NULL, \n" +
                    "  CONSTRAINT [] PRIMARY KEY ([id_itempedido]));");
            //
            comandos = sb.toString().split(";");
            //
            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }

        } catch (Exception e) {
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            StringBuilder sb = new StringBuilder();
            //
            sb.append("DROP TABLE IF EXISTS [contatos];");
            //
            String[] comandos = sb.toString().split(";");
            //
            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }
            // Item
            sb.delete(0,comandos.length);
            sb.append("DROP TABLE IF EXISTS [Item];");
            //
            comandos = sb.toString().split(";");
            //
            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }
            // Item
            sb.delete(0,comandos.length);
            sb.append("DROP TABLE IF EXISTS [ItemPEdido];");
            //
            comandos = sb.toString().split(";");
            //
            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }
            // Item
            sb.delete(0,comandos.length);
            sb.append("DROP TABLE IF EXISTS [Pedido];");
            //
            comandos = sb.toString().split(";");
            //
            for (int i = 0; i < comandos.length; i++) {
                db.execSQL(comandos[i].toLowerCase());
            }
        } catch (Exception e) {
        }

        onCreate(db);
    }
}
