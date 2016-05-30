package model;

/**
 *
 * @author Edicarla
 */

public class Fornecedor {
	
	private Integer idFornecedor;
    private String nome;
    private String cnpj;
    private String ramo;

    /**
     * @return the idFornecedor
     */
    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * @param idFornecedor the idFornecedor to set
     */
    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
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
     * @return the Cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the ramo
     */
    public String getRamo() {
        return ramo;
    }

    /**
     * @param ramo the ramo to set
     */
    public void setRamo(String ramo) {
        this.ramo = ramo;
    }
    
    public Fornecedor(Integer idFornecedor, String nome, String cnpj, String ramo ){
            setIdFornecedor(idFornecedor);
            setNome(nome);
            setCnpj(cnpj);
            setRamo(ramo);
    }
	public Fornecedor(){
        
    }
}
