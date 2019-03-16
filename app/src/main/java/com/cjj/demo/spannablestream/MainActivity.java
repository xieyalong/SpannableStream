package com.cjj.demo.spannablestream;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.spannablestream.SpannableOperate;
import com.cjj.spannablestream.SpannableStream;
import com.cjj.spannablestream.click.SimpleSpannableClickListener;
import com.cjj.spannablestream.color.ColorConfig;
import com.cjj.spannablestream.interfacer.IClickable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextView1();
        initTextView2();
    }

    private void initTextView1() {
        TextView mTextView1 = (TextView) findViewById(R.id.textView1);
        SpannableStream.with(this)
                //追加文字
                .appendText("Example 1")
                //文字居中 和最好使用aligmentLeft，aligmentCenter，aligmentRight
                .aligmentCenter()
                //下划线
                .underline()
                //换行
                .appendNewLine()
                //追加文字
                .appendText("ForegroundColor")
                //字体颜色
                .colorRes(android.R.color.holo_red_light).appendNewLine()
                //追加文字
                .appendText("BackgroundColor")
                //背景颜色
                .bgColorRes(android.R.color.holo_orange_light)
                //字体颜色
                .color(Color.BLUE)
                //换几行
                .appendNewLine(2)
                //追加文字
                .appendText("Bold,")
                //粗体
                .bold()
                //斜体
                .appendText("Italic,").italic()
                //中划线
                .appendText("StrikeThrough").strikeThrough().appendNewLine(2)
                .appendText("Plain").
                //文字向上突出
                appendText("SuperScript").superScript()
                //文字向下突出
                .appendText("SubScript").subScript().appendNewLine(2)
                //dp文字大小
                .appendText("40dp ").textSizeDp(40)
                //px文字大小
                .appendText("40px ").textSizePx(40)
                //sp文字大小
                .appendText("40sp").textSizeSp(40).appendNewLine(2)

                .appendText("1.0x TextSize")
                .appendText("1.5x TextSize").
                //类似于字体大小
                relativeTextSize(1f).appendNewLine()
                //变形
                .appendText("ScaleX").scaleX(4).appendNewLine()
                //中间插入图片
                .appendImage(R.mipmap.ic_launcher).appendText("ImageSpan").appendNewLine()
                //网页地址
                .appendUrlText("https://www.baidu.com").appendNewLine()
                //字体靠左
                .appendText("Aligment Left").aligmentLeft().appendNewLine()
                //字体居中
                .appendText("Aligment Middle").aligmentCenter().appendNewLine()
                //字体靠右
                .appendText("Aligment Right").aligmentRight().appendNewLine(2)
                .appendText("-------------------------------------").colorRes(android.R.color.holo_red_light).aligmentCenter().appendNewLine()
                .into(mTextView1);
    }

    private void initTextView2() {
        //替换的样式
        SpannableOperate mReplaceAttributes = SpannableOperate.with(this)
                .italic()
                .relativeTextSize(2f)
                .onClick(ColorConfig.getDefault()
                                //正常字体颜色
                                .colorNormal(this, android.R.color.holo_blue_light)
                                //正常背景颜色
                                .bgColorNormal(Color.TRANSPARENT)
                                //按下字体颜色
                                .colorPressed(Color.WHITE)
                                //按下背景颜色
                                .bgColorPressed(this, android.R.color.holo_red_light)
                        , new SimpleSpannableClickListener() {
                            @Override
                            public void onSpannableItemClick(View widget, CharSequence str) {
                                Toast.makeText(widget.getContext(), "替换样式="+str, Toast.LENGTH_SHORT).show();
                            }
                        });

        SpannableStream.with(this)
                .appendText("这是onClick")
                .onClick(ColorConfig.getDefault()
                                .colorNormal(this, android.R.color.holo_red_light)//正常字体颜色
                                .colorPressed(this, android.R.color.holo_green_light)//按下字体颜色
                                .bgColorNormal(getResources().getColor(R.color.colorPrimary))//正常背景颜色
                                .bgColorPressed(getResources().getColor(R.color.colorPrimaryDark))//按下背景颜色
                        , new IClickable.OnSpannableClickListener() {
                            //单机事件
                            @Override
                            public void onSpannableItemClick(View widget, CharSequence str) {
                                Toast.makeText(MainActivity.this, "单机事件内容="+str, Toast.LENGTH_SHORT).show();
                            }
                            //按下 弹起事件
                            @Override
                            public void onPressedStateChanged(boolean isPressed) {
                                Toast.makeText(MainActivity.this, "b=" + (isPressed ? "按下" : "弹起"), Toast.LENGTH_SHORT).show();
                            }
                        })
                //设置属性
                .aligmentCenter().textSizeSp(20).underline().appendNewLine()
//                .appendText(R.string.text)
                .appendText("可以替换文本{text}中所有指定{text}的样式，例如替换本段中的“text”")
                //对“text”替换样式
                .replaceString("text", mReplaceAttributes)
                .into(this, R.id.textView2);
    }
}
