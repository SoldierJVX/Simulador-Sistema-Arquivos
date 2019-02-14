/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador_arquivos;

import java.io.Serializable;

/**
 *
 * @author SoldierJVX
 */
public class Arquivo extends SistemaArquivos implements Serializable{

    private String conteudo;

    public Arquivo( Usuario proprietario, int permissaoAcesso, Diretorio pai) {
        super(proprietario, permissaoAcesso, pai);
        this.conteudo = null;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void append(String conteudoAAdicionar) {
        if (conteudo == null) {
            conteudo = "";
        }
        conteudo += conteudoAAdicionar;

        this.aumentarTamanho(conteudoAAdicionar.length());

        if (this.getPai() != null) {
            this.getPai().aumentarTamanhoDiretorio(conteudoAAdicionar.length());
        }
    }

}
