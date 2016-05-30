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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.*;

/**
 *
 * @author Igor
 */
public class ContaDao {

    private BufferedWriter bw = null;
    private BufferedReader fr = null;

    public Conta salvarConta(Conta conta) throws IOException{
        try{
            DateFormat formatacaoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            bw = new BufferedWriter(new FileWriter("Conta.cc", true));  
            conta.setIdConta(maiorIdConta()+1);

            if(conta.getDataHoraFimAtendimento() == null){
                bw.write("<idConta>"+conta.getIdConta()
                            +"<idCliente>"+conta.getCliente().getIdCliente().toString()
                            +"<idMesa>"+conta.getMesa().getIdMesa().toString()
                            +"<dataHoraInicioAtendimento>"+formatacaoData.format(conta.getDataHoraInicioAtendimento())
                            +"<dataHoraFimAtendimento>"+"null"+"<fdl>");
            }else{
                bw.write("<idConta>"+conta.getIdConta()
                            +"<idCliente>"+conta.getCliente().getIdCliente().toString()
                            +"<idMesa>"+conta.getMesa().getIdMesa().toString()
                            +"<dataHoraInicioAtendimento>"+formatacaoData.format(conta.getDataHoraInicioAtendimento())
                            +"<dataHoraFimAtendimento>"+formatacaoData.format(conta.getDataHoraFimAtendimento())+"<fdl>");
            } 
            bw.newLine();
            bw.flush();
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Conta.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
        return conta;
    }
    
    public void salvarConta(List<Conta> listConta) throws IOException{
        try{
            //Sobrescrever todas as Contas.
            File file = new File("Conta.cc");
            if ( file.exists()) {
                FileWriter fw = new FileWriter("Conta.cc",false);
            }
            DateFormat formatacaoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            bw = new BufferedWriter(new FileWriter("Conta.cc", true)); 
            for(Conta conta: listConta){
                if(conta.getDataHoraFimAtendimento() == null){
                    bw.write("<idConta>"+conta.getIdConta()
                                +"<idCliente>"+conta.getCliente().getIdCliente().toString()
                                +"<idMesa>"+conta.getMesa().getIdMesa().toString()
                                +"<dataHoraInicioAtendimento>"+formatacaoData.format(conta.getDataHoraInicioAtendimento())
                                +"<dataHoraFimAtendimento>"+"null"+"<fdl>");
                }else{
                    bw.write("<idConta>"+conta.getIdConta()
                                +"<idCliente>"+conta.getCliente().getIdCliente().toString()
                                +"<idMesa>"+conta.getMesa().getIdMesa().toString()
                                +"<dataHoraInicioAtendimento>"+formatacaoData.format(conta.getDataHoraInicioAtendimento())
                                +"<dataHoraFimAtendimento>"+formatacaoData.format(conta.getDataHoraFimAtendimento())+"<fdl>");
                }
                bw.newLine();
                bw.flush();
            }
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Conta.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
    }
    
    
    public List<Conta> abrirConta() throws IOException, ParseException{
    List<Conta> listConta = new ArrayList();
    try{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String linha;
        Integer idConta;
        Cliente cliente = new Cliente();
        Mesa mesa = new Mesa();
        Date dataHoraInicioAtendimento;
        Date dataHoraFimAtendimento;
        fr = new BufferedReader(new FileReader("Conta.cc"));

        while ((linha = fr.readLine()) != null) {
            idConta =  Integer.parseInt(linha.substring(linha.indexOf("<idConta>")+9, linha.indexOf("<idCliente>")));
            cliente.setIdCliente(Integer.parseInt(linha.substring(linha.indexOf("<idCliente>")+11, linha.indexOf("<idMesa>"))));
            mesa.setIdMesa(Integer.parseInt(linha.substring(linha.indexOf("<idMesa>")+8, linha.indexOf("<dataHoraInicioAtendimento>"))));
            dataHoraInicioAtendimento = formatter.parse(linha.substring(linha.indexOf("<dataHoraInicioAtendimento>")+27, linha.indexOf("<dataHoraFimAtendimento>")));
            String verificaNullData = linha.substring(linha.indexOf("<dataHoraFimAtendimento>")+24, linha.indexOf("<fdl>"));
            if(verificaNullData.equals("null")){
                dataHoraFimAtendimento = null;
            }else{
                dataHoraFimAtendimento = formatter.parse(verificaNullData);
            }
            listConta.add(new Conta(idConta, cliente, mesa, dataHoraInicioAtendimento, dataHoraFimAtendimento));
            cliente = new Cliente();
        }
    } catch (ParseException e){
        e.printStackTrace();
    }catch(Exception e){ 
        System.out.println("Ocorreu um erro ao ler o arquivo Conta.cc. Exception: "+e.getMessage());
    }finally{
        
    }
    return listConta;
    }
    
    public Conta abrirConta(Integer idContaProcurado) throws IOException, ParseException{
    try{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String linha;
        Integer idConta;
        Cliente cliente = new Cliente();
        Mesa mesa = new Mesa();
        Date dataHoraInicioAtendimento = null;
        Date dataHoraFimAtendimento = null;
        fr = new BufferedReader(new FileReader("Conta.cc"));

        while ((linha = fr.readLine()) != null) {
            idConta =  Integer.parseInt(linha.substring(linha.indexOf("<idConta>")+9, linha.indexOf("<idCliente>")));
            cliente.setIdCliente(Integer.parseInt(linha.substring(linha.indexOf("<idCliente>")+11, linha.indexOf("<idMesa>"))));
            
            if(cliente != null ){
                ClienteDao clientedao = new ClienteDao();
                cliente = clientedao.abrirCliente(cliente.getIdCliente());
            }
            mesa.setIdMesa(Integer.parseInt(linha.substring(linha.indexOf("<idMesa>")+8, linha.indexOf("<dataHoraInicioAtendimento>"))));
            dataHoraInicioAtendimento = formatter.parse(linha.substring(linha.indexOf("<dataHoraInicioAtendimento>")+27, linha.indexOf("<dataHoraFimAtendimento>")));
            
            String dataFimAtendimento = linha.substring(linha.indexOf("<dataHoraFimAtendimento>")+24, linha.indexOf("<fdl>"));
            if(dataFimAtendimento.equals("null")){
                dataFimAtendimento = null;
            }else{
                dataHoraFimAtendimento = formatter.parse(dataFimAtendimento);
            }

            if(idContaProcurado == idConta){
                Conta conta = new Conta(idConta, cliente, mesa, dataHoraInicioAtendimento, dataHoraFimAtendimento);
                return conta;
            }
        }
    } catch (ParseException e){
        e.printStackTrace();
    }catch(Exception e){ 
        System.out.println("Ocorreu um erro ao ler o arquivo Conta.cc. Exception: "+e.getMessage());
    }finally{
    }
    return null;
    }
    
    public Integer maiorIdConta() throws IOException, ParseException{
        Integer maiorIdConta=0;
        List<Conta> listConta = new ArrayList();
        listConta = abrirConta();
        
        if(listConta.size() > 0){
            for(Conta conta: listConta){
                if( maiorIdConta < conta.getIdConta()){
                    maiorIdConta = conta.getIdConta();
                }
            }
            return maiorIdConta;
        }else{
            return 0;
        }
    }
    
    public Conta alterarConta(Conta conta) throws IOException, ParseException{
        List<Conta> listConta = new ArrayList();
        try{
            listConta = abrirConta();
            int i;
            for(i = listConta.size()-1 ; i>= -1; i-- ){
                if( listConta.get(i).getIdConta() == conta.getIdConta() ){
                    break;
                }
            }
            listConta.set(i, conta);
            salvarConta(listConta);
            return conta;
        }catch(Exception e){ 
        }
        return null;
    }

    
}
