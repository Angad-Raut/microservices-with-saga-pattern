package com.projectx.saga.userservice.command.api.aggregate;

import com.projectx.saga.commonservice.dto.Address;
import com.projectx.saga.commonservice.dto.CardDetails;
import com.projectx.saga.userservice.command.api.command.UserCreateCommand;
import com.projectx.saga.userservice.command.api.event.UserCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String email;
    private Address address;
    private CardDetails cardDetails;

    public UserAggregate(){

    }

    @CommandHandler
    public UserAggregate(UserCreateCommand command) {
        UserCreateEvent userCreateEvent = new UserCreateEvent();
        BeanUtils.copyProperties(command,userCreateEvent);
        AggregateLifecycle.apply(userCreateEvent);
    }

    @EventSourcingHandler
    public void on(UserCreateEvent event) {
        this.userId=event.getUserId();
        this.address=event.getAddress();
        this.email=event.getEmail();
        this.mobileNumber=event.getMobileNumber();
        this.firstName=event.getFirstName();
        this.lastName=event.getLastName();
        this.cardDetails=event.getCardDetails();
    }
}
