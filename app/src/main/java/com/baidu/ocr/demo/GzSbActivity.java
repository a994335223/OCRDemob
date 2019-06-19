package com.baidu.ocr.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class GzSbActivity extends AppCompatActivity {
    private static OkHttpClient client = new OkHttpClient();
    private Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gz_sb);
        ImageView imageViewA = findViewById(R.id.test_iv_a);
        ImageView imageViewB = findViewById(R.id.test_iv_b);
//        Bitmap bm = ((BitmapDrawable) imageViewA.getDrawable()).getBitmap();
        bm = BitmapFactory.decodeResource(getResources(), R.mipmap.gzcs);
        imageViewA.setOnClickListener(v -> imageViewB.setImageBitmap(sendImage(compressImageToBase64(bm))));
//        gzSbc(bm);
        gzSbd(this, bm);

    }

    private void gzSbd(Activity activity, Bitmap bm) {
        new WqGsRzUtil().apply(activity, bm, new WqGsRzUtil.Callback<String, GsRzData>() {
            @Override
            public void onSuccess(GsRzData t) {
                System.out.println("99999111:  " + t.toString());
                Toast.makeText(GzSbActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                System.out.println("99999222:  " + s);
                Toast.makeText(GzSbActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onConfirming(String s) {
                Toast.makeText(GzSbActivity.this, s, Toast.LENGTH_LONG).show();
                System.out.println("99999333:  " + s);
            }
        });
    }


    private void gzSbc(Bitmap bmb) {
        String url = "http://stamp.market.alicloudapi.com/api/predict/ocr_official_seal";
        String appcode = "e5ebb766920147ea8aaa6e069a28fb79";
        String bodys = "{\"image\":\"" + compressImageToBase64(bmb) + "\"}";
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appcode).addHeader("Content-Type", "application/json; charset=UTF-8").post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodys)).build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("999996:  " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.code() >= 200 && response.code() < 300) {
                    System.out.println("999995: " + response.body().string());
                }
            }
        });
    }

    public String encodeImage(Bitmap bitmap) {//编码
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //参数如果为100那么就不压缩
        byte[] bytes = baos.toByteArray();
        String strbm = Base64.encodeToString(bytes, Base64.DEFAULT);
        System.out.println("99999566: " + strbm);
        return strbm;
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

    public Bitmap sendImage(String bmMsg) {//解码
        byte[] input = Base64.decode(bmMsg, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
        return bitmap;
    }
}
