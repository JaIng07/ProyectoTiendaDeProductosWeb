package com.pweb.tiendaonline.controllers;

import com.pweb.tiendaonline.dtos.cliente.ClienteDto;
import com.pweb.tiendaonline.dtos.cliente.ClienteToSaveDto;
import com.pweb.tiendaonline.exceptions.ClienteNotFoundException;
import com.pweb.tiendaonline.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable("id") Long idCliente) {
        try {
            ClienteDto clienteDto = clienteService.findClienteById(idCliente);
            return ResponseEntity.ok().body(clienteDto);
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientes = clienteService.findAllClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteDto> getClienteByEmail(@PathVariable String email) throws ClienteNotFoundException {
        ClienteDto cliente = clienteService.findClienteByEmail(email);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/city")
    public ResponseEntity<List<ClienteDto>> getClientesByCity(@RequestParam("cityName") String address) {
        try {
            List<ClienteDto> clientes = clienteService.findClienteByDireccionContainingIgnoreCase(address);
            return ResponseEntity.ok().body(clientes);
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDto> saveCliente(@RequestBody ClienteToSaveDto clienteDto) {
        ClienteDto nuevoCliente = clienteService.saveCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteToSaveDto clienteDto) {
        ClienteDto clienteActualizado = clienteService.updateCliente(id, clienteDto);
        return ResponseEntity.ok().body(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
