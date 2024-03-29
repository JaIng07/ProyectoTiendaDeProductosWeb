package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.pago.PagoDto;
import com.pweb.tiendaonline.dtos.pago.PagoMapper;
import com.pweb.tiendaonline.dtos.pago.PagoToSaveDto;
import com.pweb.tiendaonline.entities.Pago;
import com.pweb.tiendaonline.entities.PagoMetodo;
import com.pweb.tiendaonline.exceptions.NotAbleToDeleteException;
import com.pweb.tiendaonline.exceptions.PagoNotFoundException;
import com.pweb.tiendaonline.repositories.PagoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoMapper pagoMapper;
    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoMapper pagoMapper, PagoRepository pagoRepository) {
        this.pagoMapper = pagoMapper;
        this.pagoRepository = pagoRepository;
    }

    // Implementación de los métodos de la interfaz PagoService
    @Override
    public PagoDto savePago(PagoToSaveDto pagoDto) {
        Pago pago = pagoMapper.pagoToSaveDtoToPagoEntity(pagoDto);
        Pago pagoGuardado = pagoRepository.save(pago);
        return pagoMapper.pagoEntityToPagoDto(pagoGuardado);
    }

    @Override
    public PagoDto findPagoById(Long id) throws PagoNotFoundException {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new PagoNotFoundException("Pago no encontrado"));
        return pagoMapper.pagoEntityToPagoDto(pago);
    }

    @Override
    public List<PagoDto> findAllPagos() {
        List<Pago> pagos = pagoRepository.findAll();
        return pagos.stream()
                .map(pago -> pagoMapper.pagoEntityToPagoDto(pago))
                .toList();
    }

    @Override
    public PagoDto updatePago(Long id, PagoToSaveDto pago) {
        return null;
    }

    @Override
    public void deletePago(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Pago no encontrado"));
        pagoRepository.delete(pago);
    }

    @Override
    public List<PagoDto> findPagoByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return null;
    }

    @Override
    public List<PagoDto> findPagoByPedidoIdAndMetodoPago(Long idPedido, PagoMetodo metodoPago) {
        return null;
    }
}
