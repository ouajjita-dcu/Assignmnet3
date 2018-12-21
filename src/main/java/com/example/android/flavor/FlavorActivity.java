/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.flavor;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * {@link FlavorActivity} shows a list of Android platform releases.
 * For each release, display the name, version number, and image.
 */
public class FlavorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flavor);

        // Create an ArrayList of AndroidFlavor objects called androidFlavors.
        final ArrayList<AndroidFlavor> androidFlavors = new ArrayList<AndroidFlavor>();
        // Adding the AndroidFlavor objects to the ArrayList androidFlavors
        androidFlavors.add(new AndroidFlavor("Yoscki Game", "Adventure", R.drawable.yoschi));
        androidFlavors.add(new AndroidFlavor("Cute Game", "Draw And Fill", R.drawable.cute));
        androidFlavors.add(new AndroidFlavor("Friend Game", "Join your Friend", R.drawable.friend));
        androidFlavors.add(new AndroidFlavor("Elfe Game", "Strategy", R.drawable.elfe));
        androidFlavors.add(new AndroidFlavor("Mario Game", "Jump with mario", R.drawable.mario));
        androidFlavors.add(new AndroidFlavor("Lego Game", "Strategy", R.drawable.lego));
        androidFlavors.add(new AndroidFlavor("Fun Game", "Family time", R.drawable.fun));
        androidFlavors.add(new AndroidFlavor("Mushroom Game", "Puzzle", R.drawable.mushroom));
        androidFlavors.add(new AndroidFlavor("Minion Game", "Action", R.drawable.minion));
        androidFlavors.add(new AndroidFlavor("Casino Game", "Real Time Strategy", R.drawable.casino));
        androidFlavors.add(new AndroidFlavor("Fight Game", "Simulation", R.drawable.fight));
        androidFlavors.add(new AndroidFlavor("Chess Game", "Strategy", R.drawable.chess));
        androidFlavors.add(new AndroidFlavor("Ship Game", "Adventure", R.drawable.ship));
        androidFlavors.add(new AndroidFlavor("Snooker Game", "Real play", R.drawable.snooker));
        androidFlavors.add(new AndroidFlavor("Santa Game", "Adventure", R.drawable.santa));



        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        AndroidFlavorAdapter flavorAdapter = new AndroidFlavorAdapter(this, androidFlavors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_flavor);
        listView.setAdapter(flavorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {
                TextView itemClicked=(TextView)view.findViewById(R.id.version_name);
                view.setBackgroundColor(R.color.tan_background);
                Toast.makeText(FlavorActivity.this,"You Selected "+itemClicked.getText(),Toast.LENGTH_LONG).show();

            }
        });


    }

}

