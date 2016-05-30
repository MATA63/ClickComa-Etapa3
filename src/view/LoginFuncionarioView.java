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
/**
 *
 * @author Igor
 */
public class LoginFuncionarioView {
    public Funcionario console_load() throws IOException{
        Scanner scanner = new Scanner(System.in);
        Boolean TrueOuFalse = true;
        // Verifica Funcionario.
        Funcionario funcionario = new Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        do{
            System.out.println("   Acessando a Conta   ");
            System.out.print("Informe CPF: ");
            funcionario = funcionarioDao.abrirFuncionario(scanner.next());
            if(funcionario != null){
                
                switch( funcionario.getCargo() )
                {
                    case "Garçom":
                        break;
                    case "Cozinheiro":
                        break;
                    case "Gerente":
                        break;
                }
                TrueOuFalse = false;
            }else{
                System.out.println("Funcionario não encontrado!");
                System.out.print("Informar novamente o CPF (s/n)? ");
                if(scanner.next().toUpperCase().equals("S")){
                    TrueOuFalse = true;
                }else{
                    TrueOuFalse = false;
                }
            }
        }while(TrueOuFalse);
        return funcionario;
    }
}
