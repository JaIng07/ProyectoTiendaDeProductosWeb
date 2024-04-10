package com.pweb.tiendaonline.controllers;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoToSaveDto;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.exceptions.PedidoNotFoundException;
import com.pweb.tiendaonline.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getPedidoById(@PathVariable("id") Long idPedido) {
        try {
            PedidoDto pedidoDto = pedidoService.findPedidoById(idPedido);
            return ResponseEntity.ok().body(pedidoDto);
        } catch (PedidoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<PedidoDto>> getAllPedidos() {
        List<PedidoDto> pedidosDto = pedidoService.findAllPedidos();
        return ResponseEntity.ok().body(pedidosDto);
    }

    @GetMapping("/customer/{idCliente}")
    public ResponseEntity<List<PedidoDto>> getPedidosByClienteId(@PathVariable("idCliente") Long idCliente) {
        try {
            List<PedidoDto> pedido = pedidoService.findPedidoByClienteWithItems(idCliente);
            return ResponseEntity.ok().body(pedido);
        } catch (PedidoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PedidoDto>> getPedidosByDateRange(
            @RequestParam("fechaInicio") LocalDateTime fechaInicio,
            @RequestParam("fechaFin") LocalDateTime fechaFin) {
        List<PedidoDto> pedidosDto = pedidoService.findPedidoByFechaPedidoBetween(fechaInicio, fechaFin);
        return ResponseEntity.ok().body(pedidosDto);
    }

    @PostMapping
    public ResponseEntity<PedidoDto> savePedido(@RequestBody PedidoToSaveDto pedidoDto) {
        try{
            PedidoDto pedido = pedidoService.savePedido(pedidoDto);
            return ResponseEntity.ok().body(pedido);
        }catch (PedidoNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> updatePedido(@PathVariable("id") Long idPedido, @RequestBody PedidoToSaveDto pedidoDto) {
        try{
            PedidoDto pedido = pedidoService.updatePedido(idPedido, pedidoDto);
            return ResponseEntity.ok().body(pedido);
        }catch (PedidoNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable("id") Long idPedido) {
        pedidoService.deletePedido(idPedido);
        return ResponseEntity.noContent().build();
    }

}
