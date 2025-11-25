package br.com.advocaciaviviane.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    // metodo de conexão com o banco de dados
    public Connection conexao() throws ClassNotFoundException, SQLException {

        // Driver oracle
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Retornar conexão
        //return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE:AS SYSDBA",
         //       "SYS", "@Afonso08");


        // ConexaoFactory.java

        // Configura as propriedades de conexão
                java.util.Properties info = new java.util.Properties();
                info.put("user", "SYS");
                info.put("password", "@Afonso08");
        // Adiciona a propriedade de papel (role)
                info.put("internal_logon", "sysdba");

        // Conecta usando as propriedades
                return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", info);

    }

}

