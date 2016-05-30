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
public class CardapioDao {
    
    private BufferedWriter bw = null;
    private BufferedReader fr = null;
    
    //private Integer idCardapio;
    //private List<Item> listItem;
    public void salvarCardapio(Cardapio cardapio) throws IOException{
        try{
                //Apagar todo o arquivo para sobrescrever todos os itens do cardápio (diponiveis == true).
                File file = new File("Cardapio.cc");
                if ( file.exists()) {
                    FileWriter fw = new FileWriter("Cardapio.cc",false);
                }
                bw = new BufferedWriter(new FileWriter("Cardapio.cc", true));  
                List<Item> listItem = new ArrayList();
                listItem = cardapio.getListItem();
                for(Item item : listItem){
                    bw.write("<idItem>"+item.getIdItem().toString());
                }
                bw.write("<fdl>");
                bw.newLine();
                bw.flush();
                bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Cardapio.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
    }
    
    public Cardapio abrirCardapio() throws IOException{
        Cardapio cardapio = new Cardapio();
        try{
            String linha;
            Integer idItem;
            String cadeiaCaracteresParaFimDaLinha;
            List<Item> listItemAll = new ItemDao().abrirItem(); //Recebe todos os itens que existem em Item.cc
            List<Item> listItemCardapio = new ArrayList(); //Irá listar todos os ítens que existem em Cardapio.cc
            
            fr = new BufferedReader(new FileReader("Cardapio.cc"));
            linha = fr.readLine();
            cadeiaCaracteresParaFimDaLinha = linha.substring(linha.indexOf("<idItem>")+8);  //usado para percorer a linha e coletar os IDs dos Itens
            
            //Verifica Entre a lista de todos os itens, quais pertecem a lista do cardápio.
            while(cadeiaCaracteresParaFimDaLinha.length() > 7){
                idItem = Integer.parseInt(cadeiaCaracteresParaFimDaLinha.substring(0, cadeiaCaracteresParaFimDaLinha.indexOf("<idItem>")));
                for (Item item: listItemAll){
                    if(idItem == item.getIdItem()){
                        listItemCardapio.add(item);
                        break;
                    }
                }
                cadeiaCaracteresParaFimDaLinha = cadeiaCaracteresParaFimDaLinha.substring(linha.indexOf("<idItem>")+9);
            }
            //Último item do cardápio.
            idItem = Integer.parseInt(cadeiaCaracteresParaFimDaLinha.substring(0, cadeiaCaracteresParaFimDaLinha.indexOf("<fdl>")));
            for (Item item: listItemAll){
                if(idItem == item.getIdItem()){
                    listItemCardapio.add(item);
                    break;
                }
            }
            cardapio.setListItem(listItemCardapio);
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Cardapio.cc. Exception: "+e.getMessage());
        }finally{
        }
        return cardapio;
    }
}
