package com.example.pmt_tool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import static com.example.pmt_tool.utilities.Constants.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {

    private Integer id;

    @NonNull
    @Pattern(regexp = REGEX_FOR_USERNAME_VALIDATION,message = MESSAGE_FOR_INVALID_USER_NAME)
    private String userName;


    @Pattern(regexp = REGEX_FOR_ACCOUNT_NAME_VALIDATION,message = MESSAGE_FOR_INVALID_ACCOUNT_NAME)
    private String accountName;

    @Pattern(regexp = REGEX_FOR_PASSWORD_VALIDATION,message = MESSAGE_FOR_INVALID_PASSWORD)
    private String password;


    @Pattern(regexp = REGEX_FOR_VALIDATION_URL,message = MESSAGE_FOR_INVALID_URL)
    private String url;
    @Valid
    @NonNull private GroupDTO group;


}
