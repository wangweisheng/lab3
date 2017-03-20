package hua.com.uitest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by qinghua on 2017/3/14.
 */

public class MenuActivity extends AppCompatActivity {
    private EditText editText;//被测试的的内容
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        initView();
    }

    private void initView() {
        editText= (EditText) findViewById(R.id.editText);
    }

    /*
    *绑定自定义菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menutest,menu);
        return true;
    }

    /*
    *响应菜单项
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Nomalmenu:
                Toast.makeText(MenuActivity.this,"你正处在普通菜单项",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ten:
                editText.setTextSize(10);
                break;
            case R.id.sixteen:
                editText.setTextSize(16);
                break;
            case R.id.twenty:
                editText.setTextSize(20);
                break;
            case R.id.red:
                editText.setTextColor(Color.RED);
                break;
            case R.id.black:
                editText.setTextColor(Color.BLACK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
