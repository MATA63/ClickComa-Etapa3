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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.*;

public class PedidoDao {
    private BufferedWriter bw = null;
    private BufferedReader fr = null;

    public Pedido salvarPedido(Pedido pedido) throws IOException{
        try{
            DateFormat formatacaoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            bw = new BufferedWriter(new FileWriter("Pedido.cc", true));  
            pedido.setIdPedido(maiorIdPedido()+1);
            String idGarcomEhNull;
            String idCozinheiroEhNull;
            if(pedido.getCozinheiro() == null){
                idCozinheiroEhNull = "null";
            }else{
                idCozinheiroEhNull = pedido.getCozinheiro().getIdFuncionario().toString();
            }  
            if(pedido.getGarcom()== null){
                idGarcomEhNull = "null";
            }else{
                idGarcomEhNull = pedido.getGarcom().getIdFuncionario().toString();
            }
            bw.write("<idPedido>"+pedido.getIdPedido()
                        +"<conta>"+pedido.getConta().getIdConta().toString()
                        +"<item>"+pedido.getItem().getIdItem().toString()
                        +"<quantidade>"+pedido.getQuantidade().toString()
                        +"<dataHora>"+formatacaoData.format(pedido.getDataHora())
                        +"<garcom>"+idGarcomEhNull
                        +"<cozinheiro>"+idCozinheiroEhNull+"<fdl>");
            bw.newLine();
            bw.flush();
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Pedido.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
        return pedido;
    }

    public void salvarPedido(List<Pedido> listPedido) throws IOException{
        try{
            //Sobrescrever todos Pedidos.
            File file = new File("Pedido.cc");
            if ( file.exists()) {
                FileWriter fw = new FileWriter("Pedido.cc",false);
            }
            DateFormat formatacaoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            bw = new BufferedWriter(new FileWriter("Pedido.cc", true)); 
            String idGarcomEhNull;
            String idCozinheiroEhNull;
            for(Pedido pedido: listPedido){
                if(pedido.getCozinheiro() == null){
                    idCozinheiroEhNull = "null";
                }else{
                    idCozinheiroEhNull = pedido.getCozinheiro().getIdFuncionario().toString();
                }  
                if(pedido.getGarcom()== null){
                    idGarcomEhNull = "null";
                }else{
                    idGarcomEhNull = pedido.getGarcom().getIdFuncionario().toString();
                }
                
                bw.write("<idPedido>"+pedido.getIdPedido()
                            +"<conta>"+pedido.getConta().getIdConta().toString()
                            +"<item>"+pedido.getItem().getIdItem().toString()
                            +"<quantidade>"+pedido.getQuantidade().toString()
                            +"<dataHora>"+formatacaoData.format(pedido.getDataHora())
                            +"<garcom>"+idGarcomEhNull
                            +"<cozinheiro>"+idCozinheiroEhNull+"<fdl>");
                    bw.newLine();
                    bw.flush();
            }
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Pedido.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
    }
    
    public void informarAtendimentoAoPedido(Pedido pedido) throws IOException{
            List<Pedido> listPedido = new ArrayList();
            listPedido = abrirPedido();
            
            int i;
            for(i = listPedido.size()-1 ; i>= -1; i-- ){
                if( listPedido.get(i).getIdPedido() == pedido.getIdPedido()){
                    break;
                }
            }
            listPedido.set(i, pedido);
            salvarPedido(listPedido);
    }

    public List<Pedido> abrirPedido() throws IOException{
        List<Pedido> listPedido = new ArrayList();
        try{
            
            ItemDao itemDao = new ItemDao();
            ContaDao contaDao = new ContaDao();
            MesaDao mesaDao = new MesaDao();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String linha;
            Integer idPedido;
            Conta conta = new Conta();
            Item item = new Item();
            Integer quantidade;
            Date dataHora;
            Funcionario garcom = new Funcionario();
            Funcionario cozinheiro = new Funcionario();
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            fr = new BufferedReader(new FileReader("Pedido.cc"));
            
            
            while ((linha = fr.readLine()) != null) {
                idPedido =  Integer.parseInt(linha.substring(linha.indexOf("<idPedido>")+10, linha.indexOf("<conta>")));
                conta.setIdConta(Integer.parseInt(linha.substring(linha.indexOf("<conta>")+7, linha.indexOf("<item>"))));
                
                conta = contaDao.abrirConta(conta.getIdConta());
                conta.setMesa( mesaDao.abrirMesa(conta.getMesa().getIdMesa()));
                
                item.setIdItem(Integer.parseInt(linha.substring(linha.indexOf("<item>")+6, linha.indexOf("<quantidade>"))));
                item = itemDao.abrirItem(item.getIdItem());
                
                quantidade = Integer.parseInt(linha.substring(linha.indexOf("<quantidade>")+12, linha.indexOf("<dataHora>")));
                dataHora = formatter.parse(linha.substring(linha.indexOf("<dataHora>")+10, linha.indexOf("<garcom>")));

                if(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>")).equals("null")){
                    garcom = null;
                }else{
                    garcom = funcionarioDao.abrirFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>"))));
                }
                if(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>")).equals("null")){
                    cozinheiro = null;
                }else{
                    cozinheiro = funcionarioDao.abrirFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>"))));
                }
                listPedido.add(new Pedido(idPedido, conta, itemDao.abrirItem(item.getIdItem()), quantidade, dataHora, garcom, cozinheiro));
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Pedido.cc. Exception: "+e.getMessage());
        }finally{
        }
        return listPedido;
    }
    
    public List<Pedido> abrirPedido(Conta contaPesquisada) throws IOException{
    try{
        List<Pedido> listPedido = new ArrayList();
        ItemDao itemDao = new ItemDao();
        ContaDao contaDao = new ContaDao();
        MesaDao mesaDao = new MesaDao();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String linha;
        Integer idPedido;
        Conta conta = new Conta();
        Item item = new Item();
        Integer quantidade;
        Date dataHora;
        Funcionario garcom = new Funcionario();
        Funcionario cozinheiro = new Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        fr = new BufferedReader(new FileReader("Pedido.cc"));

        while ((linha = fr.readLine()) != null) {
            idPedido =  Integer.parseInt(linha.substring(linha.indexOf("<idPedido>")+10, linha.indexOf("<conta>")));
            conta.setIdConta(Integer.parseInt(linha.substring(linha.indexOf("<conta>")+7, linha.indexOf("<item>"))));

            conta = contaDao.abrirConta(conta.getIdConta());
            conta.setMesa( mesaDao.abrirMesa(conta.getMesa().getIdMesa()));

            item.setIdItem(Integer.parseInt(linha.substring(linha.indexOf("<item>")+6, linha.indexOf("<quantidade>"))));
            item = itemDao.abrirItem(item.getIdItem());

            quantidade = Integer.parseInt(linha.substring(linha.indexOf("<quantidade>")+12, linha.indexOf("<dataHora>")));
            dataHora = formatter.parse(linha.substring(linha.indexOf("<dataHora>")+10, linha.indexOf("<garcom>")));

            if(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>")).equals("null")){
                garcom = null;
            }else{
                garcom = funcionarioDao.abrirFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>"))));
            }
            if(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>")).equals("null")){
                cozinheiro = null;
            }else{
                cozinheiro = funcionarioDao.abrirFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>"))));
            }
            
            if (contaPesquisada.getIdConta() == conta.getIdConta()){
                listPedido.add(new Pedido(idPedido, contaPesquisada, itemDao.abrirItem(item.getIdItem()), quantidade, dataHora, garcom, cozinheiro));
            }
        }
        return listPedido;
    }catch(Exception e){ 
        System.out.println("Ocorreu um erro ao ler o arquivo Pedido.cc. Exception: "+e.getMessage());
    }finally{
    }
    return null;
    }
    
    public List<Pedido> abrirPedidoVisaoGarcom() throws IOException{
        try{
            List<Pedido> listPedido = new ArrayList();
            ItemDao itemDao = new ItemDao();
            ContaDao contaDao = new ContaDao();
            MesaDao mesaDao = new MesaDao();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String linha;
            Integer idPedido;
            Conta conta = new Conta();
            Item item = new Item();
            Integer quantidade;
            Date dataHora;
            Funcionario garcom = new Funcionario();
            Funcionario cozinheiro = new Funcionario();
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            fr = new BufferedReader(new FileReader("Pedido.cc"));

            while ((linha = fr.readLine()) != null) {
                idPedido =  Integer.parseInt(linha.substring(linha.indexOf("<idPedido>")+10, linha.indexOf("<conta>")));
                conta.setIdConta(Integer.parseInt(linha.substring(linha.indexOf("<conta>")+7, linha.indexOf("<item>"))));
                
                conta = contaDao.abrirConta(conta.getIdConta());
                conta.setMesa( mesaDao.abrirMesa(conta.getMesa().getIdMesa()));
                
                item.setIdItem(Integer.parseInt(linha.substring(linha.indexOf("<item>")+6, linha.indexOf("<quantidade>"))));
                item = itemDao.abrirItem(item.getIdItem());
                
                quantidade = Integer.parseInt(linha.substring(linha.indexOf("<quantidade>")+12, linha.indexOf("<dataHora>")));
                dataHora = formatter.parse(linha.substring(linha.indexOf("<dataHora>")+10, linha.indexOf("<garcom>")));

                if(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>")).equals("null")){
                    garcom = null;
                }else{
                    garcom = funcionarioDao.abrirFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>"))));
                }
                if(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>")).equals("null")){
                    cozinheiro = null;
                }else{
                    cozinheiro = funcionarioDao.abrirFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>"))));
                }

                if(( (item.getNecessitaPreparo()) && (garcom == null) && !(cozinheiro == null) ) 
                        || 
                   ( (!item.getNecessitaPreparo() && (garcom == null) && (cozinheiro == null) ) )) {
                    listPedido.add(new Pedido(idPedido, conta, itemDao.abrirItem(item.getIdItem()), quantidade, dataHora, garcom, cozinheiro));
                }else{
                    continue;
                }
                
            }
            return listPedido;
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Pedido.cc em Pedido da Visão do Garçom. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }

    public List<Pedido> abrirPedidoVisaoCozinheiro() throws IOException{
        try{
            List<Pedido> listPedido = new ArrayList();
            ItemDao itemDao = new ItemDao();
            ContaDao contaDao = new ContaDao();
            MesaDao mesaDao = new MesaDao();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String linha;
            Integer idPedido;
            Conta conta = new Conta();
            Item item = new Item();
            Integer quantidade;
            Date dataHora;
            Funcionario garcom = new Funcionario();
            Funcionario cozinheiro = new Funcionario();
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            fr = new BufferedReader(new FileReader("Pedido.cc"));

            while ((linha = fr.readLine()) != null) {
                idPedido =  Integer.parseInt(linha.substring(linha.indexOf("<idPedido>")+10, linha.indexOf("<conta>")));
                conta.setIdConta(Integer.parseInt(linha.substring(linha.indexOf("<conta>")+7, linha.indexOf("<item>"))));
                
                conta = contaDao.abrirConta(conta.getIdConta());
                conta.setMesa( mesaDao.abrirMesa(conta.getMesa().getIdMesa()));
                
                item.setIdItem(Integer.parseInt(linha.substring(linha.indexOf("<item>")+6, linha.indexOf("<quantidade>"))));
                item = itemDao.abrirItem(item.getIdItem());
                
                quantidade = Integer.parseInt(linha.substring(linha.indexOf("<quantidade>")+12, linha.indexOf("<dataHora>")));
                dataHora = formatter.parse(linha.substring(linha.indexOf("<dataHora>")+10, linha.indexOf("<garcom>")));

                if(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>")).equals("null")){
                    garcom = null;
                }else{
                    garcom = funcionarioDao.abrirFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>"))));
                }
                if(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>")).equals("null")){
                    cozinheiro = null;
                }else{
                    cozinheiro = funcionarioDao.abrirFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>"))));
                }

                if(( item.getNecessitaPreparo()  && cozinheiro == null  )) {
                    listPedido.add(new Pedido(idPedido, conta, itemDao.abrirItem(item.getIdItem()), quantidade, dataHora, garcom, cozinheiro));
                }else{
                    continue;
                }
            }
            return listPedido;
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Pedido.cc em Pedido da Visão do Cozinheiro. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }
    
    public Integer maiorIdPedido() throws IOException{
        Integer maiorIdPedido=0;
        List<Pedido> listPedido = new ArrayList();
        listPedido = abrirPedido();
        
        if(listPedido.size() > 0){
            for(Pedido pedido: listPedido){
                if( maiorIdPedido < pedido.getIdPedido()){
                    maiorIdPedido = pedido.getIdPedido();
                }
            }
            return maiorIdPedido;
        }else{
            return 0;
        }
    }
    
    /*    public Pedido abrirPedido(Conta contaPesquisada) throws IOException{
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String linha;
            Integer idPedido;
            Conta conta = new Conta();
            Item item = new Item();
            Integer quantidade;
            Date dataHora;
            Funcionario garcom = new Funcionario();
            Funcionario cozinheiro = new Funcionario();
            fr = new BufferedReader(new FileReader("Pedido.cc"));

            while ((linha = fr.readLine()) != null) {
                idPedido =  Integer.parseInt(linha.substring(linha.indexOf("<idPedido>")+10, linha.indexOf("<conta>")));
                conta.setIdConta(Integer.parseInt(linha.substring(linha.indexOf("<conta>")+7, linha.indexOf("<item>"))));
                item.setIdItem(Integer.parseInt(linha.substring(linha.indexOf("<item>")+6, linha.indexOf("<quantidade>"))));
                quantidade = Integer.parseInt(linha.substring(linha.indexOf("<quantidade>")+12, linha.indexOf("<dataHora>")));
                dataHora = formatter.parse(linha.substring(linha.indexOf("<dataHora>")+10, linha.indexOf("<garcom>")));
                garcom.setIdFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<garcom>")+8, linha.indexOf("<cozinheiro>"))));
                cozinheiro.setIdFuncionario(Integer.parseInt(linha.substring(linha.indexOf("<cozinheiro>")+12, linha.indexOf("<fdl>"))));

                if (contaPesquisada.getIdConta() == conta.getIdConta()){
                    Pedido pedido = new Pedido(idPedido, contaPesquisada, item, quantidade, dataHora, garcom, cozinheiro);
                    return pedido;
                }
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Pedido.cc. Exception: "+e.getMessage());
        }finally{
        }
        return null;
    }*/
    
}
