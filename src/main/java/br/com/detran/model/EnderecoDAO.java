/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.detran.model;
import br.com.detran.util.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 */

public class EnderecoDAO
{
    /**
    *
    * Metodo que Cadastra o endereco
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param c Armazena o objeto do endereco
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void cadastrarEndereco(Endereco c) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "INSERT INTO endereco(idendereco"
                    +"\n ,endereco"
                    +"\n ,logradouro"
                    +"\n ,numero"
                    +"\n ,complemento"
                    +"\n ,bairro"
                    +"\n ,estado"
                    +"\n ,cidade"
                    +"\n ,cep"
                    +"\n ,idcondutor)"
                    +"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setString(1, null);
        pstmt.setString(2, c.getEndereco());
        pstmt.setString(3, c.getLogradouro());
        pstmt.setInt(4, c.getNumero());
        pstmt.setString(5, c.getComplemento());
        pstmt.setString(6, c.getBairro());
        pstmt.setString(7, c.getEstado());
        pstmt.setString(8, c.getCidade());
        pstmt.setInt(9, c.getCep());
        pstmt.setInt(10, c.getCondutor().getIdcondutor());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void cadastrarEndereco(Endereco c) throws SQLException

    /**
    *
    * Metodo que deleta o endereco pelo numero
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param c Armazena o objeto do endereco
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void deletarEnderecoNumero(Endereco c) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "DELETE FROM endereco"
                    +"\n WHERE numero = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, c.getNumero());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void deletarEnderecoNumero(Endereco c) throws SQLException

    /**
    *
    * Metodo que deleta o endereco pelo codigo
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param c Armazena o objeto do endereco
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void deletarEnderecoCodigo(Endereco c) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "DELETE FROM endereco"
                    +"\n WHERE idendereco = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, c.getIdendereco());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void deletarEnderecoCodigo(Endereco c) throws SQLException

     //Metodo que Busca todos Enderecos do banco De Dados
    /**
    *
    * Metodo que busca todos os enderecos
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @return ArrayList Retorna todos os enderecos
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public ArrayList<Endereco> buscarTodosEnderecos() throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        Statement stat = conexao.createStatement();

        String sql = "SELECT idendereco"
                    +"\n ,endereco"
                    +"\n ,logradouro"
                    +"\n ,numero"
                    +"\n ,complemento"
                    +"\n ,bairro"
                    +"\n ,estado"
                    +"\n ,cidade"
                    +"\n ,cep"
                    +"\n ,condutor.nome"
                    +"\n ,condutor.idcondutor"
                    +"\n FROM endereco, condutor"
                    +"\n WHERE condutor.idcondutor = endereco.idcondutor";

        ResultSet resultado = stat.executeQuery(sql);
        ArrayList<Endereco> aAux = new ArrayList<Endereco>();

        while (resultado.next()) {
            Endereco e = new Endereco();
            e.setIdendereco(resultado.getInt("idendereco"));
            e.setEndereco(resultado.getString("endereco"));
            e.setLogradouro(resultado.getString("logradouro"));
            e.setNumero(resultado.getInt("numero"));
            e.setComplemento(resultado.getString("complemento"));
            e.setBairro(resultado.getString("bairro"));
            e.setEstado(resultado.getString("estado"));
            e.setCidade(resultado.getString("cidade"));
            e.setCep(resultado.getInt("cep"));
            e.setIdendereco(resultado.getInt("idcondutor"));

            Condutor c = new Condutor();
            c.setNome(resultado.getString("nome"));
            e.setCondutor(c);
            aAux.add(e);
        }//while (resultado.next()) {

        stat.close();
        return aAux;
    }//public ArrayList<Endereco> buscarTodosEnderecos() throws SQLException

    /**
    *
    * Metodo que verifica se existe algum endereco
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param c Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscarEnderecoNumero(Endereco c) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                    +"\n FROM endereco"
                    +"\n WHERE numero = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, c.getNumero());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscarEnderecoNumero(Endereco c) throws SQLException

    /**
    *
    * Metodo que verifica se existe algum endereco por codigo
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param a Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscarEnderecoCodigo(Endereco a) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                +"\n FROM endereco"
                +"\n WHERE idendereco = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, a.getIdendereco());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscarEnderecoCodigo(Endereco a) throws SQLException
}
