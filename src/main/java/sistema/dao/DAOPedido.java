package sistema.dao;

import sistema.model.Pedido;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPedido extends DAOClass {
    public int inserirPedido(Pedido pedido) throws SQLException {
        String query = "INSERT INTO pedido(idCliente, idProduto) VALUES(?, ?)";
        return inserir(query, pedido.getIdCliente(), pedido.getIdProduto());
    }

    public void deletarPedido(int id) throws SQLException {
        String query = "DELETE FROM Pedido WHERE id = ?";
        deletar(query, id);
    }

    public ArrayList<Pedido> listarPedidos() throws SQLException {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT id, idCliente, idProduto FROM Pedido";
        try (Statement stmt = getConexao().createStatement();
              ResultSet rs = stmt.executeQuery(query)) {
              while (rs.next()) {
                 int id = rs.getInt("id");
                 int idCliente = rs.getInt("idCliente");
                 int idProduto = rs.getInt("idProduto");
                 Pedido p1 = new Pedido(idCliente, idProduto);
                 p1.setId(id);
                 pedidos.add(p1);
              }
              return pedidos;
        }
    }
}
