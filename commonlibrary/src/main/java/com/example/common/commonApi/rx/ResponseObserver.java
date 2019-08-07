package com.example.common.commonApi.rx;

import io.reactivex.Observer;
import retrofit2.adapter.rxjava.HttpException;


/**
 * 数据返回成功的observer
 * HttpError时的message解析
 */
public abstract class ResponseObserver<T> implements Observer<T> {


    /**
     * 成功
     *
     * @param responseData 返回数据
     */
    @Override
    public final void onNext(T responseData) {
        //请求成功时
        onSuccess(responseData);
    }

    /**
     * 结束
     */
    @Override
    public void onComplete() {

    }

    /**
     * 失败
     * 如果是HttpException错误则解析
     */
    @Override
    public final void onError(Throwable e) {
        int errorCode = -1;
        String errorMessage = null;

        if (e instanceof HttpException) {
            HttpException error = (HttpException) e;

            // HTTPエラーを取得
            errorCode = error.code();
            errorMessage = error.getMessage();
            // TODO: 错误提醒
        }


        onFailure(e, errorCode, errorMessage);
    }




    /**
     * API请求失败时的回调
     *
     * @param e                エラー
     * @param httpErrorCode    HttpErrorCode, 无法获取时 '-1'
     * @param httpErrorMessage HttpErrorMessage, 无法获取时 Null
     */
    public abstract void onFailure(Throwable e, int httpErrorCode, String httpErrorMessage);

    /**
     * API请求成功時的回调
     *
     * @param response api response 接受数据的body部分
     */
    public abstract void onSuccess(T response);
}
