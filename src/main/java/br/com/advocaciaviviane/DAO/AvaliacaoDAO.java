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

        return "Avaliacao cadastrada com sucesso!";
    }

    // Delete
    public String deletar(String id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("Delete from avaliacao where id = ?");
        stmt.setString(1, id);

        stmt.execute();
        stmt.close();

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
        return listaAvaliacao;
    }


}