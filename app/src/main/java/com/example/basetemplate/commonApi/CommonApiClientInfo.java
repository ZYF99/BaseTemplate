package com.example.basetemplate.commonApi;

/**
 * 再生API(共通化)実行情報クラス
 */
/* package-private*/ class CommonApiClientInfo {
    /**
     * アクセストークンパス (/authorize/access_token)
     */
    static final String ACCESS_TOKEN_PATH = "authorize/access_token";
    /**
     * キー発行パス (/api/play/keyissue)
     */
    static final String KEY_ISSUE_PATH = "api/play/keyissue";
    /**
     * キー認証パス (/api/play/keyauth)
     */
    static final String KEY_AUTH_PATH = "api/play/keyauth";
    /**
     * 再発行パス (/api/play/regrant)
     */
    static final String REGRANT_PATH = "api/play/regrant";
    /**
     * ダウンロードURL取得[VIP001]API
     */
    static final String DOWNLOAD_URL = "api/download/getUrl";
    /**
     * ダウンロード完了通知[VIP002]API
     */
    static final String DOWNLOAD_DONE_NOTICE_PATH = "api/download/doneNotice";
    /**
     * ダウンロードライセンス取得[VIP003]API
     */
    static final String DOWNLOAD_LICENSE = "api/download/getLicense";
    /**
     * ファイル削除通知[VIP004]API
     */
    static final String DOWNLOAD_DELETE_NOTICE_PATH = "api/download/deleteNotice";
    /**
     * 再生ログ同期[VIP005]API
     */
    static final String DOWNLOAD_PLAYLOG_SYNC_PATH = "api/download/playLogSync";
    /**
     * ダウンロード操作ログ通知[VIP006]API
     */
    static final String DOWNLOAD_ACTION_LOG_NOTICE_PATH = "api/download/actionLogNotice";
    /**
     * 視聴期限同期[VIP007]API
     */
    static final String DOWNLOAD_EXPIRE_DATETIME_SYNC_PATH = "api/download/expireDatetimeSync";
}
