package com.projectx.saga.commonservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String email;
    private CardDetails cardDetails;
    private Address address;
}
