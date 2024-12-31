/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.MetodoPagoDTO;
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
public class MetodoPagoServiceTest {
    
    public MetodoPagoServiceTest() {
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
     * Test of registrarMetodoPago method, of class MetodoPagoService.
     */
    @Test
    public void testRegistrarMetodoPago() {
        System.out.println("registrarMetodoPago");
        MetodoPagoDTO metodo = null;
        MetodoPagoService instance = new MetodoPagoService();
        instance.registrarMetodoPago(metodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarMetodoPago method, of class MetodoPagoService.
     */
    @Test
    public void testModificarMetodoPago() {
        System.out.println("modificarMetodoPago");
        MetodoPagoDTO metodo = null;
        MetodoPagoService instance = new MetodoPagoService();
        instance.modificarMetodoPago(metodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarMetodoPago method, of class MetodoPagoService.
     */
    @Test
    public void testEliminarMetodoPago() {
        System.out.println("eliminarMetodoPago");
        MetodoPagoDTO metodo = null;
        MetodoPagoService instance = new MetodoPagoService();
        instance.eliminarMetodoPago(metodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verMetodoPago method, of class MetodoPagoService.
     */
    @Test
    public void testVerMetodoPago() {
        System.out.println("verMetodoPago");
        MetodoPagoService instance = new MetodoPagoService();
        List<MetodoPagoDTO> expResult = null;
        List<MetodoPagoDTO> result = instance.verMetodoPago();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
