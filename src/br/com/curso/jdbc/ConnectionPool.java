package br.com.curso.jdbc;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final String JBDC_URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static final DataSource dataSource;

    static {
        System.out.print("Iniciando Connection Pool..\n");
        JdbcConnectionPool pool =  JdbcConnectionPool.create(JBDC_URL, USER, PASS);
        dataSource = pool;
        System.out.println("Connection Pool ok..\n");
    }

    public static Connection getConnection() throws SQLException {
        System.out.print("Resolvendo conexão..\n");
        Connection connection = dataSource.getConnection();
        System.out.println("Conexão ok..\n");
        return connection;
    }

}
