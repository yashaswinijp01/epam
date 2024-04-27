package com.epam.rd.bean;

import com.epam.rd.utils.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Component("userBean")
public class UserBean {
    private int userId;

    @NotNull
    @Size(min = 5 , max = 20 , message = "Name should be of min 5 chars and max 20 chars")
    private String name;

    @NotNull
    @Size(min = 5 , max = 30 , message = "User Name should be of min 5 chars and max 30 chars")
    private String userName;

    @NotNull
    @Pattern(regexp = Constants.PASSWORD_REGEX, message = "Password Should Contain Lower case , Upper case , Numbers and Special chars with min of 8 chars")
    private String password;

    private Date createdAt;

    private Date lastModifiedAt;

    private List<AccountBean> accountBeans;
    private List<GroupBean> groupBeans;

    public UserBean(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return userId == userBean.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

