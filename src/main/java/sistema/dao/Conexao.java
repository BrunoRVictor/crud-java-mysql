package sistema.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Properties getProperties() throws IOException {
        Properties prop = new Properties();
        InputStream caminhoProp = Conexao.class.getClassLoader().getResourceAsStream("banco.properties");
        if (caminhoProp == null) {
            throw new IOException("Arquivo properties não encontrado.");
        }
        prop.load(caminhoProp);
        caminhoProp.close();
        return prop;
}

    public static Connection getConnection() throws SQLException, IOException {
        Properties prop = getProperties();
        final String url = prop.getProperty("banco.url");
        final String user = prop.getProperty("banco.user");
        final String password = prop.getProperty("banco.password");
        return DriverManager.getConnection(url, user, password);
        }
    }

