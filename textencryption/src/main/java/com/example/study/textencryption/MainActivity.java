package com.example.study.textencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;



public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.et_input_content)
    private EditText et_input_content;
    @ViewInject(R.id.tv_en_result)
    private TextView tv_en_result;
    @ViewInject(R.id.tv_de_result)
    private TextView tv_de_result;
    @ViewInject(R.id.bt_enCryption)
    private Button bt_enCryption;
    @ViewInject(R.id.bt_deCryption)
    private Button bt_deCryption;

    //加密后的结果
    private String encrypt_result;
    //解密后的结果
    private String de_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        //加密
        bt_enCryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    encrypt_result = et_input_content.getText().toString();
                    if (!TextUtils.isEmpty(encrypt_result)) {
                        tv_en_result.setText(new PBencryption().encryption(encrypt_result));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        //解密的方法
        bt_deCryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    de_result = tv_en_result.getText().toString();
                    if (!TextUtils.isEmpty(de_result)) {
                        tv_de_result.setText(new PBencryption().decryption(de_result));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
