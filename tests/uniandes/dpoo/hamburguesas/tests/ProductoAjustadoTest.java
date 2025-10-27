package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest
{
    private ProductoMenu base;
    private ProductoAjustado ajustado;

    @SuppressWarnings("unchecked")
    private ArrayList<Ingrediente> getLista(Object target, String field)
    {
        try
        {
            Field f = target.getClass().getDeclaredField(field);
            f.setAccessible(true);
            return (ArrayList<Ingrediente>) f.get(target);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private void addAgregado(Ingrediente ing)
    {
        getLista(ajustado, "agregados").add(ing);
    }

    private void addEliminado(Ingrediente ing)
    {
        getLista(ajustado, "eliminados").add(ing);
    }

    @BeforeEach
    void setUp()
    {
        base = new ProductoMenu("Hamburguesa Base", 12000);
        ajustado = new ProductoAjustado(base);
    }

    @Test
    void testGetNombre()
    {
        assertEquals("Hamburguesa Base", ajustado.getNombre());
    }

    @Test
    void testGetPrecioSinAgregados()
    {
        assertEquals(12000, ajustado.getPrecio());
    }

    @Test
    void testGetPrecioConAgregados()
    {
        addAgregado(new Ingrediente("queso", 1500));
        addAgregado(new Ingrediente("tocineta", 2000));
        assertEquals(15500, ajustado.getPrecio());
    }

    @Test
    void testGenerarTextoFacturaSinAgregados()
    {
        String txt = ajustado.generarTextoFactura();
        assertTrue(txt.contains("Hamburguesa Base"));
        assertTrue(txt.contains("12000"));
    }

    @Test
    void testGenerarTextoFacturaConAgregadosYEliminados()
    {
        addAgregado(new Ingrediente("queso", 1500));
        addEliminado(new Ingrediente("tomate", 0));
        String txt = ajustado.generarTextoFactura();
        assertTrue(txt.contains("+queso"));
        assertTrue(txt.contains("-tomate"));
        assertTrue(txt.contains("13500"));
    }
}

