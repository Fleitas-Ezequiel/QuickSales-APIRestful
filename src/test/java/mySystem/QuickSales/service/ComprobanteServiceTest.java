package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.DTO.ComprobanteDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComprobanteServiceTest {
    
    public ComprobanteServiceTest() {
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
     * Test of registrarBoleta method, of class ComprobanteService.
     */
    @Test
    public void testRegistrarBoleta() {
        System.out.println("registrarBoleta");
        ComprobanteDTO boleta = null;
        ComprobanteService instance = new ComprobanteService();
        instance.registrarComprobante(boleta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarBoleta method, of class ComprobanteService.
     */
    @Test
    public void testActualizarBoleta() {
        System.out.println("actualizarBoleta");
        ComprobanteDTO boleta = null;
        ComprobanteService instance = new ComprobanteService();
        instance.actualizarComprobante(boleta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarBoleta method, of class ComprobanteService.
     */
    @Test
    public void testEliminarBoleta() {
        System.out.println("eliminarBoleta");
        String id_comprobante = "";
        ComprobanteService instance = new ComprobanteService();
        instance.eliminarComprobante(id_comprobante);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verBoletas method, of class ComprobanteService.
     */
    @Test
    public void testVerBoletas() {
        System.out.println("verBoletas");
        ComprobanteService instance = new ComprobanteService();
        List<ComprobanteDTO> expResult = null;
        List<ComprobanteDTO> result = instance.verComprobantes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
