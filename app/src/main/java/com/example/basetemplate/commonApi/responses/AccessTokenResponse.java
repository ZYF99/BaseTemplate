package com.example.basetemplate.commonApi.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * アクセストークン取得[VIP000060]<br>
 * レスポンスクラス
 */
public class AccessTokenResponse implements Serializable {

    /**
     * アクセストークン
     */
    @SerializedName("access_token")
    public String access_token;
    /**
     * トークンタイプ
     */
    @SerializedName("token_type")
    public String token_type;
    /**
     * アクセストークン失効時間
     */
    @SerializedName("expires_in")
    public long expires_in;

    /**
     * コンストラクタ
     *
     * @param accessToken アクセストークン
     * @param tokenType   トークンタイプ
     * @param expiresIn   アクセストークン失効時間
     */
    public AccessTokenResponse(String accessToken,
                               String tokenType,
                               long expiresIn) {
        this.access_token = accessToken;
        this.token_type = tokenType;
        this.expires_in = expiresIn;
    }
}


