package com.example.pmt_tool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

import static com.example.pmt_tool.utilities.Constants.MESSAGE_FOR_INVALID_GROUP_NAME;
import static com.example.pmt_tool.utilities.Constants.REGEX_FOR_GROUP_NAME;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

    private Integer id;


    @Pattern(regexp = REGEX_FOR_GROUP_NAME,message = MESSAGE_FOR_INVALID_GROUP_NAME)
    private String groupName;

    public GroupDTO(String groupName){
        this.groupName =groupName;
    }

}
