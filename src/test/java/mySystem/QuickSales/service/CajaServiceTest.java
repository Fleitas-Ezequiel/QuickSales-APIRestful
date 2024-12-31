/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.CajaDTO;
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
public class CajaServiceTest {
    
    public CajaServiceTest() {
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
     * Test of registrarCaja method, of class CajaService.
     */
    @Test
    public void testRegistrarCaja() {
        System.out.println("registrarCaja");
        CajaDTO caja = null;
        CajaService instance = new CajaService();
        instance.registrarCaja(caja);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarCaja method, of class CajaService.
     */
    @Test
    public void testModificarCaja() {
        System.out.println("modificarCaja");
        CajaDTO caja_dto = null;
        CajaService instance = new CajaService();
        instance.modificarCaja(caja_dto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarCaja method, of class CajaService.
     */
    @Test
    public void testEliminarCaja() {
        System.out.println("eliminarCaja");
        CajaDTO caja = null;
        CajaService instance = new CajaService();
        instance.eliminarCaja(caja);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verCajas method, of class CajaService.
     */
    @Test
    public void testVerCajas() {
        System.out.println("verCajas");
        CajaService instance = new CajaService();
        List<CajaDTO> expResult = null;
        List<CajaDTO> result = instance.verCajas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
