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


public class AjudaSuporteDao {
	
	private BufferedWriter bw = null;
    private BufferedReader fr = null;

    public AjudaSuporte salvarAjudaSuporte(AjudaSuporte ajudaSuporte) throws IOException{
        try{
            bw = new BufferedWriter(new FileWriter("AjudaSuporte.cc", true));
            ajudaSuporte.setIdAjudaSuporte(maiorIdAjudaSuporte()+1);
            bw.write("<idAjudaSuporte>"+ajudaSuporte.getIdAjudaSuporte()
            			+"<Titulo>"+ajudaSuporte.getTitulo().toString()
                        +"<descricao>"+ajudaSuporte.getDescricao().toString()+"<fdl>");
            bw.newLine();
            bw.flush();
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo AjudaSuporte.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
        return ajudaSuporte;
    }

    public void salvarAjudaSuporte(List<AjudaSuporte> listAjudaSuporte) throws IOException{
        try{
            //Sobrescrever todas as AjudaSuporte.
            File file = new File("AjudaSuporte.cc");
            if ( file.exists()) {
                FileWriter fw = new FileWriter("AjudaSuporte.cc",false);
            }
            bw = new BufferedWriter(new FileWriter("AjudaSuporte.cc", true)); 
            for(AjudaSuporte ajudaSuporte: listAjudaSuporte){
                bw.write("<idReclamacao>"+ajudaSuporte.getIdAjudaSuporte().toString()
                		+"<Titulo>"+ajudaSuporte.getTitulo()	
                		+"<descricao>"+ajudaSuporte.getDescricao()+"<fdl>");
                bw.newLine();
                bw.flush();
            }
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo AjudaSuporte.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
    }
    
    public List<AjudaSuporte> abrirAjudaSuporte() throws IOException{
        List<AjudaSuporte> listAjudaSuporte = new ArrayList();
        try{
            String linha;
            Integer idAjudaSuporte;
            String titulo;
            String descricao;
            fr = new BufferedReader(new FileReader("AjudaSuporte.cc"));

            while ((linha = fr.readLine()) != null) {
                //                  Integer.parseInt(linha.substring(linha.indexOf("<idConta>")+9, linha.indexOf("<idCliente>")));
                idAjudaSuporte =  Integer.parseInt(linha.substring(linha.indexOf("<idAjudaSuporte>")+16, linha.indexOf("<Titulo>")));
                titulo = linha.substring(linha.indexOf("<Titulo>")+8, linha.indexOf("<descricao>"));
                descricao = linha.substring(linha.indexOf("<descricao>")+11, linha.indexOf("<fdl>"));

                listAjudaSuporte.add(new AjudaSuporte(idAjudaSuporte, titulo, descricao));
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo AjudaSuporte.cc. Exception: "+e.getMessage());
        }finally{
        }
        return listAjudaSuporte;
    }
    
    public AjudaSuporte abrirAjudaSuporte(Integer idAjudaSuporteProcurado) throws IOException{
        try{
            String linha;
            Integer idAjudaSuporte;
            String titulo;
            String descricao;
            fr = new BufferedReader(new FileReader("AjudaSuporte.cc"));

            while ((linha = fr.readLine()) != null) {
            	 idAjudaSuporte =  Integer.parseInt(linha.substring(linha.indexOf("<idAjudaSuporte>")+16, linha.indexOf("<titulo>")));
                 titulo = linha.substring(linha.indexOf("<titulo>")+8, linha.indexOf("<descricao>"));
                 descricao = linha.substring(linha.indexOf("<descricao>")+11, linha.indexOf("<fdl>"));
                 
                if(idAjudaSuporteProcurado == idAjudaSuporte){
                	AjudaSuporte ajudaSuporteProcurado = new AjudaSuporte(idAjudaSuporte, titulo, descricao);
                    return ajudaSuporteProcurado;
                }
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo AjudaSuporte.cc. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }
    
    public Integer maiorIdAjudaSuporte() throws IOException{
        Integer maiorIdAjudaSuporte=0;
        List<AjudaSuporte> listAjudaSuporte = new ArrayList();
        listAjudaSuporte = abrirAjudaSuporte();
        
        if(listAjudaSuporte.size() > 0){
            for(AjudaSuporte ajudaSuporte: listAjudaSuporte){
                if( maiorIdAjudaSuporte < ajudaSuporte.getIdAjudaSuporte()){
                    maiorIdAjudaSuporte = ajudaSuporte.getIdAjudaSuporte();
                }
            } 
            return maiorIdAjudaSuporte;
        }else{
            return 0;
        }
    }
    
    public List<AjudaSuporte> excluirAjudaSuporte(Integer idAjudaSuporteExcluir) throws IOException{
        List<AjudaSuporte> listAjudaSuporte = new ArrayList();
        listAjudaSuporte = abrirAjudaSuporte();
        for(AjudaSuporte ajudaSuporte: listAjudaSuporte){
            if( idAjudaSuporteExcluir == ajudaSuporte.getIdAjudaSuporte()){
                listAjudaSuporte.remove(ajudaSuporte);
                salvarAjudaSuporte(listAjudaSuporte);
                return listAjudaSuporte;
            }
        }
        return null;
    }   
   
    public List<AjudaSuporte> alterarAjudaSuporte(Integer idAjudaSuporteAlterar, Integer numAtributoAlterar, String novoAtributo) throws IOException{
        List<AjudaSuporte> listAjudaSuporte = new ArrayList();
        listAjudaSuporte = abrirAjudaSuporte();
        
        for(AjudaSuporte ajudaSuporte: listAjudaSuporte){
            if( idAjudaSuporteAlterar == ajudaSuporte.getIdAjudaSuporte()){
                switch( numAtributoAlterar )
                {
                    case 1: ajudaSuporte.setTitulo(novoAtributo);
                        break;
                    case 2: ajudaSuporte.setDescricao(novoAtributo);
                        break;
                    default: System.out.println("Erro ao escolher o atributo.");
                }
                salvarAjudaSuporte(listAjudaSuporte);
                return listAjudaSuporte;
            }
        }
        return null;
     }    
}
