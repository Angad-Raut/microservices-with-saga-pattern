package com.projectx.saga.userservice.command.api.event;

import com.projectx.saga.commonservice.dto.Address;
import com.projectx.saga.commonservice.dto.CardDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateEvent {
    private String userId;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String email;
    private Address address;
    private CardDetails cardDetails;
}
