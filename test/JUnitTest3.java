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
    private int idAjudaSuporteDescricao = 3;
    private String ReclamacaoDescricao = "titulo Reclamacao teste do JUnit";

    private String fornecedorNome = "NomeFornecedor";
    private String fornecedorRamo = "RamoFornecedor";
    private String fornecedorCNPJ = "CNPJFornecedor";
    private int idFornecedorDescricao = 3;
    
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
    public void testInserirAjudaESuporte() throws IOException{
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
    public void testExibirAjudaESuporte() throws IOException{
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        AjudaSuporte ajudaSuporte = new AjudaSuporte();
        
        ajudaSuporte = ajudaSuporteDao.abrirAjudaSuporte(this.idAjudaSuporteDescricao);

        assertNotNull(ajudaSuporte);
    }
    
    @Test
    public void testAlterarAjudaESuporte() throws IOException{
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        List<AjudaSuporte> listAjudaSuporte = new ArrayList<AjudaSuporte>();
        AjudaSuporte ajudaSuporteAlterado = new AjudaSuporte();
        String novoTitulo = "NovoTituloJUnit";
        listAjudaSuporte = ajudaSuporteDao.abrirAjudaSuporte();
        
        //1 = Alterar Titulo e 2 = Alterar Descricao
        listAjudaSuporte = ajudaSuporteDao.alterarAjudaSuporte(this.idAjudaSuporteDescricao, 1, novoTitulo);
        for(AjudaSuporte ajudaSuporte: listAjudaSuporte){
            if(ajudaSuporte.getIdAjudaSuporte() == this.idAjudaSuporteDescricao){
                ajudaSuporteAlterado = ajudaSuporte;
                break;
            }
        }
        
        if(novoTitulo.equals(ajudaSuporteAlterado.getTitulo())){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }
    
    @Test
    public void testExcluirAjudaESuporte() throws IOException{
        AjudaSuporteDao ajudaSuporteDao = new AjudaSuporteDao();
        List<AjudaSuporte> listAjudaSuporte = new ArrayList<AjudaSuporte>();
        listAjudaSuporte = ajudaSuporteDao.abrirAjudaSuporte();
        
        int QuantidadeListAntesExcluir = listAjudaSuporte.size();
        int QuantidadeListDepoisExcluir = 0;
        
        listAjudaSuporte = ajudaSuporteDao.excluirAjudaSuporte(this.idAjudaSuporteDescricao);
        QuantidadeListDepoisExcluir = listAjudaSuporte.size();
        
        assertTrue(QuantidadeListAntesExcluir != QuantidadeListDepoisExcluir);
    }

    // ================= SUGESTOES E RECLAMACOES =================
    
    @Test
    public void testInserirReclamacoes() throws IOException{
        ReclamacaoDao ajudaReclamacaoDao = new ReclamacaoDao();
        Reclamacao reclamacao = new Reclamacao();
        Reclamacao reclamacaoResultado = new Reclamacao();
        
        reclamacao.setDescricao(this.ReclamacaoDescricao);
        reclamacaoResultado = ajudaReclamacaoDao.salvarReclamacao(reclamacao);

        assertNotNull(reclamacaoResultado);
    }

    @Test
    public void testExibirRelatorioReclamacoes() throws IOException{
        ReclamacaoDao reclamacaoDao = new ReclamacaoDao();
        List<Reclamacao> listReclamacao = new ArrayList<Reclamacao>();
        
        listReclamacao = reclamacaoDao.abrirReclamacao();

        assertNotNull(listReclamacao);
    }
    
    // ================= RELATORIOS =================
    
    @Test
    public void testExibirRelatorioPedidosPorCliente() throws IOException{
        PedidoDao pedidoDao = new PedidoDao();
        List<Pedido> listPedido = new ArrayList();
        listPedido = pedidoDao.abrirPedido();
        
        assertNotNull(listPedido);
    }

    @Test
    public void testExibeRelatorioAtendimento() throws IOException{
        PedidoDao pedidoDao = new PedidoDao();
        List<Pedido> listPedido = new ArrayList();
        listPedido = pedidoDao.abrirPedido();
        
        assertNotNull(listPedido);
    }
    
    @Test
    public void testExibeRelatorioFinanceiro() throws Exception {
        ContaDao contaDao = new ContaDao();
        List<Conta> listConta = new ArrayList();
        listConta = contaDao.abrirConta();

        assertNotNull(listConta);
    }
    
    @Test
    public void testExibeRelatorioPedido() throws Exception {
        PedidoDao pedidoDao = new PedidoDao();
        List<Pedido> listPedido = new ArrayList<Pedido>();
        listPedido = pedidoDao.abrirPedido();
        
        assertNotNull(listPedido);
    }
    
    // ================= FORNECEDOR =================

    @Test
    public void testInserirFornecedor() throws IOException{
        FornecedorDao fornecedorDao = new FornecedorDao();
        Fornecedor fornecedor = new Fornecedor();
        Fornecedor fornecedorResultado = new Fornecedor();
        
        fornecedor.setNome(this.fornecedorNome);
        fornecedor.setCnpj(this.fornecedorCNPJ);
        fornecedor.setRamo(this.fornecedorRamo);

        fornecedorResultado = fornecedorDao.salvarFornecedor(fornecedor);
        this.idFornecedorDescricao = fornecedorResultado.getIdFornecedor();
        
        assertNotNull(fornecedorResultado);
        
    }

    @Test
    public void testExibirFornecedor() throws IOException{
        FornecedorDao fornecedorDao = new FornecedorDao();
        Fornecedor fornecedor = new Fornecedor();
        
        fornecedor = fornecedorDao.abrirFornecedor(this.idFornecedorDescricao);

        assertNotNull(fornecedor);
    }
  
    @Test
    public void testAlterarFornecedor() throws IOException{
        FornecedorDao fornecedorDao = new FornecedorDao();
        List<Fornecedor> listFornecedor = new ArrayList<Fornecedor>();
        Fornecedor fornecedorAlterado = new Fornecedor();
        String novoNome = "NovoNomeJUnit";
        listFornecedor = fornecedorDao.abrirFornecedor();
        
        //1 = Alterar Nome, 2 = Alterar CNPJ e 3 = Alterar Ramo
        listFornecedor = fornecedorDao.alterarFornecedor(this.idFornecedorDescricao, 1, novoNome);
        for(Fornecedor fornecedor: listFornecedor){
            if(fornecedor.getIdFornecedor() == this.idFornecedorDescricao){
                fornecedorAlterado = fornecedor;
                break;
            }
        }
        
        if(novoNome.equals(fornecedorAlterado.getNome())){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }
    
    @Test
    public void testExcluirFornecedor() throws IOException{
        FornecedorDao fornecedorDao = new FornecedorDao();
        List<Fornecedor> listFornecedor = new ArrayList<Fornecedor>();
        listFornecedor = fornecedorDao.abrirFornecedor();
        
        int QuantidadeListAntesExcluir = listFornecedor.size();
        int QuantidadeListDepoisExcluir = 0;
        
        listFornecedor = fornecedorDao.excluirFornecedor(5);
        QuantidadeListDepoisExcluir = listFornecedor.size();
        
        assertTrue(QuantidadeListAntesExcluir != QuantidadeListDepoisExcluir);
    }
    
    
}
