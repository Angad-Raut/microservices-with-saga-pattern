package com.projectx.saga.userservice.query.api.projections;

import com.projectx.saga.commonservice.dto.Address;
import com.projectx.saga.commonservice.dto.CardDetails;
import com.projectx.saga.commonservice.dto.User;
import com.projectx.saga.commonservice.queries.GetUserAddressByUserIdQuery;
import com.projectx.saga.commonservice.queries.GetUserPaymentDetailsQuery;
import com.projectx.saga.userservice.command.api.data.UserAddress;
import com.projectx.saga.userservice.command.api.data.UserCardDetails;
import com.projectx.saga.userservice.command.api.data.UserDetails;
import com.projectx.saga.userservice.command.api.data.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProjection {

    @Autowired
    private UserRepository userRepository;

    @QueryHandler
    User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
          UserDetails userDetails = userRepository.getById(query.getUserId());
          UserAddress userAddress = userDetails.getUserAddress();
          return User.builder()
                  .userId(userDetails.getUserId())
                  .email(userDetails.getEmail())
                  .lastName(userDetails.getLastName())
                  .firstName(userDetails.getFirstName())
                  .mobileNumber(userDetails.getMobileNumber())
                  .address(toAddress(userDetails.getUserAddress()))
                  .cardDetails(toCardDetails(userDetails.getCardDetails()))
                  .build();
    }

    @QueryHandler
    Address getUserAddressByUserId(GetUserAddressByUserIdQuery query) {
        List<Object[]> dataList = userRepository.getUserAddress(query.getUserId());
        if (dataList!=null && !dataList.isEmpty()) {
            Object[] data = dataList.get(0);
            return Address.builder()
                    .street(data[0]!=null?data[0].toString():"-")
                    .city(data[1]!=null?data[1].toString():"-")
                    .state(data[2]!=null?data[2].toString():"-")
                    .country(data[3]!=null?data[3].toString():"-")
                    .pinCode(data[4]!=null?Integer.parseInt(data[4].toString()):null)
                    .build();
        } else {
            return new Address();
        }
    }
    private Address toAddress(UserAddress data) {
        return data!=null?Address.builder()
                .city(data.getCity())
                .street(data.getStreet())
                .state(data.getState())
                .country(data.getCountry())
                .pinCode(data.getPinCode())
                .build() : new Address() ;
    }
    private CardDetails toCardDetails(UserCardDetails card) {
        return card!=null? CardDetails.builder()
                .name(card.getName())
                .cardNumber(card.getCardNumber())
                .cvv(card.getCvv())
                .validUtilMonth(card.getValidUtilMonth())
                .validUtilYear(card.getValidUtilYear())
                .build() : new CardDetails();
    }
}
