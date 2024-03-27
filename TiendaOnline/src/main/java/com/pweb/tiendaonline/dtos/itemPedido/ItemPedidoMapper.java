package com.pweb.tiendaonline.dtos.itemPedido;

import com.pweb.tiendaonline.entities.ItemPedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemPedidoMapper {

    ItemPedidoMapper INSTANCE = Mappers.getMapper( ItemPedidoMapper.class );

    ItemPedidoDto itemPedidoEntityToItemPedidoDto(ItemPedido itemPedido);

    @InheritInverseConfiguration
    ItemPedido itemPedidoDtoToItemPedidoEntity(ItemPedidoDto itemPedidoDto);

    ItemPedido itemPedidoSaveDtoToItemPedidoEntity(ItemPedidoToSaveDto itemPedidoDto);

}
