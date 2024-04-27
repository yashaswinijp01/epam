package com.epam.rd.entity;

import com.epam.rd.utils.AttributeEncryptor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "last_modified_at")
    private Date lastModifiedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    List<Account> accounts;

    @JsonIgnore
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    List<Group> groups;

    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

}
