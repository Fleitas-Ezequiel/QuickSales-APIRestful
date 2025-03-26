/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.ProductoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ezequiel
 */
public class ProductoServiceTest {
    
    public ProductoServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of registrarProducto method, of class ProductoService.
     */
    @Test
    public void testRegistrarProducto() {
        System.out.println("registrarProducto");
        ProductoDTO producto = null;
        ProductoService instance = new ProductoService();
        instance.registrarProducto(producto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarProducto method, of class ProductoService.
     */
    @Test
    public void testModificarProducto() {
        System.out.println("modificarProducto");
        ProductoDTO producto = null;
        ProductoService instance = new ProductoService();
        instance.modificarProducto(producto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarProducto method, of class ProductoService.
     */
    @Test
    public void testEliminarProducto() {
        System.out.println("eliminarProducto");
        int producto = 0;
        ProductoService instance = new ProductoService();
        instance.eliminarProducto(producto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verProducto method, of class ProductoService.
     */
    @Test
    public void testVerProducto() {
        System.out.println("verProducto");
        ProductoService instance = new ProductoService();
        List<ProductoDTO> expResult = null;
        List<ProductoDTO> result = instance.verProducto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
