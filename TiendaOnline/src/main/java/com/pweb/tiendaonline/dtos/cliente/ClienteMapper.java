package com.pweb.tiendaonline.dtos.cliente;

import com.pweb.tiendaonline.entities.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper( ClienteMapper.class );

    ClienteDto clienteEntityToClienteDto(Cliente cliente);

    @InheritInverseConfiguration
    Cliente clienteDtoToClienteEntity(ClienteDto clienteDto);

    Cliente clienteSaveDtoToClienteEntity(ClienteToSaveDto clienteDto);

    @Mapping(target = "pedidos", ignore = true)
    Cliente clienteToShowDtoToClienteEntity( ClienteToShowDto clienteToShowDto );

    ClienteToShowDto clienteEntityToclienteToShowDto( Cliente cliente );
}
