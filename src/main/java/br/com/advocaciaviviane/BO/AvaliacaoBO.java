package br.com.advocaciaviviane.BO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.advocaciaviviane.beans.Avaliacao;
import br.com.advocaciaviviane.DAO.AvaliacaoDAO;

// 💡 Adicione as importações de injeção e escopo
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoBO {

    // ✅ CORREÇÃO: INJETA a instância gerenciada do DAO
    @Inject
    AvaliacaoDAO avaliacaoDAO;

    // Selecionar
    public List<Avaliacao> selecionarBo() throws ClassNotFoundException, SQLException {
        // Usa o DAO injetado
        return avaliacaoDAO.selecionar();
    }

    // Inserir
    public String inserirBo(Avaliacao avaliacao) throws ClassNotFoundException, SQLException {
        // Usa o DAO injetado
        return avaliacaoDAO.inserir(avaliacao);
    }

    // Atualizar
    public void atualizarBo (Avaliacao avaliacao) throws ClassNotFoundException, SQLException {
        // Usa o DAO injetado
        avaliacaoDAO.atualizar(avaliacao);
    }

    // Deletar
    public void deletarBo (String id) throws ClassNotFoundException, SQLException {
        // Usa o DAO injetado
        avaliacaoDAO.deletar(id);
    }
}