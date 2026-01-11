package com.example.ex09222;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Yusupov <dy3722@bs.amalnet.k12.il>
 * @version 1.0
 * @since 11/1/2026
 * Main Activity
 */
public class MainActivity extends AppCompatActivity {
    private Intent si;
    private AlertDialog.Builder adb;
    private final String[] styles = {"film","restaurant","performance","night trip"};
    private LinearLayout ll;
    private TextView tvStyle,tvRE;
    private final String[] re = {"your smile","favorite food","friends","Albert","Marshmelo"};
    private final List<String> listOfRE= new ArrayList<>();

    /**
     * Initializes the activity and sets up the UI components.
     * <p>
     * This method sets the content view to activity_main, initializes the Intent for
     * the credits screen, and links the layout and TextView variables to their XML IDs.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     * being shut down then this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        si = new Intent(this,CreditsActivity.class);
        ll = findViewById(R.id.main);
        tvStyle = findViewById(R.id.tvStyle);
        tvRE = findViewById(R.id.tvRE);
    }

    /**
     * Initialize the contents of the Activity's standard options menu.
     * <p>
     * This method inflates the menu resource (R.menu.main) into the provided Menu
     * object and adds the items to the action bar.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed; if you return false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * <p>
     * This implementation checks if the selected item is the "Credits" menu item
     * and, if so, starts the activity defined by the Intent 'si'.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to proceed,
     * true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuCtedits)
        {
            startActivity(si);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Displays a single-choice dialog to select the entertainment style.
     * <p>
     * This method opens a dialog with a list of styles. Upon selecting an item,
     * the dialog closes immediately, updates the description text, and changes
     * the background color of the main layout to match the chosen style.
     *
     * @param view The view that was clicked to trigger this method.
     */
    public void style(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setCancelable(false);
        adb.setTitle("Choice of entertainment style");
        adb.setItems(styles, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which)
                {
                    case 0:
                        ll.setBackgroundColor(Color.RED);
                        tvStyle.setText("Entertainment style: " + "film"); break;
                    case 1:
                        ll.setBackgroundColor(Color.YELLOW);
                        tvStyle.setText("Entertainment style: " + "restaurant"); break;
                    case 2:
                        ll.setBackgroundColor(Color.BLUE);
                        tvStyle.setText("Entertainment style: " + "performance"); break;
                    case 3:
                        ll.setBackgroundColor(Color.GRAY);
                        tvStyle.setText("Entertainment style: " + "night trip"); break;
                }
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Displays a multi-choice dialog for selecting refreshments and equipment.
     * <p>
     * This method allows users to select multiple items from a list. The selections
     * are stored in a temporary list, and upon confirmation ('ok'), they are displayed
     * as a comma-separated string in the TextView. The list is cleared after display.
     *
     * @param view The view that was clicked to trigger this method.
     */
    public void listRE(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setCancelable(false);
        adb.setTitle("Refreshment/Equipment Menu");
        adb.setMultiChoiceItems(re, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) listOfRE.add(re[which]);
                else listOfRE.remove(re[which]);
            }
        });
        adb.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvRE.setText("Refreshment/Equipment: ");
                for (String oneRE : listOfRE)
                {
                    tvRE.setText(tvRE.getText().toString() + oneRE + ", ");
                }
                tvRE.setText(tvRE.getText().toString() + "yourself ;)");
                listOfRE.clear();
            }
        });
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Displays a dialog containing an input field for the user's name.
     * <p>
     * This method dynamically creates an EditText view within the dialog.
     * When the user confirms, a Toast message is displayed greeting the user
     * with the name they entered.
     *
     * @param view The view that was clicked to trigger this method.
     */
    public void nameQ(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setCancelable(false);
        adb.setTitle("What is your name?");
        final EditText et = new EditText(this);
        et.setHint("Your name:");
        adb.setView(et);
        adb.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Enjoy your time, " + et.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Displays a confirmation dialog to reset the planner.
     * <p>
     * This method asks the user to confirm the reset action. If confirmed,
     * it sets the background color back to white and clears the text in the
     * status TextViews to their initial state.
     *
     * @param view The view that was clicked to trigger this method.
     */
    public void reset(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setCancelable(false);
        adb.setTitle("Reset");
        adb.setMessage("Are you sure?");
        adb.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ll.setBackgroundColor(Color.WHITE);
                tvStyle.setText("Entertainment style:");
                tvRE.setText("Refreshment/Equipment:");
            }
        });
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }
}