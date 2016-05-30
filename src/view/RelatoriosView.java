/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;

/**
 *
 * @author Igor
 */
public class RelatoriosView {
	
    
    public void ExibeRelatorioAtendimento() throws IOException{
        //GERANDO RELATORIO DE Atendimento.
        //Usado para formatar a data e hora.
        DateFormat formatacaoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //instancia a classe que faz acesso aos arquivos
        PedidoDao pedidoDao = new PedidoDao();
        //instancia uma lista da classe pedidos que vai receber todos os pedidos existentes nos arquivos.
        List<Pedido> listPedido = new ArrayList();
        //Chama a funcao que recebe a lista de todos os pedidos.
        
        listPedido = pedidoDao.abrirPedido();
        //idPedido, conta/horaInicio, conta/HoraFim, garcom/nome, cozinheiro/nome
        System.out.printf("=============== RELATORIO DE ATENDIMENTO ================\n\n");
        for(Pedido pedido: listPedido){
            System.out.printf("- Numero do Pedido: " + pedido.getIdPedido()+"\n");
            System.out.printf("- Hora Inicio: " + formatacaoData.format(pedido.getConta().getDataHoraInicioAtendimento()) + "\n");
            System.out.printf("- Hora Fim: " + formatacaoData.format(pedido.getConta().getDataHoraFimAtendimento()) + "\n");
            if (pedido.getGarcom() != null){
                System.out.printf("- Garcom: " + pedido.getGarcom().getNome() + "\n");
            }else{
                System.out.printf("- Garcom: Atendimento sem Garcom\n");
            }
            if (pedido.getCozinheiro() != null){
                System.out.printf("- Cozinheiro: " + pedido.getCozinheiro().getNome() + "\n");
            }else{
                System.out.printf("- Cozinheiro: Atendimento sem Cozinheiro\n");
                }
                System.out.printf("\n\n");
        }
        System.out.printf("==================== FIM DO RELATORIO ====================");
    }
    
        public void ExibeRelatorioPedidoCliente() throws IOException{
        //GERANDO RELATORIO DE Atendimento.
        //Usado para formatar a data e hora.
        DateFormat formatacaoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //instancia a classe que faz acesso aos arquivos
        PedidoDao pedidoDao = new PedidoDao();
        //instancia uma lista da classe pedidos que vai receber todos os pedidos existentes nos arquivos.
        List<Pedido> listPedido = new ArrayList();
        List<Pedido> listPedidoPorCliente = new ArrayList();
        //Chama a funcao que recebe a lista de todos os pedidos.
        listPedido = pedidoDao.abrirPedido();
        System.out.printf("========== RELATORIO DE PEDIDOS POR CLIENTE ===========\n\n");
        int IdClienteAtual = 0;
        int contaPedidosExcluidos=0;
        int contaListPedido=0;
        do{
            contaPedidosExcluidos=0;
            contaListPedido=0;
            listPedidoPorCliente = new ArrayList();
            IdClienteAtual = listPedido.get(0).getConta().getCliente().getIdCliente();
            //Alimenta listPedidoPorCliente com todos os pedidos de 1 cliente.
            for(Pedido pedido:listPedido){
                if(pedido.getConta().getCliente().getIdCliente() == IdClienteAtual){
                listPedidoPorCliente.add(pedido);
                }
            }
            //Exclui todos os pedidos desse cliente na lista origial
            while( contaPedidosExcluidos != listPedidoPorCliente.size()){
                
                if(listPedido.get(contaListPedido).getConta().getCliente().getIdCliente() == listPedidoPorCliente.get(0).getConta().getCliente().getIdCliente()){
                    listPedido.remove(contaListPedido);
                    contaPedidosExcluidos++;
                }else{
                    contaListPedido++;
                }
            }
            System.out.printf("- Nome do Cliente: " + listPedidoPorCliente.get(0).getConta().getCliente().getNome()+"\n");
            for(Pedido pedido: listPedidoPorCliente){
                System.out.printf("\t Nome do Item: " + pedido.getItem().getNome() + " - Quantidade: " + pedido.getQuantidade() + " - Valor: R$ " + pedido.getItem().getValor()*pedido.getQuantidade() +"\n");
                System.out.printf("\t Hora Inicio: " + formatacaoData.format(pedido.getConta().getDataHoraInicioAtendimento()) + "\n");
                System.out.printf("\t Hora Fim: " + formatacaoData.format(pedido.getConta().getDataHoraFimAtendimento()) + "\n");
                System.out.printf("\n");
            }
        }while(listPedido.size() > 0);
        System.out.printf("\n==================== FIM DO RELATORIO ====================");
    }  

    public void ExibeRelatorioFinanceiro() throws IOException{
        //GERANDO RELATORIO FINANCEIRO.
        //Usado para formatar a data e hora.
        DateFormat formatacaoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //instancia a classe que faz acesso aos arquivos
        ContaDao contaDao = new ContaDao();
        //instancia uma lista da classe pedidos que vai receber todos os pedidos existentes nos arquivos.
        List<Conta> listConta = new ArrayList();
        //Chama a funcao que recebe a lista de todas as Contas.
        
        try {
			listConta = contaDao.abrirConta();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //idConta, conta/horaInicio, conta/HoraFim.                                                                  
        System.out.printf("=============== RELATORIO DE FINANCEIRO ================\n\n");
        for(Conta conta: listConta){
            System.out.printf("- Numero da Conta: " + conta.getIdConta()+"\n");
            System.out.printf("- Nome do Cliente: " + conta.getCliente()+"\n");
            System.out.printf("- Hora Inicio: " + formatacaoData.format(conta.getDataHoraInicioAtendimento()) + "\n");
            System.out.printf("- Hora Fim: " + formatacaoData.format(conta.getDataHoraFimAtendimento()) + "\n");
            System.out.printf("- Mesa Utilizada: " + conta.getMesa()+"\n");
        }
        System.out.printf("==================== FIM DO RELATORIO ====================");
    } 
    
    public void ExibeRelatorioPedido() throws IOException{
        //GERANDO RELATORIO DE PEDIDOS.
        //Usado para formatar a data e hora.
        DateFormat formatacaoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //instancia a classe que faz acesso aos arquivos
        PedidoDao pedidoDao = new PedidoDao();
        //instancia uma lista da classe pedidos que vai receber todos os pedidos existentes nos arquivos.
        List<Pedido> listPedido = new ArrayList();
        //Chama a funcao que recebe a lista de todos os pedidos.
        
        listPedido = pedidoDao.abrirPedido();
        //idPedido, conta/horaInicio, conta/HoraFim, garcom/nome, cozinheiro/nome
        System.out.printf("=============== RELATORIO DE PEDIDOS ================\n\n");
        for(Pedido pedido: listPedido){
            System.out.printf("- Numero do Pedido: " + pedido.getIdPedido()+"\n");
            System.out.printf("- Numero da Conta: " + pedido.getConta()+"\n");
            System.out.printf("- Itens Selecionados: " + pedido.getItem()+"\n");
            System.out.printf("- Numero do Pedido: " + pedido.getQuantidade()+"\n");
            System.out.printf("- Hora Inicio: " + formatacaoData.format(pedido.getConta().getDataHoraInicioAtendimento()) + "\n");
            System.out.printf("- Hora Fim: " + formatacaoData.format(pedido.getConta().getDataHoraFimAtendimento()) + "\n");            
        }
        
        System.out.printf("==================== FIM DO RELATORIO ====================");
    }

}
