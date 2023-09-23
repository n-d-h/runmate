package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.RunningSessionDTO;
import com.nib.runningapp.entities.RunningSession;
import com.nib.runningapp.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RunningSessionMapper {

    RunningSessionMapper INSTANCE = Mappers.getMapper(RunningSessionMapper.class);

    @Mapping(target = "userId", source = "userRunningSession.id")
    @Mapping(target = "username", source = "userRunningSession.username")
    RunningSessionDTO toDTO(RunningSession runningSession);

    @Mapping(target = "userRunningSession", source = "userId", qualifiedByName = "mapUser")
    RunningSession toEntity(RunningSessionDTO runningSessionDTO);

    @Named("mapUser")
    default User mapUser(String id) {
        User p = new User();
        p.setId(id);
        return p;
    }
}
