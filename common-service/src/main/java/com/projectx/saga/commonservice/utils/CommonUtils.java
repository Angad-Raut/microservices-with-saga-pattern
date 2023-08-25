package com.projectx.saga.commonservice.utils;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

    public static final String PRODUCT_QUANTITY_EXCEED_PREFIX="Product quantity exceeds for ";
    public static final String PRODUCT_QUANTITY_EXCEED_END="please contact to system admin!!";
    public static final String ADD_USER="User Created Successfully";
    public static final String ADD_PRODUCT="Product created successfully";
    public static final String ORDER_PLACED="Order placed successfully";
    public static final String SHIPMENT_STATUS="COMPLETED";
    public static final String SHIPMENT_INCOMPLETE_STATUS="INCOMPLETE";
    public static final String PAYMENT_STATUS="COMPLETED";
    public static final String PAYMENT_FAILED_STATUS="FAILED";
    public static final String ORDER_CREATED_STATUS="CREATED";
    public static final String ORDER_APPROVED_STATUS="APPROVED";
    public static final String ORDER_CANCELLED_STATUS="CANCELLED";
}
