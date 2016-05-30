/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import control.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.*;
import view.*;

/**
 *
 * 
 */
public class ConsoleView {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        int sair =0;
        do{
            System.out.println("   Selecione o modulo  ");
            System.out.println("1. Cliente");
            System.out.println("2. Funcionario");
            System.out.println("3. Sair");
            System.out.print("Opcao: ");
            String menuModulo = scanner.nextLine();
            switch( menuModulo )
            {
                case "1":
                    ModuloClienteView moduloClienteView = new ModuloClienteView();
                    moduloClienteView.console_load();
                    break;
                case "2":
                    ModuloFuncionarioView moduloFuncionarioView = new ModuloFuncionarioView();
                    moduloFuncionarioView.console_Load();
                    break;
                case "3":
                    sair = 1;
                    break;
                default:
                    System.out.println("Opcao inexistente.");
                    sair = 1;
            }
        
        }while(sair == 0);
    }
    

}
