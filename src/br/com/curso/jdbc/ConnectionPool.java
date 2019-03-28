package br.com.curso.jdbc;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private String JBDC_URL;
    private String USER;
    private String PASS;
    private DataSource dataSource;

    public ConnectionPool(String jdbcUrl, String usuario, String senha) {
        System.out.println("Criando Pool de conexões..");
        dataSource = JdbcConnectionPool.create(jdbcUrl, usuario, senha);
    }

    public Connection getConexao() throws SQLException {
        System.out.print("Resolvendo conexão..\n");
        Connection connection = dataSource.getConnection();
        System.out.println("Conexão ok..\n");
        return connection;
    }

}
