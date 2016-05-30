/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Igor
 */
public class Item {

    private Integer idItem;
    private String nome;
    private float valor;
    private Boolean disponivel;
    private Boolean necessitaPreparo;

    /**
     * @return the idItem
     */
    public Integer getIdItem() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return the disponivel
     */
    public Boolean getDisponivel() {
        return disponivel;
    }

    /**
     * @param disponivel the disponivel to set
     */
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * @return the necessitaPreparo
     */
    public Boolean getNecessitaPreparo() {
        return necessitaPreparo;
    }

    /**
     * @param necessitaPreparo the necessitaPreparo to set
     */
    public void setNecessitaPreparo(Boolean necessitaPreparo) {
        this.necessitaPreparo = necessitaPreparo;
    }

    public Item(Integer idItem, String nome, float valor, Boolean disponivel, Boolean necessitaPreparo) {
        setIdItem(idItem);
        setNome(nome);
        setValor(valor);
        setDisponivel(disponivel);
        setNecessitaPreparo(necessitaPreparo);
    }

    public Item() {

    }
}
