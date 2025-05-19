package org.example.OOP;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

public class PbxRequestHolder {

    private final HttpServletRequest httpServletRequest;

    public PbxRequestHolder(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public Optional<String> getOptAccountId() {
        return getOptAttribute(PbxRequestHeaderKey.PBX_ACCOUNT_ID.getKey(), String.class);
    }

    public Optional<Account> getOptAccount() {
        return getOptAttribute(PbxRequestHeaderKey.PBX_ACCOUNT.getKey(), Account.class);
    }

    public Optional<String> getOptUserId() {
        return getOptAttribute(PbxRequestHeaderKey.PBX_USER_ID.getKey(), String.class);
    }

    public Optional<User> getOptUser() {
        return getOptAttribute(PbxRequestHeaderKey.PBX_USER.getKey(), User.class);
    }

    public String getAccountId() {
        return getNonNullAttribute(PbxRequestHeaderKey.PBX_ACCOUNT_ID.getKey(), String.class);
    }

    public Account getAccount() {
        return getNonNullAttribute(PbxRequestHeaderKey.PBX_ACCOUNT.getKey(), Account.class);
    }

    public String getUserId() {
        return getNonNullAttribute(PbxRequestHeaderKey.PBX_USER_ID.getKey(), String.class);
    }

    public User getUser() {
        return getNonNullAttribute(PbxRequestHeaderKey.PBX_USER.getKey(), User.class);
    }

    private <T> Optional<T> getOptAttribute(String key, Class<T> tClass) {
        return Optional.ofNullable(getAttribute(key, tClass));
    }

    private <T> T getNonNullAttribute(String key, Class<T> tClass) {
        return Objects.requireNonNull(getAttribute(key, tClass), MessageFormat.format("%s is null", key));
    }

    private <T> T getAttribute(String key, Class<T> tClass) {
        Object obj = httpServletRequest.getAttribute(key);
        if (Objects.isNull(obj)) {
            return null;
        }
        return tClass.cast(obj);
    }
}

enum PbxRequestHeaderKey {
    PBX_ACCOUNT("pbx_account"),
    PBX_ACCOUNT_ID("pbx_account_id"),
    PBX_USER("pbx_user"),
    PBX_USER_ID("pbx_user_id");

    private final String key;

    PbxRequestHeaderKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

class Account {}

class User {}