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
public class ManterItemView {
   
    private List<Item> listItem = new ArrayList();
    public void console_load() throws IOException{
        Scanner scanner = new Scanner(System.in);
        String menuItemString;
        int sair =0;
        do{
            System.out.println("\n   Item   ");
            System.out.println("1. Adicionar Item");
            System.out.println("2. Exibir Item");
            System.out.println("3. Alterar Item");
            System.out.println("4. Excluir Item");
            System.out.println("5. Retornar Menu Anterior");
            System.out.print("Opção: ");

            menuItemString = scanner.next();
            switch( menuItemString )
            {
                case "1": itemAdicionar();
                    break;
                case "2": itemExibir();
                    break;
                case "3": itemAlterar();
                    break;
                case "4": itemExcluir();
                    break;
                default: sair =1;
            }
        }while(sair == 0);
    
    }
    
    public void itemAdicionar() throws IOException{
        Scanner scanner = new Scanner(System.in);
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        
        //for(short i=0; i<20; i++) System.out.println("\n");
        System.out.println("   Adicionar Item");
        System.out.print("Nome: ");
        item.setNome(scanner.nextLine());
        
        System.out.print("Esta disponivel (s/n): ");
        String trueOuFalseAdicionarItem = scanner.next();
        if (trueOuFalseAdicionarItem.toUpperCase().equals("S")){
            item.setDisponivel(true);
        }else{
            item.setDisponivel(false);
        }
        
        System.out.print("Necessita de Preparo (s/n): ");
        trueOuFalseAdicionarItem = scanner.next();
        if (trueOuFalseAdicionarItem.toUpperCase().equals("S")){
            item.setNecessitaPreparo(true);
        }else{
            item.setNecessitaPreparo(false);
        }
        scanner.reset();
        System.out.print("Valor: ");
        item.setValor(scanner.nextFloat());
        
        itemDao.salvarItem(item);                
    }
    
    public void itemAlterar() throws IOException{
        Scanner scanner = new Scanner(System.in);
        Integer menuItemInt;
        ItemDao itemDao = new ItemDao();
        
        itemExibir();
        System.out.print("Qual item:   ");
        menuItemInt = scanner.nextInt();

        System.out.printf("\n   O que deseja alterar?   \n");
        System.out.println("1. Nome");
        System.out.println("2. Disponibilidade");
        System.out.println("3. Necessidade de Preparo");
        System.out.println("4. Valor");

        System.out.print("Opção: ");
        Integer menuAlterar = scanner.nextInt();
        scanner.reset();
        
        if (itemDao.alterarItem(menuItemInt, menuAlterar) != null){
                System.out.println("Alterado com Sucesso!");
            }else{
                System.out.println("Erro ao Alterar");
        }
    }
    
    public void itemExibir() throws IOException{
        ItemDao itemDao = new ItemDao();
        listItem = itemDao.abrirItem();
        System.out.printf("\n   Items\n");
        for(Item item: listItem){
            System.out.printf("%d. %s  |  %b  |  %b  |  R$ %.2f\n", 
                    item.getIdItem(), item.getNome(), 
                    item.getDisponivel(), item.getNecessitaPreparo(),
                    item.getValor());
        }
    }
   
    public void itemExcluir() throws IOException{
        Scanner scanner = new Scanner(System.in);
        ItemDao itemDao = new ItemDao();
        itemExibir();
        
        System.out.printf("\nQual item:   ");
        Integer menuItemDeleteInt = scanner.nextInt();
        
        if(itemDao.excluirItem(menuItemDeleteInt) != null){
            System.out.println("Excluído com Sucesso!");
        }else{
            System.out.println("Erro ao excluir");
        }
    }

    
}
