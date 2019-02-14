/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador_arquivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SoldierJVX
 */
class Persistencia {

    public static ListaUsuarios carregarUsuarios(ListaUsuarios listaUsuarios) {

        File arquivo = new File("./usuarios.dat");

        if (!arquivo.exists()) {
            return null;
        }

        try {
            ObjectInputStream arq = new ObjectInputStream(new FileInputStream(arquivo));
            boolean sair = false;

            Object obj = arq.readObject();

            listaUsuarios = (ListaUsuarios) obj;

            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUsuarios;

    }

    public static Diretorio carregarDiretorios(Diretorio diretorioAtual) {

        File arquivo = new File("./diretorios.dat");

        if (!arquivo.exists()) {
            return null;
        }

        try {
            ObjectInputStream arq = new ObjectInputStream(new FileInputStream(arquivo));
            boolean sair = false;

            Object obj = arq.readObject();

            diretorioAtual = (Diretorio) obj;

            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return diretorioAtual;

    }

    public static void salvarUsuarios(ListaUsuarios listaUsuarios) {

        File arquivo = new File("./usuarios.dat");

        try {

            if (!arquivo.exists()) {

                arquivo.createNewFile();

            }

            ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream(arquivo));

            arq.writeObject(listaUsuarios);

            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void salvarDiretorios(Diretorio diretorioAtual) {

        File arquivo = new File("./diretorios.dat");

        try {

            if (!arquivo.exists()) {

                arquivo.createNewFile();

            }

            ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream(arquivo));

            arq.writeObject(diretorioAtual);

            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
