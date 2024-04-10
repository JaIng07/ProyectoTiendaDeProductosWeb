package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioDto;
import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioMapper;
import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.pweb.tiendaonline.entities.DetalleEnvio;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.exceptions.DetalleEnvioNotFoundException;
import com.pweb.tiendaonline.exceptions.NotAbleToDeleteException;
import com.pweb.tiendaonline.repositories.DetalleEnvioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleEnvioServiceImpl implements DetalleEnvioService {

    private final DetalleEnvioMapper detalleEnvioMapper;
    private final DetalleEnvioRepository detalleEnvioRepository;

    public DetalleEnvioServiceImpl(DetalleEnvioMapper detalleEnvioMapper, DetalleEnvioRepository detalleEnvioRepository) {
        this.detalleEnvioMapper = detalleEnvioMapper;
        this.detalleEnvioRepository = detalleEnvioRepository;
    }

    // Implementación de los métodos de la interfaz DetalleEnvioService
    @Override
    public DetalleEnvioDto saveDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioDto) {
        DetalleEnvio detalleEnvio = detalleEnvioMapper.detalleEnvioSaveDtoToDetalleEnvioEntity(detalleEnvioDto);
        DetalleEnvio detalleEnvioGuardado = detalleEnvioRepository.save(detalleEnvio);
        return detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioDto(detalleEnvioGuardado);
    }

    @Override
    public DetalleEnvioDto findDetalleEnvioById(Long id) throws DetalleEnvioNotFoundException {
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findById(id)
                .orElseThrow(() -> new DetalleEnvioNotFoundException("Detalle de envío no encontrado"));
        return detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioDto(detalleEnvio);
    }

    @Override
    public List<DetalleEnvioDto> findAllDetallesEnvio() {
        List<DetalleEnvio> detallesEnvio = detalleEnvioRepository.findAll();
        return detallesEnvio.stream()
                .map(detalleEnvio -> detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioDto(detalleEnvio))
                .toList();
    }

    @Override
    public DetalleEnvioDto updateDetalleEnvio(Long id, DetalleEnvioToSaveDto detalleEnvioDto) {
        DetalleEnvio detalleEnvioInDb = detalleEnvioRepository.findById(id)
                .orElseThrow(() -> new DetalleEnvioNotFoundException("Detalle de envío no encontrado"));

        detalleEnvioInDb.setDireccion(detalleEnvioDto.direccion());
        detalleEnvioInDb.setTransportadora(detalleEnvioDto.transportadora());
        detalleEnvioInDb.setNumeroGuia(detalleEnvioDto.numeroGuia());

        DetalleEnvio detalleEnvioGuardado = detalleEnvioRepository.save(detalleEnvioInDb);

        return detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioDto(detalleEnvioGuardado);
    }

    @Override
    public void deleteDetalleEnvio(Long id) {
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Detalle de envío no encontrado"));
        detalleEnvioRepository.delete(detalleEnvio);
    }

    @Override
    public List<DetalleEnvioDto> findDetalleEnvioByPedidoId(Long idPedido) {
        List<DetalleEnvio> detallesEnvio = detalleEnvioRepository.findDetalleEnvioByPedidoId(idPedido);
        if (detallesEnvio.isEmpty())
            throw new DetalleEnvioNotFoundException("No se encontraron detalles de envío para el pedido con id " + idPedido);
        return detallesEnvio.stream()
                .map(detalleEnvio -> detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioDto(detalleEnvio))
                .toList();
    }

    @Override
    public List<DetalleEnvioDto> findDetalleEnvioByTransportadora(String transportadora) {
        List<DetalleEnvio> detallesEnvio = detalleEnvioRepository.findDetalleEnvioByTransportadora(transportadora);
        if (detallesEnvio.isEmpty())
            throw new DetalleEnvioNotFoundException("No se encontraron detalles de envío para la transportadora " + transportadora);
        return detallesEnvio.stream()
                .map(detalleEnvio -> detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioDto(detalleEnvio))
                .toList();
    }

    @Override
    public List<DetalleEnvioDto> findDetalleEnvioByEstado(PedidoStatus status) {
        List<DetalleEnvio> detallesEnvio = detalleEnvioRepository.findDetalleEnvioByEstado(status);
        if (detallesEnvio.isEmpty())
            throw new DetalleEnvioNotFoundException("No se encontraron detalles de envío con estado " + status);
        return detallesEnvio.stream()
                .map(detalleEnvio -> detalleEnvioMapper.detalleEnvioEntityToDetalleEnvioDto(detalleEnvio))
                .toList();
    }

}
