package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest
{
    private Combo combo;

    @BeforeEach
    void setUp()
    {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 10000));
        items.add(new ProductoMenu("Papas", 5000));
        combo = new Combo("Combo Especial", 0.1, items);
    }

    @Test
    void testGetNombre()
    {
        assertEquals("Combo Especial", combo.getNombre());
    }

    @Test
    void testGetPrecioConDescuento()
    {
        assertEquals(13500, combo.getPrecio());
    }

    @Test
    void testGenerarTextoFactura()
    {
        String t = combo.generarTextoFactura();
        assertTrue(t.contains("Combo Combo Especial"));
        assertTrue(t.contains("Descuento: 0.1"));
        assertTrue(t.contains("13500"));
    }
}

