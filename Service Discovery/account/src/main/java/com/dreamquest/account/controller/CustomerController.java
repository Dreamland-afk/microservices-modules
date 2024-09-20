package com.dreamquest.account.controller;

import com.dreamquest.account.dto.CustomerDetailsDto;
import com.dreamquest.account.dto.CustomerDto;
import com.dreamquest.account.dto.ErrorResponseDto;
import com.dreamquest.account.service.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    ICustomersService iCustomersService;

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(@Valid @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNumber) {
        CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber);
        return new ResponseEntity<>(customerDetailsDto, HttpStatus.OK);
    }
}
