package br.com.advocaciaviviane.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.advocaciaviviane.beans.Avaliacao;
import br.com.advocaciaviviane.DAO.AvaliacaoDAO;

public class AvaliacaoBO {

    AvaliacaoDAO avaliacaoDAO;

    // Selecionar
    public ArrayList<Avaliacao> selecionarBo() throws ClassNotFoundException, SQLException {
        avaliacaoDAO = new AvaliacaoDAO();
        // Regra de negocios

        return (ArrayList<Avaliacao>) avaliacaoDAO.selecionar();
    }

    // Inserir
    public String inserirBo(Avaliacao avaliacao) throws ClassNotFoundException, SQLException {
        AvaliacaoDAO usuarioDao = new AvaliacaoDAO();
        // Regra de negocios
        return usuarioDao.inserir(avaliacao);
    }

    // Atualizar
    public void atualizarBo (Avaliacao avaliacao) throws ClassNotFoundException, SQLException {
        AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
        // Regra de negocios
        avaliacaoDao.atualizar(avaliacao);
    }

    // Deletar
    public void deletarBo (String id) throws ClassNotFoundException, SQLException {
        AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
        // Regra de negocios
        avaliacaoDao.deletar(id);
    }


}