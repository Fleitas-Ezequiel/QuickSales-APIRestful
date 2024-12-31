package mySystem.QuickSales.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="descuento")
public class Descuento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_descuento;
    
    private float importe;
    
    @OneToMany(mappedBy = "descuento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stock = new ArrayList<>();
}
