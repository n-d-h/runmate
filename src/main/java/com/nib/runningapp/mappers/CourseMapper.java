package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.CourseDTO;
import com.nib.runningapp.entities.Course;
import com.nib.runningapp.entities.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "subscriptionId", source = "courseSubscription.id")
    @Mapping(target = "subscriptionType", source = "courseSubscription.name")
    CourseDTO toDTO(Course course);

    @Mapping(target = "courseSubscription", source = "subscriptionId", qualifiedByName = "mapSubscription")
    Course toEntity(CourseDTO courseDTO);

    @Named("mapSubscription")
    default Subscription mapSubscription(Long id) {
        Subscription s = new Subscription();
        s.setId(id);
        return s;
    }
}
