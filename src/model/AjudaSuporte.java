package model;

public class AjudaSuporte {
	
	private Integer idAjudaSuporte;
	private String titulo;
	private String descricao;
	
    /**
     * @return the idAjudaSuporte
     */
    public Integer getIdAjudaSuporte() {
        return idAjudaSuporte;
    }

    /**
     * @param idAjudaSuportethe idAjudaSuporte to set
     */
    public void setIdAjudaSuporte(Integer idAjudaSuporte) {
        this.idAjudaSuporte = idAjudaSuporte;
    }
    
    public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
    
    public AjudaSuporte (Integer idAjudaSuporte, String titulo, String descricao ){
    		setIdAjudaSuporte(idAjudaSuporte);
    		setTitulo(titulo);
            setDescricao(descricao);
    }
	public AjudaSuporte(){
        
    }

}
