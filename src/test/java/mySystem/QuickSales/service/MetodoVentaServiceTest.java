/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.MetodoVentaDTO;
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
public class MetodoVentaServiceTest {
    
    public MetodoVentaServiceTest() {
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
     * Test of registrarMetodoVenta method, of class MetodoVentaService.
     */
    @Test
    public void testRegistrarMetodoVenta() {
        System.out.println("registrarMetodoVenta");
        MetodoVentaDTO metodo = null;
        MetodoVentaService instance = new MetodoVentaService();
        instance.registrarMetodoVenta(metodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarMetodoVenta method, of class MetodoVentaService.
     */
    @Test
    public void testActualizarMetodoVenta() {
        System.out.println("actualizarMetodoVenta");
        MetodoVentaDTO metodo = null;
        MetodoVentaService instance = new MetodoVentaService();
        instance.actualizarMetodoVenta(metodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarMetodoVenta method, of class MetodoVentaService.
     */
    @Test
    public void testEliminarMetodoVenta() {
        System.out.println("eliminarMetodoVenta");
        MetodoVentaDTO metodo = null;
        MetodoVentaService instance = new MetodoVentaService();
        instance.eliminarMetodoVenta(metodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verMetodoVenta method, of class MetodoVentaService.
     */
    @Test
    public void testVerMetodoVenta() {
        System.out.println("verMetodoVenta");
        MetodoVentaService instance = new MetodoVentaService();
        List<MetodoVentaDTO> expResult = null;
        List<MetodoVentaDTO> result = instance.verMetodoVenta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
