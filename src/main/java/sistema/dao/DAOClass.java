package sistema.dao;

import java.io.IOException;
import java.sql.*;

public class DAOClass {
     Connection con;

     public Connection getConexao() throws SQLException{
        try {
            if (con == null || con.isClosed()) {
                con = Conexao.getConnection();
            }
                return con;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao carregar banco", e);
        }
    }

    public int inserir(String query, Object... atributos) throws SQLException {
        try (PreparedStatement pstmt = getConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setarAtributos(pstmt, atributos);
            if (pstmt.executeUpdate() > 0) {
                System.out.println("Sucesso!");
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            } else {
                System.out.println("Falha!");
            }
        }
        return -1;
    }

    public void setarAtributos(PreparedStatement pstmt, Object... args) throws SQLException {
        int index = 1;
        for (Object object : args) {
            if (object instanceof String) {
                pstmt.setString(index, (String) object);
            } else if (object instanceof Integer) {
                pstmt.setInt(index, (Integer) object);
            } else if (object instanceof Double) {
                pstmt.setDouble(index, (Double) object);
            } else if (object instanceof Boolean) {
                pstmt.setBoolean(index, (Boolean) object);
            }
            index++;
        }
    }

    public void atualizar(String query, Object... atributos) throws SQLException {
        try (PreparedStatement pstmt = getConexao().prepareStatement(query)){
            setarAtributos(pstmt, atributos);
            if (pstmt.executeUpdate() > 0) {
                System.out.println("Atualização feita com sucesso!");
            } else {
                System.out.println("Falha.");
            }
        }
    }

    public void deletar(String query, int id) throws SQLException {
       try (PreparedStatement pstmt = getConexao().prepareStatement(query)) {
            setarAtributos(pstmt, id);
            if (pstmt.executeUpdate() > 0) {
                System.out.println("Sucesso.");
            } else {
                System.out.println("Erro.");
            }
        }
    }
}
