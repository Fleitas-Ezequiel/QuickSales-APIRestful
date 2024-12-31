/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.PagoDTO;
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
public class PagoServiceTest {
    
    public PagoServiceTest() {
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
     * Test of registrarPagoProveedor method, of class PagoService.
     */
    @Test
    public void testRegistrarPagoProveedor() {
        System.out.println("registrarPagoProveedor");
        PagoDTO pago = null;
        PagoService instance = new PagoService();
        instance.registrarPagoProveedor(pago);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizrPagoProveedor method, of class PagoService.
     */
    @Test
    public void testActualizrPagoProveedor() {
        System.out.println("actualizrPagoProveedor");
        PagoDTO pago = null;
        PagoService instance = new PagoService();
        instance.actualizrPagoProveedor(pago);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarPagoProveedor method, of class PagoService.
     */
    @Test
    public void testEliminarPagoProveedor() {
        System.out.println("eliminarPagoProveedor");
        PagoDTO pago = null;
        PagoService instance = new PagoService();
        instance.eliminarPagoProveedor(pago);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verPagoProveedor method, of class PagoService.
     */
    @Test
    public void testVerPagoProveedor() {
        System.out.println("verPagoProveedor");
        PagoService instance = new PagoService();
        List<PagoDTO> expResult = null;
        List<PagoDTO> result = instance.verPagoProveedor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
