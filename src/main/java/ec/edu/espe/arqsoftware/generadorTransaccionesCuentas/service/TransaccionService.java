/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.service;

import ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.dao.ClienteProductoPasivoRepository;
import ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.generator.Generador;
import ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.model.ClienteProductoPasivo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author valen
 */

@Service
@Slf4j
public class TransaccionService {
    @Autowired
    private ClienteProductoPasivoRepository clienteProdRepo;
    
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    
    public void generador(int numeroTransacciones, int tiempo){
        
        List<ClienteProductoPasivo> clienteProductoPasivo = this.clienteProdRepo.findAll();
        log.info("GENERANDO");
        
        Generador generador1 = new Generador(kafkaTemplate, numeroTransacciones / 2, clienteProductoPasivo, tiempo);
        Generador generador2 = new Generador(kafkaTemplate, (numeroTransacciones / 2)+(numeroTransacciones % 2), clienteProductoPasivo, tiempo);
        
         generador1.start();
        try {
            generador1.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(TransaccionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        generador2.start();
                
    }
}
