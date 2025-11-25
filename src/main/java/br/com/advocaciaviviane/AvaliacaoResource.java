package br.com.advocaciaviviane;

import br.com.advocaciaviviane.beans.Avaliacao;
import br.com.advocaciaviviane.BO.AvaliacaoBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;


@Provider

@Path("/avaliacao")
public class AvaliacaoResource {

    private AvaliacaoBO avaliacaoBO = new AvaliacaoBO();

    // Selecionar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Avaliacao> selecionarRs() throws ClassNotFoundException, SQLException, SQLException {
        return  (ArrayList<Avaliacao>)  avaliacaoBO.selecionarBo();
    }

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Avaliacao avaliacao, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        AvaliacaoBO avaliacaoBo = new AvaliacaoBO();

        // ALTERAÇÃO: Chama o inserir e armazena o ID gerado
        String idGerado = avaliacaoBo.inserirBo(avaliacao);

        // Verifica se um ID foi gerado
        if (idGerado == null) {
            // Caso a inserção falhe por algum motivo (embora o DAO devesse lançar exceção)
            return Response.serverError().entity("Falha ao obter ID gerado.").build();
        }

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        // CORREÇÃO: Usa o ID gerado pelo banco de dados para construir a URL
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