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

public class Multa {

private int idmulta;
private String infracao;
private int pontos;
private String penalidade;
private Double  valor;

    //Métodos não acessores acessores
    public int getIdmulta() {
        return idmulta;
    }
    public void setIdmulta(int idmulta) {
        this.idmulta = idmulta;
    }
    public String getInfracao() {
        return infracao;
    }
    public void setInfracao(String infracao) {
        this.infracao = infracao;
    }
    public String getPenalidade() {
        return penalidade;
    }
    public void setPenalidade(String penalidade) {
        this.penalidade = penalidade;
    }
    public int getPontos() {
        return pontos;
    }
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double   valor) {
        this.valor = valor;
    }

    /* Método toString(), utilizado para
       informar para o Java o que aparecerá
       na JComboBox */
 @Override
    public String toString(){
        return this.getInfracao();
    }
}
