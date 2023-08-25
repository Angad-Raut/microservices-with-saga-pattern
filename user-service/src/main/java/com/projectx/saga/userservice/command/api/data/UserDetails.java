package com.projectx.saga.userservice.command.api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String email;
    private Date insertTime;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private UserAddress userAddress;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    private UserCardDetails cardDetails;
}
