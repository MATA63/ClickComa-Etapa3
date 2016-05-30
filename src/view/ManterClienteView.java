/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import control.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class ManterClienteView {
    private List<Cliente> listCliente = new ArrayList();
    public void console_load() throws IOException{
        Scanner scanner = new Scanner(System.in);
        String menuClienteString;
        int sair =0;
        do{
            //for(short i=0; i<20; i++) System.out.println("\n");
            System.out.println("   Cliente   ");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Exibir Cliente");
            System.out.println("3. Alterar Cliente");
            System.out.println("4. Excluir Cliente");
            System.out.println("5. Retornar Menu Anterior");
            System.out.print("Opcao: ");

            menuClienteString = scanner.next();
            scanner.nextLine();
            switch( menuClienteString )
            {
                case "1": clienteAdicionar();
                    break;
                case "2": clienteExibir();
                    break;
                case "3": clienteAlterar();
                    break;
                case "4": clienteExcluir();
                    break;
                default: sair =1;
            }
        }while(sair == 0);
    
    }
    
    public void clienteAdicionar() throws IOException{
        Scanner scanner = new Scanner(System.in);
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = new Cliente();
        
        //for(short i=0; i<20; i++) System.out.println("\n");
        System.out.println("   Adicionar Cliente:");
        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());
        System.out.print("cpf: ");
        cliente.setCpf(scanner.next());
        System.out.print("e-mail: ");
        cliente.setEmail(scanner.next());
        
        clienteDao.salvarCliente(cliente);                
    }
    
    public void clienteAlterar() throws IOException{
        Scanner scanner = new Scanner(System.in);
        Integer menuClienteInt = scanner.nextInt();
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = new Cliente();
        List<Cliente> listCliente = new ArrayList();
        
        clienteExibir();
        System.out.print("Qual cliente:   ");
        menuClienteInt = scanner.nextInt();
        scanner.reset();

        ////for(short i=0; i<20; i++) System.out.println("\n");
        System.out.println("   O que deseja alterar?   ");
        System.out.println("1. Nome");
        System.out.println("2. CPF");
        System.out.println("3. e-mail");
        System.out.print("Opcao: ");
        Integer menuAlterar = scanner.nextInt();
        scanner.reset();
        
        System.out.print("Alterar por: ");
        String novoAtributoCliente = scanner.next();
        scanner.reset();
        
        if (clienteDao.alterarCliente(menuClienteInt, menuAlterar, novoAtributoCliente) != null){
                System.out.println("Alterado com Sucesso!");
            }else{
                System.out.println("Erro ao Alterar");
        }
    }
    
    public void clienteExibir() throws IOException{
        ClienteDao clienteDao = new ClienteDao();
        listCliente = clienteDao.abrirCliente();
        System.out.println("   Clientes");
        for(Cliente cliente: listCliente){
            System.out.printf("%d. %s  |  %s  |  %s\n", 
                    cliente.getIdCliente(), cliente.getNome(), 
                    cliente.getCpf(), cliente.getEmail());
        }
    }
   
    public void clienteExcluir() throws IOException{
        Scanner scanner = new Scanner(System.in);
        ClienteDao clienteDao = new ClienteDao();
        clienteExibir();
        
        System.out.print("Qual cliente:   ");
        Integer menuClienteDeleteInt = scanner.nextInt();
        
        if(clienteDao.excluirCliente(menuClienteDeleteInt) != null){
            System.out.println("Excluído com Sucesso!");
        }else{
            System.out.println("Erro ao excluir");
        }
    }

}