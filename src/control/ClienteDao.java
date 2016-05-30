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
public class ClienteDao {

    private BufferedWriter bw = null;
    private BufferedReader fr = null;

    public Cliente salvarCliente(Cliente cliente) throws IOException{
        try{
            bw = new BufferedWriter(new FileWriter("Cliente.cc", true));
            cliente.setIdCliente(maiorIdCliente()+1);
            bw.write("<idCliente>"+cliente.getIdCliente()
                        +"<nome>"+cliente.getNome()
                        +"<cpf>"+cliente.getCpf()
                        +"<email>"+cliente.getEmail()+"<fdl>");
            bw.newLine();
            bw.flush();
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Cliente.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
        return cliente;
    }

    public void salvarCliente(List<Cliente> listCliente) throws IOException{
        try{
            //Sobrescrever todos Clientes.
            File file = new File("Cliente.cc");
            if ( file.exists()) {
                FileWriter fw = new FileWriter("Cliente.cc",false);
            }
            bw = new BufferedWriter(new FileWriter("Cliente.cc", true)); 
            for(Cliente cliente: listCliente){
                bw.write("<idCliente>"+cliente.getIdCliente().toString()
                    +"<nome>"+cliente.getNome()
                    +"<cpf>"+cliente.getCpf()
                    +"<email>"+cliente.getEmail()+"<fdl>");
                bw.newLine();
                bw.flush();
            }
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Cliente.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
    }
    
    public List<Cliente> abrirCliente() throws IOException{
        List<Cliente> listCliente = new ArrayList();
        try{
            String linha;
            Integer idCliente;
            String nome;
            String cpf;
            String email;
            fr = new BufferedReader(new FileReader("Cliente.cc"));

            while ((linha = fr.readLine()) != null) {
                idCliente =  Integer.parseInt(linha.substring(linha.indexOf("<idCliente>")+11, linha.indexOf("<nome>")));
                nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cpf>"));
                cpf = linha.substring((linha.indexOf("<cpf>")+5), linha.indexOf("<email>"));
                email = linha.substring((linha.indexOf("<email>")+7), linha.indexOf("<fdl>"));

                listCliente.add(new Cliente(idCliente, nome, cpf, email));
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Cliente.cc. Exception: "+e.getMessage());
        }finally{
        }
        return listCliente;
    }
    
    public Cliente abrirCliente(Integer idClienteProcurado) throws IOException{
        try{
            String linha;
            Integer idCliente;
            String nome;
            String cpf;
            String email;
            fr = new BufferedReader(new FileReader("Cliente.cc"));

            while ((linha = fr.readLine()) != null) {
                idCliente =  Integer.parseInt(linha.substring(linha.indexOf("<idCliente>")+11, linha.indexOf("<nome>")));
                nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cpf>"));
                cpf = linha.substring((linha.indexOf("<cpf>")+5), linha.indexOf("<email>"));
                email = linha.substring((linha.indexOf("<email>")+7), linha.indexOf("<fdl>"));

                if(idClienteProcurado == idCliente){
                    Cliente clienteProcurado = new Cliente(idCliente, nome, cpf, email);
                    return clienteProcurado;
                }
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Cliente.cc. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }
    
    public Cliente abrirCliente(String cpfProcurado) throws IOException{
        try{
            String linha;
            Integer idCliente;
            String nome;
            String cpf;
            String email;
            fr = new BufferedReader(new FileReader("Cliente.cc"));

            while ((linha = fr.readLine()) != null) {
                idCliente =  Integer.parseInt(linha.substring(linha.indexOf("<idCliente>")+11, linha.indexOf("<nome>")));
                nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cpf>"));
                cpf = linha.substring((linha.indexOf("<cpf>")+5), linha.indexOf("<email>"));
                email = linha.substring((linha.indexOf("<email>")+7), linha.indexOf("<fdl>"));

                if(cpfProcurado.equals(cpf)){
                    Cliente clienteProcurado = new Cliente(idCliente, nome, cpf, email);
                    return clienteProcurado;
                }
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Cliente.cc. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }
     
    public Integer maiorIdCliente() throws IOException{
        Integer maiorIdCliente=0;
        List<Cliente> listCliente = new ArrayList();
        listCliente = abrirCliente();
        
        if(listCliente.size() > 0){
            for(Cliente cliente: listCliente){
                if( maiorIdCliente < cliente.getIdCliente()){
                    maiorIdCliente = cliente.getIdCliente();
                }
            }
            return maiorIdCliente;
        }else{
            return 0;
        }
    }
    
    public List<Cliente> excluirCliente(Integer idClienteExcluir) throws IOException{
        List<Cliente> listCliente = new ArrayList();
        listCliente = abrirCliente();
        for(Cliente cliente: listCliente){
            if( idClienteExcluir == cliente.getIdCliente()){
                listCliente.remove(cliente);
                salvarCliente(listCliente);
                return listCliente;
            }
        }
        return null;
    }

    public List<Cliente> alterarCliente(Integer idClienteAlterar, Integer numAtributoAlterar, String novoAtributo) throws IOException{
        List<Cliente> listCliente = new ArrayList();
        listCliente = abrirCliente();
        
        for(Cliente cliente: listCliente){
            if( idClienteAlterar == cliente.getIdCliente()){
                switch( numAtributoAlterar )
                {
                    case 1: cliente.setNome(novoAtributo);
                        break;
                    case 2: cliente.setCpf(novoAtributo);
                        break;
                    case 3: cliente.setEmail(novoAtributo);
                        break;
                    default: System.out.println("Erro ao escolher o atributo.");
                }
                salvarCliente(listCliente);
                return listCliente;
            }
        }
        return null;
    }

}
