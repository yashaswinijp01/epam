package com.epam.rd.entity;

import com.epam.rd.utils.AttributeEncryptor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "account_name")
    private String accountName;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "user_name")
    private String userName;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "password")
    private String password;

    @Column(name = "url")
    private String url;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "last_modified_at")
    private Date lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;


    public Account(String accountName, String userName, String password, String url) {
        this.accountName = accountName;
        this.userName = userName;
        this.password = password;
        this.url = url;
    }


}
