package com.multiselectcheckgridview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView mGridView;
    private LinearLayout mLlAddStuffContainer;
    private Button mButtonAdd;
    private Button mButtonGetData;
    private Button mButtonOther;
    private EditText mEtItem;
    private MyCustomAdapter customAdapter;
    private ArrayList<DataModel> mDataModelList;
    private ArrayList<DataModel> mSelectedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.gv_container);
        mLlAddStuffContainer = (LinearLayout) findViewById(R.id.ll_add_stuff_container);
        mEtItem = (EditText) findViewById(R.id.et_add);
        mButtonAdd = (Button) findViewById(R.id.btn_add);
        mButtonOther = (Button) findViewById(R.id.btn_other);
        mButtonGetData = (Button) findViewById(R.id.btn_get_data);
        mButtonAdd.setOnClickListener(this);
        mButtonOther.setOnClickListener(this);
        mButtonGetData.setOnClickListener(this);
        mDataModelList = new ArrayList<>();
        mSelectedList = new ArrayList<>();
        setDataToList();
        customAdapter = new MyCustomAdapter(this, mDataModelList);
        mGridView.setAdapter(customAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                DataModel model = (DataModel) parent.getItemAtPosition(position);
                if (model.getCheckStatus()) {
                    model.setCheckStatus(false);
                } else {
                    model.setCheckStatus(true);
                }
                customAdapter.notifyDataSetChanged();
            }
        });


    }

    public List<DataModel> setDataToList() {
        DataModel d1 = new DataModel();
        d1.setName("Item 1");
        d1.setCheckStatus(true);
        mDataModelList.add(d1);
        DataModel d2 = new DataModel();
        d2.setName("Item 2");
        d2.setCheckStatus(false);
        mDataModelList.add(d2);
        DataModel d3 = new DataModel();
        d3.setName("Item 3");
        d3.setCheckStatus(true);
        mDataModelList.add(d3);
        DataModel d4 = new DataModel();
        d4.setName("Item 4");
        d4.setCheckStatus(true);
        mDataModelList.add(d4);
        DataModel d5 = new DataModel();
        d5.setName("Item 5");
        d5.setCheckStatus(false);
        mDataModelList.add(d5);
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
                if (item.length() > 3) {
                    DataModel d= new DataModel();
                    d.setName(item);
                    d.setCheckStatus(true);
                    mDataModelList.add(d);
                    customAdapter.notifyDataSetChanged();
                    mEtItem.setText("");
                }
                break;
            case R.id.btn_get_data:
                for (int i = 0; i < mDataModelList.size(); i++) {
                    DataModel d = new DataModel();
                    if (mDataModelList.get(i).getCheckStatus()) {
                        mSelectedList.add(d);

                    }

                }
                Toast.makeText(this, "selected items " + mSelectedList.size(), Toast.LENGTH_SHORT).show();
                mSelectedList.clear();
                break;
        }
    }


}


