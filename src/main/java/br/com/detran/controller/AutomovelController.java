/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Automovel;
import model.AutomovelDAO;
import util.Erro;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 * @exception SQLException
 */
public class AutomovelController 
{
    /*
    * Armazena uma flag que indica a situação do retorno
    */
    private boolean retorno;
    
    /*
    * Armazena todos os erros
    */
    public String erros;
    
    /**
    *
    * @author Matheus Souza
    * @since 02/05/2011
    * @exception SQLException
    * @return  Método que retorna todos cadastros no banco de dados de automovel
    */
    public void cadastrarAutomovel(Automovel a) throws SQLException
    {
  
        AutomovelDAO ab = new AutomovelDAO();
        ab.cadastrarAutomovel(a);
    }

    /**
    * @author Matheus Souza
    
    * @since 02/05/2011
    * @exception SQLException
    * @return  Método que Deleta o automovel do banco de dados pela placa
    */
    public void deletarAutomovelPlaca(Automovel a) throws SQLException
    {
        AutomovelDAO ab = new AutomovelDAO();
        ab.deletarAutomovelPlaca(a);
    }
    
    /**
    * @author Matheus Souza
    
    * @since 02/05/2011
    * @exception SQLException
    * @return  Método que Deleta o automovel do banco de dados pelo codigo do automovel
    */
    public boolean deletarAutomovelCodigo(Automovel a) throws SQLException
    {
        this.retorno     = true;
        AutomovelDAO ab  = new AutomovelDAO();
        Erro  er         = new Erro();
         
        if (ab.buscaRestricaoPkAutomovel(a) == false) {
            ab.deletarAutomovelCodigo(a);
        } else {
            this.retorno = false;
            er.SetErro("Tem um condutor vinculado ao automovel que você esta tentando excluir!");
        }
        
        this.erros = er.GetErro();
        return this.retorno;
        
    }

    // Método que mostra todos automoveis cadastrados no banco do banco de dados 
    public ArrayList<Automovel> buscarTodosAutomoveis() throws SQLException
    {
        AutomovelDAO ab = new AutomovelDAO();
        return ab.buscarTodosAutomoveis();
    }

   /** Método que busca  todos automoveis cadastrados no banco do banco de dados
       somente pela placa */
    public boolean buscarAutomovelPlaca(Automovel a) throws SQLException
    {
        AutomovelDAO ab = new AutomovelDAO();
        return ab.buscarAutomovelPlaca(a);
    }

    /**Método que busca  todos automoveis cadastrados no banco do banco de dados
    somente pelo codigo do automovel*/
    public boolean buscarAutomovelCodigo(Automovel a) throws SQLException
    {
        AutomovelDAO ab = new AutomovelDAO();
        return ab.buscarAutomovelCodigo(a);
    }

    // Método que atualisa a tabela do automovel
    public void atualizarAutomovelCodigo(Automovel a) throws SQLException
    {
        AutomovelDAO ab = new AutomovelDAO();
        ab.atualizarAutomovelCodigo(a);
    }

}
