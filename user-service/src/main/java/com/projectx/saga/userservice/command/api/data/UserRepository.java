package com.projectx.saga.userservice.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails,String> {
    @Query(value = "select * from user_details where user_id=:userId",nativeQuery = true)
    UserDetails getById(@Param("userId")String userId);

    @Query(value = "select ud.street,ud.city,ud.state,ud.country,ud.pin_code from user_address ud "
            +"join user_details u on ud.id=u.address_id "
            +"where u.user_id=:userId",nativeQuery = true)
    List<Object[]> getUserAddress(@Param("userId")String userId);
}
