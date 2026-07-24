/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.detran.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import br.com.detran.model.Usuario;
import br.com.detran.model.UsuarioDAO;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 * @exception SQLException
 */

public class UsuarioController 
{

    public void cadastrarUsuario(Usuario u) throws SQLException, UnsupportedEncodingException
    {
        UsuarioDAO ub = new UsuarioDAO();
        ub.cadastrarUsuario(u);
    }

    public int validarSenha(Usuario u) throws SQLException
    {
        UsuarioDAO user = new UsuarioDAO();
        return user.validarSenha(u);
    }
}
