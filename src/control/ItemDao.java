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
import java.util.Scanner;
import model.*;

/**
 *
 * @author Igor
 */
public class ItemDao {
    private BufferedWriter bw = null;
    private BufferedReader fr = null;

    public Item salvarItem(Item item) throws IOException{
        try{
                bw = new BufferedWriter(new FileWriter("Item.cc", true)); 
                item.setIdItem(maiorIdItem()+1);
                bw.write("<idItem>"+item.getIdItem()
                            +"<nome>"+item.getNome()
                            +"<valor>"+item.getValor()
                            +"<disponivel>"+item.getDisponivel().toString()
                            +"<necessitaPreparo>"+item.getNecessitaPreparo().toString()+"<fdl>");
                bw.newLine();
                bw.flush();
                bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Item.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
        return item;
    }

    public void salvarItem(List<Item> listItem) throws IOException{
        try{
            //Sobrescrever todos Items.
            File file = new File("Item.cc");
            if ( file.exists()) {
                FileWriter fw = new FileWriter("Item.cc",false);
            }
            bw = new BufferedWriter(new FileWriter("Item.cc", true)); 
            for(Item item: listItem){
                bw.write("<idItem>"+item.getIdItem().toString()
                            +"<nome>"+item.getNome()
                            +"<valor>"+item.getValor()
                            +"<disponivel>"+item.getDisponivel().toString()
                            +"<necessitaPreparo>"+item.getNecessitaPreparo().toString()+"<fdl>");
                bw.newLine();
                bw.flush();
            }
            bw.close();    
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao salvar no arquivo Item.cc. Exception: "+e.getMessage());
        }finally{
        }
        System.out.println("Salvo com Sucesso!");
    }

    public List<Item> abrirItem() throws IOException{
        List<Item> listItem = new ArrayList();
        try{
            String linha;
            Integer idItem;
            String nome;
            float valor;
            Boolean disponivel;
            Boolean necessitaPreparo;
            fr = new BufferedReader(new FileReader("Item.cc"));

            while ((linha = fr.readLine()) != null) {
                idItem = Integer.parseInt(linha.substring(linha.indexOf("<idItem>")+8,linha.indexOf("<nome>")));
                nome = linha.substring(linha.indexOf("<nome>")+6, linha.indexOf("<valor>"));
                valor = Float.parseFloat(linha.substring(linha.indexOf("<valor>")+7, linha.indexOf("<disponivel>")));
                disponivel = Boolean.parseBoolean(linha.substring(linha.indexOf("<disponivel>")+12, linha.indexOf("<necessitaPreparo>")));
                necessitaPreparo = Boolean.parseBoolean(linha.substring(linha.indexOf("<necessitaPreparo>")+18, linha.indexOf("<fdl>")));
                
                listItem.add(new Item(idItem, nome, valor, disponivel, necessitaPreparo));
            }
        }catch(Exception e){ 
            System.out.println("Ocorreu um erro ao ler o arquivo Item.cc. Exception: "+e.getMessage());
        }finally{
        }
        return listItem;
    }
    
    public Item abrirItem(Integer idItemProcurado) throws IOException{
    try{
        String linha;
        Integer idItem;
        String nome;
        float valor;
        Boolean disponivel;
        Boolean necessitaPreparo;
        fr = new BufferedReader(new FileReader("Item.cc"));

        while ((linha = fr.readLine()) != null) {
            idItem = Integer.parseInt(linha.substring(linha.indexOf("<idItem>")+8,linha.indexOf("<nome>")));
            nome = linha.substring(linha.indexOf("<nome>")+6, linha.indexOf("<valor>"));
            valor = Float.parseFloat(linha.substring(linha.indexOf("<valor>")+7, linha.indexOf("<disponivel>")));
            disponivel = Boolean.parseBoolean(linha.substring(linha.indexOf("<disponivel>")+12, linha.indexOf("<necessitaPreparo>")));
            necessitaPreparo = Boolean.parseBoolean(linha.substring(linha.indexOf("<necessitaPreparo>")+18, linha.indexOf("<fdl>")));

            if (idItemProcurado == idItem){
                Item itemProcurado = new Item(idItem, nome, valor, disponivel, necessitaPreparo);
                return itemProcurado;
            }
        }
    }catch(Exception e){ 
        System.out.println("Ocorreu um erro ao ler o arquivo Item.cc. Exception: "+e.getMessage());
    }finally{
    }
    return null;
    }
    
    public List<Item> abrirItemCardapio() throws IOException{
        List<Item> listItem = new ArrayList();
        List<Item> listItemCardapio = new ArrayList();
        listItem = abrirItem();
        
        for(Item item: listItem){
            if(item.getDisponivel() == true){
                listItemCardapio.add(item);
            }
        }
        return listItemCardapio;
    }

    public Integer maiorIdItem() throws IOException{
        Integer maiorIdItem=0;
        List<Item> listItem = new ArrayList();
        listItem = abrirItem();
        
        if(listItem.size() > 0){
            for(Item item: listItem){
                if( maiorIdItem < item.getIdItem()){
                    maiorIdItem = item.getIdItem();
                }
            }
            return maiorIdItem;
        }else{
            return 0;
        }
    }
    
    public List<Item> excluirItem(Integer idItemExcluir) throws IOException{
        List<Item> listItem = new ArrayList();
        listItem = abrirItem();
        for(Item item: listItem){
            if( idItemExcluir == item.getIdItem()){
                listItem.remove(item);
                salvarItem(listItem);
                return listItem;
            }
        }
        return null;
    }

    public List<Item> alterarItem(Integer idItemAlterar, Integer numAtributoAlterar) throws IOException{
        List<Item> listItem = new ArrayList();
        listItem = abrirItem();
        String novoAtributo;
        Scanner scanner = new Scanner(System.in);
        
        for(Item item: listItem){
            if( idItemAlterar == item.getIdItem()){
                switch( numAtributoAlterar )
                {                    
                    case 1: 
                        System.out.print("Alterar por: ");
                        item.setNome(scanner.next());
                        break;
                        
                    case 2: 
                        System.out.println("Produto com disponibilidade?(s/n)");
                        novoAtributo = scanner.next();
                        if (novoAtributo.toUpperCase().equals("S")){
                            item.setDisponivel(true);
                        }else{
                            item.setDisponivel(false);
                        }
                        break;
                        
                    case 3: item.setNecessitaPreparo(true);
                        System.out.println("Necessita de Preparo na Cozinha?(s/n)");
                        novoAtributo = scanner.next();
                        if (novoAtributo.toUpperCase().equals("S")){
                            item.setDisponivel(true);
                        }else{
                            item.setDisponivel(false);
                        }
                        break;
                        
                    case 4: 
                        System.out.print("Alterar por: ");
                        item.setValor(scanner.nextFloat());
                        break;
                        
                    default: System.out.println("Erro ao escolher o atributo.");
                }
                salvarItem(listItem);
                return listItem;
            }
        }
        return null;
    }

    
}
