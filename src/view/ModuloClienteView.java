/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import model.*;
import control.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class ModuloClienteView {
    private Conta conta = new Conta();
    
    //Inicia Conta;
    public void console_load() throws IOException, ParseException{
        String menuModuloCliente = "n";
        Scanner scanner = new Scanner(System.in);
        LoginClienteView loginClienteView = new LoginClienteView();
        this.conta = loginClienteView.console_load(conta);
        Boolean sair = true;
        
        if(conta != null){
            do{
                System.out.println("  O que deseja: ");
                System.out.println("1. Visualizar Cardapio ");
                System.out.println("2. Solicitar Pedido ");
                System.out.println("3. Sugest�es e Reclamacoes ");
                System.out.println("4. Ajuda e Suporte ");
                System.out.println("5. Fechar Conta ");
                System.out.print("Opcao: ");

                menuModuloCliente = scanner.next();
                switch( menuModuloCliente )
                {
                    case "1":
                        cardapioExibir();
                        break;
                    case "2":
                        novoPedido();
                        break;
                    case "3":
                        novaReclamacao();
                        break;
                    case "4":
                    	ajudaSuporteExibir();
                    	break;                   
                    case "5":
                        fecharConta();
                        sair = false;
                        break;
                    default:
                }
            }while (sair);
        }

        //return conta;
    }
    
    public void cardapioExibir() throws IOException{
        
        List<Item> listItem = new ArrayList();
        ItemDao itemDao = new ItemDao();
        listItem = itemDao.abrirItemCardapio();
        
        System.out.println("  Card�pio Digital:  ");
        for(Item item: listItem){
            if(item.getDisponivel() == true){
                System.out.printf("%d. %s \t R$ %.2f\n", item.getIdItem(),
                                    item.getNome(), item.getValor());
            }
        }
    }  
      // Cliente informa qual pedido e quantidade.
    //TODO: Nesse momento vai enviar informação para o módulo Garçom e Cozinheiro.
    
    public void novoPedido() throws IOException{
        Scanner scanner = new Scanner(System.in);
        PedidoDao pedidoDao = new PedidoDao();
        Pedido pedido = new Pedido();
        pedido.setConta(conta);
        
        cardapioExibir();
        System.out.print("Informe o n�mero do item: ");
        Integer idItem = scanner.nextInt();
        ItemDao itemDao = new ItemDao();
        pedido.setItem(itemDao.abrirItem(idItem));

        System.out.print("Informe a quantidade: ");
        pedido.setQuantidade(scanner.nextInt());
        
        pedido.setDataHora(new Date());
        
        pedido = pedidoDao.salvarPedido(pedido);
    }
    
    public void novaReclamacao() throws IOException{
    	Scanner scanner = new Scanner(System.in);
        ReclamacaoDao reclamacaoDao = new ReclamacaoDao();
        Reclamacao reclamacao = new Reclamacao();
        
        //for(short i=0; i<20; i++) System.out.println("\n");
        System.out.println("   Adicionar Reclamacao:");
        System.out.print("Descricao: ");
        reclamacao.setDescricao(scanner.nextLine());
        
        reclamacaoDao.salvarReclamacao(reclamacao);
    }
 
   public Boolean ajudaSuporteExibir() throws IOException{
        Scanner scanner = new Scanner(System.in);
        String menuAjudaSuporteExibir = "-1";
        List<AjudaSuporte> listAjudaSuporte = new ArrayList();
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        listAjudaSuporte = ajudaSuporteDao.abrirAjudaSuporte();
        
        System.out.println("  Ajuda e Suporte  ");
        for(AjudaSuporte ajudaSuporte: listAjudaSuporte){
            System.out.println(ajudaSuporte.getIdAjudaSuporte() + ". - " + ajudaSuporte.getTitulo());
        }
        System.out.printf("Opcao: ");
        menuAjudaSuporteExibir = scanner.next();
        if(menuAjudaSuporteExibir.equals("0")){
            return true;
        }else{
            for(AjudaSuporte ajudaSuporte: listAjudaSuporte){
                if(ajudaSuporte.getIdAjudaSuporte() == Integer.parseInt(menuAjudaSuporteExibir)){
                    System.out.println(ajudaSuporte.getIdAjudaSuporte() + ". - " + ajudaSuporte.getTitulo());
                    System.out.println("Descricao: " + ajudaSuporte.getDescricao());
                }
            }
        }
        return true;
  }
    
    public void fecharConta() throws IOException, ParseException{
        List<Pedido> listPedido = new ArrayList();
        PedidoDao pedidoDao = new PedidoDao();
        listPedido = pedidoDao.abrirPedido(conta);
        exibirFechamentoConta(listPedido);        
        conta.setDataHoraFimAtendimento(new Date());
        ContaDao contaDao = new ContaDao();
        conta = contaDao.alterarConta(conta);
    }
    
    public void exibirFechamentoConta(List<Pedido> listPedido) throws IOException{
        float valorTotal =0;
        if(listPedido == null){
            System.out.println("Não foi pedido nada. Conta vazia!");
        }else{
            System.out.println("   Conta:  ");
            System.out.println("Nº | Quantidade |    Nome     |    Valor ");
            for(Pedido pedido: listPedido){
                System.out.printf("%d.\t %d \t %s \t %.2f\n",
                                    pedido.getIdPedido(), pedido.getQuantidade(),
                                    pedido.getItem().getNome(), pedido.getItem().getValor());
                valorTotal = ( pedido.getItem().getValor() * pedido.getQuantidade() ) + valorTotal;
            }
            System.out.printf("Total: R$ %.2f \n", valorTotal);
        }
    }
    
}
