package com.pweb.tiendaonline.dtos.producto;

import com.pweb.tiendaonline.entities.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper( ProductoMapper.class );

    ProductoDto productoEntityToProductoDto(Producto producto);

    @InheritInverseConfiguration
    Producto productoDtoToProductoEntity(ProductoDto productoDto);

    Producto productoSaveDtoToProductoEntity(ProductoToSaveDto productoDto);

}
