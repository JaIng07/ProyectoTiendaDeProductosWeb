package com.pweb.tiendaonline.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pweb.tiendaonline.dtos.pago.PagoDto;
import com.pweb.tiendaonline.entities.Pago;
import com.pweb.tiendaonline.entities.PagoMetodo;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.repositories.PagoRepository;
import com.pweb.tiendaonline.repositories.PedidoRepository;

@ExtendWith(MockitoExtension.class)
class PagoServiceImplTest {
    
    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PagoServiceImpl pagoService;

    private Pago pago;
    private PagoMetodo cashMethod = PagoMetodo.EFECTIVO;
    private Pedido pedido;
    @BeforeEach
    void setUp(){
        pago = Pago.builder()
               .id(1l)
               .metodoPago(cashMethod)
               .fechaPago(LocalDateTime.of(2024, 2, 28, 13, 30, 0))
               .totalPago(100.000)
               .build();
        pedido = Pedido.builder()
                .id(2l)
                .fechaPedido(LocalDateTime.of(2024, 2, 28, 13, 30, 0))
                .pago(pago)
                .build();
    }

    @Test
    void givenPago_WhenfindPagoById_ThenReturnPayment(){

        when(pagoRepository.findById(1l)).thenReturn(Optional.ofNullable(pago));

        PagoDto savedPayment = pagoService.findPagoById(1l);

        Assertions.assertThat(savedPayment).isNotNull();
        Assertions.assertThat(savedPayment.totalPago()).isEqualTo(100.000);
    }

    @Test
    void givenPago_WhenfindAllPagos_ThenReturnPaymentList(){
         
        List<Pago> pagos = pagoRepository.findAll();

        when(pagoRepository.findAll()).thenReturn(pagos);

        List<PagoDto> foundPayments = pagoService.findAllPagos();

        Assertions.assertThat(foundPayments.size()).isGreaterThan(0);
        Assertions.assertThat(foundPayments.contains(pago)).isTrue();
        Assertions.assertThat(foundPayments).isEqualTo(1);
    }

    @Test
    void givenPago_WhenUpdatePago_ThenReturnUpdatedPayment(){
         
    }

    @Test
    void givenPago_WhenDeletePago_ThenReturnDeletedPayment(){
         
        given(pagoRepository.findById(1l)).willReturn(Optional.ofNullable(pago));
        doNothing().when(pagoRepository).delete(pago);

        PagoDto deletedPayment = pagoService.findPagoById(1l);

        assertAll(() -> pagoService.deletePago(1l));
        assertThat(deletedPayment).isNull();
    }

    @Test
    void givenPago_WhenfindPagoByFechaPagoBetween_ThenReturnPaymentList(){
         
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 12, 1, 12, 0, 0);
        LocalDateTime fechaFinal = LocalDateTime.now();
        
        List<Pago> payments = pagoRepository.findPagoByFechaPagoBetween(fechaInicio, fechaFinal);
        when(pagoRepository.findPagoByFechaPagoBetween(fechaInicio, fechaFinal)).thenReturn(payments);

        List<PagoDto> foundPayments = pagoService.findPagoByFechaPagoBetween(fechaInicio, fechaFinal);

        Assertions.assertThat(foundPayments).isNotEmpty();
        assertThat(foundPayments.size()).isEqualTo(1);
        assertThat(foundPayments.contains(pago)).isTrue();
    }

    @Test
    void givenPago_WhenfindPagoByPedidoIdAndMetodoPago_ThenReturnPayment(){
         
        List<Pago> payments = pagoRepository.findPagoByPedidoIdAndMetodoPago(2l, cashMethod);

        when(pagoRepository.findPagoByPedidoIdAndMetodoPago(2l, cashMethod)).thenReturn(payments);

        List<PagoDto> foundPayments = pagoService.findPagoByPedidoIdAndMetodoPago(2l, cashMethod);

        Assertions.assertThat(foundPayments).isNotEmpty();
        assertThat(foundPayments.size()).isEqualTo(1);
        assertThat(foundPayments.contains(pago)).isTrue();
    }
}
