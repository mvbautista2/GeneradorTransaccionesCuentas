/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author valen
 */
@Data
@Entity
@Table(name = "producto_pasivo")
@NoArgsConstructor
public class ProductoPasivo implements Serializable {

    @Id
    @Column(name = "cod_producto_pasivo", length = 3)
    private String codProductoPasivo;

    @Column(name = "nombre", length = 64)
    private String nombre;

    @Column(name = "tasa", precision = 3, scale = 2)
    private BigDecimal tasa;

    @Column(name = "acreditacion", length = 3)
    private String acreditacion;

    @Column(name = "saldo_inicial", precision = 18, scale = 2)
    private BigDecimal saldoInicial;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoPasivo", fetch = FetchType.LAZY)
    private List<ProductoPasivoCanal> productoPasivoCanal;

}