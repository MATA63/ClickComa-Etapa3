/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Igor
 */
public class Conta {
    private Integer idConta;
    private Cliente cliente;
    private Mesa mesa;
    private Date dataHoraInicioAtendimento;
    private Date dataHoraFimAtendimento;


    /**
     * @return the idConta
     */
    public Integer getIdConta() {
        return idConta;
    }

    /**
     * @param idConta the idConta to set
     */
    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    /**
     * @return the idCliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the idCliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the idMesa
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * @param mesa the idMesa to set
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * @return the dataHoraInicioAtendimento
     */
    public Date getDataHoraInicioAtendimento() {
        return dataHoraInicioAtendimento;
    }

    /**
     * @param dataHoraInicioAtendimento the dataHoraInicioAtendimento to set
     */
    public void setDataHoraInicioAtendimento(Date dataHoraInicioAtendimento) {
        this.dataHoraInicioAtendimento = dataHoraInicioAtendimento;
    }

    /**
     * @return the dataHoraFimAtendimento
     */
    public Date getDataHoraFimAtendimento() {
        return dataHoraFimAtendimento;
    }

    /**
     * @param dataHoraFimAtendimento the dataHoraFimAtendimento to set
     */
    public void setDataHoraFimAtendimento(Date dataHoraFimAtendimento) {
        this.dataHoraFimAtendimento = dataHoraFimAtendimento;
    }
    
    public Conta(Integer idConta, Cliente cliente, Mesa mesa, Date dataHoraInicioAtendimento, Date dataHoraFimAtendimento){
        setIdConta(idConta);
        setCliente(cliente);
        setMesa(mesa);
        setDataHoraInicioAtendimento(dataHoraInicioAtendimento);
        setDataHoraFimAtendimento(dataHoraFimAtendimento);
    }
    
    public Conta(){
        
    }
    
}
