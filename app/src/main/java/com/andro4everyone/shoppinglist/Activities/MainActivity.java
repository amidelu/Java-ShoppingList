package com.andro4everyone.shoppinglist.Activities;

import android.os.Bundle;

import com.andro4everyone.shoppinglist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogueBuilder;
    private AlertDialog dialogue;
    private EditText shoppingItem, shoppingQuantity;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                alterPopupDialogue();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void alterPopupDialogue() {
        dialogueBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        shoppingItem = view.findViewById(R.id.etShoppingItemId);
        shoppingQuantity = view.findViewById(R.id.etShoppingQtyId);
        saveBtn = view.findViewById(R.id.btnSaveId);

        dialogueBuilder.setView(view);
        dialogue = dialogueBuilder.create();
        dialogue.show();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save to DB
                //Go to next screen
                saveShoppingToDB (view);
            }
        });
    }

    private void saveShoppingToDB(View view) {

    }
}
