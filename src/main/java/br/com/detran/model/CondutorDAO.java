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

public class CondutorDAO
{
    /**
    *
    * Metodo que Cadastra o Condutor
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param pc Armazena o objeto do condutor
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void cadastrarCondutor(Condutor pc) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "INSERT INTO condutor(idcondutor"
                    +"\n ,sexo"
                    +"\n ,nome"
                    +"\n ,sobrenome"
                    +"\n ,rg"
                    +"\n ,cpf"
                    +"\n ,datanascimento"
                    +"\n ,cnh"
                    +"\n ,idautomovel)"
                    +"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setString(1, null);
        pstmt.setString(2, pc.getSexo());
        pstmt.setString(3, pc.getNome());
        pstmt.setString(4, pc.getSobrenome());
        pstmt.setInt(5, pc.getRg());
        pstmt.setInt(6, pc.getCfp());
        pstmt.setString(7, pc.getDatanascimento());
        pstmt.setInt(8, pc.getCnh());
        pstmt.setInt(9, pc.getAutomovel().getIdautomovel());
        
        pstmt.executeUpdate();
        pstmt.close();
    }//public void cadastrarCondutor(Condutor c) throws SQLException

    /**
    *
    * Metodo que deleta o condutor pelo Cnh
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param pc Armazena o objeto do condutor
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void deletarCondutorCnh(Condutor pc) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "DELETE FROM condutor"
                    +"\n WHERE cnh = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, pc.getCnh());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void deletarCondutorCnh(Condutor c) throws SQLException

    /**
    *
    * Metodo que deleta o condutor pelo Codigo
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param pc Armazena o objeto do condutor
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public void deletarCondutorCodigo(Condutor pc) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "DELETE FROM condutor "
                    +"\n WHERE idcondutor = ?";
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, pc.getIdcondutor());

        pstmt.executeUpdate();
        pstmt.close();
    }//public void deletarCondutorCodigo(Condutor c) throws SQLException

    /**
    *
    * Metodo que Busca todos Condutores
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @return ArrayList Retorna todos os condutores
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public ArrayList<Condutor> buscarTodosCondutores() throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        Statement stat = conexao.createStatement();
        
        String sql = "SELECT idcondutor"
                    +"\n ,sexo"
                    +"\n ,nome"
                    +"\n ,sobrenome"
                    +"\n ,rg"
                    +"\n ,cpf"
                    +"\n ,datanascimento"
                    +"\n ,cnh"
                    +"\n ,automovel.placa"
                    +"\n FROM condutor"
                    +"\n ,automovel"
                    +"\n WHERE automovel.idautomovel = condutor.idautomovel";

        ResultSet resultado = stat.executeQuery(sql);
        ArrayList<Condutor> pcAux = new ArrayList<>();

        while (resultado.next()) {
            Condutor pc = new Condutor();
            pc.setIdcondutor(resultado.getInt("idcondutor"));
            pc.setSexo(resultado.getString("sexo"));
            pc.setNome(resultado.getString("nome"));
            pc.setSobrenome(resultado.getString("sobrenome"));
            pc.setRg(resultado.getInt("rg"));
            pc.setCfp(resultado.getInt("cpf"));
            pc.setDatanascimento(resultado.getString("datanascimento"));
            pc.setCnh(resultado.getInt("cnh"));

            Automovel pa = new Automovel();
            pa.setPlaca(resultado.getString("placa"));
            
            pc.setAutomovel(pa);
            pcAux.add(pc);
        }//while (resultado.next()) {

        stat.close();
        return pcAux;
    }//public ArrayList<Condutor> buscarTodosCondutores() throws SQLException

    /**
    *
    * Metodo que verifica se existe algum condutor vinculado ao cnh informado
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param pc Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscarCondutorCnh(Condutor pc) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT idcondutor"
                    +"\n FROM condutor WHERE cnh = ?"; 

        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, pc.getCnh());
        
        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pc.setIdcondutor(resultado.getInt("idcondutor"));
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscarCondutorCnh(Condutor a) throws SQLException

    /**
    *
    * Metodo que verifica se existe algum condutor vinculado ao codigo informado
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param pc Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscarCondutorCodigo(Condutor pc) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                    +"\n FROM condutor"
                    +"\n WHERE idcondutor = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);
        
        pstmt.setInt(1, pc.getIdcondutor());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscarCondutorCodigo(Condutor a) throws SQLException
    
    /**
    *
    * Metodo que verifica se existe alguma restrição de fk
    * 
    * @version 2.4
    * @author Matheus Souza da Silva
    * @since 02/05/2011
    * @param pc Armazena o objeto
    * @return Boolean Retorna uma flag indicando se achou ou não
    * @throws SQLException Lança uma excessão caso algum erro no banco de dados venha ocorrer
    */
    public boolean buscaRestricaoPkCondutor(Condutor pc) throws SQLException
    {
        Connection conexao = ConexaoBanco.getConexao();
        
        String updt = "SELECT 1 AS resultado"
                    +"\n FROM endereco"
                    +"\n WHERE idcondutor = ?"; 
        
        PreparedStatement pstmt = conexao.prepareStatement(updt);

        pstmt.setInt(1, pc.getIdcondutor());

        ResultSet resultado = pstmt.executeQuery();

        if (resultado.next()) {
            pstmt.close();
            return true;
        } else {
            pstmt.close();
            return false;
        }//if (resultado.next()) {
    }//public boolean buscaRestricaoPkCondutor(Condutor a) throws SQLException
}
