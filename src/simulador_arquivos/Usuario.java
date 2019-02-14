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
public class Usuario implements Serializable{

    private String login;
    private String password;
    private boolean root;

    public Usuario(String nome, String password, boolean root) {
        this.login = nome;
        this.root = root;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRoot() {
        return root;
    }

    

}
