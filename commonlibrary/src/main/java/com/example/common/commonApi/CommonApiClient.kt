package com.example.common.commonApi

import com.example.common.commonApi.responses.AccessTokenResponse
import io.reactivex.Observable

class CommonApiClient {

    private val isLogging: Boolean = false

    private var baseUrl: String? = null
    private var apiHeader: ApiHeader? = null
    private var commonApi: CommonApi? = null


    /**
     * アクセストークン取得[VIP000060]
     *
     * @param grantType    アクセストークン取得タイプ<br></br>
     * [GrantType]にて定義された値
     * @param clientId     クライアントID
     * @param clientSecret クライアント秘密鍵
     * @return アクセストークン取得Observable
     */
    fun requestAccessToken(grantType: String, clientId: String, clientSecret: String): Observable<AccessTokenResponse> {
        return commonApi!!.accessToken(grantType, clientId, clientSecret)
    }

}