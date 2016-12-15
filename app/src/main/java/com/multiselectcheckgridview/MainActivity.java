package com.multiselectcheckgridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView mGridView;
    private LinearLayout mLlAddStuffContainer;
    private Button mButtonAdd;
    private Button mButtonOther;
    private Button mButtonGetData;
    private EditText mEtItem;
    private CustomAdapter customAdapter;
    private List<DataModel> mDataModelList;
    public static  List<DataModel> mSelectedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataModelList=new ArrayList<>();
        setDataToList();

        mGridView = (GridView) findViewById(R.id.gv_container);
        mLlAddStuffContainer = (LinearLayout) findViewById(R.id.ll_add_stuff_container);
        mEtItem = (EditText) findViewById(R.id.et_add);
        mButtonAdd = (Button) findViewById(R.id.btn_add);
        mButtonOther = (Button) findViewById(R.id.btn_other);
        mButtonGetData = (Button) findViewById(R.id.btn_get_data);
        mButtonAdd.setOnClickListener(this);
        mButtonOther.setOnClickListener(this);
        mButtonGetData.setOnClickListener(this);
        customAdapter = new CustomAdapter(this, mDataModelList);
        mGridView.setAdapter(customAdapter);
        mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

                view.setSelected(true);
                customAdapter.notifyDataSetChanged();
            }

        });

    }

    private List<DataModel> setDataToList() {
        for (int i = 0; i < 10; i++) {
            DataModel d = new DataModel();
            d.setName("Aalishan");
          //  d.setFlag(true);
            mDataModelList.add(d);
        }
        return mDataModelList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_other:
                mLlAddStuffContainer.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_add:
                String item = mEtItem.getText().toString();
                boolean status = true;
                if (item.length() > 3) {
                    DataModel d = new DataModel();
                    d.setName(item);
                  //  d.setFlag(status);
                    mDataModelList.add(d);
                    mGridView.setItemChecked(mDataModelList.size(), true);
                    customAdapter.notifyDataSetChanged();
                    mEtItem.setText("");
                }
                break;
            case R.id.btn_get_data:
                mSelectedItems=new ArrayList<>();
                SparseBooleanArray checkedLeads = mGridView.getCheckedItemPositions();
                int checkedItemsCount = checkedLeads.size();
                for (int i = 0; i < checkedItemsCount; ++i) {
                    int position = checkedLeads.keyAt(i);
                    if (checkedLeads.valueAt(i))
                        mSelectedItems.add((DataModel) customAdapter.getItem(position));
                }

                  Toast.makeText(MainActivity.this, "" + mSelectedItems.size(), Toast.LENGTH_LONG).show();

                break;
        }
    }
}
