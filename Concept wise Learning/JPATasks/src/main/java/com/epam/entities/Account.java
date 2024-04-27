package com.epam.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounts")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Integer id;

    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_password")
    private String accountPassword;
    @Column(name = "account_status")
    private boolean accountStatus;
    @Column(name = "account_created_date")
    private Date accountCreatedDate;
    @Column(name = "account_modified_date")
    private Date accountModifiedDate;
    @Column(name = "account_deleted_date")
    private Date accountDeletedDate;
    public Account() {
        this.id = id;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountStatus = accountStatus;
        this.accountCreatedDate = accountCreatedDate;
        this.accountModifiedDate = accountModifiedDate;
        this.accountDeletedDate = accountDeletedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(Date accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

    public Date getAccountModifiedDate() {
        return accountModifiedDate;
    }

    public void setAccountModifiedDate(Date accountModifiedDate) {
        this.accountModifiedDate = accountModifiedDate;
    }

    public Date getAccountDeletedDate() {
        return accountDeletedDate;
    }

    public void setAccountDeletedDate(Date accountDeletedDate) {
        this.accountDeletedDate = accountDeletedDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountStatus=" + accountStatus +
                ", accountCreatedDate=" + accountCreatedDate +
                ", accountModifiedDate=" + accountModifiedDate +
                ", accountDeletedDate=" + accountDeletedDate +
                '}';
    }


}
