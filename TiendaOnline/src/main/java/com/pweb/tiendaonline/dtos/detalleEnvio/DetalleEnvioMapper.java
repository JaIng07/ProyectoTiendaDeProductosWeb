package com.pweb.tiendaonline.dtos.detalleEnvio;

import com.pweb.tiendaonline.entities.DetalleEnvio;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DetalleEnvioMapper {

    DetalleEnvioMapper INSTANCE = Mappers.getMapper( DetalleEnvioMapper.class );

    DetalleEnvioDto detalleEnvioEntityToDetalleEnvioDto(DetalleEnvio detalleEnvio);

    @InheritInverseConfiguration
    DetalleEnvio detalleEnvioDtoToDetalleEnvioEntity(DetalleEnvioDto detalleEnvioDto);

    DetalleEnvio detalleEnvioSaveDtoToDetalleEnvioEntity(DetalleEnvioToSaveDto detalleEnvioDto);
}
