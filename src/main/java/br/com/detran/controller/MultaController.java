/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Multa;
import model.MultaDAO;
import util.Erro;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 * @exception SQLException
 */
public class MultaController 
{
    /*
    * Armazena uma flag que indica a situação do retorno
    */
    private boolean retorno;
    
    /*
    * Armazena todos os erros
    */
    public String erros;
    
    
    //Método que cadastra no banco de dados detran na tabela Endereco
    public void cadastrarMulta(Multa m) throws SQLException
    {
        MultaDAO mb = new MultaDAO();
        mb.cadastrarMulta(m);
    }

    //Método que deleta  Multa do banco de dados detran por Codigo
    public void deletarMultaCodigo1(Multa m) throws SQLException
    {
       MultaDAO mb = new MultaDAO();
        mb.deletarMultaCodigo(m);
    }
    
    /**
    *
    * Método que valida a exclusão da multa
    * 
    * @version 2.3 beta
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @exception SQLException lança um excessão caso algum erro de sql venha ocorrer
    * @param m Armazena o objeto passado por parametro
    * @return Boolean Método que deleta  Multa do banco de dados detran por Codigo
    */
    public boolean deletarMultaCodigo(Multa m) throws SQLException
    {
        this.retorno = true;
        MultaDAO ab  = new MultaDAO();
        Erro  er     = new Erro();
        
        if (ab.buscaRestricaoPkMulta(m) == true) {
            er.SetErro("Tem um automovel vinculado a multa que você esta tentando excluir!");
            this.retorno = false;
        } else {
            ab.deletarMultaCodigo(m);
        }
        
        this.erros   = er.GetErro();
        return this.retorno;
    }//private boolean validaExclusaoMulta(PerMulta m) throws SQLException

    //Método que Busca todas as  Multas do banco de dados.
    public ArrayList<Multa> buscarTodasMultas() throws SQLException
    {
        MultaDAO ab = new MultaDAO();
        return ab.buscarTodasMultas();
    }

    //Método que Busca Multas do banco de dados pelo Codigo.
    public boolean buscarMultaCodigo(Multa m) throws SQLException
    {
        MultaDAO mb = new MultaDAO();
        return mb.buscarMultaCodigo(m);
    }

    //Método que Atualiza as Multas do banco de dados e da tabela.
     public void atualizarMultaCodigo(Multa m) throws SQLException
     {
        MultaDAO mb = new MultaDAO();
        mb.atualizarMultaCodigo(m);
    }
}
