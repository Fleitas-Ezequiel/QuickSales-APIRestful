/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.VentaDTO;
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
public class VentaServiceTest {
    
    public VentaServiceTest() {
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
     * Test of registrarVenta method, of class VentaService.
     */
    @Test
    public void testRegistrarVenta() {
        System.out.println("registrarVenta");
        VentaDTO venta_dto = null;
        VentaService instance = new VentaService();
        instance.registrarVenta(venta_dto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarVenta method, of class VentaService.
     */
    @Test
    public void testModificarVenta() {
        System.out.println("modificarVenta");
        VentaDTO venta = null;
        VentaService instance = new VentaService();
        instance.modificarVenta(venta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarVenta method, of class VentaService.
     */
    @Test
    public void testEliminarVenta() {
        System.out.println("eliminarVenta");
        VentaDTO venta_dto = null;
        VentaService instance = new VentaService();
        instance.eliminarVenta(venta_dto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verVenta method, of class VentaService.
     */
    @Test
    public void testVerVenta() {
        System.out.println("verVenta");
        VentaService instance = new VentaService();
        List<VentaDTO> expResult = null;
        List<VentaDTO> result = instance.verVenta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
