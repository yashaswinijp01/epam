package com.example.pmt_tool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.example.pmt_tool.utilities.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull(message = MESSAGE_FOR_INVALID_USER_NAME)
    @Pattern(regexp = REGEX_FOR_USERNAME_VALIDATION,message = MESSAGE_FOR_INVALID_USER_NAME)
    private String userName;

    @Pattern(regexp = REGEX_FOR_MASTER_PASSWORD,message = MESSAGE_FOR_INVALID_MASTER_PASSWORD)
    private String masterPassword;

}
