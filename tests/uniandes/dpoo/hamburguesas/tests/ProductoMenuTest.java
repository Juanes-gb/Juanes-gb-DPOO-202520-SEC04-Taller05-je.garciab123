package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest
{
    private ProductoMenu producto;

    @BeforeEach
    void setUp()
    {
        producto = new ProductoMenu("Hamburguesa Sencilla", 10000);
    }

    @Test
    void testGetNombre()
    {
        assertEquals("Hamburguesa Sencilla", producto.getNombre());
    }

    @Test
    void testGetPrecio()
    {
        assertEquals(10000, producto.getPrecio());
    }

    @Test
    void testGenerarTextoFactura()
    {
        String esperado = "Hamburguesa Sencilla\n            10000\n";
        assertEquals(esperado, producto.generarTextoFactura());
    }
}

