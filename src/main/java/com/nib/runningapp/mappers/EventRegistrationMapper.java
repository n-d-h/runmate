package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.EventRegistrationDTO;
import com.nib.runningapp.entities.Event;
import com.nib.runningapp.entities.EventRegistration;
import com.nib.runningapp.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventRegistrationMapper {

    EventRegistrationMapper INSTANCE = Mappers.getMapper(EventRegistrationMapper.class);

    @Mapping(target = "eventId", source = "eventContent.id")
    @Mapping(target = "eventName", source = "eventContent.name")
    @Mapping(target = "userId", source = "userEvent.id")
    @Mapping(target = "username", source = "userEvent.username")
    EventRegistrationDTO toDTO(EventRegistration eventRegistration);

    @Mapping(target = "eventContent", source = "eventId", qualifiedByName = "mapEvent")
    @Mapping(target = "userEvent", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "status", ignore = true)
    EventRegistration toEntity(EventRegistrationDTO eventRegistrationDTO);

    @Named("mapEvent")
    default Event mapEvent(Long id) {
        Event e = new Event();
        e.setId(id);
        return e;
    }

    @Named("mapUser")
    default User mapUser(Long id) {
        User u = new User();
        u.setId(id);
        return u;
    }
}
