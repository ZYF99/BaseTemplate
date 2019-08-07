package com.example.common.commonApi.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * 获取token
 */
public class AccessTokenResponse implements Serializable {

    /**
     * token
     */
    @SerializedName("access_token")
    public String access_token;
    /**
     * token类型
     */
    @SerializedName("token_type")
    public String token_type;
    /**
     * token失効時間
     */
    @SerializedName("expires_in")
    public long expires_in;

    /**
     * constructor
     *
     * @param accessToken token
     * @param tokenType   token类型
     * @param expiresIn   token失效时间
     */
    public AccessTokenResponse(String accessToken,
                               String tokenType,
                               long expiresIn) {
        this.access_token = accessToken;
        this.token_type = tokenType;
        this.expires_in = expiresIn;
    }
}


