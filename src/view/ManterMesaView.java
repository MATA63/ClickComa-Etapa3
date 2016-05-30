/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;

/**
 *
 * @author Igor
 */
public class ManterMesaView {
    private List<Mesa> listMesa = new ArrayList();
    public void console_load() throws IOException{
        Scanner scanner = new Scanner(System.in);
        String menuMesaString;
        int sair =0;
        do{
            //for(short i=0; i<20; i++) System.out.println("\n");
            System.out.println("   Mesa   ");
            System.out.println("1. Adicionar Mesa");
            System.out.println("2. Exibir Mesa");
            System.out.println("3. Alterar Mesa");
            System.out.println("4. Excluir Mesa");
            System.out.println("5. Retornar Menu Anterior");
            System.out.print("Opcao: ");

            menuMesaString = scanner.next();
            scanner.nextLine();
            switch( menuMesaString )
            {
                case "1": mesaAdicionar();
                    break;
                case "2": mesaExibir();
                    break;
                case "3": mesaAlterar();
                    break;
                case "4": mesaExcluir();
                    break;
                default: sair =1;
            }
        }while(sair == 0);
    
    }
    
    public void mesaAdicionar() throws IOException{
        Scanner scanner = new Scanner(System.in);
        MesaDao mesaDao = new MesaDao();
        Mesa mesa = new Mesa();
        
        //for(short i=0; i<20; i++) System.out.println("\n");
        System.out.println("   Adicionar Mesa:");
        System.out.print("Local: ");
        mesa.setLocal(scanner.nextLine());
        System.out.print("Numero: ");
        mesa.setNumero(scanner.next());
        
        mesaDao.salvarMesa(mesa);                
    }
   
    public void mesaAlterar() throws IOException{
        Scanner scanner = new Scanner(System.in);
        Integer menuMesaInt;
        MesaDao mesaDao = new MesaDao();
        List<Mesa> listMesa = new ArrayList();
        
        mesaExibir();
        System.out.print("Qual mesa:   ");
        menuMesaInt = scanner.nextInt();
        scanner.reset();

        ////for(short i=0; i<20; i++) System.out.println("\n");
        System.out.println("   O que deseja alterar?   ");
        System.out.println("1. Numero");
        System.out.println("2. Local");
        System.out.print("Opção: ");
        Integer menuAlterar = scanner.nextInt();
        
        System.out.print("Alterar por: ");
        String novoAtributoMesa = scanner.next();
        scanner.reset();
        
        if (mesaDao.alterarMesa(menuMesaInt, menuAlterar, novoAtributoMesa) != null){
                System.out.println("Alterado com Sucesso!");
            }else{
                System.out.println("Erro ao Alterar");
        }
    }
    
    public void mesaExibir() throws IOException{
        MesaDao mesaDao = new MesaDao();
        listMesa = mesaDao.abrirMesa();
        System.out.println("   Mesas");
        for(Mesa mesa: listMesa){
            System.out.printf("%d. %s  |  %s  \n", 
                    mesa.getIdMesa(), mesa.getNumero(), mesa.getLocal());
        }
    }
   
    public void mesaExcluir() throws IOException{
        Scanner scanner = new Scanner(System.in);
        MesaDao mesaDao = new MesaDao();
        mesaExibir();
        
        System.out.print("Qual mesa:   ");
        Integer menuMesaDeleteInt = scanner.nextInt();
        
        if(mesaDao.excluirMesa(menuMesaDeleteInt) != null){
            System.out.println("Excluído com Sucesso!");
        }else{
            System.out.println("Erro ao excluir");
        }
    }

}
