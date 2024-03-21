package com.sales.quiz.converter;


import com.sales.quiz.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO ClientToClientDTO(Client client);

    Client ClientDTOToClient(ClientDTO clientDTO);
}
