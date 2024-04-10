package com.pweb.tiendaonline.controllers;

import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioDto;
import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.pweb.tiendaonline.exceptions.DetalleEnvioNotFoundException;
import com.pweb.tiendaonline.services.DetalleEnvioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
public class DetalleEnvioController {

    private final DetalleEnvioService detalleEnvioService;

    public DetalleEnvioController(DetalleEnvioService detalleEnvioService) {
        this.detalleEnvioService = detalleEnvioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleEnvioDto> getDetalleEnvioById(@PathVariable("id") Long id) {
        try {
            DetalleEnvioDto detalleEnvioDto = detalleEnvioService.findDetalleEnvioById(id);
            return ResponseEntity.ok().body(detalleEnvioDto);
        } catch (DetalleEnvioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DetalleEnvioDto>> getAllDetallesEnvio() {
        List<DetalleEnvioDto> detallesEnvioDto = detalleEnvioService.findAllDetallesEnvio();
        return ResponseEntity.ok().body(detallesEnvioDto);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<DetalleEnvioDto>> getDetallesEnvioByOrderId(@PathVariable("orderId") Long orderId) {
        List<DetalleEnvioDto> detallesEnvioDto = detalleEnvioService.findDetalleEnvioByPedidoId(orderId);
        return ResponseEntity.ok().body(detallesEnvioDto);
    }

    @GetMapping("/carrier")
    public ResponseEntity<List<DetalleEnvioDto>> getDetallesEnvioByNumeroGuia(@RequestParam("numeroGuia") String numeroGuia) {
        List<DetalleEnvioDto> detallesEnvioDto = detalleEnvioService.findDetalleEnvioByTransportadora(numeroGuia);
        return ResponseEntity.ok().body(detallesEnvioDto);
    }

    @PostMapping
    public ResponseEntity<DetalleEnvioDto> saveDetalleEnvio(@RequestBody DetalleEnvioToSaveDto detalleEnvioDto) {
        DetalleEnvioDto createdDetalleEnvioDto = detalleEnvioService.saveDetalleEnvio(detalleEnvioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDetalleEnvioDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleEnvioDto> updateDetalleEnvio(@PathVariable("id") Long id, @RequestBody DetalleEnvioToSaveDto detalleEnvioDto) {
        try {
            DetalleEnvioDto updatedDetalleEnvioDto = detalleEnvioService.updateDetalleEnvio(id, detalleEnvioDto);
            return ResponseEntity.ok().body(updatedDetalleEnvioDto);
        } catch (DetalleEnvioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleEnvio(@PathVariable("id") Long id) {
        detalleEnvioService.deleteDetalleEnvio(id);
        return ResponseEntity.noContent().build();
    }

}
