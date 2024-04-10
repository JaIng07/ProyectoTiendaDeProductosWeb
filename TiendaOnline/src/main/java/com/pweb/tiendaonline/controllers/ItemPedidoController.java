package com.pweb.tiendaonline.controllers;

import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoDto;
import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoToSaveDto;
import com.pweb.tiendaonline.exceptions.ItemPedidoNotFoundException;
import com.pweb.tiendaonline.services.ItemPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> getItemPedidoById(@PathVariable("id") Long id) {
        try {
            ItemPedidoDto itemPedidoDto = itemPedidoService.findItemPedidoById(id);
            return ResponseEntity.ok().body(itemPedidoDto);
        } catch (ItemPedidoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<ItemPedidoDto>> getAllItemPedidos() {
        List<ItemPedidoDto> itemPedidosDto = itemPedidoService.findAllItemPedidos();
        return ResponseEntity.ok().body(itemPedidosDto);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ItemPedidoDto>> getItemPedidosByOrderId(@PathVariable("orderId") Long orderId) {
        List<ItemPedidoDto> itemPedidosDto = itemPedidoService.findItemPedidoByPedidoId(orderId);
        return ResponseEntity.ok().body(itemPedidosDto);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ItemPedidoDto>> getItemPedidosByProductId(@PathVariable("productId") Long productId) {
        List<ItemPedidoDto> itemPedidosDto = itemPedidoService.findItemPedidoByProductoId(productId);
        return ResponseEntity.ok().body(itemPedidosDto);
    }

    @PostMapping()
    public ResponseEntity<ItemPedidoDto> saveItemPedido(@RequestBody ItemPedidoToSaveDto itemPedidoDto) {
        ItemPedidoDto savedItemPedidoDto = itemPedidoService.saveItemPedido(itemPedidoDto);
        return ResponseEntity.ok().body(savedItemPedidoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> updateItemPedido(@PathVariable("id") Long id, @RequestBody ItemPedidoToSaveDto itemPedidoDto) {
        ItemPedidoDto updatedItemPedidoDto = itemPedidoService.updateItemPedido(id, itemPedidoDto);
        return ResponseEntity.ok().body(updatedItemPedidoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemPedido(@PathVariable("id") Long id) {
        itemPedidoService.deleteItemPedido(id);
        return ResponseEntity.noContent().build();
    }

}
