/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import control.*;
import model.*;
import view.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Igor
 */
public class JUnitTest3 { // testeeeeeeeeeeeeeeeeeee
    
    private String ajudaSuporteTitulo = "titulo teste do JUnit";
    private String ajudaSuporteDescricao = "descricao teste do JUnit";
    private int idAjudaSuporteDescricao;
    
    public JUnitTest3() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    // ================= AJUDA E SUPORTE =================
    @Test
    public void InserirAjudaESuporte() throws IOException{
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        AjudaSuporte ajudaSuporte = new AjudaSuporte();
        AjudaSuporte ajudaSuporteResultado = new AjudaSuporte();
        
        ajudaSuporte.setTitulo(ajudaSuporteTitulo);
        ajudaSuporte.setDescricao(ajudaSuporteDescricao);

        ajudaSuporteResultado = ajudaSuporteDao.salvarAjudaSuporte(ajudaSuporte);
        this.idAjudaSuporteDescricao = ajudaSuporteResultado.getIdAjudaSuporte();
        assertNotNull(ajudaSuporteResultado);
        
    }

    @Test
    public void ExibirAjudaESuporte() throws IOException{
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        AjudaSuporte ajudaSuporte = new AjudaSuporte();
        
        ajudaSuporte = ajudaSuporteDao.abrirAjudaSuporte(2);

        assertNotNull(ajudaSuporte);
    }
    
    @Test
    public void ExibirAjudaESuporte() throws IOException{
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        AjudaSuporte ajudaSuporte = new AjudaSuporte();
        
        ajudaSuporte = ajudaSuporteDao.abrirAjudaSuporte(2);

        assertNotNull(ajudaSuporte);
    }
    
    // ================= RELATORIO PEDIDOS POR CLIENTE =================
    
    @Test
    public void ExibirRelatorioPedidosPorCliente() throws IOException{
        PedidoDao pedidoDao = new PedidoDao();
        List<Pedido> listPedido = new ArrayList();
        listPedido = pedidoDao.abrirPedido();
        
        assertNotNull(listPedido);
    }

    @Test
    public void ExibeRelatorioPorAtendimento() throws IOException{
        PedidoDao pedidoDao = new PedidoDao();
        List<Pedido> listPedido = new ArrayList();
        
        listPedido = pedidoDao.abrirPedido();
        
        assertNotNull(listPedido);
    }
}
