package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.PaymentMethodDTO;
import com.nib.runningapp.entities.PaymentMethod;
import com.nib.runningapp.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMethodMapper {

    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    @Mapping(target = "userId", source = "userPayment.id")
    @Mapping(target = "username", source = "userPayment.username")
    PaymentMethodDTO toDTO(PaymentMethod paymentMethod);

    @Mapping(target = "userPayment", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "userPaymentHistoryList", ignore = true)
    @Mapping(target = "status", ignore = true)
    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);

    @Named("mapUser")
    default User mapUser(Long id) {
        User p = new User();
        p.setId(id);
        return p;
    }
}
