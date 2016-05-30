/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.*;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import model.*;

public class LoginClienteView {

    public Conta console_load(Conta conta) throws IOException{
        Scanner scanner = new Scanner(System.in);
        Boolean TrueOuFalse = true;
        //for(short i=0; i<20; i++) System.out.println("\n");

        // Verifica Cliente.
        ClienteDao clienteDao = new ClienteDao();
        do{
            System.out.println("   Acessando a Conta   ");
            System.out.print("Informe CPF: ");
            Cliente cliente = clienteDao.abrirCliente(scanner.next());
            if(cliente != null){
                conta.setCliente(cliente);
               
                ManterMesaView manterMesa = new ManterMesaView();
                manterMesa.mesaExibir();

                // Verifica Mesa
                MesaDao mesaDao = new MesaDao();
                do{
                    System.out.print("Informe Numero: ");
                    Mesa mesa = mesaDao.abrirMesa(scanner.nextInt());
                    if(mesa != null){
                        conta.setMesa(mesa);
                        TrueOuFalse = false;
                    }else{
                        System.out.println("Mesa não encontrada!");
                        System.out.print("Informar novamente a mesa (s/n)? ");
                        if(scanner.next().toUpperCase().equals("S")){
                            TrueOuFalse = true;
                        }else{
                            TrueOuFalse = false;
                        }
                    }
                }while(TrueOuFalse);
                
            }else{
                System.out.println("Cliente não encontrado!");
                System.out.print("Informar novamente o CPF (s/n)? ");
                if(scanner.next().toUpperCase().equals("S")){
                    TrueOuFalse = true;
                }else{
                    TrueOuFalse = false;
                }
            }
        }while(TrueOuFalse);
        
        
        // Informa data da abertura do atendimento
        // Salva Registro da Conta
        if(conta.getCliente() != null && conta.getMesa() != null){
            conta.setDataHoraInicioAtendimento(new Date());
            ContaDao contaDao = new ContaDao();
            conta = contaDao.salvarConta(conta);      
            return conta;
        }else{
            return null;
        }
        
    }
    
}
