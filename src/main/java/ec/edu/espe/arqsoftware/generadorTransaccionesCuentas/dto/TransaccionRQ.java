/*
 * Copyright (c) 2021 yazbe.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransaccionRQ {

    private String cuentaId;

    private String cuentaSalida;

    private String descripcion;

    private String tipo;

    private float monto;
    
}
