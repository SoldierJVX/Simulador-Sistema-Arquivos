/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador_arquivos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author SoldierJVX
 */
public class ListaUsuarios implements Serializable{

    HashMap<String,Usuario> listaUsuarios;

    public ListaUsuarios() {
        this.listaUsuarios = new HashMap<>();
    }

    public void addUser(String name, String password, boolean root) {

        Usuario aAdicionar = new Usuario(name, password, root);

        listaUsuarios.put(aAdicionar.getLogin(), aAdicionar);

    }

    boolean buscarLogin(String login) {

        if (listaUsuarios.containsKey(login)) {
            return true;
        }

        return false;

    }

    public Usuario getUser(String nomeUsuario) {
        
        if (listaUsuarios.containsKey(nomeUsuario)) {
            return listaUsuarios.get(nomeUsuario);
        }
        
        return null;
        
    }

    public void removeUser(String nomeUsuario) {
        listaUsuarios.remove(nomeUsuario);
    }

}
