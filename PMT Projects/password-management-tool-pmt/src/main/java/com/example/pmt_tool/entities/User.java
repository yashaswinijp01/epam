package com.example.pmt_tool.entities;
import com.example.pmt_tool.configs.AttributeEncryptor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userName;

    @Convert(converter = AttributeEncryptor.class)
    private String masterPassword;

}
