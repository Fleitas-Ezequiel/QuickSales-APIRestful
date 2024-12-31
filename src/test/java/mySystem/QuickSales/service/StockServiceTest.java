/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.StockDTO;
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
public class StockServiceTest {
    
    public StockServiceTest() {
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
     * Test of registrarDeposito method, of class StockService.
     */
    @Test
    public void testRegistrarDeposito() {
        System.out.println("registrarDeposito");
        StockDTO deposito_dto = null;
        StockService instance = new StockService();
        instance.registrarStock(deposito_dto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarDeposito method, of class StockService.
     */
    @Test
    public void testActualizarDeposito() {
        System.out.println("actualizarDeposito");
        StockDTO deposito_dto = null;
        StockService instance = new StockService();
        instance.actualizarStock(deposito_dto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarDesposito method, of class StockService.
     */
    @Test
    public void testEliminarDesposito() {
        System.out.println("eliminarDesposito");
        StockDTO deposito_dto = null;
        StockService instance = new StockService();
        instance.eliminarStock(deposito_dto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verDeposito method, of class StockService.
     */
    @Test
    public void testVerDeposito() {
        System.out.println("verDeposito");
        StockService instance = new StockService();
        List<StockDTO> expResult = null;
        List<StockDTO> result = instance.verStock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
