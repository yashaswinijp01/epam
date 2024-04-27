package com.example.pmt_tool.services;

import com.example.pmt_tool.dto.GroupDTO;
import javax.validation.Valid;
import java.util.List;

public interface GroupService {
    List<GroupDTO> findAll();

    void save(@Valid GroupDTO group);

    void deleteById(Integer id);

    GroupDTO findById(Integer id);
}
