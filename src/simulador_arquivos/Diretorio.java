/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador_arquivos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author SoldierJVX
 */
public class Diretorio extends SistemaArquivos implements Serializable{

    private HashMap<String, SistemaArquivos> nivel;
    private Diretorio raiz;

    public Diretorio(Usuario proprietario, int permissaoAcesso, Diretorio pai, Diretorio raiz) {
        super(proprietario, permissaoAcesso, pai);

        nivel = new HashMap<>();

        if (raiz == null) {

            this.raiz = this;

        } else {

            this.raiz = raiz;

        }
        
    }

    public Diretorio getRaiz() {

        return this.raiz;

    }

    public boolean addArquivo(String nome, Usuario proprietario, Diretorio pai) {

        SistemaArquivos jaExiste;

        jaExiste = buscaArquivoOuDiretorio(nome, pai);

        if (jaExiste != null) {

            return false;

        }

        Arquivo novo = new Arquivo(proprietario, 2, pai);

        nivel.put(nome, novo);

        return true;

    }

    public boolean addDiretorio(String nome, Usuario proprietario, Diretorio pai, Diretorio raiz ) {

        SistemaArquivos jaExiste;

        jaExiste = buscaArquivoOuDiretorio(nome, pai);

        if (jaExiste != null) {

            return false;

        }

        Diretorio novo = new Diretorio(proprietario, 2, pai, raiz);

        nivel.put(nome, novo);

        return true;
    }

    public SistemaArquivos buscaArquivoOuDiretorio(String nome, Diretorio pai) {

        if (pai.nivel.containsKey(nome)) {

            return pai.nivel.get(nome);

        }

        return null;

    }

    public boolean renomearArquivoOuDiretorio(String antigoNome, String novoNome, Diretorio diretorioAtual) {

        SistemaArquivos aRenomear;

        aRenomear = buscaArquivoOuDiretorio(antigoNome, diretorioAtual);

        if (aRenomear == null) {

            return false;

        }

        aRenomear.atualizarDataModificacao();

        diretorioAtual.nivel.remove(antigoNome);
        diretorioAtual.nivel.put(novoNome, aRenomear);

        return true;

    }

    public boolean removerArquivoOuDiretorio(String parte, Diretorio diretorioAtual) {

        SistemaArquivos aRemover;
        int tamanhoARemover;

        aRemover = buscaArquivoOuDiretorio(parte, diretorioAtual);

        if (aRemover == null) {

            return false;

        }

        tamanhoARemover = aRemover.getTamanho();

        this.reduzirTamanhoDiretorio(tamanhoARemover);

        diretorioAtual.nivel.remove(parte);

        return true;

    }

    public void formatLogic() {
        if (this.getPai() == null) {
            this.nivel.clear();
        } else {
            this.getPai().formatLogic();
        }
    }

    public void aumentarTamanhoDiretorio(int valorAAcrescentar) {

        this.aumentarTamanho(valorAAcrescentar);

        if (this.getPai() != null) {
            this.getPai().aumentarTamanhoDiretorio(valorAAcrescentar);
        }

    }

    public void reduzirTamanhoDiretorio(int valorAReduzir) {

        this.reduzirTamanho(valorAReduzir);

        if (this.getPai() != null) {
            this.getPai().reduzirTamanhoDiretorio(valorAReduzir);
        }

    }

}
