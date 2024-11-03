package  py.edu.unasur.resources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jdk.jfr.Timestamp;
import py.edu.unasur.resources.SegundoParcialResource;
import py.edu.unasur.services.SegundoParcialService;

public class SegundoParcialResourceTest {

    @Mock   
    private SegundoParcialService segundoParcialService;

    @InjectMocks
    private SegundoParcialResource segundoParcialResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcesarLista() {
        List<String> lista = Arrays.asList("radar", "level", "hello");
        
        // Configuración del comportamiento del mock
        when(segundoParcialService.contarPalindromos(lista)).thenReturn(2);
        when(segundoParcialService.obtenerPalindromos(lista)).thenReturn(Arrays.asList("radar", "level"));

        // Ejecuta el método a probar
        Response response = segundoParcialResource.procesarLista(lista);
        
        // Verifica el estado de la respuesta
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        // Obtiene y verifica el resultado
        SegundoParcialResource.Resultado resultado = (SegundoParcialResource.Resultado) response.getEntity();
        assertEquals(2, resultado.TotalPalindromos);
        assertEquals(Arrays.asList("radar", "level"), resultado.ListaPalindromos);
    }
}
