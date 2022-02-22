/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.controller;

import ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.service.TransaccionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author valen
 */
@Slf4j
@RestController
@RequestMapping("/api/v2/cuentas/transacciones")
public class GeneradorController {
    
     @Autowired
     private TransaccionService transaccionService;
     
     @GetMapping(value = "/{numeroTransacciones}")
     public ResponseEntity generar(@PathVariable int numeroTransacciones){
         this.transaccionService.generador(numeroTransacciones);
         return ResponseEntity.ok().build();
     }
     
}
