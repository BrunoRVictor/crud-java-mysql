package sistema.dao;

import sistema.model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOCliente extends DAOClass {
    public int inserirCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente(nome) VALUES(?)";
        return inserir(query, cliente.getNome());
    }

    public ArrayList<Cliente> listarClientes() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = ("SELECT id, nome FROM cliente");
        try (Statement stmt = getConexao().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Cliente c1 = new Cliente(nome);
                c1.setId(id);
                clientes.add(c1);
            }
            return clientes;
        }
    }

    public void atualizarCliente(Cliente cliente, String nome) throws SQLException {
        String query = "UPDATE Cliente set nome = ? where id = ?";
        atualizar(query, nome, cliente.getId());
    }

    public void deletarCliente(int id) throws SQLException {
        String query = "DElETE FROM Cliente WHERE id = ?";
        deletar(query, id);
    }

}
