package br.com.advocaciaviviane.excecoes;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Context
    HttpHeaders headers;

    @Override
    public Response toResponse(Exception e) {

        // NÃO intercepta preflight
        if ("OPTIONS".equalsIgnoreCase(headers.getRequestHeaders()
                .getFirst(":method"))) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .build();
    }
}

