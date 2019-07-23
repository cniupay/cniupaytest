package com.cniupay.app.cniupaytest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cniupay.pay.CNiuPay;
import com.cniupay.pay.common.PayResult;
import com.cniupay.pay.enums.PayResultCodeEnum;
import com.cniupay.pay.listener.PayResultListener;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private EditText title;
    private EditText amount;
    private Button confirm;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("丑牛支付-测试支付");
        context = this;
        title = (EditText) findViewById(R.id.title);
        amount = (EditText) findViewById(R.id.amount);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleInput = title.getText().toString();
                String amountStr = amount.getText().toString();
                if (null == titleInput || titleInput.length() <= 0) {
                    Toasty.warning(context, "标题不能为空").show();
                    return;
                }
                if (null == amountStr || amountStr.length() <= 0) {
                    Toasty.warning(context, "金额不能为空").show();
                    return;
                }
                try {
                    int amountInt = Integer.parseInt(amountStr);
                    CNiuPay.getInstance(context).pay(amountInt, titleInput, null, new PayResultListener() {
                        @Override
                        public void onPayFinished(Context context, PayResultCodeEnum payResult, String resultMsg, PayResult data) {
                            if (PayResultCodeEnum.SUCCESS == payResult) {
                                Toasty.info(context, "支付成功").show();
                            } else {
                                Toasty.info(context, "支付失败").show();
                            }
                        }
                    });
                } catch (NumberFormatException e) {
                    Toasty.warning(context, "输入金额错误").show();
                }
            }
        });
    }
}
