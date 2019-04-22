package fi.tuni.gymdiary.mygymdiary.bodyweight;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fi.tuni.gymdiary.mygymdiary.MyDbHelper;
import fi.tuni.gymdiary.mygymdiary.R;

/**
 * Activity for displaying bodyweights in ListView and uses MyDbHelper for database.
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public class BodyweightActivity extends AppCompatActivity {
    /**
     * ListView for displaying bodyweights
     */
    private ListView listView;
    /**
     * ArrayList for the bodyweights
     */
    ArrayList<BodyWeight> listItems;
    /**
     * Databasehelper for using database
     */
    MyDbHelper dbHelper;

    /**
     * Method called when BodyweightActivity is created.
     * Intializes variables, sets layout and calls needed methods.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        listView = (ListView) findViewById(R.id.weightlist);
        listItems=new ArrayList<>();
        dbHelper = new MyDbHelper(this);
        setWeights();
        setListView();
        setListViewListener();
    }

    /**
     * Method called when button is clicked.
     *
     * @param view view containing button that was clicked
     */
    public void onClick(View view) {
        MyBodyweightDialog myDialog = new MyBodyweightDialog();
        myDialog.show(getSupportFragmentManager(),"tag");
    }

    /**
     * Sets ListView for bodyweights
     */
    protected void setListView() {

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.mysimple_list_layout, R.id.text1, listItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(R.id.text1);
                TextView text2 = (TextView) view.findViewById(R.id.text2);
                TextView text3 = (TextView) view.findViewById(R.id.text3);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                BodyWeight weight = listItems.get(position);
                text1.setText(weight.getWeight()+" kg");
                text1.setTextSize(20);
                text2.setText(dateFormat.format(weight.getDate().getTime()));

                if(position > 0) {
                    BodyWeight previousWeight = listItems.get(position-1);
                    double difference = weight.getWeight()-previousWeight.getWeight();
                    difference = Math.round(difference*100.0)/100.0;
                    if(difference > 0) {
                        text3.setText("+"+difference+"kg");
                        text3.setTextColor(getResources().getColor(R.color.negative));
                    } else {
                        text3.setText(""+difference+"kg");
                        text3.setTextColor(getResources().getColor(R.color.positive));
                    }
                } else {
                    text3.setText("");
                }

                return view;
            }
        };
        listView.setAdapter(adapter);
    }

    /**
     * Sets all bodyweight from database using MyDbHelper to ArrayList and by that to ListView.
     */
    protected void setWeights() {
        listItems = dbHelper.getAllBodyweights();
    }

    /**
     * Adds bodyweight to ArrayList and by that to ListView and calls method to add it to database.
     *
     * @param weight containing info about bodyweight
     */
    protected void addBodyWeight(BodyWeight weight) {
        Log.d("Tag","ADDING");
        listItems.add(weight);
        dbHelper.addBodyweight(weight);
        // adapter.notifyDataSetChanged();
    }

    /**
     * Sets listener to ListView.
     */
    protected void setListViewListener() {
        final Context thisContext = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final BodyWeight weight = listItems.get(position);
                AlertDialog.Builder adb=new AlertDialog.Builder(thisContext);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete selected weight information");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteBodyweight(weight.getId());
                        setWeights();
                        setListView();
                    }});
                adb.show();
            }
        });
    }

}
