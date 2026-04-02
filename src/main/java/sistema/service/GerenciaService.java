package sistema.service;

import sistema.model.Cliente;
import sistema.model.Pedido;
import sistema.model.Produto;
import sistema.dao.DAOCliente;
import sistema.dao.DAOPedido;
import sistema.dao.DAOProduto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaService {
    private  ArrayList<Cliente> listaClientes = new ArrayList<>();
    private  ArrayList<Produto> listaProdutos = new ArrayList<>();
    private  ArrayList<Pedido> listaPedidos = new ArrayList<>();

    private  final DAOPedido daoPedido = new DAOPedido();
    private  final DAOProduto daoProduto = new DAOProduto();
    private  final DAOCliente daoCliente = new DAOCliente();
    private  final Scanner sc = new Scanner(System.in);


    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public ArrayList<Produto> exibirProdutos() throws SQLException {
        listaProdutos = daoProduto.listarProdutos();
        return listaProdutos;
    }

    public ArrayList<Cliente> exibirClientes() throws SQLException {
        listaClientes = daoCliente.listarClientes();
        return listaClientes;
    }

    public ArrayList<Pedido> exibirPedidos() throws SQLException {
        listaPedidos = daoPedido.listarPedidos();
        return listaPedidos;
    }

    public void cadastrarCliente(Cliente cliente) throws SQLException {
            int id = daoCliente.inserirCliente(cliente);
            System.out.println("Cliente cadastrado com o ID: " + id);
            listaClientes = daoCliente.listarClientes();
        }


    public void cadastrarProduto(Produto produto) throws SQLException {
        int id = daoProduto.inserirProduto(produto);
        System.out.println("Produto cadastrado com o ID: " + id);
        listaProdutos = daoProduto.listarProdutos();
    }

    public void atualizarCliente(Cliente cliente, String nome) throws SQLException {
        daoCliente.atualizarCliente(cliente, nome);
        listaClientes = daoCliente.listarClientes();
    }

    public void atualizarQtdProduto(Produto produto, int quantidade) throws SQLException {
        daoProduto.atualizarQtdProduto(produto, quantidade);
        listaProdutos = daoProduto.listarProdutos();
    }

    public void deletarCliente(Cliente cliente) throws SQLException {
        daoCliente.deletarCliente(cliente.getId());
        listaClientes = daoCliente.listarClientes();
    }

    public void deletarProduto(Produto produto) throws SQLException {
        daoProduto.deletarProduto(produto.getId());
        listaProdutos = daoProduto.listarProdutos();
    }

    public void cadastrarPedido(Produto produto, Cliente cliente) throws SQLException {
        Pedido pedido = new Pedido(cliente.getId(), produto.getId());
        int id = daoPedido.inserirPedido(pedido);
        System.out.println("Pedido cadastrado com o ID: " + id);
        listaPedidos = daoPedido.listarPedidos();
    }

    public void deletarPedido(Pedido pedido) throws SQLException {
        daoPedido.deletarPedido(pedido.getId());
        listaPedidos = daoPedido.listarPedidos();
    }

    public void carregarDadosBanco() throws SQLException {
        exibirProdutos();
        exibirClientes();
        exibirPedidos();
    }
}
