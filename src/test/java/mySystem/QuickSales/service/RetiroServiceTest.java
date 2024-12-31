/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.RetiroDTO;
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
public class RetiroServiceTest {
    
    public RetiroServiceTest() {
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
     * Test of registrarRetiro method, of class RetiroService.
     */
    @Test
    public void testRegistrarRetiro() {
        System.out.println("registrarRetiro");
        RetiroDTO retiro = null;
        RetiroService instance = new RetiroService();
        instance.registrarRetiro(retiro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarRetiro method, of class RetiroService.
     */
    @Test
    public void testModificarRetiro() {
        System.out.println("modificarRetiro");
        RetiroDTO retiro = null;
        RetiroService instance = new RetiroService();
        instance.modificarRetiro(retiro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarRetiro method, of class RetiroService.
     */
    @Test
    public void testEliminarRetiro() {
        System.out.println("eliminarRetiro");
        RetiroDTO retiro = null;
        RetiroService instance = new RetiroService();
        instance.eliminarRetiro(retiro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verRetiro method, of class RetiroService.
     */
    @Test
    public void testVerRetiro() {
        System.out.println("verRetiro");
        RetiroService instance = new RetiroService();
        List<RetiroDTO> expResult = null;
        List<RetiroDTO> result = instance.verRetiro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
