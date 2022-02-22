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
//    public List<Transaccion> transaccionList;

    @Override
    public void run() {

        int numeroCuentas = clienteProductoPasivoList.size();

        Random random = new Random();

        List<String> descripciones = new ArrayList<>();

        descripciones.add("Pago de deuda");
        descripciones.add("Pago de alimentación");
        descripciones.add("Pago de supermercados");
        descripciones.add("Pago de servicios básicos");

        int numDescripciones = descripciones.size();

        for (int i = 0; i < numeroTransacciones; i++) {
            int nDescripcion = random.nextInt(numDescripciones) + 0;
            int nCuentas = random.nextInt(numeroCuentas) + 0;
            String tipo = "";
            if (random.nextBoolean()) {
                tipo = "DEP";
            } else {
                tipo = "RET";
            }
            if (random.nextBoolean()) {
                tipo = "TRI";
            } else {
                tipo = "TRO";
            }
            if (random.nextBoolean()) {
                tipo = "PAI";
            }
            float monto = 0;
            if (clienteProductoPasivoList.get(nCuentas).getSaldoDisponible().intValue() != 0) {
                monto = (clienteProductoPasivoList.get(nCuentas).getSaldoDisponible().intValue()) + 10;
            }
//            log.info("monto:{}", clienteProductoPasivoList.get(nCuentas).getSaldoDisponible().intValue());

            TransaccionRQ transaccionRQ = TransaccionRQ.builder()
                    .cuentaId(clienteProductoPasivoList.get(nCuentas).getCuentaId())
                    .cuentaSalida(clienteProductoPasivoList.get(nCuentas).getCodCliente())
                    .descripcion(descripciones.get(nDescripcion))
                    .monto(monto)
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
