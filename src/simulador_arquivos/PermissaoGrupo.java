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
public class PermissaoGrupo extends ControleAcesso implements Serializable{

    private String grupo;

    public PermissaoGrupo(String grupo, int nivelPermissao) {
        super(nivelPermissao);
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }

}
