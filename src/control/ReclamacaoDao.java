package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author Edicarla
 */
public class ReclamacaoDao {
	
	 private BufferedWriter bw = null;
	    private BufferedReader fr = null;

	    public Reclamacao salvarReclamacao(Reclamacao reclamacao) throws IOException{
	        try{
	            bw = new BufferedWriter(new FileWriter("Reclamacao.cc", true));

	            bw.write("<idReclamacao>"+reclamacao.getIdReclamacao()
	                        +"<descricao>"+reclamacao.getDescricao()+"<fdl>");
	            bw.newLine();
	            bw.flush();
	            bw.close();    
	        }catch(Exception e){ 
	            System.out.println("Ocorreu um erro ao salvar no arquivo Reclamacao.cc. Exception: "+e.getMessage());
	        }finally{
	        }
	        System.out.println("Salvo com Sucesso!");
	        return reclamacao;
	    }

	    public void salvarReclamacao(List<Reclamacao> listReclamacao) throws IOException{
	        try{
	            //Sobrescrever todas as Reclamacoes.
	            File file = new File("Reclamacao.cc");
	            if ( file.exists()) {
	                FileWriter fw = new FileWriter("Reclamacao.cc",false);
	            }
	            bw = new BufferedWriter(new FileWriter("Reclamacao.cc", true)); 
	            for(Reclamacao reclamacao: listReclamacao){
	                bw.write("<idReclamacao>"+reclamacao.getIdReclamacao().toString()
	                    +"<descricao>"+reclamacao.getDescricao()+"<fdl>");
	                bw.newLine();
	                bw.flush();
	            }
	            bw.close();    
	        }catch(Exception e){ 
	            System.out.println("Ocorreu um erro ao salvar no arquivo Reclamacao.cc. Exception: "+e.getMessage());
	        }finally{
	        }
	        System.out.println("Salvo com Sucesso!");
	    }
	    
	    public List<Reclamacao> abrirReclamacao() throws IOException{
	        List<Reclamacao> listReclamacao = new ArrayList();
	        try{
	            String linha;
	            Integer idReclamacao;
	            String descricao;
	            fr = new BufferedReader(new FileReader("Reclamacao.cc"));

	            while ((linha = fr.readLine()) != null) {
	                idReclamacao =  Integer.parseInt(linha.substring(linha.indexOf("<idReclamacao>")+11, linha.indexOf("<descricao>")));
	                descricao = linha.substring((linha.indexOf("<descricao>")+7), linha.indexOf("<fdl>"));

	                listReclamacao.add(new Reclamacao(idReclamacao, descricao));
	            }
	        }catch(Exception e){ 
	            System.out.println("Ocorreu um erro ao ler o arquivo Reclamacao.cc. Exception: "+e.getMessage());
	        }finally{
	        }
	        return listReclamacao;
	    }
	    
	    public Reclamacao abrirReclamacao(Integer idReclamacaoProcurado) throws IOException{
	        try{
	            String linha;
	            Integer idReclamacao;
	            String descricao;
	            fr = new BufferedReader(new FileReader("Reclamacao.cc"));

	            while ((linha = fr.readLine()) != null) {
	                idReclamacao =  Integer.parseInt(linha.substring(linha.indexOf("<idReclamacao>")+11, linha.indexOf("<descricao>")));
	                descricao = linha.substring((linha.indexOf("<descricao>")+7), linha.indexOf("<fdl>"));

	                if(idReclamacaoProcurado == idReclamacao){
	                	Reclamacao reclamacaoProcurado = new Reclamacao(idReclamacao, descricao);
	                    return reclamacaoProcurado;
	                }
	            }
	        }catch(Exception e){ 
	            System.out.println("Ocorreu um erro ao ler o arquivo Reclamacao.cc. Exception: "+e.getMessage());
	        }finally{
	        }
	        return null;
	    }
	    
	   
	     
	    
	    
	   

	
	
	

}
