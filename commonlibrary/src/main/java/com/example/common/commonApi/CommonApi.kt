package com.example.common.commonApi

import androidx.annotation.Nullable
import com.example.common.commonApi.responses.AccessTokenResponse
import io.reactivex.Observable
import retrofit2.http.*

interface CommonApi {

    /**
     * アクセストークン取得[VIP000060]API
     *
     * @param grantType    取得タイプ
     * @param clientId     API利用者に対して付与した一意なクライアントID
     * @param clientSecret API利用者に対して付与したクライアントシークレット
     * @return アクセストークン取得 Observable
     */
    @FormUrlEncoded
    @POST(CommonApiClientInfo.ACCESS_TOKEN_PATH)
    abstract fun accessToken(
        @Field("grant_type") @Nullable grantType: String,
        @Field("client_id") @Nullable clientId: String,
        @Field("client_secret") @Nullable clientSecret: String
    ): Observable<AccessTokenResponse>


}