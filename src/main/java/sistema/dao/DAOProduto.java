package sistema.dao;

import sistema.model.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOProduto extends DAOClass {
    public int inserirProduto(Produto produto) throws SQLException {
        String query = "INSERT INTO produto(nome, quantidade) VALUES (?, ?)";
        return inserir(query, produto.getNome(), produto.getQuantidade());
    }

    public ArrayList<Produto> listarProdutos() throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<>();
        String query = "SELECT id, nome, quantidade FROM produto";
        try (Statement stmt = getConexao().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int qtd = rs.getInt("quantidade");
                Produto p1 = new Produto(nome, qtd);
                p1.setId(id);
                produtos.add(p1);
            }
        }
        return produtos;
    }

    public void atualizarQtdProduto(Produto produto, int valor) throws SQLException {
        String query = "UPDATE Produto SET quantidade = ? where id = ?";
        atualizar(query, valor, produto.getId());
    }

    public void deletarProduto(int id) throws SQLException {
        String query = "DELETE FROM Produto WHERE id = ?";
        deletar(query, id);
    }

}
