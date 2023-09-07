package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.EventDTO;
import com.nib.runningapp.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDTO toDTO(Event event);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "eventRegistrationList", ignore = true)
    Event toEntity(EventDTO eventDTO);
}
