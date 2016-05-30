/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Date;
/**
 *
 * @author cmelo
 */
public class Pedido {
    private Integer idPedido;
    private Conta conta;
    private Item item;
    private Integer quantidade;
    private Date dataHora;
    private Funcionario garcom;
    private Funcionario cozinheiro;
    
    /**
     * @return the idPedido
     */
    public Integer getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the conta
     */
    public Conta getConta() {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the dataHora
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * @param dataHora the dataHora to set
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * @return the Garcom
     */
    public Funcionario getGarcom() {
        return garcom;
    }

    /**
     * @param garcom the Garcom to set
     */
    public void setGarcom(Funcionario garcom) {
        this.garcom = garcom;
    }

    /**
     * @return the Cozinheiro
     */
    public Funcionario getCozinheiro() {
        return cozinheiro;
    }

    /**
     * @param cozinheiro the Cozinheiro to set
     */
    public void setCozinheiro(Funcionario cozinheiro) {
        this.cozinheiro = cozinheiro;
    }

    public Pedido (Integer idPedido, Conta conta, Item item, Integer quantidade, Date dataHora, Funcionario garcom, Funcionario cozinheiro){
        setIdPedido(idPedido);
        setConta(conta);
        setItem(item);
        setQuantidade(quantidade);
        setDataHora(dataHora);
        setGarcom(garcom);
        setCozinheiro(cozinheiro);
    }
    
    public Pedido(){
        
    }
    
}
