/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import model.Funcionario;
import model.*;
import control.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class ModuloFuncionarioView {
    
    public void console_Load() throws IOException {
        
        LoginFuncionarioView loginFuncionarioView = new LoginFuncionarioView();
        Funcionario funcionario = new Funcionario();
        funcionario = loginFuncionarioView.console_load();

        if(funcionario != null){
            switch( funcionario.getCargo() )
            {
                case "Garçom":
                    visaoGarcom(funcionario);
                    break;
                case "Cozinheiro":
                    visaoCozinheiro(funcionario);
                    break;
                case "Gerente":
                    visaoGerente(funcionario);
                    break;
            }
        }
    }

    public void visaoGarcom(Funcionario garcom) throws IOException{
        Scanner scanner = new Scanner(System.in);
        Boolean trueOuFalse = true;
        do{
            System.out.printf("\n   Opções do Garçom  \n");
            System.out.println("1. Visualizar Mesas Disponíveis [Não Implementado!]");
            System.out.println("2. Visualizar Pedidos");
            System.out.println("3. Confirmar Entrega Pedido");
            System.out.println("4. Sair");
            System.out.print("Opção: ");
            String menuModulo = scanner.nextLine();
            switch( menuModulo )
            {
                case "1":
                    break;
                case "2":
                    GarcomVisualizarPedidos();
                    break;
                case "3":
                    GarcomConfirmarEntregaPedidos(garcom);
                    break;
                case "4":
                    trueOuFalse = false;
                    break;
                default:
                    System.out.println("Opção inexistente.");
            }
        }while(trueOuFalse);
    }
    
    public void visaoCozinheiro(Funcionario cozinheiro) throws IOException{
        Scanner scanner = new Scanner(System.in);
        Boolean trueOuFalse = true;
        do{
            System.out.printf("\n   Opções do Cozinheiro  \n");
            System.out.println("1. Visualizar Pedidos");
            System.out.println("2. Confirmar Entrega Pedido");
            System.out.println("3. Gerir Cardápio");
            System.out.println("4. Sair");
            System.out.print("Opção: ");
            String menuModulo = scanner.nextLine();
            switch( menuModulo )
            {
                case "1":
                    CozinheiroVisualizarPedidos();
                    break;
                case "2":
                    CozinheiroConfirmarEntregaPedidos(cozinheiro);
                    break;
                case "3":
                    ManterItemView manterItemView = new ManterItemView();
                    manterItemView.console_load();
                    break;
                case "4":
                    trueOuFalse = false;
                    break;
                default:
                    System.out.println("Opção inexistente.");
            }
        }while(trueOuFalse);
    }
    
    public void visaoGerente(Funcionario gerente) throws IOException{
        Scanner scanner = new Scanner(System.in);
        Boolean trueOuFalse = true;
        RelatoriosView relatorioview = new RelatoriosView();
        do{
            System.out.printf("\n   Opcoes do Gerente  \n");
            System.out.println("1. Gerir Clientes");
            System.out.println("2. Gerir Funcionarios");
            System.out.println("3. Gerir Mesas");
            System.out.println("4. Gerir Fornecedores");
            System.out.println("5. Gerir Ajuda e Suporte");
            System.out.println("\n\t Relatorios:");
            System.out.println("6. Gerar Relatorio de Atendimento");
            System.out.println("7. Gerar Relatorio de Pedido por Cliente");
            System.out.println("8. Gerar Relatorio de Pedidos");
            System.out.println("9. Gerar Relatorio Financeiro");
            System.out.println("10. Sair");
            System.out.print("Opcao: ");
            String menuModulo = scanner.nextLine();
            switch( menuModulo )
            {
                case "1":
                    ManterClienteView manterClienteView = new ManterClienteView();
                    manterClienteView.console_load();
                    break;
                case "2":
                    ManterFuncionarioView manterFuncionarioView = new ManterFuncionarioView();
                    manterFuncionarioView.console_load();
                    break;
                case "3":
                    ManterMesaView manterMesaView = new ManterMesaView();
                    manterMesaView.console_load();
                    break;
                case "4":
                    ManterFornecedorView manterFornecedorView = new ManterFornecedorView();
                    manterFornecedorView.console_load();
                    break;
                case "5":
                    ManterAjudaSuporteView manterAjudaSuporteView = new ManterAjudaSuporteView();
                    manterAjudaSuporteView.console_load();
                    break;
                case "6":
                    relatorioview.ExibeRelatorioAtendimento();
                    break;
                case "7":
                    relatorioview.ExibeRelatorioPedidoCliente();
                    break;
                case "8":
                    relatorioview.ExibeRelatorioPedido();
                    break;
                case "9":
                    relatorioview.ExibeRelatorioFinanceiro();
                    break;
                case "10":                	
                    trueOuFalse = false;
                    break;
                default:
                    System.out.println("Opção inexistente.");
            }
        }while(trueOuFalse);
        
    }
    

    //================================ /// ======================================
    
    
    public void GarcomVisualizarPedidos() throws IOException{
        List<Pedido> listPedido = new ArrayList();
        PedidoDao pedidoDao = new PedidoDao();
        listPedido = pedidoDao.abrirPedidoVisaoGarcom();
        
        for(Pedido pedido: listPedido){
            System.out.println("Nº \t Nº mesa \t nm Item ");
            System.out.printf("%d. \t %s \t %s\n", pedido.getIdPedido(), pedido.getConta().getMesa().getNumero(), pedido.getItem().getNome() );   
        }
    }

    public void CozinheiroVisualizarPedidos() throws IOException{
        List<Pedido> listPedido = new ArrayList();
        PedidoDao pedidoDao = new PedidoDao();
        listPedido = pedidoDao.abrirPedidoVisaoCozinheiro();
        
        for(Pedido pedido: listPedido){
            System.out.println("Nº \t Nº mesa \t nm Item ");
            System.out.printf("%d. \t %s \t %s\n", pedido.getIdPedido(), pedido.getConta().getMesa().getNumero(), pedido.getItem().getNome() );   
        }
    }
    
    
    public void GarcomConfirmarEntregaPedidos(Funcionario garcom) throws IOException{
        List<Pedido> listPedido = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        PedidoDao pedidoDao = new PedidoDao();
        listPedido = pedidoDao.abrirPedidoVisaoGarcom();
        
        for(Pedido pedido: listPedido){
            System.out.println("Nº \t Nº mesa \t nm Item ");
            System.out.printf("%d. \t %s \t %s\n", pedido.getIdPedido(), pedido.getConta().getMesa().getNumero(), pedido.getItem().getNome() );   
        }
        System.out.println("Informe o nº pedido que deseja marcar como entregue: ");
        int idPedidoEntregue = scanner.nextInt();
        
            int i;
            for(i = listPedido.size()-1 ; i>= -1; i-- ){
                if( listPedido.get(i).getIdPedido() == idPedidoEntregue ){
                    break;
                }
            }
            listPedido.get(i).setGarcom(garcom);
            pedidoDao.informarAtendimentoAoPedido(listPedido.get(i));
    }
    
    public void CozinheiroConfirmarEntregaPedidos(Funcionario cozinheiro) throws IOException{
        List<Pedido> listPedido = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        PedidoDao pedidoDao = new PedidoDao();
        listPedido = pedidoDao.abrirPedidoVisaoCozinheiro();
        
        for(Pedido pedido: listPedido){
            System.out.println("Nº \t Nº mesa \t nm Item ");
            System.out.printf("%d. \t %s \t %s\n", pedido.getIdPedido(), pedido.getConta().getMesa().getNumero(), pedido.getItem().getNome() );   
        }
        System.out.println("Informe o nº pedido para encaminhar ao garçom: ");
        int idPedidoEntregue = scanner.nextInt();
        
            int i;
            for(i = listPedido.size()-1 ; i>= -1; i-- ){
                if( listPedido.get(i).getIdPedido() == idPedidoEntregue ){
                    break;
                }
            }
            listPedido.get(i).setCozinheiro(cozinheiro);
            pedidoDao.informarAtendimentoAoPedido(listPedido.get(i));
    }
    
}
