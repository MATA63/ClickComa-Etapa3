/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Igor
 */
public class MesaDao {

    private BufferedWriter bw = null;
    private BufferedReader fr = null;

    public Mesa salvarMesa(Mesa mesa) throws IOException{
        try{
                bw = new BufferedWriter(new FileWriter("Mesa.cc", true));
                mesa.setIdMesa(maiorIdMesa()+1);
                bw.write("<idMesa>"+mesa.getIdMesa()
                            +"<numero>"+mesa.getNumero()
                            +"<local>"+mesa.getLocal()+"<fdl>");
                bw.newLine();
                bw.flush();
                bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Mesa.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
        return mesa;
    }
    
    public void salvarMesa(List<Mesa> listMesa) throws IOException{
        try{
            //Sobrescrever todos Mesas.
            File file = new File("Mesa.cc");
            if ( file.exists()) {
                FileWriter fw = new FileWriter("Mesa.cc",false);
            }
            bw = new BufferedWriter(new FileWriter("Mesa.cc", true)); 
            for(Mesa mesa: listMesa){
                bw.write("<idMesa>"+mesa.getIdMesa().toString()
                            +"<numero>"+mesa.getNumero()
                            +"<local>"+mesa.getLocal()+"<fdl>");
                bw.newLine();
                bw.flush();
            }
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Mesa.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
    }
    
    public List<Mesa> abrirMesa() throws IOException{
        List<Mesa> listMesa = new ArrayList();
        try{
            String linha;
            Integer idMesa;
            String numero;
            String local;
            fr = new BufferedReader(new FileReader("Mesa.cc"));

            while ((linha = fr.readLine()) != null) {
                idMesa =  Integer.parseInt(linha.substring(linha.indexOf("<idMesa>")+8, linha.indexOf("<numero>")));
                numero = linha.substring(linha.indexOf("<numero>")+8, linha.indexOf("<local>"));
                local = linha.substring(linha.indexOf("<local>")+7, linha.indexOf("<fdl>"));

                listMesa.add(new Mesa(idMesa, numero, local));
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Mesa.cc. Exception: "+e.getMessage());
        }finally{
        }
        return listMesa;
    }
    
    public Mesa abrirMesa(Integer idMesaProcurada) throws IOException{
    try{
        String linha;
        Integer idMesa;
        String numero;
        String local;
        fr = new BufferedReader(new FileReader("Mesa.cc"));

        while ((linha = fr.readLine()) != null) {
            idMesa =  Integer.parseInt(linha.substring(linha.indexOf("<idMesa>")+8, linha.indexOf("<numero>")));
            numero = linha.substring(linha.indexOf("<numero>")+8, linha.indexOf("<local>"));
            local = linha.substring(linha.indexOf("<local>")+7, linha.indexOf("<fdl>"));
            
            if (idMesaProcurada == idMesa){
                Mesa mesa = new Mesa(idMesa, numero, local);
                return mesa;
            }
        }
    }catch(Exception e){ 
        System.out.println("Ocorreu um erro ao ler o arquivo Mesa.cc. Exception: "+e.getMessage());
    }finally{
    }
    return null;
    }
    
    public Integer maiorIdMesa() throws IOException{
        Integer maiorIdMesa=0;
        List<Mesa> listMesa = new ArrayList();
        listMesa = abrirMesa();
        
        if(listMesa.size() > 0){
            for(Mesa mesa: listMesa){
                if( maiorIdMesa < mesa.getIdMesa()){
                    maiorIdMesa = mesa.getIdMesa();
                }
            } 
            return maiorIdMesa;
        }else{
            return 0;
        }
    }
    
    public List<Mesa> excluirMesa(Integer idMesaExcluir) throws IOException{
        List<Mesa> listMesa = new ArrayList();
        listMesa = abrirMesa();
        for(Mesa mesa: listMesa){
            if( idMesaExcluir == mesa.getIdMesa()){
                listMesa.remove(mesa);
                salvarMesa(listMesa);
                return listMesa;
            }
        }
        return null;
    }

    public List<Mesa> alterarMesa(Integer idMesaAlterar, Integer numAtributoAlterar, String novoAtributo) throws IOException{
        List<Mesa> listMesa = new ArrayList();
        listMesa = abrirMesa();
        
        for(Mesa mesa: listMesa){
            if( idMesaAlterar == mesa.getIdMesa()){
                switch( numAtributoAlterar )
                {
                    case 1: mesa.setNumero(novoAtributo);
                        break;
                    case 2: mesa.setLocal(novoAtributo);
                        break;
                    default: System.out.println("Erro ao escolher o atributo.");
                }
                salvarMesa(listMesa);
                return listMesa;
            }
        }
        return null;
    }

    
}
