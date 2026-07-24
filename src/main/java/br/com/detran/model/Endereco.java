/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.detran.model;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 */

public class Endereco {
 private int idendereco;
 private String endereco;
 private String logradouro;
 private int numero;
 private String complemento;
 private String bairro;
 private String estado;
 private String cidade;
 private int cep;
 
 /* Objeto condutor do tipo Condutor
       Através deste método conseguiremos passar
       o idCondutor através do nome que o usuário
       escolher na JComboBox */
 
 private Condutor condutor;

    //Métodos acessores relacionado ao OBJETO Condutor
    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    //Métodos não acessores acessores
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(int idendereco) {
        this.idendereco = idendereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

     /* Método toString(), utilizado para
       informar para o Java o que aparecerá
       na JComboBox */
@Override
    public String toString(){
        return this.getCondutor().getNome();
    }
 
}
