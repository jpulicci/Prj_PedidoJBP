package br.com.impacta.prj_010_dbase.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.com.impacta.prj_010_dbase.R;
import br.com.impacta.prj_010_dbase.banco.Constantes;
import br.com.impacta.prj_010_dbase.banco.HMAux;
import br.com.impacta.prj_010_dbase.dao.ContatoDao;
import br.com.impacta.prj_010_dbase.model.Contato;

public class MainActivity extends BaseActivity {

    private ContatoDao contatoDao;
    private ListView lv_contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        initVars();
        initActions();
    }

    private void initVars() {
        contatoDao = new ContatoDao(context);
        //
        lv_contatos = (ListView)
                findViewById(R.id.telainicial_lv_contatos);
        //
        String[] De = {ContatoDao.NOME, ContatoDao.TELEFONE};
        int[] Para = {R.id.celula_tv_nome, R.id.celula_tv_telefone};
        lv_contatos.setAdapter(
                new SimpleAdapter(
                        context,
                        contatoDao.obterListaContatos(),
                        R.layout.celula,
                        De,
                        Para
                )
        );
    }

    private void initActions() {

        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HMAux item = (HMAux) parent.getItemAtPosition(position);
                //
                chamarTelaDetalhes(Long.parseLong(item.get(ContatoDao.IDCONTATO)));

            }
        });

    }

    private void chamarTelaDetalhes(long id) {
        Intent mIntent = new Intent(context, TelaDetalhes.class);
        mIntent.putExtra(Constantes.PARAMETRO_ID, id);
        //
        startActivity(mIntent);
        //
        finish();
        //
        Toast.makeText(context, "É nóis Véi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ic) {

            chamarTelaDetalhes(-1L);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
