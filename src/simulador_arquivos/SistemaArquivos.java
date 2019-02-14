/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador_arquivos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SoldierJVX
 */
public abstract class SistemaArquivos implements Serializable {

    private ControleAcesso[] tabelaDeControle;
    private int tamanho;
    private Date criacao;
    private Date modificacao;
    private Date ultimoAcesso;

    private Diretorio pai;

    public SistemaArquivos(Usuario proprietario, int permissaoAcesso, Diretorio pai) {

        definirControle(proprietario, permissaoAcesso);

        this.tamanho = 0;
        this.criacao = new Date();
        this.modificacao = this.criacao;
        this.ultimoAcesso = this.criacao;
        this.pai = pai;

    }

    public Diretorio getPai() {
        return pai;
    }

    public int getTamanho() {
        return tamanho;
    }

    public String getProprietario() {

        PermissaoProprietario temp = (PermissaoProprietario) tabelaDeControle[0];

        return temp.getReferente().getLogin();

    }

    public int getPermissaoProprietario() {

        return tabelaDeControle[0].getNivelPermissao();

    }

    public int getPermissaoGrupo() {

        return tabelaDeControle[1].getNivelPermissao();

    }

    public int getPermissaoOutros() {

        return tabelaDeControle[2].getNivelPermissao();

    }

    public void setPermissaoProprietario(int valor) {

        tabelaDeControle[0].setNivelPermissao(valor);

    }

    public void setPermissaoGrupo(int valor) {

        tabelaDeControle[1].setNivelPermissao(valor);

    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String mostrarPropriedades() {

        PermissaoProprietario permissaoProprietario = (PermissaoProprietario) this.tabelaDeControle[0];
        PermissaoGrupo permissaoGrupo = (PermissaoGrupo) this.tabelaDeControle[1];
        PermissaoOutros permissaoOutros = (PermissaoOutros) this.tabelaDeControle[2];

        String texto = "";

        texto += "Proprietário: " + permissaoProprietario.getReferente().getLogin() + "\n";
        texto += "Permissão Proprietario: " + permissaoProprietario.getNivelPermissao() + "\n";
        texto += "Grupo Pertencente: " + permissaoGrupo.getGrupo() + "\n";
        texto += "Permissão Grupo: " + permissaoGrupo.getNivelPermissao() + "\n";
        texto += "Permissão Universo: " + permissaoOutros.getNivelPermissao() + "\n";
        texto += "Tamanho: " + this.tamanho + " Bytes \n";
        texto += "Data de criação: " + this.criacao + "\n";
        texto += "Data de modificação: " + this.modificacao + "\n";
        texto += "Data Ultimo Acesso: " + this.ultimoAcesso + "\n";

        return texto;

    }

    public void aumentarTamanho(int valorAAcrescentar) {

        this.tamanho += valorAAcrescentar;

    }

    public void reduzirTamanho(int valorAReduzir) {

        this.tamanho -= valorAReduzir;

    }

    public void atualizarDataModificacao() {
        this.modificacao = new Date();
    }

    public void atualizarDataUltimoAcesso() {
        this.ultimoAcesso = new Date();
    }

    private void definirControle(Usuario proprietario, int permissaoAcesso) {

        PermissaoProprietario permissaoProprietario = new PermissaoProprietario(proprietario, permissaoAcesso);
        PermissaoGrupo permissaoGrupo = new PermissaoGrupo("default", 0);
        PermissaoOutros permissaoOutros = new PermissaoOutros(0);

        tabelaDeControle = new ControleAcesso[3];

        tabelaDeControle[0] = permissaoProprietario;
        tabelaDeControle[1] = permissaoGrupo;
        tabelaDeControle[2] = permissaoOutros;

    }

}
