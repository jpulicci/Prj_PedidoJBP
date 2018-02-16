package br.com.impacta.prj_010_dbase.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import br.com.impacta.prj_010_dbase.R;
import br.com.impacta.prj_010_dbase.banco.Constantes;
import br.com.impacta.prj_010_dbase.dao.ContatoDao;
import br.com.impacta.prj_010_dbase.model.Contato;

/**
 * Created by nalmir on 06/02/2018.
 */

public class TelaDetalhes extends BaseActivity {

    private ContatoDao contatoDao;

    private EditText et_codigo;
    private EditText et_nome;
    private EditText et_telefone;
    private EditText et_idade;

    private Button btn_excluir;
    private Button btn_salvar;

    private String msgAux;

    private long idAtual;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teladetalhes);

        initVars();
        initActions();
    }

    private void initVars() {
        contatoDao = new ContatoDao(context);
        //
        et_codigo = (EditText)
                findViewById(R.id.teladetalhes_et_codigo);
        et_nome = (EditText)
                findViewById(R.id.teladetalhes_et_nome);

        et_telefone = (EditText)
                findViewById(R.id.teladetalhes_et_telefone);

        et_idade = (EditText)
                findViewById(R.id.teladetalhes_et_idade);
        btn_excluir = (Button)
                findViewById(R.id.teladetalhes_btn_excluir);
        btn_salvar = (Button)
                findViewById(R.id.teladetalhes_btn_salvar);
        //
        recuperarParametros();
        //
        if (idAtual != -1) {
            Contato cAux = contatoDao.obterContatoByID(idAtual);
            //
            et_codigo.setText(String.valueOf(cAux.getIdcontato()));
            et_nome.setText(cAux.getNome());
            et_telefone.setText(cAux.getTelefone());
            et_idade.setText(String.valueOf(cAux.getIdade()));
            //
            btn_excluir.setVisibility(View.VISIBLE);
        } else {
            btn_excluir.setVisibility(View.GONE);
        }
    }

    private void recuperarParametros() {
        idAtual = getIntent().getLongExtra(Constantes.PARAMETRO_ID, 0);
    }

    private void initActions() {

        btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contatoDao.apagarContato(idAtual);
                //
                chamarTelaInicial();
            }
        });

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validacao()) {
                    salvar();
                } else {
                    exibirMensagem("Validacao Contato",msgAux);
                }
            }
        });

    }

    private boolean validacao() {
        String nome = et_nome.getText().toString().trim();
        String telefone = et_telefone.getText().toString().trim();
        String idade = et_idade.getText().toString().trim();

        if (nome.length() == 0) {
            msgAux = "Erro: Nome é Obrigatorio!!!";

            return false;
        }
        //
        if (telefone.length() == 0) {
            msgAux = "Erro: Telefone é Obrigatorio!!!";

            return false;
        }
        //
        if (idade.length() == 0) {
            msgAux = "Erro: Idade é Obrigatoria!!!";

            return false;
        }
        //
        try {
            Integer.parseInt(idade);
        } catch (Exception e) {
            msgAux = "Erro: Idade é Invalida!!!";

            return false;
        }
        //
        return true;
    }

    private void salvar() {
        Contato cAux = new Contato();
        cAux.setNome(et_nome.getText().toString().trim());
        cAux.setTelefone(et_telefone.getText().toString().trim());
        cAux.setIdade(Integer.parseInt(et_idade.getText().toString()));
        //
        if (idAtual != -1) {
            // atualizacao
            cAux.setIdcontato(idAtual);
            //
            contatoDao.atualizarContato(cAux);
        } else {
            // inclusacao
            idAtual = contatoDao.proximoID();
            cAux.setIdcontato(idAtual);
            //
            contatoDao.inserirContato(cAux);
            //
            et_codigo.setText(String.valueOf(cAux.getIdcontato()));
            //
            btn_excluir.setVisibility(View.VISIBLE);
        }
    }

    private void chamarTelaInicial() {
        Intent mIntent = new Intent(context, MainActivity.class);
        startActivity(mIntent);
        //
        finish();
    }

    @Override
    public void onBackPressed() {
        temCerteza();
    }

    private void temCerteza() {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaDetalhes.this);
        alerta.setTitle("Saida da Tela");
        alerta.setMessage("Tem Certeza que voce quer sair?");
        alerta.setIcon(R.mipmap.ic_launcher_round);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chamarTelaInicial();
            }
        });
        alerta.setNegativeButton("Nao", null);
        //
        alerta.show();
    }

    private void exibirMensagem(String titulo, String mensagem) {
        AlertDialog.Builder alerta =
                new AlertDialog.Builder(TelaDetalhes.this);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        alerta.setIcon(R.mipmap.ic_launcher_round);
        alerta.setPositiveButton("Sim", null);
        //
        alerta.show();
    }
}
