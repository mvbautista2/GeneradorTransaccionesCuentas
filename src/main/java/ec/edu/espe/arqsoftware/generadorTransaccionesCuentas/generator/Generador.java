    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.generator;

import ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.dto.TransaccionRQ;
import ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.model.ClienteProductoPasivo;
import ec.edu.espe.arqsoftware.generadorTransaccionesCuentas.model.Transaccion;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

/**
 *
 * @author valen
 */
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Generador extends Thread {

    private KafkaTemplate<String, Object> kafkaTemplate;

    private int numeroTransacciones;

    public List<ClienteProductoPasivo> clienteProductoPasivoList;
    
    public int tiempo;
//    public List<Transaccion> transaccionList;

    @Override
    public void run() {

        int numeroCuentas = clienteProductoPasivoList.size();
        
        float espera = (((float)tiempo/((float)numeroTransacciones-1))*1000);
        log.info("Duracion hilo: {}",espera/1000);

        Random random = new Random();

        List<String> descripciones = new ArrayList<>();

        descripciones.add("Pago de deuda");
        descripciones.add("Pago de alimentación");
        descripciones.add("Pago de supermercados");
        descripciones.add("Pago de servicios básicos");

        int numDescripciones = descripciones.size();

        for (int i = 0; i < numeroTransacciones; i++) {
            int nDescripcion = random.nextInt(numDescripciones) + 0;
            int nCuenta = random.nextInt(numeroCuentas) + 0;
            String tipo = "";
            if (random.nextBoolean()) {
                tipo = "DEP";
            } else {
                tipo = "RET";
            }
            
//            float monto = 0;
//            if (clienteProductoPasivoList.get(nCuenta).getSaldoDisponible().intValue() != 0) {
//                monto = (random.nextInt(clienteProductoPasivoList.get(nCuenta).getSaldoDisponible().intValue()) + 10);
//            }
//            log.info("monto:{}", clienteProductoPasivoList.get(nCuentas).getSaldoDisponible().intValue());

            TransaccionRQ transaccionRQ = TransaccionRQ.builder()
                    .cuentaId(clienteProductoPasivoList.get(nCuenta).getCuentaId())
                    .cuentaSalida(clienteProductoPasivoList.get(nCuenta).getCodCliente())
                    .descripcion("DEP".equals(tipo)?descripciones.get(nDescripcion):"Retiro de dinero")
                    .monto(random.nextInt(90)+10)
                    .tipo(tipo)
                    .build();
            log.info("Info:{}", transaccionRQ.toString());

            kafkaTemplate.send("lbyl3ivs-tarjetas", transaccionRQ);
            log.info("Pasa Kafka: {}", transaccionRQ);
//             log.info("NCuentas{}", nCuentas);
//             log.info("NDescripcion{}",nDescripcion);

        }

    }

}
