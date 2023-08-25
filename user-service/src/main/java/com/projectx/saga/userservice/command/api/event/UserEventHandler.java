package com.projectx.saga.userservice.command.api.event;

import com.projectx.saga.commonservice.dto.Address;
import com.projectx.saga.commonservice.dto.CardDetails;
import com.projectx.saga.userservice.command.api.data.UserAddress;
import com.projectx.saga.userservice.command.api.data.UserCardDetails;
import com.projectx.saga.userservice.command.api.data.UserDetails;
import com.projectx.saga.userservice.command.api.data.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserEventHandler {

    @Autowired
    private UserRepository userRepository;

    @EventHandler
    public void on(UserCreateEvent event) {
        UserDetails userDetails = UserDetails.builder()
                .userId(event.getUserId())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .email(event.getEmail())
                .mobileNumber(event.getMobileNumber())
                .insertTime(new Date())
                .userAddress(setUserAddress(event.getAddress()))
                .cardDetails(setCardDetails(event.getCardDetails()))
                .build();
        userRepository.save(userDetails);

    }
    private UserAddress setUserAddress(Address address) {
        return address!=null?UserAddress.builder()
                .street(address.getStreet()!=null?address.getStreet():null)
                .city(address.getCity()!=null?address.getCity():null)
                .state(address.getState()!=null?address.getState():null)
                .country(address.getCountry()!=null?address.getCountry():null)
                .pinCode(address.getPinCode()!=null?address.getPinCode():null)
                .build() : new UserAddress();
    }
    private UserCardDetails setCardDetails(CardDetails card) {
        return card!=null?UserCardDetails.builder()
                .name(card.getName()!=null?card.getName():null)
                .cardNumber(card.getCardNumber()!=null?card.getCardNumber():null)
                .cvv(card.getCvv()!=null?card.getCvv():null)
                .validUtilMonth(card.getValidUtilMonth()!=null?card.getValidUtilMonth():null)
                .validUtilYear(card.getValidUtilYear()!=null?card.getValidUtilYear():null)
                .build() : new UserCardDetails();
    }
}
