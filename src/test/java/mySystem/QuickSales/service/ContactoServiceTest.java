/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.ContactoDTO;
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
public class ContactoServiceTest {
    
    public ContactoServiceTest() {
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
     * Test of registrarContacto method, of class ContactoService.
     */
    @Test
    public void testRegistrarContacto() {
        System.out.println("registrarContacto");
        ContactoDTO contacto = null;
        ContactoService instance = new ContactoService();
        instance.registrarContacto(contacto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarContacto method, of class ContactoService.
     */
    @Test
    public void testActualizarContacto() {
        System.out.println("actualizarContacto");
        ContactoDTO contacto_dto = null;
        ContactoService instance = new ContactoService();
        instance.actualizarContacto(contacto_dto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarContacto method, of class ContactoService.
     */
    @Test
    public void testEliminarContacto(int id_contacto) {
        System.out.println("eliminarContacto");
        ContactoService instance = new ContactoService();
        instance.eliminarContacto(id_contacto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verContactos method, of class ContactoService.
     */
    @Test
    public void testVerContactos() {
        System.out.println("verContactos");
        ContactoService instance = new ContactoService();
        List<ContactoDTO> expResult = null;
        List<ContactoDTO> result = instance.verContactos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarPorProveedor method, of class ContactoService.
     */
    @Test
    public void testListarPorProveedor() {
        System.out.println("listarPorProveedor");
        int id_proveedor = 0;
        ContactoService instance = new ContactoService();
        List<ContactoDTO> expResult = null;
        List<ContactoDTO> result = instance.listarPorProveedor(id_proveedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
