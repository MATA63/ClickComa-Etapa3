package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import model.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author iandrade
 */
public class TesteView {
    public static void main2(String[] args) throws IOException, ParseException {
       
        //Teste cliente::
        /*Cliente cliente = new Cliente();
        ClienteDao clienteDao = new ClienteDao();
        cliente = clienteDao.abrirCliente(2);
        if(true){};
        
        Cliente cliente = new Cliente("novoNome","123456798","333@zaroi.com");
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.salvarCliente(cliente);*/
        
        /* List<Cliente> listCliente = new ArrayList();
         ClienteDao clienteDao = new ClienteDao();
         listCliente = clienteDao.abrirCliente();
         if(true){}
        
        //Teste Funcionario
        Funcionario funcionario = new Funcionario(2,"2","22","222","2222");
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        funcionarioDao.salvarFuncionario(funcionario);
         List<Funcionario> listFuncionario = new ArrayList();
         FuncionarioDao funcionarioDao = new FuncionarioDao();
         listFuncionario = funcionarioDao.abrirFuncionario();
         if(true){}*/
        
        //Teste Item
        /*Item item = new Item(6,3,"nome",99.99f,true,false);
        ItemDao itemDao = new ItemDao();
        itemDao.salvarItem(item);
         List<Item> listItem = new ArrayList();
         ItemDao itemDao = new ItemDao();
         listItem = itemDao.abrirItem();
         if(true){}
        Item item = new Item();
        ItemDao itemDao = new ItemDao();
        item = itemDao.abrirItem(2);
        if(true){};*/
        
        //Teste Cardapio
        /* List<Item> listItem = new ArrayList();
         ItemDao itemDao = new ItemDao();
         listItem = itemDao.abrirItem();
        Cardapio cardapio = new Cardapio(listItem);
        CardapioDao cardapioDao = new CardapioDao();
        cardapioDao.salvarCardapio(cardapio);
        if(true){}
        
         Cardapio cardapio = new Cardapio();
         CardapioDao cardapioDao = new CardapioDao();
         cardapio = cardapioDao.abrirCardapio();
         if(true){}*/
        
        
        //Teste Mesa::
        /*Mesa mesa = new Mesa(3,3,"Ala 3");
        MesaDao mesaDao = new MesaDao();
        mesaDao.salvarMesa(mesa);
         if(true){}
         List<Mesa> listMesa = new ArrayList();
         MesaDao mesaDao = new MesaDao();
         listMesa = mesaDao.abrirMesa();
         if(true){}*/

        //Teste Conta
        /*Cliente cliente = new Cliente();
        ClienteDao clienteDao = new ClienteDao();
        cliente = clienteDao.abrirCliente(1);
        if(true){};
        
        Mesa mesa = new Mesa();
        MesaDao mesaDao = new MesaDao();
        mesa = mesaDao.abrirMesa(1);        
        
        Date todaysDate = new Date();
        Conta conta = new Conta(2,cliente,mesa,todaysDate,todaysDate);
        ContaDao contaDao = new ContaDao();
        contaDao.salvarConta(conta);
         if(true){}
        
         List<Conta> listConta = new ArrayList();
         ContaDao contaDao = new ContaDao();
         listConta = contaDao.abrirConta();
         if(true){}*/
         
        /*Conta conta = new Conta();
        ContaDao contaDao = new ContaDao();
        conta = contaDao.abrirConta(1);
         if(true){}
        
        //Teste Pedido*/
         /*List<Conta> listConta = new ArrayList();
         ContaDao contaDao = new ContaDao();
         listConta = contaDao.abrirConta();
         
         List<Item> listItem = new ArrayList();
         ItemDao itemDao = new ItemDao();
         listItem = itemDao.abrirItem();
         
         List<Funcionario> listFuncionario = new ArrayList();
         FuncionarioDao funcionarioDao = new FuncionarioDao();
         listFuncionario = funcionarioDao.abrirFuncionario();
        
         Date todaysDate = new Date();
         
        Pedido pedido = new Pedido(1, listConta.get(1), listItem.get(0), 2, todaysDate, listFuncionario.get(0), listFuncionario.get(1));
        PedidoDao pedidoDao = new PedidoDao();
        pedidoDao.salvarPedido(pedido);
        if(true){}
        
        List<Pedido> listPedido = new ArrayList();
        PedidoDao pedidoDao = new PedidoDao();
        listPedido = pedidoDao.abrirPedido();
        if(true){}
        Conta conta = new Conta();
        conta.setIdConta(2);
        Pedido pedido = new Pedido();*/
        PedidoDao pedidoDao = new PedidoDao();
        Funcionario garcom = new Funcionario();
        garcom.setIdFuncionario(1);
        List<Pedido> listPedido = pedidoDao.abrirPedidoVisaoGarcom();
        ModuloFuncionarioView moduloFuncionarioView = new ModuloFuncionarioView();
        moduloFuncionarioView.GarcomConfirmarEntregaPedidos(garcom);
        
        if(true){}
        
        
       /* Visão Cliente
        Pedido pedido = new Pedido();
        PedidoDao pedidoDao = new PedidoDao();
        PedidoView pedidoView = new PedidoView();
        pedidoView.console_load();*/
        
        
    }
}
