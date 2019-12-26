package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.listView);

        afficherListeArticles();
    }

    public static ArrayList<Article> getArticles(){
        ArrayList<Article> articles = new ArrayList<>();

        try {

            String myurl = "https://newsapi.org/v2/everything?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr&sources=google-news-fr";

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            String result = InputStreamToString(inputStream);

            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = new JSONArray(jsonObject.getString("articles"));

            Log.d("GETARTICLES", "JE SUIS LA !");

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = new JSONObject(array.getString(i));
                String source = obj.getString("source");
                String author = obj.getString("author");
                String title = obj.getString("title");
                String description = obj.getString("description");
                String url_article = obj.getString("url");

                Article article = new Article(source, author, title, description, url_article);

                articles.add(article);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        //Ajout d'articles totalement artificiel car le code qui devrait donner accès aux vrais articles ne fonctionne pas
        articles.add(new Article("Google News", "Jean-Eude", "Les animaux de France", "Un descriptif", "www.googlenews.fr"));
        articles.add(new Article("Le Monde", "Christophe", "Il était une fois", "Un descriptif", "www.lemonde.fr"));
        articles.add(new Article("BBC", "Marie-Jeanne", "En Australie il pleut", "Un descriptif", "www.BBC.fr"));

        return articles;


    }

    public static String InputStreamToString (InputStream in, int bufSize) {
        final StringBuilder out = new StringBuilder();
        final byte[] buffer = new byte[bufSize];
        try {
            for (int ctr; (ctr = in.read(buffer)) != -1;) {
                out.append(new String(buffer, 0, ctr));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot convert stream to string", e);
        }
        return out.toString();
    }

    public static String InputStreamToString (InputStream in) {
        // On appelle la methode precedente avec une taille de buffer par defaut
        return InputStreamToString(in, 1024);
    }


    private void afficherListeArticles(){
        List<Article> articles = getArticles();

        ArticleAdapter adapter = new ArticleAdapter(MainActivity.this, articles);
        myListView.setAdapter(adapter);
    }

}
