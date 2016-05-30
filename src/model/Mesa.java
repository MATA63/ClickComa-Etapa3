/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cmelo
 */
public class Mesa {
    private Integer idMesa;
    private String numero;
    private String local;

    /**
     * @return the idMesa
     */
    public Integer getIdMesa() {
        return idMesa;
    }

    /**
     * @param idMesa the idMesa to set
     */
    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }
    
    
    public Mesa(Integer idMesa, String numero, String local){
        setIdMesa(idMesa);
        setNumero(numero);
        setLocal(local);
    }
    
    public Mesa(){
        
    }
}
