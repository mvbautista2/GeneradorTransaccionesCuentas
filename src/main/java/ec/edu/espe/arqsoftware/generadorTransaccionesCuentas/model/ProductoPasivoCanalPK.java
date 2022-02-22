/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author valen
 */
@Data
@Embeddable
@NoArgsConstructor
public class ProductoPasivoCanalPK implements Serializable {

    @Column(name = "cod_producto_pasivo")
    private String codProductoPasivo;

    @Column(name = "cod_canal")
    private String codCanal;

}
