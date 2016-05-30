package model;

/**
*
* @author Edicarla
*/

public class Reclamacao {
	
	private Integer idReclamacao;
    private String descricao;

    /**
     * @return the idFornecedor
     */
    public Integer getIdReclamacao() {
        return idReclamacao;
    }

    /**
     * @param idFornecedor the idFornecedor to set
     */
    public void setIdReclamacao(Integer idReclamacao) {
        this.idReclamacao = idReclamacao;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Reclamacao(Integer idreclamacao, String descricao ){
    		setIdReclamacao(idreclamacao);
            setDescricao(descricao);
    }
	public Reclamacao(){
        
    }

}
