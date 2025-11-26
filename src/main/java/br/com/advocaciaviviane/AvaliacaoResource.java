package br.com.advocaciaviviane;

import br.com.advocaciaviviane.beans.Avaliacao;
import br.com.advocaciaviviane.BO.AvaliacaoBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.inject.Inject; // Importação essencial para injeção

// @Provider é geralmente desnecessário para Resources JAX-RS no Quarkus
@Path("/avaliacao")
public class AvaliacaoResource {

    // ✅ CORREÇÃO: INJETA a instância gerenciada do BO
    @Inject
    private AvaliacaoBO avaliacaoBO;

    // Selecionar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Avaliacao> selecionarRs() throws ClassNotFoundException, SQLException {
        return  (ArrayList<Avaliacao>)  avaliacaoBO.selecionarBo();
    }

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Avaliacao avaliacao, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        // REMOVIDO: Instanciação manual do BO

        // ALTERAÇÃO: Chama o inserir e armazena o ID gerado
        String idGerado = avaliacaoBO.inserirBo(avaliacao);

        // Verifica se um ID foi gerado
        if (idGerado == null) {
            return Response.serverError().entity("Falha ao obter ID gerado.").build();
        }

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(idGerado);
        return Response.created(builder.build()).build();
    }

    // Atualizar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(Avaliacao avaliacao, @PathParam("id") String id) throws ClassNotFoundException, SQLException {
        avaliacaoBO.atualizarBo(avaliacao);
        return Response.ok().build();
    }

    // Deletar
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRs(@PathParam("id") String id) throws ClassNotFoundException, SQLException {
        avaliacaoBO.deletarBo(id);
        return Response.ok().build();
    }
}