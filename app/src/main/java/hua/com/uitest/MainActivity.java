package hua.com.uitest;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btn_listview,btn_AlterDialog,btn_mune,btn_actionmode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_listview= (Button) findViewById(R.id.btn_listview);
        btn_AlterDialog= (Button) findViewById(R.id.btn_AlterDialog);
        btn_mune= (Button) findViewById(R.id.btn_mune);
        btn_actionmode= (Button) findViewById(R.id.btn_actionmode);
        //设置监听
        btn_listview.setOnClickListener(this);
        btn_AlterDialog.setOnClickListener(this);
        btn_mune.setOnClickListener(this);
        btn_actionmode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_listview:
                Intent intent1=new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_AlterDialog:
                show_AlterDialog();
                break;

            case R.id.btn_mune:
                Intent intent2=new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_actionmode:
                Intent intent3=new Intent(MainActivity.this,ActionModeActivity.class);
                startActivity(intent3);
                break;

        }
    }

    /*
    *显示自定义的AlterDialog
     */
    private void show_AlterDialog() {
        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.alterdialog,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
