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

public class MultaDAO
{
    /**
    *
    * Método que Faz o Cadastro da multa
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param m Armazena o objeto da multa
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void cadastrarMulta(Multa m) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "INSERT INTO multa(idmulta"
                    +"\n ,infracao"
                    +"\n ,pontos"
                    +"\n ,penalidade"
                    +"\n ,valor)"
                    +"\n VALUES(?, ?, ?, ?, ?)"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setString(1, null);
        pstmt.setString(2, m.getInfracao());
        pstmt.setInt(3, m.getPontos());
        pstmt.setString(4, m.getPenalidade());
        pstmt.setDouble(5, m.getValor());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void cadastrarMulta(Multa m) throws SQLException

    /**
    *
    * Método que deleta a multa pelo Codigo.
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param m Armazena o objeto da multa
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void deletarMultaCodigo(Multa m) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "DELETE FROM multa"
                    +"\n WHERE idmulta = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, m.getIdmulta());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void deletarMultaCodigo(Multa m) throws SQLException
    
    /**
    *
    * Método que atualiza a multa pelo Codigo.
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param t Armazena o objeto da multa
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void atualizarMultaCodigo(Multa t) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "UPDATE multa"
                    +"\n SET idmulta   = ?"
                    +"\n ,infracao     = ?"
                    +"\n ,pontos       = ?"
                    +"\n ,penalidade   = ?"
                    +"\n ,valor        = ?"
                    +"\n WHERE idmulta = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, t.getIdmulta());
        pstmt.setString(2, t.getInfracao());
        pstmt.setInt(3, t.getPontos());
        pstmt.setString(4, t.getPenalidade());
        pstmt.setDouble(5, t.getValor());
        pstmt.setInt(6, t.getIdmulta());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void atualizarMultaCodigo(Multa t) throws SQLException

    /**
    *
    * Método que busca todas Multas
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    * @return ArrayList Retorna todas as multas
    */
    public ArrayList<Multa> buscarTodasMultas() throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        Statement stat = conexao.createStatement();

        String sql = "SELECT idmulta"
                    +"\n ,infracao"
                    +"\n ,pontos"
                    +"\n ,penalidade"
                    +"\n ,valor"
                    +"\n FROM multa";

        ResultSet resultado      = stat.executeQuery(sql);
        ArrayList<Multa> aAux = new ArrayList<>();

        while (resultado.next()) {
            Multa m = new Multa();
            m.setIdmulta(resultado.getInt("idmulta"));
            m.setInfracao(resultado.getString("infracao"));
            m.setPontos(resultado.getInt("pontos"));
            m.setPenalidade(resultado.getString("penalidade"));
            m.setValor(resultado.getDouble("valor"));
            aAux.add(m);
        }//while (resultado.next()) {

        stat.close();
        return aAux;
    }//public ArrayList<Multa> buscarTodasMultas() throws SQLException
    
    /**
    *
    * Metodo que verifica se existe a multa informada
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param m Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscarMultaCodigo(Multa m) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                    +"\n FROM multa"
                    +"\n WHERE idmulta = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, m.getIdmulta());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
        
    }//public boolean buscarMultaCodigo(Multa m) throws SQLException
    
    //Metodo que busca por placa os automoveis do Banco De Dados 
    
    /**
    *
    * Metodo que verifica se existe alguma restricao de fk
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param m Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscaRestricaoPkMulta(Multa m) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                    +"\n FROM automovel"
                    +"\n WHERE idmulta = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);

        pstmt.setInt(1, m.getIdmulta());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscaRestricaoPkMulta(Multa m) throws SQLException
}
