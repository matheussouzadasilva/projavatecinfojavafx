/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 * @version 2.4
 * @author Matheus Souza
 * @since 02/05/2011
 */

public class Condutor {

 private int  idcondutor;
 private String sexo;
 private String nome;
 private String sobrenome;
 private int rg;
 private int cfp;
 private String datanascimento;
 private int cnh;
 private String placa;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

 

    /* Objeto automovel do tipo Automovel
       Através deste método conseguiremos passar
       o idAutomovelatravés do nome que o usuário
       escolher na JComboBox */
    private Automovel automovel;

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }
    //Métodos acessores relacionado ao OBJETO Automovel

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    
    public int getCfp() {
        return cfp;
    }
    public void setCfp(int cfp) {
        this.cfp = cfp;
    }
    public int getCnh() {
        return cnh;
    }
    public void setCnh(int cnh) {
        this.cnh = cnh;
    }
    public String getDatanascimento() {
        return datanascimento;
    }
    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }
    public int getIdcondutor() {
        return idcondutor;
    }
    public void setIdcondutor(int idcondutor) {
        this.idcondutor = idcondutor;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getRg() {
        return rg;
    }
    public void setRg(int rg) {
        this.rg = rg;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

 @Override
    public String toString(){
        return this.getNome();
    }


}
