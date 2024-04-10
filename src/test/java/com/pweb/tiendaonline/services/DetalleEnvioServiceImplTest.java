package com.pweb.tiendaonline.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pweb.tiendaonline.dtos.cliente.ClienteToShowDto;
import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioDto;
import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioMapper;
import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioToShowDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoToShowDto;
import com.pweb.tiendaonline.entities.Cliente;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pweb.tiendaonline.dtos.cliente.ClienteDto;
import com.pweb.tiendaonline.dtos.cliente.ClienteToSaveDto;
import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoToSaveDto;
import com.pweb.tiendaonline.entities.DetalleEnvio;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.repositories.ClienteRepository;
import com.pweb.tiendaonline.repositories.DetalleEnvioRepository;
import com.pweb.tiendaonline.repositories.ItemPedidoRepository;
import com.pweb.tiendaonline.repositories.PedidoRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetalleEnvioServiceImplTest {
    
    @Mock
    private DetalleEnvioRepository detalleEnvioRepository;

    @Mock
    private DetalleEnvioMapper detalleEnvioMapper;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private DetalleEnvioServiceImpl detalleEnvioService;

    private PedidoStatus pendienteStatus = PedidoStatus.PENDIENTE;
    private PedidoStatus enviadoStatus = PedidoStatus.ENVIADO;
    private Pedido firstOrder;
    private Pedido secondOrder;
    private Pedido thirdOrder;
    private Cliente client;
    private DetalleEnvio firstShippingDetail;
    private DetalleEnvio secondShippingDetail;
    private ClienteToShowDto clientToShowDto;
    private PedidoToShowDto firstOrderToShowDto;
    private PedidoToShowDto secondOrderToShowDto;
    @BeforeEach
    void setUp(){
        firstOrder = Pedido.builder()
                    .fechaPedido(LocalDateTime.of(2023, 12, 24, 13, 30, 0))
                    .status(pendienteStatus)
                    .cliente(client)
                    .build();
        firstOrderToShowDto = new PedidoToShowDto(
                firstOrder.getId(),
                firstOrder.getFechaPedido(),
                firstOrder.getStatus(),
                clientToShowDto
        );
        secondOrder = Pedido.builder()
                    .fechaPedido(LocalDateTime.of(2024, 3, 10, 12, 50, 0))
                    .status(enviadoStatus)
                    .cliente(client)
                    .build();
        secondOrderToShowDto = new PedidoToShowDto(
                secondOrder.getId(),
                secondOrder.getFechaPedido(),
                secondOrder.getStatus(),
                clientToShowDto
        );
        thirdOrder = Pedido.builder()
                    .fechaPedido(LocalDateTime.of(2024, 1, 10, 12, 50, 0))
                    .status(enviadoStatus)
                    .cliente(client)
                    .build();
        client = Cliente.builder()
                .id(1l)
                .nombre("Nelson Martinez")
                .email("nelsonmartinezdh@gmail.com")
                .direccion("Calle29i#21-D1")
                .build();
        clientToShowDto = new ClienteToShowDto(
                client.getId(),
                client.getNombre(),
                client.getEmail(),
                client.getDireccion()
        );

        firstShippingDetail = DetalleEnvio.builder()
                .id(4l)
                .pedido(secondOrder)
                .direccion("Cra Test #Test-02")
                .transportadora("SERVIENTREGA")
                .numeroGuia("SEV9876543210")
                .build();
        secondShippingDetail = DetalleEnvio.builder()
                .id(5l)
                .direccion("Calle29i#21-D1")
                .numeroGuia("IRV1234567890")
                .transportadora("Inter Rapidísimo")
                .pedido(thirdOrder)
                .build();
    }

    @Test
    void shippingDetailService_whenSaveShippingDetail_ThenReturnShippingDetail(){

        DetalleEnvioToSaveDto shippingDetailToSaveDto = new DetalleEnvioToSaveDto(
                firstShippingDetail.getId(),
                firstShippingDetail.getDireccion(),
                firstShippingDetail.getTransportadora(),
                firstShippingDetail.getNumeroGuia(),
                firstOrderToShowDto
        );

        when(pedidoRepository.findById(firstOrder.getId())).thenReturn(Optional.ofNullable(firstOrder));
        given(detalleEnvioMapper.detalleEnvioSaveDtoToDetalleEnvioEntity(any())).willReturn(firstShippingDetail);
        given(detalleEnvioRepository.save(any())).willReturn(firstShippingDetail);

        DetalleEnvioDto savedShippingDetail = detalleEnvioService.saveDetalleEnvio(shippingDetailToSaveDto);

        assertThat(savedShippingDetail).isNotNull();
        assertThat(savedShippingDetail.transportadora()).isEqualTo("SERVIENTREGA");
    }

    @Test
    void shippingDetailService_WhenfindDetalleEnvioById_ThenReturnShippingDetail(){

        when(detalleEnvioRepository.findById(secondShippingDetail.getId())).thenReturn(Optional.ofNullable(secondShippingDetail));

        DetalleEnvioDto savedShippingDetail = detalleEnvioService.findDetalleEnvioById(5l);

        assertThat(savedShippingDetail).isNotNull();
        assertThat(savedShippingDetail.transportadora()).isEqualTo("Inter Rapidísimo");
    }

    @Test
    void shippingDetailService_WhenfindAllDetallesEnvio_ThenReturnListShippingDetail(){

        when(detalleEnvioRepository.findAll()).thenReturn(List.of(firstShippingDetail, secondShippingDetail));

        List<DetalleEnvioDto> foundShippingDetails = detalleEnvioService.findAllDetallesEnvio();

        org.assertj.core.api.Assertions.assertThat(foundShippingDetails.size()).isGreaterThan(1);
        org.assertj.core.api.Assertions.assertThat(foundShippingDetails.contains(firstShippingDetail)).isTrue();
        org.assertj.core.api.Assertions.assertThat(foundShippingDetails).isEqualTo(2);
    }

    @Test
    public void shippingDetailService_WhenUpdateDetalleEnvio_ThenReturnUpdatedShippingDetail(){

        DetalleEnvioToSaveDto shippingDetailToSave = new DetalleEnvioToSaveDto(
                firstShippingDetail.getId(),
                firstShippingDetail.getDireccion(),
                firstShippingDetail.getTransportadora(),
                firstShippingDetail.getNumeroGuia(),
                firstOrderToShowDto
        );
        when(detalleEnvioRepository.findById(4l)).thenReturn(Optional.ofNullable(firstShippingDetail));
        when(detalleEnvioRepository.save(Mockito.any(DetalleEnvio.class))).thenReturn(firstShippingDetail);

        DetalleEnvioDto savedShippingDetail = detalleEnvioService.updateDetalleEnvio(4l, shippingDetailToSave);

        assertThat(savedShippingDetail).isNotNull();
        assertThat(savedShippingDetail.id()).isEqualTo(4l);
    }

    @Test
    void shippingDetailService_WhenDeleteDetalleEnvio_ThenReturnDeletedShippingDetailIsEmpty(){

        given(detalleEnvioRepository.findById(4l)).willReturn(Optional.ofNullable(firstShippingDetail));
        doNothing().when(detalleEnvioRepository).deleteById(4l);

        DetalleEnvioDto deletedShippingDetail = detalleEnvioService.findDetalleEnvioById(4l);

        assertAll(() -> detalleEnvioService.deleteDetalleEnvio(4l));
        assertThat(deletedShippingDetail).isNull();
    }

    @Test
    void shippingDetailService_WhenfindDetalleEnvioByPedidoId_ThenReturnShippingDetail(){

        when(detalleEnvioRepository.findDetalleEnvioByPedidoId(thirdOrder.getId())).thenReturn(List.of(secondShippingDetail));

        List<DetalleEnvioDto> savedShippingDetail = detalleEnvioService.findDetalleEnvioByPedidoId(thirdOrder.getId());

        assertThat(savedShippingDetail.size()).isGreaterThan(0);
        assertThat(savedShippingDetail.contains(secondShippingDetail)).isTrue();
    }

    @Test
    void shippingDetailService_WhenfindDetalleEnvioByTransportadora_ThenReturnShippingDetail() {

        given(detalleEnvioRepository.findDetalleEnvioByTransportadora("Inter Rapidísimo")).willReturn(List.of(secondShippingDetail));

        List<DetalleEnvioDto> savedShippingDetail = detalleEnvioService.findDetalleEnvioByTransportadora("Inter Rapidísimo");

        assertThat(savedShippingDetail).isNotEmpty();
        assertThat(savedShippingDetail.contains(secondShippingDetail)).isTrue();
        assertThat(savedShippingDetail.get(0).id()).isEqualTo(5l);
    }

    @Test
    void shippingDetailService_WhenfindDetalleEnvioByEstado_ThenReturnShippingDetail(){

        given(detalleEnvioRepository.findDetalleEnvioByEstado(pendienteStatus)).willReturn(List.of(firstShippingDetail));

        List<DetalleEnvioDto> foundShippingDetails = detalleEnvioService.findDetalleEnvioByEstado(pendienteStatus);

        assertThat(foundShippingDetails.size()).isGreaterThan(0);
        assertThat(foundShippingDetails.contains(firstShippingDetail)).isTrue();
        assertThat(foundShippingDetails.get(0).numeroGuia()).isEqualTo("IRV1234567890");
    }
}
