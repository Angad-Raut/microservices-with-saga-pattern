package com.projectx.saga.productservice.command.api.controller;

import com.projectx.saga.commonservice.dto.ResponseDto;
import com.projectx.saga.productservice.command.api.dto.ProductDto;
import com.projectx.saga.productservice.command.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productDetails")
public class ProductCommandController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ResponseDto<String>> createProduct(@RequestBody ProductDto dto) {
        try {
            String result = productService.createProduct(dto);
            return new ResponseEntity<ResponseDto<String>>(new ResponseDto<String>(result,null),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<ResponseDto<String>>(new ResponseDto<String>(null,e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
