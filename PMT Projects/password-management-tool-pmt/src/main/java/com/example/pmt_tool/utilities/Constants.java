package com.example.pmt_tool.utilities;

public class Constants {
    public static final String REGEX_FOR_USERNAME_VALIDATION = "[a-zA-Z$_]{4,}";
    public static final String REGEX_FOR_ACCOUNT_NAME_VALIDATION = "[a-zA-Z$_]{4,}";
    public static final String REGEX_FOR_PASSWORD_VALIDATION = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    public static final String REGEX_FOR_VALIDATION_URL = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
    public static final String MESSAGE_FOR_INVALID_USER_NAME = "UserName can contain alphabets and underscore with minimum length of 4";
    public static final String MESSAGE_FOR_INVALID_ACCOUNT_NAME = "AccountName can contain alphabets and underscore with minimum length of 4";
    public static final String MESSAGE_FOR_INVALID_PASSWORD = "Password should include lowercase alphabets,uppercase alphabets,digits,special characters with minimum length of 8 and maximum length of 20";
    public static final String MESSAGE_FOR_INVALID_URL = "Please enter valid URL (ex:https://www.google.com)";

    public static final String REGEX_FOR_MASTER_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    public static final String MESSAGE_FOR_INVALID_MASTER_PASSWORD = "Password should include lowercase alphabets,uppercase alphabets,digits,special characters with minimum length of 8 and maximum length of 20";

    public static final String REGEX_FOR_GROUP_NAME = "[a-zA-Z$_]{4,}";
    public static final String MESSAGE_FOR_INVALID_GROUP_NAME = "GroupName can contain alphabets and underscore with minimum length of 4";

}
