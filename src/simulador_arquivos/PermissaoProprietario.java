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
public class PermissaoProprietario extends ControleAcesso implements Serializable{

    private Usuario referente;

    public PermissaoProprietario(Usuario referente, int nivelPermissao) {
        super(nivelPermissao);
        this.referente = referente;
    }

    public Usuario getReferente() {
        return referente;
    }

}
