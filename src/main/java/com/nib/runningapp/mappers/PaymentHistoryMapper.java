package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.PaymentHistoryDTO;
import com.nib.runningapp.entities.PaymentHistory;
import com.nib.runningapp.entities.PaymentMethod;
import com.nib.runningapp.entities.Subscription;
import com.nib.runningapp.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentHistoryMapper {

    PaymentHistoryMapper INSTANCE = Mappers.getMapper(PaymentHistoryMapper.class);

    @Mapping(target = "userId", source = "userPaymentHistory.id")
    @Mapping(target = "username", source = "userPaymentHistory.username")
    @Mapping(target = "subscriptionId", source = "paymentSubscription.id")
    @Mapping(target = "subscriptionType", source = "paymentSubscription.name")
    @Mapping(target = "paymentMethodId", source = "userPaymentMethod.id")
    @Mapping(target = "paymentMethod", source = "userPaymentMethod.type")
    PaymentHistoryDTO toDTO(PaymentHistory paymentHistory);


    @Mapping(target = "userPaymentHistory", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "paymentSubscription", source = "subscriptionId", qualifiedByName = "mapSubscription")
    @Mapping(target = "userPaymentMethod", source = "paymentMethodId", qualifiedByName = "mapPaymentMethod")
    @Mapping(target = "status", ignore = true)
    PaymentHistory toEntity(PaymentHistoryDTO paymentHistoryDTO);

    @Named("mapUser")
    default User mapUser(String id) {
        User p = new User();
        p.setId(id);
        return p;
    }

    @Named("mapSubscription")
    default Subscription mapSubscription(Long id) {
        Subscription p = new Subscription();
        p.setId(id);
        return p;
    }

    @Named("mapPaymentMethod")
    default PaymentMethod mapPayment(Long id) {
        PaymentMethod p = new PaymentMethod();
        p.setId(id);
        return p;
    }
}
