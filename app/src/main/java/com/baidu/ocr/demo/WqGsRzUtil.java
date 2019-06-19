package com.baidu.ocr.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WqGsRzUtil {

    private static OkHttpClient client = new OkHttpClient();
    private static String url = "http://stamp.market.alicloudapi.com/api/predict/ocr_official_seal";
    private static String appcode = "e5ebb766920147ea8aaa6e069a28fb79";

    public void apply(Activity mActivity, Bitmap bmb, Callback<String, GsRzData> callback) {
        String bodys = "{\"image\":\"" + compressImageToBase64(bmb) + "\"}";
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appcode).addHeader("Content-Type", "application/json; charset=UTF-8").post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodys)).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("999996:  " + e.getMessage());
                mActivity.runOnUiThread(() -> callback.onError(e.getMessage()));
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.code() >= 200 && response.code() < 300) {
                    String responseString = response.body().string();
                    System.out.println("999995: " + responseString);
                    GsRzData zFBFHSJ = new Gson().fromJson(responseString, GsRzData.class);
                    mActivity.runOnUiThread(() -> callback.onSuccess(zFBFHSJ));
                } else {
                    mActivity.runOnUiThread(() -> callback.onConfirming(response.toString()));

                }
            }
        });

    }

    private String compressImageToBase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        byte[] bytes = baos.toByteArray();
        String url = new String(Base64Coder.encode(bytes));
        System.out.println("99999588: " + url);
        return url;
    }


    public static abstract class Callback<T, GsRzData> {

        public abstract void onSuccess(GsRzData t);

        public abstract void onError(T t);

        public abstract void onConfirming(T t);

    }

}
