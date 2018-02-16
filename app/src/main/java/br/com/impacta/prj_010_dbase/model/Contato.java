package br.com.impacta.prj_010_dbase.model;

/**
 * Created by nalmir on 05/02/2018.
 */

public class Contato {
    private long idcontato;
    private String nome;
    private String telefone;
    private int idade;

    public Contato() {
        this.idcontato = -1;
        this.nome = "sem nome";
        this.telefone = "sem telefone";
        this.idade = -1;
    }

    public long getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(long idcontato) {
        this.idcontato = idcontato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
