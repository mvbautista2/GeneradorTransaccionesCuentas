/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author valen
 */
    @Data
@Entity
@Table(name = "transaccion")
@NoArgsConstructor
    
public class Transaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_transaccion")
    private Integer codTransaccion;
    
    @Column(name = "cuenta_salida", length = 10)
    private String cuentaSalida;
    
    @Column(name = "descripcion", length = 150)
    private String descripcion;
    
    @Column(name = "tipo", length = 3)
    private String tipo;
    
    @Column(name = "monto", precision = 18, scale = 2)
    private BigDecimal monto;
    
    @Column(name = "saldo_anterior", precision = 18, scale = 2)
    private BigDecimal saldoAnterior;
    
    @Column(name = "saldo_actual", precision = 18, scale = 2)
    private BigDecimal saldoActual;
    
    @Column(name = "fecha")
    private LocalDateTime fecha;
    
    @JoinColumn(name = "cuenta_id", referencedColumnName = "cuenta_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClienteProductoPasivo clienteProductoPasivo;
}
