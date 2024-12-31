/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.CtaCteDTO;
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
public class CtaCteServiceTest {
    
    public CtaCteServiceTest() {
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
     * Test of registrarCtaCte method, of class CtaCteService.
     */
    @Test
    public void testRegistrarCtaCte() {
        System.out.println("registrarCtaCte");
        CtaCteDTO ctacte = null;
        CtaCteService instance = new CtaCteService();
        instance.registrarCtaCte(ctacte);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarCtaCte method, of class CtaCteService.
     */
    @Test
    public void testActualizarCtaCte() {
        System.out.println("actualizarCtaCte");
        CtaCteDTO ctacte = null;
        CtaCteService instance = new CtaCteService();
        instance.actualizarCtaCte(ctacte);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarCtaCte method, of class CtaCteService.
     */
    @Test
    public void testEliminarCtaCte() {
        System.out.println("eliminarCtaCte");
        CtaCteDTO ctacte = null;
        CtaCteService instance = new CtaCteService();
        instance.eliminarCtaCte(ctacte);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verCtaCte method, of class CtaCteService.
     */
    @Test
    public void testVerCtaCte() {
        System.out.println("verCtaCte");
        CtaCteService instance = new CtaCteService();
        List<CtaCteDTO> expResult = null;
        List<CtaCteDTO> result = instance.verCtaCte();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
