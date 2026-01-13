/*
package br.com.advocaciaviviane.DAO;

import br.com.advocaciaviviane.beans.Avaliacao;
import br.com.advocaciaviviane.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {

    public Connection minhaConexao;

    // metodo construtor com parâmetro vazio
    public AvaliacaoDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Insert
    public String inserir(Avaliacao avaliacao) throws SQLException {
        // CORREÇÃO: Listamos explicitamente as 6 colunas para coincidir com os 6 valores (?)
        // e excluimos o ID (que é auto-incrementado) para evitar o ORA-32795.
        String sql = "INSERT INTO avaliacao (nome, dia, mes, ano, avaliacao, nota) VALUES (?, ?, ?, ?, ?, ?)";

        // ALTERAÇÃO CRÍTICA: Indicamos ao PreparedStatement para retornar as chaves geradas
        PreparedStatement stmt = minhaConexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        stmt.setString(1, avaliacao.getNome());
        stmt.setString(2, avaliacao.getDia());
        stmt.setString(3, avaliacao.getMes());
        stmt.setString(4, avaliacao.getAno());
        stmt.setString(5, avaliacao.getAvaliacao());
        stmt.setInt(6, avaliacao.getNota());

        stmt.execute();
        stmt.close();
        this.minhaConexao.close();

        return "Avaliacao cadastrada com sucesso!";
    }

    // Delete
    public String deletar(String id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("Delete from avaliacao where id = ?");
        stmt.setString(1, id);

        stmt.execute();
        stmt.close();
        this.minhaConexao.close();

        return  "Avaliacao deletada com sucesso!";
    }

    // UpDate
    public String atualizar(Avaliacao avaliacao) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("Update avaliacao set NOME = ?, DIA = ?, MES = ?, ANO = ?, AVALIACAO = ?, NOTA = ? where ID = ?");
        stmt.setString(1, avaliacao.getId());
        stmt.setString(2, avaliacao.getNome());
        stmt.setString(3, avaliacao.getDia());
        stmt.setString(4, avaliacao.getMes());
        stmt.setString(5, avaliacao.getAno());
        stmt.setString(6, avaliacao.getAvaliacao());
        stmt.setInt(7, avaliacao.getNota());

        stmt.executeUpdate();
        stmt.close();
        this.minhaConexao.close();

        return "Avaliacao atualizada com sucesso!";
    }

    // Select
    public List<Avaliacao> selecionar() throws SQLException {
        List<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("select * from avaliacao");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setId(rs.getString(1));
            avaliacao.setNome(rs.getString(2));
            avaliacao.setDia(rs.getString(3));
            avaliacao.setMes(rs.getString(4));
            avaliacao.setAno(rs.getString(5));
            avaliacao.setAvaliacao(rs.getString(6));
            avaliacao.setNota(rs.getInt(7));
            listaAvaliacao.add(avaliacao);
        }
        this.minhaConexao.close();
        return listaAvaliacao;
    }


}*/


package br.com.advocaciaviviane.DAO;

import br.com.advocaciaviviane.beans.Avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import javax.sql.DataSource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoDAO {

    // 1. INJEÇÃO DO POOL DE CONEXÕES GERENCIADO PELO QUARKUS
    @Inject
    DataSource dataSource;

    // Insert
//    public String inserir(Avaliacao avaliacao) throws SQLException {
//        String sql = "INSERT INTO avaliacao (nome, dia, mes, ano, avaliacao, nota) VALUES (?, ?, ?, ?, ?, ?)";
//
//        // USO DE TRY-WITH-RESOURCES: Conexão e Statement são fechados automaticamente.
//        try (Connection conexao = dataSource.getConnection();
//             PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//
//            stmt.setString(1, avaliacao.getNome());
//            stmt.setString(2, avaliacao.getDia());
//            stmt.setString(3, avaliacao.getMes());
//            stmt.setString(4, avaliacao.getAno());
//            stmt.setString(5, avaliacao.getAvaliacao());
//            stmt.setInt(6, avaliacao.getNota());
//
//            stmt.execute();
//            return "Avaliacao cadastrada com sucesso!";
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    // No AvaliacaoDAO.java
    public String inserir(Avaliacao avaliacao) throws SQLException {
        // Adicionamos a coluna 'nota' que você criou no banco
        String sql = "INSERT INTO avaliacao (nome, dia, mes, ano, avaliacao, nota) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, avaliacao.getNome());
            stmt.setString(2, avaliacao.getDia());
            stmt.setString(3, avaliacao.getMes());
            stmt.setString(4, avaliacao.getAno());
            stmt.setString(5, avaliacao.getAvaliacao());
            stmt.setInt(6, avaliacao.getNota());

            stmt.executeUpdate();
            return "Sucesso"; // Retorno simples para o BO

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Delete
    public String deletar(String id) throws SQLException {
        String sql = "Delete from avaliacao where id = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.execute();
            return  "Avaliacao deletada com sucesso!";

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // UpDate
    public String atualizar(Avaliacao avaliacao) throws SQLException {
        String sql = "Update avaliacao set NOME = ?, DIA = ?, MES = ?, ANO = ?, AVALIACAO = ?, NOTA = ? where ID = ?";

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, avaliacao.getNome());
            stmt.setString(2, avaliacao.getDia());
            stmt.setString(3, avaliacao.getMes());
            stmt.setString(4, avaliacao.getAno());
            stmt.setString(5, avaliacao.getAvaliacao());
            stmt.setInt(6, avaliacao.getNota());
            stmt.setString(7, avaliacao.getId());

            stmt.executeUpdate();
            return "Avaliacao atualizada com sucesso!";

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Select
//    public List<Avaliacao> selecionar() throws SQLException {
//        List<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();
//        String sql = "select * from avaliacao";
//
//        try (Connection conexao = dataSource.getConnection();
//             PreparedStatement stmt = conexao.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while(rs.next()){
//                Avaliacao avaliacao = new Avaliacao();
//                avaliacao.setNome(rs.getString(1));
//                avaliacao.setDia(rs.getString(2));
//                avaliacao.setMes(rs.getString(3));
//                avaliacao.setAno(rs.getString(4));
//                avaliacao.setAvaliacao(rs.getString(5));
//                avaliacao.setNota(rs.getInt(6));
//                listaAvaliacao.add(avaliacao);
//            }
//
//            return listaAvaliacao;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }


    public List<Avaliacao> selecionar() throws SQLException {
        List<Avaliacao> listaAvaliacao = new ArrayList<>();
        String sql = "SELECT id, nome, dia, mes, ano, avaliacao, nota FROM avaliacao"; // Liste as colunas explicitamente

        try (Connection conexao = dataSource.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                Avaliacao avaliacao = new Avaliacao();
                // Use nomes de colunas em vez de números para evitar erros de índice
                avaliacao.setId(rs.getString("id"));
                avaliacao.setNome(rs.getString("nome"));
                avaliacao.setDia(rs.getString("dia"));
                avaliacao.setMes(rs.getString("mes"));
                avaliacao.setAno(rs.getString("ano"));
                avaliacao.setAvaliacao(rs.getString("avaliacao"));
                // Se a coluna 'nota' existir no MySQL, mantenha abaixo.
                // Se não existir, remova esta linha:
                avaliacao.setNota(rs.getInt("nota"));

                listaAvaliacao.add(avaliacao);
            }
            return listaAvaliacao;
        }
    }
}