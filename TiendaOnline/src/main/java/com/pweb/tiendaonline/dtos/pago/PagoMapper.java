package com.pweb.tiendaonline.dtos.pago;


import com.pweb.tiendaonline.entities.Pago;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PagoMapper {

    PagoMapper INSTANCE = Mappers.getMapper( PagoMapper.class );

    PagoDto pagoEntityToPagoDto(Pago pago);

    @InheritInverseConfiguration
    Pago pagoDtoToPagoEntity(PagoDto pagoDto);

    Pago pagoToSaveDtoToPagoEntity(PagoToSaveDto pagoDto);
    
}
