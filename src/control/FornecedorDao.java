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
public class FornecedorDao {
	
	private BufferedWriter bw = null;
    private BufferedReader fr = null;

    public Fornecedor salvarFornecedor(Fornecedor fornecedor) throws IOException{
        try{
            bw = new BufferedWriter(new FileWriter("Fornecedor.cc", true));
            fornecedor.setIdFornecedor(maiorIdFornecedor()+1);
            bw.write("<idFornecedor>"+fornecedor.getIdFornecedor()
                        +"<nome>"+fornecedor.getNome()
                        +"<cnpj>"+fornecedor.getCnpj()
                        +"<ramo>"+fornecedor.getRamo()+"<fdl>");
            bw.newLine();
            bw.flush();
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Fornecedor.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
        return fornecedor;
    }

    public void salvarFornecedor(List<Fornecedor> listFornecedor) throws IOException{
        try{
            //Sobrescrever todos os Fornecedores.
            File file = new File("Fornecedor.cc");
            if ( file.exists()) {
                FileWriter fw = new FileWriter("Fornecedor.cc",false);
            }
            bw = new BufferedWriter(new FileWriter("Fornecedor.cc", true)); 
            for(Fornecedor fornecedor: listFornecedor){
                bw.write("<idFornecedor>"+fornecedor.getIdFornecedor().toString()
                    +"<nome>"+fornecedor.getNome()
                    +"<cnpj>"+fornecedor.getCnpj()
                    +"<ramo>"+fornecedor.getRamo()+"<fdl>");
                bw.newLine();
                bw.flush();
            }
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Fornecedor.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
    }
    
    public List<Fornecedor> abrirFornecedor() throws IOException{
        List<Fornecedor> listFornecedor = new ArrayList();
        try{
            String linha;
            Integer idFornecedor;
            String nome;
            String cnpj;
            String ramo;
            fr = new BufferedReader(new FileReader("Fornecedor.cc"));

            while ((linha = fr.readLine()) != null) {
                idFornecedor =  Integer.parseInt(linha.substring(linha.indexOf("<idFornecedor>")+14, linha.indexOf("<nome>")));
                nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cnpj>"));
                cnpj = linha.substring((linha.indexOf("<cnpj>")+6), linha.indexOf("<ramo>"));
                ramo = linha.substring((linha.indexOf("<ramo>")+6), linha.indexOf("<fdl>"));

                listFornecedor.add(new Fornecedor(idFornecedor, nome, cnpj, ramo));
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Fornecedor.cc. Exception: "+e.getMessage());
        }finally{
        }
        return listFornecedor;
    }
    
    public Fornecedor abrirFornecedor(Integer idFornecedorProcurado) throws IOException{
        try{
            String linha;
            Integer idFornecedor;
            String nome;
            String cnpj;
            String ramo;
            fr = new BufferedReader(new FileReader("Fornecedor.cc"));

            while ((linha = fr.readLine()) != null) {
                idFornecedor =  Integer.parseInt(linha.substring(linha.indexOf("<idFornecedor>")+14, linha.indexOf("<nome>")));
                nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cnpj>"));
                cnpj = linha.substring((linha.indexOf("<cnpj>")+6), linha.indexOf("<ramo>"));
                ramo = linha.substring((linha.indexOf("<ramo>")+6), linha.indexOf("<fdl>"));

                if(idFornecedorProcurado == idFornecedor){
                    Fornecedor fornecedorProcurado = new Fornecedor(idFornecedor, nome, cnpj, ramo);
                    return fornecedorProcurado;
                }
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Fornecedor.cc. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }
    
    public Fornecedor abrirFornecedor(String cnpjProcurado) throws IOException{
        try{
            String linha;
            Integer idFornecedor;
            String nome;
            String cnpj;
            String ramo;
            fr = new BufferedReader(new FileReader("Fornecedor.cc"));

            while ((linha = fr.readLine()) != null) {
                idFornecedor =  Integer.parseInt(linha.substring(linha.indexOf("<idFornecedor>")+14, linha.indexOf("<nome>")));
                nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cnpj>"));
                cnpj = linha.substring((linha.indexOf("<cnpj>")+6), linha.indexOf("<ramo>"));
                ramo = linha.substring((linha.indexOf("<ramo>")+6), linha.indexOf("<fdl>"));

                if(cnpjProcurado.equals(cnpj)){
                    Fornecedor fornecedorProcurado = new Fornecedor(idFornecedor, nome, cnpj, ramo);
                    return fornecedorProcurado;
                }
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Fornecedor.cc. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }
     
    public Integer maiorIdFornecedor() throws IOException{
        Integer maiorIdFornecedor=0;
        List<Fornecedor> listFornecedor = new ArrayList();
        listFornecedor = abrirFornecedor();
        
        if(listFornecedor.size() > 0){
            for(Fornecedor fornecedor: listFornecedor){
                if( maiorIdFornecedor < fornecedor.getIdFornecedor()){
                    maiorIdFornecedor = fornecedor.getIdFornecedor();
                }
            }
            return maiorIdFornecedor;
        }else{
            return 0;
        }
    }
    
    public List<Fornecedor> excluirFornecedor(Integer idFornecedorExcluir) throws IOException{
        List<Fornecedor> listFornecedor = new ArrayList();
        listFornecedor = abrirFornecedor();
        for(Fornecedor fornecedor: listFornecedor){
            if( idFornecedorExcluir == fornecedor.getIdFornecedor()){
                listFornecedor.remove(fornecedor);
                salvarFornecedor(listFornecedor);
                return listFornecedor;
            }
        }
        return null;
    }

    public List<Fornecedor> alterarFornecedor(Integer idFornecededorAlterar, Integer numAtributoAlterar, String novoAtributo) throws IOException{
        List<Fornecedor> listFornecedor = new ArrayList();
        listFornecedor = abrirFornecedor();
        
        for(Fornecedor fornecedor: listFornecedor){
            if( idFornecededorAlterar == fornecedor.getIdFornecedor()){
                switch( numAtributoAlterar )
                {
                    case 1: fornecedor.setNome(novoAtributo);
                        break;
                    case 2: fornecedor.setCnpj(novoAtributo);
                        break;
                    case 3: fornecedor.setRamo(novoAtributo);
                        break;
                    default: System.out.println("Erro ao escolher o atributo.");
                }
                salvarFornecedor(listFornecedor);
                return listFornecedor;
            }
        }
        return null;
    }

}
