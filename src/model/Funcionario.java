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
public class Funcionario {
    private Integer idFuncionario;
    private String cpf;
    private String numeroCtps;
    private String nome;
    private String cargo;

    /**
     * @return the idFuncionario
     */
    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * @param idFuncionario the idFuncionario to set
     */
    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the numeroCtps
     */
    public String getNumeroCtps() {
        return numeroCtps;
    }

    /**
     * @param numeroCtps the numeroCtps to set
     */
    public void setNumeroCtps(String numeroCtps) {
        this.numeroCtps = numeroCtps;
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
     * @return the Cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param Cargo the Cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Funcionario(Integer idFuncionario, String cpf, String numeroCtps, String nome, String cargo){
        setIdFuncionario(idFuncionario);
        setCpf(cpf);
        setNumeroCtps(numeroCtps);
        setNome(nome);
        setCargo(cargo);
    }
    
    public Funcionario(){
        
    }
}