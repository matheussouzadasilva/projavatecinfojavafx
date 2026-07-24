/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.detran.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.detran.model.Endereco;
import br.com.detran.model.EnderecoDAO;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 * @exception SQLException
 */

public class EnderecoController 
{

    //Método que cadastra no banco de dados detran na tabela Endereco
    public void cadastrarEndereco(Endereco e) throws SQLException
    {
       EnderecoDAO eb = new EnderecoDAO();
        eb.cadastrarEndereco(e);
    }

    //Método que Deleta o Condutor do banco de dados pelo Numero
    public void deletarEnderecoNumero(Endereco e) throws SQLException
    {
       EnderecoDAO eb = new EnderecoDAO();
        eb.deletarEnderecoNumero(e);
    }

    //Método que Deleta o Condutor do banco de dados pelo Codigo do Endereco
    public void deletarEnderecoCodigo(Endereco e) throws SQLException
    {
       EnderecoDAO eb = new EnderecoDAO();
        eb.deletarEnderecoCodigo(e);
    }


    //Método que Busca todos os Condutores do banco de dados.
    public ArrayList<Endereco> buscarTodosEnderecos() throws SQLException
    {
        EnderecoDAO eb = new EnderecoDAO();
        return eb.buscarTodosEnderecos();
    }

    //Método que Busca todos os Condutores do banco de dados pelo Numero.
    public boolean buscarEnderecoNumero(Endereco e) throws SQLException
    {
        EnderecoDAO eb = new EnderecoDAO();
        return eb.buscarEnderecoNumero(e);
    }

    //Método que Busca todos os Condutores do banco de dados pelo Codigo.
    public boolean buscarEnderecoCodigo(Endereco e) throws SQLException
    {
        EnderecoDAO eb = new EnderecoDAO();
        return eb.buscarEnderecoCodigo(e);
    }
}
