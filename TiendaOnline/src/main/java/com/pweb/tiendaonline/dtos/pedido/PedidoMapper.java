package com.pweb.tiendaonline.dtos.pedido;

import com.pweb.tiendaonline.entities.Pedido;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PedidoMapper {
    
    PedidoMapper INSTANCE = Mappers.getMapper( PedidoMapper.class );

    PedidoDto pedidoEntityToPedidoDto(Pedido pedido);

    @InheritInverseConfiguration
    Pedido pedidoDtoToPedidoEntity(PedidoDto pedidoDto);

    Pedido pedidoSaveDtoToPedidoEntity(PedidoToSaveDto pedidoDto);
}
