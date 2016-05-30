package view;

import control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;

public class ManterAjudaSuporteView {
    private List<AjudaSuporte> listAjudaSuporte = new ArrayList();
    public void console_load() throws IOException{
        Scanner scanner = new Scanner(System.in);
        String menuAjudaSuporteString;
        int sair =0;
        do{
            //for(short i=0; i<20; i++) System.out.println("\n");
            System.out.println("   Ajuda e Suporte   ");
            System.out.println("1. Adicionar Ajuda");
            System.out.println("2. Exibir Ajuda");
            System.out.println("3. Alterar Ajuda");
            System.out.println("4. Excluir Ajuda");
            System.out.println("5. Retornar Menu Anterior");
            System.out.print("Opcao: ");

            menuAjudaSuporteString = scanner.next();
            scanner.nextLine();
            switch( menuAjudaSuporteString )
            {
                case "1": ajudaSuporteAdicionar();
                    break;
                case "2": ajudaSuporteExibir();
                    break;
                case "3": ajudaSuporteAlterar();
                    break;
                case "4": ajudaSuporteExcluir();
                    break;
                default: sair =1;
            }
        }while(sair == 0);
    
    }
    
    public void ajudaSuporteAdicionar() throws IOException{
        Scanner scanner = new Scanner(System.in);
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        AjudaSuporte ajudaSuporte = new AjudaSuporte();
        
        //for(short i=0; i<20; i++) System.out.println("\n");
        System.out.println("   Adicionar Ajuda Suporte:");
        System.out.print("Titulo: ");
        ajudaSuporte.setTitulo(scanner.nextLine());
        System.out.print("Descricao: ");
        ajudaSuporte.setDescricao(scanner.next());
        
        ajudaSuporteDao.salvarAjudaSuporte(ajudaSuporte);                
    }
   
    public void ajudaSuporteAlterar() throws IOException{
        Scanner scanner = new Scanner(System.in);
        Integer menuAjudaSuporteInt;
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        List<AjudaSuporte> listAjudaSuporte = new ArrayList();
        
        ajudaSuporteExibir();
        System.out.print("Qual a Ajuda e Suporte:   ");
        menuAjudaSuporteInt = scanner.nextInt();
        scanner.reset();

        ////for(short i=0; i<20; i++) System.out.println("\n");
        System.out.println("   O que deseja alterar?   ");
        System.out.println("1. Titulo");
        System.out.println("2. Descricao");
        System.out.print("Opcao: ");
        Integer menuAlterar = scanner.nextInt();
        
        System.out.print("Alterar por: ");
        String novoAtributoAjudaSuporte = scanner.next();
        
        if (ajudaSuporteDao.alterarAjudaSuporte(menuAjudaSuporteInt, menuAlterar, novoAtributoAjudaSuporte) != null){
                System.out.println("Alterado com Sucesso!");
            }else{
                System.out.println("Erro ao Alterar");
        }
    }
    
    public void ajudaSuporteExibir() throws IOException{
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        listAjudaSuporte = ajudaSuporteDao.abrirAjudaSuporte();
        System.out.println("   Ajuda e Suporte ");
        for(AjudaSuporte ajudaSuporte: listAjudaSuporte){
            System.out.printf("%d. %s  |  %s  \n", 
                    ajudaSuporte.getIdAjudaSuporte(), ajudaSuporte.getTitulo(), ajudaSuporte.getDescricao());
        }
    }
   
    public void ajudaSuporteExcluir() throws IOException{
        Scanner scanner = new Scanner(System.in);
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        ajudaSuporteExibir();
        
        System.out.print("Qual Ajuda:   ");
        Integer menuAjudaSuporteDeleteInt = scanner.nextInt();
        
        if(ajudaSuporteDao.excluirAjudaSuporte(menuAjudaSuporteDeleteInt) != null){
            System.out.println("Excluído com Sucesso!");
        }else{
            System.out.println("Erro ao excluir");
        }
    }
	
}
