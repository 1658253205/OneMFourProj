package com.example.administrator.onemfourproj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.onemfourproj.Adapter.LettersAdapter;
import com.example.administrator.onemfourproj.Utils.LettersSorting;
import com.example.administrator.onemfourproj.Utils.Trans2PinYin;
import com.example.administrator.onemfourproj.View.LettersModel;
import com.example.administrator.onemfourproj.View.LettersView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LettersView.OnLettersListViewListener{
    //TAG
    private static final String TAG = "Letters";

    //联系人列表
    private ListView mListView;
    //字母提示
    private TextView tvToast;
    //字母列表
    private LettersView mLettersView;
    //清除按钮
    private ImageView tv_cancel;
    //搜索框
    private EditText et_search;
    //列表数据
    private List<LettersModel> mList = new ArrayList<>();
    //数据源
    private LettersAdapter adapter;
    //联系人数据模拟
    private String[] strName = {"张三", "李四", "李七", "刘某人", "王五", "Android", "IOS", "王寡妇",
            "阿三", "爸爸", "妈妈", "CoCo", "弟弟", "尔康", "紫薇", "小燕子", "皇阿玛", "福尔康", "哥哥", "Hi", "I", "杰克", "克星", "乐乐", "你好", "Oppo", "皮特", "曲奇饼",
            "日啊", "思思", "缇娜", "U", "V", "王大叔", "嘻嘻", "一小伙子", "撒贝宁", "吱吱", "舅舅", "老总", "隔壁老王", "许仙"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    /**
     * 初始化View
     */
    private void initView() {
        tv_cancel = (ImageView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.mListView);
        tvToast = (TextView) findViewById(R.id.tvToast);
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    tv_cancel.setVisibility(View.VISIBLE);
                } else {
                    tv_cancel.setVisibility(View.GONE);
                }
            }
        });
        mLettersView = (LettersView) findViewById(R.id.mLettersView);
        mLettersView.setmTextView(tvToast);
        //绑定
        mLettersView.setOnLettersListViewListener(this);

        //加载联系人的模拟数据
        mList = parsingData();
        //对字母进行排序A-Z #
        Collections.sort(mList, new LettersSorting());
        //加载适配器
        adapter = new LettersAdapter(this, mList);
        //设置数据
        mListView.setAdapter(adapter);
    }

    /**
     * 联系人数组转换实体数据
     *
     * @return
     */
    private List<LettersModel> parsingData() {
        List<LettersModel> listModel = new ArrayList<>();
        Log.i(TAG, " strName.length:" + strName.length);
        for (int i = 0; i < strName.length; i++) {
            LettersModel model = new LettersModel();
            model.setName(strName[i]);
            Log.i(TAG, strName[i]);
            //转换拼音截取首字母并且大写
            String pinyin = Trans2PinYin.trans2PinYin(strName[i]);
            Log.i(TAG, "pinyin:" + pinyin);
            String letter = pinyin.substring(0, 1).toUpperCase();
            Log.i(TAG, "letter:" + letter);
            model.setLetter(letter);
            listModel.add(model);
        }
        return listModel;
    }

    /**
     * ListView与字母导航联动
     *
     * @param s
     */
    @Override
    public void onLettersListener(String s) {
        //对应的位置
        int position = adapter.getPositionForNmae(s.charAt(0));
        //移动
        mListView.setSelection(position);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                et_search.setText("");
                tv_cancel.setVisibility(View.GONE);
                break;
        }
    }
}
