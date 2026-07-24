/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.detran.model;

import br.com.detran.util.ConexaoBanco;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 */

public class UsuarioDAO
{
    /**
    *
    * Método que Faz o Cadastro do usuario
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param u Armazena o objeto da multa
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    * @throws UnsupportedEncodingException Lança uma excessão caso algum erro venha ocorrer
    */
    public void cadastrarUsuario(Usuario u) throws SQLException, UnsupportedEncodingException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "INSERT INTO usuario(idusuario"
                + "\n ,login"
                + "\n ,senha"
                + "\n ,tipo) "
                + "VALUES(?, ?, ?, ?)"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setString(1, null);
        pstmt.setString(2, u.getLogin());
        pstmt.setString(3, u.getSenha());
        pstmt.setString(4, u.getTipo());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void cadastrarUsuario(Usuario u) throws SQLException, UnsupportedEncodingException

    /**
    *
    * Método que faz a validacao de login,senha e tipo de usuario
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param u Armazena o objeto da multa
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    * @return Int Retorna uma flag indicando se esta ok ou nao
    */
    public int validarSenha(Usuario u) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT tipo"
                    +"\n FROM usuario"
                    +"\n WHERE login = ?"
                    +"\n AND senha   = ?"
                    +"\n AND tipo    = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setString(1, u.getLogin());
        pstmt.setString(2, u.getSenha());
        pstmt.setString(3, u.getTipo());
        
        ResultSet rs = pstmt.executeQuery();
        int retorno = -1;
        
        while (rs.next()) {
            if (rs.getString(1).equals("Administrador")) {
                retorno = 1;
            } else if (rs.getString(1).equals("Comum")) {
                retorno = 2;
            }//if (rs.getString(1).equals("administrador")) {
        }//while (rs.next()) {
        
        pstmt.close();
        return retorno;
    }//public int validarSenha(Usuario u) throws SQLException  
}
