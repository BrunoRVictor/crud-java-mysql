package sistema;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import sistema.model.Cliente;
import sistema.model.Pedido;
import sistema.model.Produto;
import sistema.service.GerenciaService;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        GerenciaService service = new GerenciaService();
        service.carregarDadosBanco();
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Atualizar Cliente");
            System.out.println("4 - Deletar Cliente");
            System.out.println("5 - Cadastrar Produto");
            System.out.println("6 - Listar Produtos");
            System.out.println("7 - Atualizar Produto");
            System.out.println("8 - Deletar Produto");
            System.out.println("9 - Fazer um pedido");
            System.out.println("10 - Listar os pedidos");
            System.out.println("11 - Deletar pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
            try {
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o nome do cliente:");
                        String ansNome = sc.nextLine();
                        while (!ansNome.matches("^[A-Za-z\s]{1,50}$")) {
                            System.out.println("Nome inválido. (Só letras e no máximo 50 letras)\nTente novamente:");
                            ansNome = sc.nextLine();
                        }
                        Cliente c1 = new Cliente(ansNome);
                        boolean jaCadastrado = false;
                        for (Cliente c : service.getListaClientes()) {
                            if (c1.getNome().equals(c.getNome())) {
                                jaCadastrado = true;
                                break;
                            }
                    }
                        if (jaCadastrado) {
                            System.out.println("Houve uma falha. Cliente já cadastrado anteriormente.");
                            break;
                        }
                        service.cadastrarCliente(c1);
                        break;
                    case 2:
                        if (service.getListaClientes().isEmpty()) {
                            System.out.println("Não há nenhum cliente cadastrado.");
                            break;
                        }
                        System.out.println("[Clientes:]");
                        for (Cliente c : service.getListaClientes()) {
                            System.out.println(c);
                        }
                        break;
                    case 3:
                        if (service.getListaClientes().isEmpty()) {
                            System.out.println("Não há nenhum cliente cadastrado.");
                            break;
                        }
                        System.out.println("[Clientes:]");
                        for (Cliente c : service.getListaClientes()) {
                            System.out.println(c);
                        }
                        System.out.println("Digite o ID do cliente que deseja alterar:");
                        int ansID = sc.nextInt();
                        sc.nextLine();
                        boolean achou = false;
                        for (Cliente c : service.getListaClientes()) {
                            if (c.getId() == ansID) {
                                achou = true;
                                System.out.println("Encontrado. (" + c.getNome() + ")\nQual o novo nome?");
                                ansNome = sc.nextLine();
                                while (!ansNome.matches("^[A-Za-z\s]{1,50}$")) {
                                    System.out.println("Nome inválido. (Só letras e no máximo 50 letras)\nTente novamente:");
                                    ansNome = sc.nextLine();
                                }
                                service.atualizarCliente(c, ansNome);
                                break;
                            }
                        }
                        if (!achou) System.out.println("ID não encontrado.");
                        break;
                    case 4:
                        if (service.getListaClientes().isEmpty()) {
                            System.out.println("Não há nenhum cliente cadastrado.");
                            break;
                        }
                        System.out.println("[Clientes:]");
                        for (Cliente c : service.getListaClientes()) {
                            System.out.println(c);
                        }
                        System.out.println("Digite o ID do cliente que deseja deletar:");
                        ansID = sc.nextInt();
                        achou = false;
                        for (Cliente c : service.getListaClientes()) {
                            if (c.getId() == ansID) {
                                achou = true;
                                System.out.println("Encontrado. (" + c.getNome() + ")\nProsseguir?\n[1] Sim [2] Não" +
                                        ".");
                                int ans = sc.nextInt();
                                while (ans != 1 && ans != 2) {
                                    System.out.println("Entrada incorreta. (1 ou 2)\nTente novamente:");
                                    ans = sc.nextInt();
                                }
                                if (ans == 1) {
                                    service.deletarCliente(c);
                                    break;
                                } else {
                                    System.out.println("Cancelando...");
                                    break;
                                }
                            }
                        }
                        if (!achou) {
                            System.out.println("ID não encontrado.");
                            break;
                        }
                        break;
                    case 5:
                        System.out.println("Digite o nome do produto:");
                        ansNome = sc.nextLine();
                        while (!ansNome.matches("^[A-Za-z\s]{1,50}$")) {
                            System.out.println("Nome inválido. (Só letras e no máximo 50 letras)" +
                                    "\nTente novamente:");
                            ansNome = sc.nextLine();
                        }
                        jaCadastrado = false;
                        for (Produto p : service.getListaProdutos()) {
                            if (p.getNome().equals(ansNome)) {
                                jaCadastrado = true;
                                break;
                            }
                        }
                        if (jaCadastrado) {
                            System.out.println("Houve uma falha. Produto já cadastrado anteriormente.");
                            break;
                        }
                        System.out.println("Digite a quantidade:");
                        int ansQtd = sc.nextInt();
                        sc.nextLine();
                        while (ansQtd < 0 || ansQtd > 100) {
                            System.out.println("Quantidade inválida. (0 a 100)\nTente novamente:");
                            ansQtd = sc.nextInt();
                            sc.nextLine();
                        }
                        Produto p1 = new Produto(ansNome, ansQtd);
                        service.cadastrarProduto(p1);
                        break;
                    case 6:
                        if (service.getListaProdutos().isEmpty()) {
                            System.out.println("Não há nenhum produto cadastrado.");
                            break;
                        }
                        System.out.println("[Produtos:]");
                        for (Produto p : service.getListaProdutos()) {
                            System.out.println(p);
                        }
                        break;
                    case 7:
                        if (service.getListaProdutos().isEmpty()) {
                            System.out.println("Não há nenhum produto cadastrado.");
                            break;
                        }
                        System.out.println("[Produtos:]");
                        for (Produto p : service.getListaProdutos()) {
                            System.out.println(p);
                        }
                        System.out.println("Digite o ID do Produto que deseja alterar (Apenas alterações de " +
                                "quantidade são permitidas):");
                        ansID = sc.nextInt();
                        sc.nextLine();
                        achou = false;
                        for (Produto p : service.getListaProdutos()) {
                            if (p.getId() == ansID) {
                                achou = true;
                                System.out.println("Encontrado.\n[" + p.toString() + "]\nQual a nova quantidade?");
                                ansQtd = sc.nextInt();
                                sc.nextLine();
                                while (ansQtd < 0 || ansQtd > 100) {
                                    System.out.println("Quantidade inválida. (0 a 100)\nTente novamente:");
                                    ansQtd = sc.nextInt();
                                    sc.nextLine();
                                }
                                service.atualizarQtdProduto(p, ansQtd);
                                break;
                            }
                        }
                        if (!achou) System.out.println("ID não encontrado.");
                        break;
                    case 8:
                        if (service.getListaProdutos().isEmpty()) {
                            System.out.println("Não há nenhum produto cadastrado.");
                            break;
                        }
                        System.out.println("[Produtos:]");
                        for (Produto p : service.getListaProdutos()) {
                            System.out.println(p);
                        }
                        System.out.println("Digite o ID do produto que deseja deletar:");
                        ansID = sc.nextInt();
                        sc.nextLine();
                        achou = false;
                        for (Produto p : service.getListaProdutos()) {
                            if (p.getId() == ansID) {
                                achou = true;
                                System.out.println("Encontrado. (" + p.getNome() + ")\nProsseguir?\n[1] Sim " +
                                        "[2] Não" +
                                        ".");
                                int ans = sc.nextInt();
                                sc.nextLine();
                                while (ans != 1 && ans != 2) {
                                    System.out.println("Entrada incorreta. (1 ou 2)\nTente novamente:");
                                    ans = sc.nextInt();
                                    sc.nextLine();
                                }
                                if (ans == 1) {
                                    service.deletarProduto(p);
                                    break;
                                } else {
                                    System.out.println("Cancelando...");
                                    break;
                                }
                            }
                        }
                        if (!achou) {
                            System.out.println("ID não encontrado.");
                            break;
                        }
                        break;
                    case 9:
                        Cliente cliente = null;
                        Produto produto = null;
                        System.out.println("[Clientes:]");
                        for (Cliente c : service.getListaClientes()) {
                            System.out.println(c);
                        }
                        System.out.println("===============");
                        System.out.println("[Produtos:]");
                        for (Produto p : service.getListaProdutos()) {
                            System.out.println(p);
                        }
                        System.out.println("===============");
                        System.out.println("Escolha um cliente e um produto para fazer um pedido.");
                        System.out.println("Cliente (ID):");
                        ansID = sc.nextInt();
                        sc.nextLine();
                        for (Cliente c : service.getListaClientes()) {
                            if (c.getId() == ansID) {
                                System.out.println("Cliente encontrado.");
                                cliente = c;
                                break;
                            }
                        }
                        if (cliente == null) {
                            System.out.println("Cliente não encontrado.");
                            break;
                        }
                        System.out.println("Produto (ID):");
                        ansID = sc.nextInt();
                        sc.nextLine();
                        for (Produto p : service.getListaProdutos()) {
                            if (p.getId() == ansID) {
                                System.out.println("Produto encontrado.");
                                produto = p;
                                break;
                            }
                        }
                        if (produto == null) {
                            System.out.println("Produto não encontrado.");
                            break;
                        }
                        service.cadastrarPedido(produto, cliente);
                        break;
                    case 10:
                        if (service.getListaPedidos().isEmpty()) {
                            System.out.println("Não há nenhum pedido cadastrado.");
                            break;
                        }
                        System.out.println("[Pedidos:]");
                        System.out.println("[Produtos:]");
                        for (Pedido p : service.getListaPedidos()) {
                            System.out.println(p);
                        }
                        break;
                    case 11:
                        if (service.getListaPedidos().isEmpty()) {
                            System.out.println("Não há nenhum pedido cadastrado.");
                            break;
                        }
                        System.out.println("[Pedidos:]");
                        for (Pedido p : service.getListaPedidos()) {
                            System.out.println(p);
                        }
                        System.out.println("Digite o ID do pedido que deseja deletar:");
                        ansID = sc.nextInt();
                        sc.nextLine();
                        achou = false;
                        for (Pedido p : service.getListaPedidos()) {
                            if (p.getId() == ansID) {
                                achou = true;
                                System.out.println("Encontrado. (" + p.toString() + ")\nProsseguir?\n[1] Sim [2] Não");
                                int ans = sc.nextInt();
                                sc.nextLine();
                                while (ans != 1 && ans != 2) {
                                    System.out.println("Entrada incorreta. (1 ou 2)\nTente novamente:");
                                    ans = sc.nextInt();
                                    sc.nextLine();
                                }
                                if (ans == 1) {
                                    service.deletarPedido(p);
                                    break;
                                } else {
                                    System.out.println("Cancelando...");
                                    break;
                                }
                            }
                        }
                        if (!achou) {
                            System.out.println("ID não encontrado.");
                            break;
                        }
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }
        } while (opcao != 0);
        sc.close();
    }
}