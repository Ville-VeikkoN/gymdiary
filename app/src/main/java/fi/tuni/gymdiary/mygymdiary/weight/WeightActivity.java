package fi.tuni.gymdiary.mygymdiary.weight;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fi.tuni.gymdiary.mygymdiary.MyDbHelper;
import fi.tuni.gymdiary.mygymdiary.R;

public class WeightActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<Weight> listItems;
    ArrayAdapter<String> adapter;
    MyDbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        listView = (ListView) findViewById(R.id.weightlist);
        listItems=new ArrayList<>();
        dbHelper = new MyDbHelper(this);
        setWeights();
        setListView();
    }

    public void onClick(View view) {
        MyWeightDialog myDialog = new MyWeightDialog();
        myDialog.show(getSupportFragmentManager(),"tag");
    }

    protected void setListView() {
     /*   adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2,
                listItems);*/
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, listItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                Weight weight = listItems.get(position);
                text1.setText(weight.getWeight()+" kg");
                text1.setTextSize(20);
                text2.setText(dateFormat.format(weight.getDate().getTime()));
                return view;
            }
        };
        listView.setAdapter(adapter);
    }

    protected void setWeights() {
        listItems = dbHelper.getAllBodyWeights();
    }

    protected void addBodyWeight(Weight weight) {
        Log.d("Tag","ADDING");
        listItems.add(weight);
        dbHelper.addBodyWeight(weight);
        // adapter.notifyDataSetChanged();
    }

}
