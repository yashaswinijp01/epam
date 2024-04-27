package com.example.pmt_tool.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;

    @Column(nullable = false,unique = true)
    private String accountName;


    private String password;
    @Column(length = 255)
    private String url;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @NonNull private Group group;


}
