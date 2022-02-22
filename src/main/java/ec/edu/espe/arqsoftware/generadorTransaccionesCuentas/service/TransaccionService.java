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
    
    public void generador(int numeroTransacciones){
        
        List<ClienteProductoPasivo> clienteProductoPasivo = this.clienteProdRepo.findAll();
        log.info("GENERANDO");
        
        long inicio = System.nanoTime();
        
        List<Generador> hilos = new ArrayList<>();
        for(int i=0;i<3;i++){
            Generador generador = new Generador(kafkaTemplate,numeroTransacciones/2,clienteProductoPasivo);
            hilos.add(generador);
        }
          
        for(Generador h: hilos){
            h.start();
        }
        
       long fin = System.nanoTime();
        log.info("Tiempo de ejecucion: {}",(fin-inicio));
        
                
    }
}
