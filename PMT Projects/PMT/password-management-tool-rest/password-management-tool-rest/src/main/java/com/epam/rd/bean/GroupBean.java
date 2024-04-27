package com.epam.rd.bean;

import com.epam.rd.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class GroupBean {

    private int groupId;
    @NotNull
    @Size(min = 5 , max = 20 , message = "Group name Should have min 5 chars and max 20 chars")
    @Pattern(regexp = Constants.ACCOUNT_GROUP_NAME_REGEX, message = "Group Name should be lower case alphabets and can have underscore(_)")
    private String groupName;
    @JsonIgnore
    private Date createdAt;
    @JsonIgnore
    private Date lastModifiedAt;
    @JsonManagedReference
    private List<AccountBean> accounts;
    @JsonIgnore
    private UserBean user;

    public GroupBean(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupBean groupBean = (GroupBean) o;
        return groupId == groupBean.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }
}
