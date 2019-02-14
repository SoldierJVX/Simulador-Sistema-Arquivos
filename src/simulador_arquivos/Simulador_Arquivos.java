/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador_arquivos;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author SoldierJVX
 */
public class Simulador_Arquivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        ListaUsuarios listaUsuarios = null;
        Usuario usuarioAtual = null;
        String comando;
        String partes[];
        Diretorio diretorioAtual = null;

        listaUsuarios = Persistencia.carregarUsuarios(listaUsuarios);
        diretorioAtual = Persistencia.carregarDiretorios(diretorioAtual);

        if (listaUsuarios != null) {
            usuarioAtual = listaUsuarios.getUser("root");
        } else {

            listaUsuarios = new ListaUsuarios();

            listaUsuarios.addUser("root", "toor", true); // Adicionando usuário root
            usuarioAtual = listaUsuarios.getUser("root");

            diretorioAtual = new Diretorio(usuarioAtual, 2, null, null);

            diretorioAtual.addDiretorio("root", usuarioAtual, diretorioAtual, diretorioAtual);

        }

        while (true) {

            System.out.print(usuarioAtual.getLogin() + "@localhost:");
            comando = teclado.nextLine();
            partes = comando.split(" ");

            if (partes[0].equals("exit")) {
                diretorioAtual = diretorioAtual.getRaiz();
                Persistencia.salvarUsuarios(listaUsuarios);
                Persistencia.salvarDiretorios(diretorioAtual);
                System.exit(0);
            } else if (partes[0].equals("format")) {
                diretorioAtual = format(diretorioAtual, usuarioAtual);
            } else {

                if (partes.length >= 2) {

                    if (partes[0].equals("adduser")) {
                        addUser(partes[1], listaUsuarios, diretorioAtual);
                    } else if (partes[0].equals("removeuser")) {
                        diretorioAtual = removeUser(partes[1], usuarioAtual, listaUsuarios, diretorioAtual);
                    } else if (partes[0].equals("cd")) {
                        diretorioAtual = cd(partes[1], diretorioAtual, usuarioAtual);
                    } else if (partes[0].equals("crfile")) {
                        crFile(partes[1], diretorioAtual, usuarioAtual);
                    } else if (partes[0].equals("mkdir")) {
                        mkDir(partes[1], diretorioAtual, usuarioAtual);
                    } else if (partes[0].equals("rm")) {
                        rm(partes[1], diretorioAtual, usuarioAtual);
                    } else if (partes[0].equals("properties")) {
                        properties(partes[1], diretorioAtual, usuarioAtual);
                    } else if (partes[0].equals("cat")) {
                        cat(partes[1], diretorioAtual, usuarioAtual);
                    } else {

                        if (partes.length == 3) {

                            if (partes[0].equals("ren")) {
                                ren(partes[1], partes[2], diretorioAtual, usuarioAtual);
                            } else if (partes[0].equals("append")) {

                                String textoAppend = "";

                                for (int i = 2; i < partes.length; i++) {
                                    if (i + 1 == partes.length) {
                                        textoAppend += partes[i];
                                    } else {
                                        textoAppend += partes[i] + " ";
                                    }

                                }

                                append(partes[1], textoAppend, diretorioAtual, usuarioAtual);
                            } else {
                                System.out.println("Comando/Parametros incorretos!");
                            }

                        } else if (partes.length >= 4) {

                            if (partes[0].equals("chmod")) {
                                chMod(partes[1], partes[2], partes[3], listaUsuarios, diretorioAtual);
                            } else if (partes[0].equals("append")) {

                                String textoAppend = "";

                                for (int i = 2; i < partes.length; i++) {
                                    if (i + 1 == partes.length) {
                                        textoAppend += partes[i];
                                    } else {
                                        textoAppend += partes[i] + " ";
                                    }

                                }

                                append(partes[1], textoAppend, diretorioAtual, usuarioAtual);
                            } else {
                                System.out.println("Comando/Parametros incorretos!");
                            }

                        }

                    }

                } else {
                    System.out.println("Comando/Parametros incorretos!");
                }

            }

        }

    }

    private static void addUser(String nomeUsuario, ListaUsuarios listaUsuarios, Diretorio diretorioAtual) {

        Diretorio raiz;

        boolean usuarioJaExiste;
        boolean senhaOk;
        String password;
        String confPassword;
        Scanner teclado;

        teclado = new Scanner(System.in);
        senhaOk = false;

        usuarioJaExiste = listaUsuarios.buscarLogin(nomeUsuario);

        if (usuarioJaExiste) {
            System.out.println("Usuário já cadastrado!");
            return;
        }

        while (!senhaOk) {

            System.out.print("Digite a senha: ");
            password = teclado.nextLine();
            System.out.print("Confirme a senha: ");
            confPassword = teclado.nextLine();

            if (password.equals(confPassword)) {
                listaUsuarios.addUser(nomeUsuario, password, false);
                senhaOk = true;
            } else {
                System.out.println("Senhas não conferem!");
            }

        }

        raiz = diretorioAtual.getRaiz();

        raiz.addDiretorio(nomeUsuario, listaUsuarios.getUser(nomeUsuario), diretorioAtual, raiz);

    }

    private static Diretorio removeUser(String usuarioARemover, Usuario atual, ListaUsuarios listaUsuarios, Diretorio diretorioAtual) {

        Diretorio raiz;

        if (!atual.isRoot()) {
            System.out.println("Somente root pode excluir usuário!");
            return diretorioAtual;
        }

        if (listaUsuarios.getUser(usuarioARemover) == null) {
            System.out.println("Usuário não encontrado!");
            return diretorioAtual;
        }

        listaUsuarios.removeUser(usuarioARemover);

        raiz = diretorioAtual.getRaiz();

        raiz.removerArquivoOuDiretorio(usuarioARemover, raiz);

        return diretorioAtual.getRaiz();

    }

    private static Diretorio cd(String caminho, Diretorio diretorioAtual, Usuario usuarioAtual) {

        if (caminho.equals(".")) {

            return diretorioAtual;

        }

        if (caminho.equals("..")) {

            if (diretorioAtual.getPai() != null) {
                return diretorioAtual.getPai();
            }
            return diretorioAtual;
        }

        if (caminho.equals("~")) {

            return (Diretorio) diretorioAtual.getRaiz().buscaArquivoOuDiretorio(usuarioAtual.getLogin(), diretorioAtual.getRaiz());

        }

        if (caminho.equals("/")) {
            return diretorioAtual.getRaiz();
        }

        String partesCaminho[] = caminho.split("/");

        if (partesCaminho[0].equals("")) {
            return navegandoDiretorios(partesCaminho, diretorioAtual.getRaiz(), 1);
        }

        if (partesCaminho[0].equals(".")) {
            return navegandoDiretorios(partesCaminho, diretorioAtual, 1);
        }

        return navegandoDiretorios(partesCaminho, diretorioAtual, 0);

    }

    private static void crFile(String parte, Diretorio diretorioAtual, Usuario usuarioAtual) {

        boolean arquivoCriado;
        boolean temPermissao = false;
        
        temPermissao = verificarPermissao(diretorioAtual, usuarioAtual);
        
        if (!temPermissao) {
            System.out.println("Não possui permissao! ");
            return;
        }

        arquivoCriado = diretorioAtual.addArquivo(parte, usuarioAtual, diretorioAtual);
        

        if (!arquivoCriado) {
            System.out.println("Nome já existente!");
        }

    }

    private static void mkDir(String parte, Diretorio diretorioAtual, Usuario usuarioAtual) {

        boolean diretorioCriado;
        boolean temPermissao = false;
        
        temPermissao = verificarPermissao(diretorioAtual, usuarioAtual);
        
        if (!temPermissao) {
            System.out.println("Não possui permissao! ");
            return;
        }

        diretorioCriado = diretorioAtual.addDiretorio(parte, usuarioAtual, diretorioAtual, diretorioAtual.getRaiz());

        if (!diretorioCriado) {
            System.out.println("Nome já existente!");
        }

    }

    private static void ren(String antigoNome, String novoNome, Diretorio diretorioAtual, Usuario usuarioAtual) {

        SistemaArquivos arq = diretorioAtual.buscaArquivoOuDiretorio(antigoNome, diretorioAtual);
        boolean temPermissao = false;

        if (arq == null) {
            System.out.println("Arquivo/Diretório não encontrado!");
            return;
        }

        temPermissao = verificarPermissao(arq, usuarioAtual);

        if (!temPermissao) {
            System.out.println("Não possui permissao! ");
            return;
        }

        diretorioAtual.renomearArquivoOuDiretorio(antigoNome, novoNome, diretorioAtual);

    }

    private static void rm(String aRemover, Diretorio diretorioAtual, Usuario usuarioAtual) {

        SistemaArquivos arq = diretorioAtual.buscaArquivoOuDiretorio(aRemover, diretorioAtual);
        boolean temPermissao = false;

        if (arq == null) {
            System.out.println("Arquivo/Diretório não encontrado!");
            return;
        }

        temPermissao = verificarPermissao(arq, usuarioAtual);

        if (!temPermissao) {
            System.out.println("Não possui permissão! ");
            return;
        }

        diretorioAtual.removerArquivoOuDiretorio(aRemover, diretorioAtual);

    }

    private static void chMod(String stringArquivo, String stringNivel, String usuario, ListaUsuarios listaUsuarios, Diretorio diretorioAtual) {

        Usuario aModificar;
        int novoNivel;
        SistemaArquivos arquivo;
        String comparacaoProprietario;

        aModificar = listaUsuarios.getUser(usuario);

        if (aModificar == null) {

            System.out.println("Usuário não encontrado");
            return;

        }

        try {

            novoNivel = Integer.parseInt(stringNivel);

            if (novoNivel < 0 || novoNivel > 2) {

                throw new Exception();

            }

        } catch (Exception e) {

            System.out.println("Novo valor de acesso inválido!");
            return;
        }

        arquivo = diretorioAtual.buscaArquivoOuDiretorio(stringArquivo, diretorioAtual);

        if (arquivo == null) {
            System.out.println("Arquivo não encontrado!");
            return;
        }

        if (arquivo.getClass() != Arquivo.class) {
            System.out.println("Não é arquivo!");
            return;
        }

        comparacaoProprietario = arquivo.getProprietario();

        if (comparacaoProprietario.equals(usuario)) {

            arquivo.setPermissaoProprietario(novoNivel);

        } else {

            arquivo.setPermissaoGrupo(novoNivel);

        }

    }

    private static void properties(String parte, Diretorio diretorioAtual, Usuario usuarioAtual) {

        SistemaArquivos aMostrar;
        boolean temPermissao = false;

        aMostrar = diretorioAtual.buscaArquivoOuDiretorio(parte, diretorioAtual);

        if (aMostrar == null) {
            System.out.println("Arquivo/Diretorio não encontrado!");
            return;
        }

        temPermissao = verificarPermissaoSemLeituraEEscrita(aMostrar, usuarioAtual);

        if (!temPermissao) {
            System.out.println("Não possui permissao! ");
            return;
        }

        System.out.println(aMostrar.mostrarPropriedades());

    }

    private static void cat(String parte, Diretorio diretorioAtual, Usuario usuarioAtual) {

        SistemaArquivos arquivoBuscado;
        Arquivo aLer;
        boolean temPermissao = false;

        arquivoBuscado = diretorioAtual.buscaArquivoOuDiretorio(parte, diretorioAtual);

        if (arquivoBuscado == null) {
            System.out.println("Arquivo não encontrado!");
            return;
        }

        temPermissao = verificarPermissaoSemLeituraEEscrita(arquivoBuscado, usuarioAtual);

        if (!temPermissao) {
            System.out.println("Não possui permissao! ");
            return;
        }

        if (arquivoBuscado.getClass() != Arquivo.class) {
            System.out.println("Não é arquivo!");
            return;
        }

        aLer = (Arquivo) arquivoBuscado;

        if (aLer.getConteudo() == null) {
            return;
        }

        aLer.atualizarDataUltimoAcesso();

        System.out.println(aLer.getConteudo());
    }

    private static Diretorio format(Diretorio diretorioAtual, Usuario usuarioAtual) {

        if (!usuarioAtual.isRoot()) {
            System.out.println("Somente root!");
            return diretorioAtual;
        }

        diretorioAtual.formatLogic();
        return diretorioAtual.getRaiz();
    }

    private static void append(String nomeArquivo, String conteudoAAdicionar, Diretorio diretorioAtual, Usuario usuarioAtual) {
        SistemaArquivos arquivoBuscado;
        Arquivo aEscrever;
        boolean temPermissao = false;

        arquivoBuscado = diretorioAtual.buscaArquivoOuDiretorio(nomeArquivo, diretorioAtual);

        if (arquivoBuscado == null) {
            System.out.println("Arquivo não encontrado!");
            return;
        }

        temPermissao = verificarPermissao(arquivoBuscado, usuarioAtual);

        if (!temPermissao) {
            System.out.println("Não possui permissao! ");
            return;
        }

        if (arquivoBuscado.getClass() != Arquivo.class) {
            System.out.println("Não é arquivo!");
            return;
        }

        aEscrever = (Arquivo) arquivoBuscado;

        aEscrever.append(conteudoAAdicionar);

        aEscrever.atualizarDataModificacao();
        aEscrever.atualizarDataUltimoAcesso();

    }

    private static Diretorio navegandoDiretorios(String[] partesCaminho, Diretorio diretorioAtual, int comeco) {

        SistemaArquivos navegando;
        Diretorio acessando = null;
        int aPercorrer;

        if (partesCaminho[partesCaminho.length - 1].equals("")) {
            aPercorrer = partesCaminho.length - 1;
        } else {
            aPercorrer = partesCaminho.length;
        }

        for (int i = 0 + comeco; i < aPercorrer; i++) {

            navegando = diretorioAtual.buscaArquivoOuDiretorio(partesCaminho[i], diretorioAtual);

            if (navegando == null) {
                System.out.println("Caminho não encontrado!");
                return diretorioAtual;
            }

            if (navegando.getClass() != Diretorio.class) {
                System.out.println("Diretorio inválido!");
                return diretorioAtual;
            }

            acessando = (Diretorio) navegando;

        }

        if (acessando == null) {
            return diretorioAtual;
        }

        return acessando;

    }

    private static boolean verificarPermissao(SistemaArquivos arq, Usuario usuarioAtual) {

        if (arq.getProprietario().equals(usuarioAtual.getLogin()) && (arq.getPermissaoProprietario() == 1 || arq.getPermissaoProprietario() == 0)) {
            return false;
        } else if (arq.getProprietario().equals(usuarioAtual.getLogin())) {
            return true;
        }

        if (arq.getPermissaoGrupo() == 1 || arq.getPermissaoGrupo() == 0) {
            return false;
        }

        return true;
    }

    private static boolean verificarPermissaoSemLeituraEEscrita(SistemaArquivos arq, Usuario usuarioAtual) {

        if (arq.getProprietario().equals(usuarioAtual.getLogin()) && (arq.getPermissaoProprietario() == 0)) {
            return false;
        } else if (arq.getProprietario().equals(usuarioAtual.getLogin())) {
            return true;
        }

        if (arq.getPermissaoGrupo() == 0) {
            return false;
        }

        return true;
    }

}
