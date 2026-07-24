/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Condutor;
import model.CondutorDAO;
import util.Erro;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 * @exception SQLException
 */
public class CondutorController 
{
    
    /*
    * Armazena uma flag que indica a situação do retorno
    */
    private boolean retorno;
    
    /*
    * Armazena todos os erros
    */
    public String erros;
    
    //Método que cadastra no banco de dados detran na tabela Condutor
    public void cadastrarCondutor(Condutor c) throws SQLException
    {
        CondutorDAO cb = new CondutorDAO();
        cb.cadastrarCondutor(c);
    }

    /**
    * @author Matheus Souza
    
    * @since 02/05/2011
    * @exception SQLException
    * @return boolean Método que Deleta o Condutor do banco de dados pelo Cnh 
    */
    public boolean deletarCondutorCnh(Condutor c) throws SQLException
    {
        this.retorno     = true;
        CondutorDAO cb   = new CondutorDAO();
        Erro  er         = new Erro();
         
        if (cb.buscaRestricaoPkCondutor(c) == false) {
            cb.deletarCondutorCnh(c);
        } else {
            this.retorno = false;
            er.SetErro("Tem um endereco vinculado ao condutor que você esta tentando excluir!");
        }
        
        this.erros = er.GetErro();
        return this.retorno;
        
    }

    /**
    * @author Matheus Souza
    
    * @since 02/05/2011
    * @exception SQLException
    * @return boolean Método que Deleta o Condutor do banco de dados pelo Codigo do Condutor
    */
    public boolean deletarCondutorCodigo(Condutor c) throws SQLException
    {
        this.retorno     = true;
        CondutorDAO cb   = new CondutorDAO();
        Erro  er         = new Erro();
         
        if (cb.buscaRestricaoPkCondutor(c) == false) {
            cb.deletarCondutorCodigo(c);
        } else {
            this.retorno = false;
            er.SetErro("Tem um endereco vinculado ao condutor que você esta tentando excluir!");
        }
        
        this.erros = er.GetErro();
        return this.retorno;
        
    }


    //Método que Busca todos os Condutores do banco de dados.
    public ArrayList<Condutor> buscarTodosCondutores() throws SQLException
    {
        CondutorDAO cb = new CondutorDAO();
        return cb.buscarTodosCondutores();
    }

     //Método que Busca os Condutores do banco de dados pelo Cnh.
    public boolean buscarCondutorCnh(Condutor c) throws SQLException
    {
        CondutorDAO cb = new CondutorDAO();
        return cb.buscarCondutorCnh(c);
    }

    //Método que Busca os Condutores do banco de dados pelo Codigo Condutor.
    public boolean buscarCondutorCodigo(Condutor c) throws SQLException
    {
        CondutorDAO cb = new CondutorDAO();
        return cb.buscarCondutorCodigo(c);
    }
}
