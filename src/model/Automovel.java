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

public class Automovel {
    //declaração dos atributos
    private int idautomovel;
    private String marca;
    private String modelo;
    private int ano;
    private String placa;
    private String infracao;

    public String getInfracao() {
        return infracao;
    }

    public void setInfracao(String infracao) {
        this.infracao = infracao;
    }

    

     /* Objeto multa do tipo Multa
       Através deste método conseguiremos passar
       o idmulta através do nome que o usuário
       escolher na JComboBox */
    private Multa multa;

    //Métodos acessores relacionado ao OBJETO MULTA
    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

   //Métodos não acessores acessores
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getIdautomovel() {
        return idautomovel;
    }

    public void setIdautomovel(int idautomovel) {
        this.idautomovel = idautomovel;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

 /* Método toString(), utilizado para
       informar para o Java o que aparecerá
       na JComboBox */

    @Override
    public String toString(){
        return this.getMarca();
    }

}
