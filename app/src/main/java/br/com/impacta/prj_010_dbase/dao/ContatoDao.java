package br.com.impacta.prj_010_dbase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.impacta.prj_010_dbase.banco.Dao;
import br.com.impacta.prj_010_dbase.banco.HMAux;
import br.com.impacta.prj_010_dbase.model.Contato;

/**
 * Created by nalmir on 05/02/2018.
 */

public class ContatoDao extends Dao {
    public static final String TABELA = "contatos";
    public static final String IDCONTATO = "idcontato";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";
    public static final String IDADE = "idade";

    public ContatoDao(Context context) {
        super(context);
    }

    public void inserirContato(Contato contato) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        cv.put(IDCONTATO, contato.getIdcontato());
        cv.put(NOME, contato.getNome());
        cv.put(TELEFONE, contato.getTelefone());
        cv.put(IDADE, contato.getIdade());
        //
        db.insert(TABELA, null, cv);
        //
        fecharBanco();
    }

    public void atualizarContato(Contato contato) {
        abrirBanco();
        //
        ContentValues cv = new ContentValues();
        //
        String filtro = IDCONTATO + " = ? ";
        String[] argumentos = {String.valueOf(contato.getIdcontato())};
        //
        cv.put(NOME, contato.getNome());
        cv.put(TELEFONE, contato.getTelefone());
        cv.put(IDADE, contato.getIdade());
        //
        db.update(TABELA, cv, filtro, argumentos);
        //
        fecharBanco();
    }

    public void apagarContato(long idcontato) {
        abrirBanco();
        //
        String filtro = IDCONTATO + " = ? ";
        String[] argumentos = {String.valueOf(idcontato)};
        //
        db.delete(TABELA, filtro, argumentos);
        //
        fecharBanco();
    }

    public Contato obterContatoByID(long idcontato) {
        Contato cAux = null;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select * from " + TABELA + " where idcontato = ? ";
            String[] argumentos = {String.valueOf(idcontato)};

            cursor = db.rawQuery(comando, argumentos);

            while (cursor.moveToNext()) {
                cAux = new Contato();
                cAux.setIdcontato(cursor.getLong(cursor.getColumnIndex(IDCONTATO)));
                cAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                cAux.setTelefone(cursor.getString(cursor.getColumnIndex(TELEFONE)));
                cAux.setIdade(cursor.getInt(cursor.getColumnIndex(IDADE)));
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return cAux;
    }

    public ArrayList<HMAux> obterListaContatos() {
        ArrayList<HMAux> contatos = new ArrayList<>();
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder comando = new StringBuilder();
            comando
                    .append(" select ")
                    .append(IDCONTATO)
                    .append(",")
                    .append(NOME)
                    .append(",")
                    .append(TELEFONE)
                    .append(" from ")
                    .append(TABELA)
                    .append(" order by ")
                    .append(NOME);

            cursor = db.rawQuery(comando.toString(), null);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(IDCONTATO,
                        cursor.getString(cursor.getColumnIndex(IDCONTATO))
                );
                //
                aux.put(NOME,
                        cursor.getString(cursor.getColumnIndex(NOME))
                );
                //
                aux.put(TELEFONE,
                        cursor.getString(cursor.getColumnIndex(TELEFONE))
                );
                //
                contatos.add(aux);
            }

            cursor.close();
        } catch (Exception e) {
        }
        //
        fecharBanco();
        //
        return contatos;
    }

    public long proximoID() {
        long proID = 1L;
        //
        abrirBanco();
        //
        Cursor cursor = null;
        //
        try {
            String comando = " select max(idcontato) + 1 as id from " + TABELA;

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
