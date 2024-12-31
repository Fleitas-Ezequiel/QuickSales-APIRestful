/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.ProveedorDTO;
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
public class ProveedorServiceTest {
    
    public ProveedorServiceTest() {
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
     * Test of registrarProveedor method, of class ProveedorService.
     */
    @Test
    public void testRegistrarProveedor() {
        System.out.println("registrarProveedor");
        ProveedorDTO proveedor = null;
        ProveedorService instance = new ProveedorService();
        instance.registrarProveedor(proveedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarProveedor method, of class ProveedorService.
     */
    @Test
    public void testModificarProveedor() {
        System.out.println("modificarProveedor");
        ProveedorDTO proveedor = null;
        ProveedorService instance = new ProveedorService();
        instance.modificarProveedor(proveedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarProveedor method, of class ProveedorService.
     */
    @Test
    public void testEliminarProveedor() {
        System.out.println("eliminarProveedor");
        ProveedorDTO proveedor = null;
        ProveedorService instance = new ProveedorService();
        instance.eliminarProveedor(proveedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verProveedor method, of class ProveedorService.
     */
    @Test
    public void testVerProveedor() {
        System.out.println("verProveedor");
        ProveedorService instance = new ProveedorService();
        List<ProveedorDTO> expResult = null;
        List<ProveedorDTO> result = instance.verProveedor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verProveedoresFiltrado method, of class ProveedorService.
     */
    @Test
    public void testVerProveedoresFiltrado() {
        System.out.println("verProveedoresFiltrado");
        String filtrado = "";
        ProveedorService instance = new ProveedorService();
        List<ProveedorDTO> expResult = null;
        List<ProveedorDTO> result = instance.verProveedoresFiltrado(filtrado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
