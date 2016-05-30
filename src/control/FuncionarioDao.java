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
public class FuncionarioDao {
    private BufferedWriter bw = null;
    private BufferedReader fr = null;

    public Funcionario salvarFuncionario(Funcionario funcionario) throws IOException{
        try{
            bw = new BufferedWriter(new FileWriter("Funcionario.cc", true));
            funcionario.setIdFuncionario(maiorIdFuncionario()+1);
            bw.write("<idFuncionario>"+funcionario.getIdFuncionario()
                        +"<cpf>"+funcionario.getCpf()
                        +"<numeroCtps>"+funcionario.getNumeroCtps()
                        +"<nome>"+funcionario.getNome()
                        +"<cargo>"+funcionario.getCargo()+"<fdl>");
            bw.newLine();
            bw.flush();
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Funcionario.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
        return funcionario;
    }
    
    public void salvarFuncionario(List<Funcionario> listFuncionario) throws IOException{
    try{

        //Sobrescrever todos Funcionarios.
        File file = new File("Funcionario.cc");
        if ( file.exists()) {
            FileWriter fw = new FileWriter("Funcionario.cc",false);
        }
        bw = new BufferedWriter(new FileWriter("Funcionario.cc", true)); 
        for(Funcionario funcionario: listFuncionario){
            bw.write("<idFuncionario>"+funcionario.getIdFuncionario().toString()
                        +"<cpf>"+funcionario.getCpf()
                        +"<numeroCtps>"+funcionario.getNumeroCtps()
                        +"<nome>"+funcionario.getNome()
                        +"<cargo>"+funcionario.getCargo()+"<fdl>");
            bw.newLine();
            bw.flush();
        }
        bw.close();    
    }catch(Exception e){ 
        System.out.println("Ocorreu um erro ao salvar no arquivo Funcionario.cc. Exception: "+e.getMessage());
    }finally{
    }
    System.out.println("Salvo com Sucesso!");
}

    public List<Funcionario> abrirFuncionario() throws IOException{
        List<Funcionario> listFuncionario = new ArrayList();
        try{
            String linha;
            Integer idFuncionario;
            String cpf;
            String numeroCtps;
            String nome;
            String cargo;
            fr = new BufferedReader(new FileReader("Funcionario.cc"));

            while ((linha = fr.readLine()) != null) {
                idFuncionario = Integer.parseInt(linha.substring(linha.indexOf("<idFuncionario>")+15, linha.indexOf("<cpf>")));
                cpf = linha.substring((linha.indexOf("<cpf>")+5), linha.indexOf("<numeroCtps>"));
                numeroCtps = linha.substring((linha.indexOf("<numeroCtps>")+12), linha.indexOf("<nome>"));
                nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cargo>"));
                cargo = linha.substring((linha.indexOf("<cargo>")+7), linha.indexOf("<fdl>"));

                listFuncionario.add(new Funcionario(idFuncionario, cpf, numeroCtps, nome, cargo));
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Funcionario.cc. Exception: "+e.getMessage());
        }finally{
        }
        return (List<Funcionario>) listFuncionario;
    }
    
    public Funcionario abrirFuncionario(Integer idFuncionarioProcurado) throws IOException{
    try{
        String linha;
        Integer idFuncionario;
        String cpf;
        String numeroCtps;
        String nome;
        String cargo;
        fr = new BufferedReader(new FileReader("Funcionario.cc"));

        while ((linha = fr.readLine()) != null) {
            idFuncionario = Integer.parseInt(linha.substring(linha.indexOf("<idFuncionario>")+15, linha.indexOf("<cpf>")));
            cpf = linha.substring((linha.indexOf("<cpf>")+5), linha.indexOf("<numeroCtps>"));
            numeroCtps = linha.substring((linha.indexOf("<numeroCtps>")+12), linha.indexOf("<nome>"));
            nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cargo>"));
            cargo = linha.substring((linha.indexOf("<cargo>")+7), linha.indexOf("<fdl>"));

            if (idFuncionarioProcurado == idFuncionario){
                Funcionario funcionario = new Funcionario(idFuncionario, cpf, numeroCtps, nome, cargo);
                return funcionario;
            }
        }
    }catch(Exception e){ 
        System.out.println("Ocorreu um erro ao ler o arquivo Funcionario.cc. Exception: "+e.getMessage());
    }finally{
    }
    return null;
    }

    public Funcionario abrirFuncionario(String cpfProcurado) throws IOException{
        try{
            String linha;
            Integer idFuncionario;
            String cpf;
            String numeroCtps;
            String nome;
            String cargo;

            fr = new BufferedReader(new FileReader("Funcionario.cc"));

            while ((linha = fr.readLine()) != null) {
                idFuncionario = Integer.parseInt(linha.substring(linha.indexOf("<idFuncionario>")+15, linha.indexOf("<cpf>")));
                cpf = linha.substring((linha.indexOf("<cpf>")+5), linha.indexOf("<numeroCtps>"));
                numeroCtps = linha.substring((linha.indexOf("<numeroCtps>")+12), linha.indexOf("<nome>"));
                nome = linha.substring((linha.indexOf("<nome>")+6), linha.indexOf("<cargo>"));
                cargo = linha.substring((linha.indexOf("<cargo>")+7), linha.indexOf("<fdl>"));

                if(cpfProcurado.equals(cpf)){
                    Funcionario funcionarioProcurado = new Funcionario(idFuncionario, cpf, numeroCtps, nome, cargo);
                    
                    return funcionarioProcurado;
                }
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Funcionario.cc. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }
    
    public Integer maiorIdFuncionario() throws IOException{
        Integer maiorIdFuncionario=0;
        List<Funcionario> listFuncionario = new ArrayList();
        listFuncionario = abrirFuncionario();
        
        if(listFuncionario.size() > 0){
            for(Funcionario funcionario: listFuncionario){
                if( maiorIdFuncionario < funcionario.getIdFuncionario()){
                    maiorIdFuncionario = funcionario.getIdFuncionario();
                }
            }
            return maiorIdFuncionario;
        }else{
            return 0;
        }
    }

    public List<Funcionario> excluirFuncionario(Integer idFuncionarioExcluir) throws IOException{
    List<Funcionario> listFuncionario = new ArrayList();
    listFuncionario = abrirFuncionario();
    for(Funcionario funcionario: listFuncionario){
        if( idFuncionarioExcluir == funcionario.getIdFuncionario()){
            listFuncionario.remove(funcionario);
            salvarFuncionario(listFuncionario);
            return listFuncionario;
        }
    }
    return null;
    }

    public List<Funcionario> alterarFuncionario(Integer idFuncionarioAlterar, Integer numAtributoAlterar, String novoAtributo) throws IOException{
        List<Funcionario> listFuncionario = new ArrayList();
        listFuncionario = abrirFuncionario();
        
        for(Funcionario funcionario: listFuncionario){
            if( idFuncionarioAlterar == funcionario.getIdFuncionario()){
                switch( numAtributoAlterar )
                {
                    case 1: funcionario.setCpf(novoAtributo);
                        break;
                    case 2: funcionario.setNumeroCtps(novoAtributo);
                        break;
                    case 3: funcionario.setNome(novoAtributo);
                        break;
                    case 4: funcionario.setCargo(novoAtributo);
                        break;
                    default: System.out.println("Erro ao escolher o atributo.");
                }
                salvarFuncionario(listFuncionario);
                return listFuncionario;
            }
        }
        return null;
    }

}
