package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvNews;
    ArrayList<News> arrayNews;
    ArrayList<String> arrayLink;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadRSS().execute("https://vnexpress.net/rss/suc-khoe.rss");

        lvNews = (ListView) findViewById(R.id.lvNews);
        arrayNews = new ArrayList<>();
        adapter = new NewsAdapter(MainActivity.this, R.layout.item_news, arrayNews);
        lvNews.setAdapter(adapter);

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrayNews.get(position).url, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("linkNews", arrayNews.get(position).url);
                startActivity(intent);
            }
        });
    }
    private class ReadRSS extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList node = document.getElementsByTagName("item");
            String title = "";
            String desc = "";
            String date = "";
            String url = "";

            for (int i = 0; i < node.getLength(); i++) {
                Element element = (Element) node.item(i);
                title = parser.getValue(element, "title");
                desc = parser.getValue(element, "description");
                date = parser.getValue(element, "pubDate");
                url = parser.getValue(element,"link");

                    arrayNews.add(new News(title, desc, date, url));
//                    arrayLink.add(link);
                }
                adapter.notifyDataSetChanged();
//            Toast.makeText(MainActivity.this, desc, Toast.LENGTH_SHORT).show();

        }
        }
    }
