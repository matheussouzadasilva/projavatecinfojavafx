/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

Automovel a
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

public class AutomovelDAO
{
    /**
    *
    * Método que Faz o Cadastro do automovel
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @aram a Armazena o objeto do automovel
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void cadastrarAutomovel(Automovel a) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "INSERT INTO automovel(idautomovel"
                    +"\n ,marca"
                    +"\n ,modelo"
                    +"\n ,ano"
                    +"\n ,placa"
                    +"\n ,idmulta) "
                    +"\n VALUES(?, ?, ?, ?, ?, ?)"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setString(1, null);
        pstmt.setString(2, a.getMarca());
        pstmt.setString(3, a.getModelo());
        pstmt.setInt(4, a.getAno());
        pstmt.setString(5, a.getPlaca());
        pstmt.setInt(6, a.getMulta().getIdmulta());
        
        pstmt.executeUpdate();
        pstmt.close();
    }//public void cadastrarAutomovel(Automovel a) throws SQLException

    /**
    *
    * Metodo que Deleta o automovel pela placa
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @aram a Armazena o objeto do automovel
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void deletarAutomovelPlaca(Automovel a) throws SQLException 
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "DELETE FROM automovel "
                    + "WHERE placa = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setString(1, a.getPlaca());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void deletarAutomovelPlaca(Automovel a) throws SQLException 
    
    /**
    *
    * Metodo que Deleta o automovel pelo Codigo
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @aram a Armazena o objeto do automovel
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void deletarAutomovelCodigo(Automovel a) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "DELETE FROM automovel "
                    + "WHERE idautomovel = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, a.getIdautomovel());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void deletarAutomovelCodigo(Automovel a) throws SQLException

    /**
    *
    * Metodo que atualiza os dados do automovel pelo codigo
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @aram t Armazena o objeto do automovel pelo codigo
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void atualizarAutomovelCodigo(Automovel t) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "UPDATE automovel"
                    +"\n SET idautomovel   = ?"
                    +"\n ,marca            = ?"
                    +"\n ,modelo           = ?"
                    +"\n ,ano              = ?"
                    +"\n ,placa            = ?"
                    +"\n WHERE idautomovel = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, t.getIdautomovel());
        pstmt.setString(2, t.getMarca());
        pstmt.setString(3, t.getModelo());
        pstmt.setInt(4, t.getAno());
        pstmt.setString(5, t.getPlaca());
        pstmt.setInt(6, t.getIdautomovel());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void atualizarAutomovelCodigo(Automovel t) throws SQLException

    /**
    *
    * Metodo que busca todos os automoveis
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @return ArrayList Retorna todos os automoveis
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public ArrayList<Automovel> buscarTodosAutomoveis() throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        Statement stat = conexao.createStatement();
        
        String sql  = "SELECT aut.idautomovel"
                    +"\n ,aut.marca"
                    +"\n ,aut.modelo"
                    +"\n ,aut.ano"
                    +"\n ,aut.placa"
                    +"\n ,mul.infracao"
                    +"\n FROM automovel AS aut, multa AS mul"
                    +"\n WHERE mul.idmulta = aut.idmulta";

        ResultSet resultado = stat.executeQuery(sql);
        ArrayList<Automovel> aAux = new ArrayList<>();

        while (resultado.next()) {
            Automovel a = new Automovel();
            a.setIdautomovel(resultado.getInt("aut.idautomovel"));
            a.setInfracao(resultado.getString("mul.infracao"));
            a.setMarca(resultado.getString("aut.marca"));
            a.setModelo(resultado.getString("aut.modelo"));
            a.setAno(resultado.getInt("aut.ano"));
            a.setPlaca(resultado.getString("aut.placa"));
            
            Multa m = new Multa();
            m.setInfracao(resultado.getString("mul.infracao"));

            a.setMulta(m);
            aAux.add(a);
        }//while (resultado.next()) {

        stat.close();
        return aAux;
    }//public ArrayList<Automovel> buscarTodosAutomoveis() throws SQLException
    
    /**
    *
    * Metodo que verifica se existe algum automovel vinculado a placa informada
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @aram a Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscarAutomovelPlaca(Automovel a) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                    +"\n FROM automovel"
                    +"\n WHERE placa = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setString(1, a.getPlaca());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscarAutomovelPlaca(Automovel a) throws SQLException

    /**
    *
    * Metodo que verifica se existe alguma restrição de fk
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @aram a Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscaRestricaoPkAutomovel(Automovel a) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                    +"\n FROM condutor"
                    +"\n WHERE idautomovel = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);

        pstmt.setInt(1, a.getIdautomovel());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscaRestricaoPkAutomovel(Automovel a) throws SQLException
    
    /**
    *
    * Metodo que busca por Codigo o automovel
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @aram a Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscarAutomovelCodigo(Automovel a) throws SQLException 
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                    +"\n FROM automovel"
                    +"\n WHERE idautomovel = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, a.getIdautomovel());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscarAutomovelCodigo(Automovel a) throws SQLException
  
}
