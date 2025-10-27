package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest
{
    private Restaurante restaurante;

    @BeforeEach
    void setUp() throws Exception
    {
        restaurante = new Restaurante();
        restaurante.cargarInformacionRestaurante(
                new File("data/ingredientes.txt"),
                new File("data/menu.txt"),
                new File("data/combos.txt"));
    }

    @Test
    void testCargaArchivos()
    {
        assertFalse(restaurante.getIngredientes().isEmpty());
        assertFalse(restaurante.getMenuBase().isEmpty());
        assertFalse(restaurante.getMenuCombos().isEmpty());
    }

    @Test
    void testIniciarPedidoDobleLanzaExcepcion() throws Exception
    {
        restaurante.iniciarPedido("Juan", "Calle 123");
        assertThrows(YaHayUnPedidoEnCursoException.class,
                () -> restaurante.iniciarPedido("Ana", "Calle 456"));
    }

    @Test
    void testCerrarYGuardarPedidoGeneraArchivo() throws Exception
    {
        Path dir = Path.of("./facturas");
        if (!Files.exists(dir)) Files.createDirectories(dir);
        restaurante.iniciarPedido("Juan", "Calle 123");
        Pedido p = restaurante.getPedidoEnCurso();
        int id = p.getIdPedido();
        restaurante.cerrarYGuardarPedido();
        File f = dir.resolve("factura_" + id + ".txt").toFile();
        assertTrue(f.exists());
        f.delete();
    }

    @Test
    void testCerrarSinPedidoLanzaExcepcion()
    {
        assertThrows(NoHayPedidoEnCursoException.class,
                () -> restaurante.cerrarYGuardarPedido());
    }

    @Test
    void testHistoricoDePedidosSeActualiza()
    {
        assertEquals(0, restaurante.getPedidos().size());
    }
}

