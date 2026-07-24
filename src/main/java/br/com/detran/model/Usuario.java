/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.detran.model;

import java.sql.SQLException;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 */

public class Usuario {
    private String login;
    private String senha;
    private String tipo;

    //Métodos não acessores acessores
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
