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
public abstract class ControleAcesso implements Serializable{

    private int nivelPermissao;

    public ControleAcesso(int nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }

    public int getNivelPermissao() {
        return nivelPermissao;
    }

    public void setNivelPermissao(int nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }

}
