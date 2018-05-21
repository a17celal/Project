package com.example.a17celal.project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] gumNames = {"White Sweet Mint","Superberries","Fusion Strawberry lime", "Strength Spearmint", "Frost Peppermint", "Sweet menthol"};
    private List<Tuggummi> namnen = new ArrayList<Tuggummi>();
    private  ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new Fetchdata().execute();


        List<String> listData = new ArrayList<String>(Arrays.asList(gumNames));

        adapter = new ArrayAdapter(getApplicationContext(),R.layout.list_item_textview,
                R.id.my_item_textview, namnen);

        ListView myListView = (ListView)findViewById(R.id.my_listview);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tuggummi n = namnen.get(position);
                Toast.makeText(MainActivity.this,n.fakta(), Toast.LENGTH_LONG).show();
            }
        });
        myListView.setAdapter(adapter);
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
        if (id == R.id.about) {
            Toast.makeText(MainActivity.this,"Detta är en app för dig som tycker om tuggummin. Här kan du ha koll på de senaste nyheterna, men även jämföra de olika sorterna med varandra, t.ex. priser.", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class Fetchdata extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String jsonStr = null;

            try {
                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a17celal");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            }
                catch (IOException e) {
                    Log.e("PlaceholderFragment", "Error ", e);
                    return null;
                }
                finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    }
                    catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);

            Log.d("Hej", o);

            try {
                JSONArray allt=new JSONArray(o);
                adapter.clear();
                for (int r=0;r<allt.length();r++) {
                    JSONObject obj=allt.getJSONObject(r);
                    String ID=obj.getString("ID");
                    String name=obj.getString("name");
                    String company=obj.getString("company");
                    String location=obj.getString("location");
                    String category=obj.getString("category");
                    int cost=obj.getInt("cost");
                    Log.d("Hej", Integer.toString(r) + ID + name + company + location + category + cost);
                    Tuggummi temp= new Tuggummi(ID, name, company, location, category, cost);
                    adapter.add(temp);
                }
            }
            catch (JSONException e) {
                Log.e("Hej", "E:"+e.getMessage());
            }
        }
    }

}
