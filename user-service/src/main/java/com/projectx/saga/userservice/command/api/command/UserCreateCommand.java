package com.projectx.saga.userservice.command.api.command;

import com.projectx.saga.commonservice.dto.Address;
import com.projectx.saga.commonservice.dto.CardDetails;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UserCreateCommand {
    @TargetAggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String email;
    private Address address;
    private CardDetails cardDetails;
}
