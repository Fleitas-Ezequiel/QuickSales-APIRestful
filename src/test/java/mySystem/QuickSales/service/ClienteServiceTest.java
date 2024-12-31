/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.ClienteDTO;
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
public class ClienteServiceTest {
    
    public ClienteServiceTest() {
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
     * Test of registrarCliente method, of class ClienteService.
     */
    @Test
    public void testRegistrarCliente() {
        System.out.println("registrarCliente");
        ClienteDTO cliente = null;
        ClienteService instance = new ClienteService();
        instance.registrarCliente(cliente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarCliente method, of class ClienteService.
     */
    @Test
    public void testActualizarCliente() {
        System.out.println("actualizarCliente");
        ClienteDTO cliente = null;
        ClienteService instance = new ClienteService();
        instance.actualizarCliente(cliente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarCliente method, of class ClienteService.
     */
    @Test
    public void testEliminarCliente() {
        System.out.println("eliminarCliente");
        ClienteDTO cliente = null;
        ClienteService instance = new ClienteService();
        instance.eliminarCliente(cliente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarClientes method, of class ClienteService.
     */
    @Test
    public void testMostrarClientes() {
        System.out.println("mostrarClientes");
        ClienteService instance = new ClienteService();
        List<ClienteDTO> expResult = null;
        List<ClienteDTO> result = instance.mostrarClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
