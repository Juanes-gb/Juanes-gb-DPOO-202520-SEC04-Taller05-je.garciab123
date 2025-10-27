package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest
{
    private Pedido pedido;

    @BeforeEach
    void setUp()
    {
        pedido = new Pedido("Juan", "Calle 123");
    }

    @Test
    void testGetPrecioTotalPedido()
    {
        pedido.agregarProducto(new ProductoMenu("Hamburguesa", 10000));
        pedido.agregarProducto(new ProductoMenu("Gaseosa", 4000));
        assertEquals(16660, pedido.getPrecioTotalPedido());
    }

    @Test
    void testGenerarTextoFactura()
    {
        pedido.agregarProducto(new ProductoMenu("Hamburguesa", 10000));
        pedido.agregarProducto(new ProductoMenu("Gaseosa", 4000));
        String t = pedido.generarTextoFactura();
        assertTrue(t.contains("Cliente: Juan"));
        assertTrue(t.contains("Dirección: Calle 123"));
        assertTrue(t.contains("Precio Neto:  14000"));
        assertTrue(t.contains("IVA:          2660"));
        assertTrue(t.contains("Precio Total: 16660"));
    }

    @Test
    void testGuardarFactura() throws IOException
    {
        pedido.agregarProducto(new ProductoMenu("Hamburguesa", 10000));
        File tmp = File.createTempFile("factura_test_", ".txt");
        tmp.deleteOnExit();
        pedido.guardarFactura(tmp);
        assertTrue(tmp.exists());
        String contenido = Files.readString(tmp.toPath());
        assertTrue(contenido.contains("Precio Total:"));
    }
}

