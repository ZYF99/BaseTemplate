package com.example.common.commonApi;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ApiHeader情報クラス<br>
 * <br>
 * ・各値はアプリ起動中は変わらないため使い回し可能<br>
 * ・AccessTokenのみ変わる可能性があるため別で管理することを推奨、そのため本クラスには含めない(ステータス名のみ記載)<br>
 * ・AccessTokenは同一Service内で使い回し可能(有効期限は10年単位)<br>
 */
public class ApiHeader implements Serializable {

    public final static String AUTHORIZATION = "X-Authorization";
    public final static String VIDEO_MARKET_BRAND = "X-VideoMarket-Brand";
    public final static String VIDEO_MARKET_BOARD = "X-VideoMarket-Board";
    public final static String VIDEO_MARKET_DEVICE = "X-VideoMarket-Device";
    public final static String VIDEO_MARKET_MANUFACTURER = "X-VideoMarket-Manufacturer";
    public final static String VIDEO_MARKET_MODEL = "X-VideoMarket-Model";
    public final static String VIDEO_MARKET_PRODUCT = "X-VideoMarket-Product";
    public final static String VIDEO_MARKET_APP_VER_CODE = "X-VideoMarket-AppVerCode";
    public final static String VIDEO_MARKET_APP_VER_NAME = "X-VideoMarket-AppVerName";
    public final static String VIDEO_MARKET_OS_VER = "X-VideoMarket-OsVer";
    public final static String VIDEO_MARKET_SUPPORT_4K = "X-Support-4K";

    private final String brand;
    private final String board;
    private final String device;
    private final String manufacturer;
    private final String model;
    private final String product;
    private final String appVerCode;
    private final String appVerName;
    private final String osVer;

    /**
     * コンストラクタ<br>
     * デバイスとアプリに関する情報を設定する
     */
    public ApiHeader(String brand, String board, String device, String manufacturer,
                     String model, String product, String appVerCode, String appVerName, String osVer) {
        this.brand = brand;
        this.board = board;
        this.device = device;
        this.manufacturer = manufacturer;
        this.model = model;
        this.product = product;
        this.appVerCode = appVerCode;
        this.appVerName = appVerName;
        this.osVer = osVer;
    }

    public Map<String, String> getHeader() {
        HashMap<String, String> map = new HashMap<>();
        map.put(VIDEO_MARKET_BRAND, brand);
        map.put(VIDEO_MARKET_BOARD, board);
        map.put(VIDEO_MARKET_DEVICE, device);
        map.put(VIDEO_MARKET_MANUFACTURER, manufacturer);
        map.put(VIDEO_MARKET_MODEL, model);
        map.put(VIDEO_MARKET_PRODUCT, product);
        map.put(VIDEO_MARKET_APP_VER_CODE, appVerCode);
        map.put(VIDEO_MARKET_APP_VER_NAME, appVerName);
        map.put(VIDEO_MARKET_OS_VER, osVer);
        map.put(VIDEO_MARKET_SUPPORT_4K, isSupport4K() ? "1" : "0");
        return map;
    }

    /**
     * 4K対応ディスプレイに接続されているか確認<br>
     * 4K作品の面出しの条件に使用される
     *
     * @return true:supported 4K, false:unSupported 4K
     */
    private boolean isSupport4K() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            MediaCodecList mediaCodecList = new MediaCodecList(MediaCodecList.ALL_CODECS);
            MediaCodecInfo[] mediaCodecInfos = mediaCodecList.getCodecInfos();
            for (MediaCodecInfo mediaCodecInfo : mediaCodecInfos) {
                String[] types = mediaCodecInfo.getSupportedTypes();
                for (String type : types) {
                    MediaCodecInfo.CodecCapabilities codecCapabilities = mediaCodecInfo.getCapabilitiesForType(type);
                    if (mediaCodecInfo.isEncoder()) {
                        continue;
                    }
                    if (!Objects.equals(type, MediaFormat.MIMETYPE_VIDEO_AVC)) {
                        continue;
                    }
                    if (!codecCapabilities.isFeatureSupported(MediaCodecInfo.CodecCapabilities.FEATURE_SecurePlayback)) {
                        continue;
                    }
                    try {
                        MediaCodecInfo.VideoCapabilities videoCapabilities = mediaCodecInfo.getCapabilitiesForType(MediaFormat.MIMETYPE_VIDEO_AVC).getVideoCapabilities();
                        if (videoCapabilities != null && videoCapabilities.isSizeSupported(3840, 2160)) {
                            return true;
                        }
                    } catch (Exception e) {
                        // 握り潰す
                    }
                }
            }
        }
        return false;
    }
}
