package py.edu.unasur.resources;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.edu.unasur.services.SegundoParcialService;

@Path("/segundo-parcial")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SegundoParcialResource {

    @Inject
    SegundoParcialService segundoParcialService;

    @POST
    public Response procesarLista(List<String> lista) {
        if (lista == null || lista.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("La lista no puede estar vacía o ser nula.")
                           .build();
        }

        for (String str : lista) {
            if (str == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Los elementos de la lista no pueden ser nulos.")
                               .build();
            }
        }

        int totalPalindromos = segundoParcialService.contarPalindromos(lista);
        List<String> listaPalindromos = segundoParcialService.obtenerPalindromos(lista);

        Resultado respuesta = new Resultado(totalPalindromos, listaPalindromos);
        return Response.ok(respuesta).build();
    }

    public static class Resultado {
        public int TotalPalindromos;
        public List<String> ListaPalindromos;

        public Resultado(int totalPalindromos, List<String> listaPalindromos) {
            TotalPalindromos = totalPalindromos;
            ListaPalindromos = listaPalindromos;
        }
    }
}
