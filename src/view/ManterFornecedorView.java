package view;

import control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;

/**
 *
 * @author Edicarla
 */

public class ManterFornecedorView {
	
	 private List<Fornecedor> listFornecedor = new ArrayList();
	    public void console_load() throws IOException{
	        Scanner scanner = new Scanner(System.in);
	        String menuFornecedorString;
	        int sair =0;
	        do{
	            //for(short i=0; i<20; i++) System.out.println("\n");
	            System.out.println("   Fornecedor   ");
	            System.out.println("1. Adicionar Fornecedor");
	            System.out.println("2. Exibir Fornecedor");
	            System.out.println("3. Alterar Fornecedor");
	            System.out.println("4. Excluir Fornecedor");
	            System.out.println("5. Retornar Menu Anterior");
	            System.out.print("Opcao: ");

	            menuFornecedorString = scanner.next();
	            scanner.nextLine();
	            switch( menuFornecedorString )
	            {
	                case "1": fornecedorAdicionar();
	                    break;
	                case "2": fornecedorExibir();
	                    break;
	                case "3": fornecedorAlterar();
	                    break;
	                case "4": fornecedorExcluir();
	                    break;
	                default: sair =1;
	            }
	        }while(sair == 0);
	    
	    }
	    
	    public void fornecedorAdicionar() throws IOException{
	        Scanner scanner = new Scanner(System.in);
	        FornecedorDao fornecedorDao = new FornecedorDao();
	        Fornecedor fornecedor = new Fornecedor();
	        
	        //for(short i=0; i<20; i++) System.out.println("\n");
	        System.out.println("   Adicionar Fornecedor:");
	        System.out.print("Nome: ");
	        fornecedor.setNome(scanner.nextLine());
	        System.out.print("cnpj: ");
	        fornecedor.setCnpj(scanner.next());
	        System.out.print("Ramo: ");
	        fornecedor.setRamo(scanner.next());
	        
	        fornecedorDao.salvarFornecedor(fornecedor);                
	    }
	    
	    public void fornecedorAlterar() throws IOException{
	        Scanner scanner = new Scanner(System.in);
	        Integer menuFornecedorInt =0;
	        FornecedorDao fornecedorDao = new FornecedorDao();
	        Fornecedor fornecedor = new Fornecedor();
	        List<Fornecedor> listFornecedor = new ArrayList();
	        
	        fornecedorExibir();
	        System.out.print("Qual fornecedor:   ");
	        menuFornecedorInt = scanner.nextInt();
                
	        System.out.println("   O que deseja alterar?   ");
	        System.out.println("1. Nome");
	        System.out.println("2. CNPJ");
	        System.out.println("3. Ramo");
	        System.out.print("Opcao: ");
	        Integer menuAlterar = scanner.nextInt();
	        scanner.reset();
	        
	        System.out.print("Alterar por: ");
	        String novoAtributoFornecedor = scanner.next();
	        scanner.reset();
	        
	        if (fornecedorDao.alterarFornecedor(menuFornecedorInt, menuAlterar, novoAtributoFornecedor) != null){
	                System.out.println("Alterado com Sucesso!");
	            }else{
	                System.out.println("Erro ao Alterar");
	        }
	    }
	    
	    public void fornecedorExibir() throws IOException{
	        FornecedorDao fornecedorDao = new FornecedorDao();
	        listFornecedor = fornecedorDao.abrirFornecedor();
	        System.out.println("   Fornecedores");
	        for(Fornecedor fornecedor: listFornecedor){
	            System.out.printf("%d. %s  |  %s  |  %s\n", 
	            		fornecedor.getIdFornecedor(), fornecedor.getNome(), 
	            		fornecedor.getCnpj(), fornecedor.getRamo());
	        }
	    }
	   
	    public void fornecedorExcluir() throws IOException{
	        Scanner scanner = new Scanner(System.in);
	        FornecedorDao fornecedorDao = new FornecedorDao();
	        fornecedorExibir();
	        
	        System.out.print("Qual fornecedor:   ");
	        Integer menuFornecedorDeleteInt = scanner.nextInt();
	        
	        if(fornecedorDao.excluirFornecedor(menuFornecedorDeleteInt) != null){
	            System.out.println("Excluido com Sucesso!");
	        }else{
	            System.out.println("Erro ao excluir");
	        }
	    }

}
