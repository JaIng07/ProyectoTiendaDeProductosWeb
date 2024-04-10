package com.pweb.tiendaonline.controllers;

import com.pweb.tiendaonline.dtos.pago.PagoDto;
import com.pweb.tiendaonline.dtos.pago.PagoToSaveDto;
import com.pweb.tiendaonline.entities.PagoMetodo;
import com.pweb.tiendaonline.exceptions.PagoNotFoundException;
import com.pweb.tiendaonline.services.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDto> getPagoById(@PathVariable Long id) {
        try {
            PagoDto pagoDto = pagoService.findPagoById(id);
            return ResponseEntity.ok().body(pagoDto);
        } catch (PagoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PagoDto>> getAllPagos() {
        List<PagoDto> pagosDto = pagoService.findAllPagos();
        return ResponseEntity.ok().body(pagosDto);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PagoDto>> getPagosByOrderId(@PathVariable Long orderId, PagoMetodo metodoPago) {
        List<PagoDto> pagosDto = pagoService.findPagoByPedidoIdAndMetodoPago(orderId, metodoPago);
        return ResponseEntity.ok().body(pagosDto);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PagoDto>> getPagosByDateRange(
            @RequestParam LocalDateTime fechaInicio,
            @RequestParam LocalDateTime fechaFin) {
        List<PagoDto> pagosDto = pagoService.findPagoByFechaPagoBetween(fechaInicio, fechaFin);
        return ResponseEntity.ok().body(pagosDto);
    }

    @PostMapping
    public ResponseEntity<PagoDto> savePago(@RequestBody PagoToSaveDto pagoToSaveDto) {
        PagoDto pagoDto = pagoService.savePago(pagoToSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDto> updatePago(@PathVariable Long id, @RequestBody PagoToSaveDto pagoToSaveDto) {
        try {
            PagoDto updatedPagoDto = pagoService.updatePago(id, pagoToSaveDto);
            return ResponseEntity.ok().body(updatedPagoDto);
        } catch (PagoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deletePago(id);
        return ResponseEntity.noContent().build();
    }

}
