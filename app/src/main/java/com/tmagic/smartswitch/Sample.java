package com.tmagic.smartswitch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class Sample extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Switch classicSwitch = (Switch) findViewById(R.id.switch_me);
        Switch switch2 = (Switch) findViewById(R.id.switch_me_2);
        Switch switch3 = (Switch) findViewById(R.id.switch_me_4);


        switch2.setStyle(Switch.STYLE.ROUND);
        switch3.setStyle(Switch.STYLE.ROUND);


        classicSwitch.setStyle(Switch.STYLE.BOX);
        classicSwitch.setSelectedIndex(1);

        classicSwitch.setOnSelectChangeListener(new Switch.onSelectChangeListener() {
            @Override
            public void onSelectChange(int index, String selectedText) {
                Log.d("Something selected", selectedText);
            }
        });

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);

        Switch newSwitch = new Switch(this);
        Button optionTall = new Button(this);
        Button optionShort = new Button(this);
        Button optionMedium = new Button(this);
        optionTall.setText("Tall");
        optionShort.setText("Short");
        optionMedium.setText("Medium");
        newSwitch.addView(optionTall);
        newSwitch.addView(optionMedium);
        newSwitch.addView(optionShort);

        newSwitch.setStyle(Switch.STYLE.ROUND);
        newSwitch.setActiveColor(getResources().getColor(R.color.black));
        newSwitch.setInActiveColor(getResources().getColor(R.color.white));
        newSwitch.setActiveTextColor(getResources().getColor(R.color.white));
        newSwitch.setInactiveTextColor(getResources().getColor(R.color.black));
        newSwitch.setSelectedIndex(2);
        mainLayout.addView(newSwitch);



    }


}
