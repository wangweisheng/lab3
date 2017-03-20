package hua.com.uitest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinghua on 2017/3/19.
 */

public class ActionModeActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter adapter;
    private List<String> mDate;
    private static String TAG="ActionMode";
    private MultiChoiceListener mMultiChoiceListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionmode_test);
        initDate();
        initView();
        SetListViewClick();
    }

    private void SetListViewClick() {
        listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.setSelected(true);
                return false;
            }
        });

    }

    private void initDate() {
        mDate=new ArrayList<String>();
        for(int i=1;i<=10;i++)
        {
            mDate.add("Item"+i);
        }
    }

    private void initView() {
        mMultiChoiceListener=new MultiChoiceListener();
        listView= (ListView) findViewById(R.id.lv_actionmode);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(mMultiChoiceListener);
        listView.setSelector(R.color.listItemBg);
        adapter=new ArrayAdapter(ActionModeActivity.this,R.layout.support_simple_spinner_dropdown_item,mDate);
        listView.setAdapter(adapter);
    }

    class MultiChoiceListener implements AbsListView.MultiChoiceModeListener {

        private List<Integer> mPosition=new ArrayList<Integer>();//全部元素的索引
        private int position;//单一索引
        private boolean isSellectedAll=false;//是否全选的标识

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            Log.d(TAG, "onItemCheckedStateChanged"+position);
            this.position=position;
            mode.setTitle("Item"+(position+1)+" Selected");

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.d(TAG, "onCreateActionMode");
            MenuInflater inflater=getMenuInflater();
            inflater.inflate(R.menu.actionmode,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.d(TAG, "onPrepareActionMode");
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d(TAG, "onActionItemClicked");
            switch(item.getItemId())
            {
                case R.id.action_delete:
                    Log.d(TAG, "action_deleteClicked");
                    if(mPosition.size()==mDate.size())
                    {
                        mDate.clear();
                        adapter.notifyDataSetChanged();
                    }
                    else
                    {
                        if(position<mDate.size())
                        {
                            mDate.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                        else
                        {
                            mode.setTitle(" No Items Selected");
                        }
                    }
                    break;
                case R.id.action_choose_all:
                    if(!isSellectedAll)
                    {

                        if(mDate.size()==0) {
                            mode.setTitle(" No Items Selected");
                        } else {
                            mode.setTitle(" All Items Selected");
                        }
                        mPosition.clear();
                        for(int i=0;i<mDate.size();i++)
                        {
                            mPosition.add(i);
                        }
                        listView.setBackgroundResource(R.color.listItemBg);
                        isSellectedAll=true;
                    }
                    else
                    {
                        if(mDate.size()==0) {
                            mode.setTitle(" No Items Selected");
                        }
                        else {
                            mode.setTitle("Item" + (position + 1) + " Selected");
                        }
                        listView.setBackgroundResource(R.color.white);
                        mPosition.clear();
                        isSellectedAll=false;
                    }
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.d(TAG, "onDestroyActionMode");

        }
    }
}
