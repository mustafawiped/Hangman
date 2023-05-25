package com.example.adamasmaca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/*  D E V E L O P E D   B Y   A H M E T  A K Y O L   W I T H   S U P P O R T   O F   M U S T A F A W I P E D  */
public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    LinearLayout linearLayoutKelime;
    Button buttonOyun;
    String[] kelimeler = {"MAL", "SALİH", "AKILLI", "AHMET", "ANANI"};
    String kelime = "";
    boolean oyundurumu = false;
    int hata = 7;
    ArrayList<String> kelimeHarfler = new ArrayList<>();
    private void init() {
        buttonOyun = findViewById(R.id.buttonOyun);
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.adam_asmaca_7);
        linearLayoutKelime = findViewById(R.id.linearLayoutKelime);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void buttonOyun(View view) {
        Button button = (Button) view;
        if (button.getText().toString().equals("Başlat")) {
            int random = new Random().nextInt(kelimeler.length);
            kelime = kelimeler[random];
            kelimeHarfler.clear();
            oyundurumu = true;
            hata = 7;
            imageView.setImageResource(R.drawable.adam_asmaca_7);
            for (int i = 0; i < kelime.length(); i++) {
                TextView harfiolustur = new TextView(this);
                harfiolustur.setText(" _ ");
                harfiolustur.setTextSize(34);
                harfiolustur.setTextColor(getResources().getColor(android.R.color.black));
                linearLayoutKelime.addView(harfiolustur);
            }
            button.setText("Sıfırla");
        } else {
            int random = new Random().nextInt(kelimeler.length);
            kelime = kelimeler[random];
            kelimeHarfler.clear();
            linearLayoutKelime.removeAllViews();
            oyundurumu = true;
            hata = 7;
            imageView.setImageResource(R.drawable.adam_asmaca_7);
            for (int i = 0; i < kelime.length(); i++) {
                TextView textViewHarf = new TextView(this);
                textViewHarf.setText(" _ ");
                textViewHarf.setTextSize(34);
                textViewHarf.setTextColor(getResources().getColor(android.R.color.black));
                linearLayoutKelime.addView(textViewHarf);
            }
        }
    }
    public void buttonHarf(View view) {
        Button buttonHarf = (Button) view;
        String harf = buttonHarf.getText().toString();
        if (!oyundurumu) {
            Toast.makeText(this, "Lütfen oyunu başlatınız.", Toast.LENGTH_LONG).show();
        } else if (kelime.contains(harf)) {
            for (int i = 0; i < kelime.length(); i++) {
                if (String.valueOf(kelime.charAt(i)).equals(harf)) {
                    TextView textViewHarf = (TextView) linearLayoutKelime.getChildAt(i);
                    textViewHarf.setText(harf);
                }
            }
            kelimeHarfler.add(harf);
            boolean oyundurumune = true;
            for (int i = 0; i < kelime.length(); i++) {
                String harfKelime = String.valueOf(kelime.charAt(i));
                if (!kelimeHarfler.contains(harfKelime)) {
                    oyundurumune = false;
                    break;
                }
            }
            if (oyundurumune) {
                buttonOyun.setText("Başlat");
                linearLayoutKelime.removeAllViews();
                imageView.setImageResource(R.drawable.adam_asmaca_7);
                hata = 7;
                Toast.makeText(this, "Oyun bitti, kazandınız!", Toast.LENGTH_LONG).show();
                kelimeHarfler.clear();
                oyundurumu = false;
            }
        } else {
            hata--;
            if (hata >= 1 && hata <= 7) {
                int resId = getResources().getIdentifier("adam_asmaca_" + hata, "drawable", getPackageName());
                imageView.setImageResource(resId);
            }
            if (hata == 0) {
                buttonOyun.setText("Başlat");
                linearLayoutKelime.removeAllViews();
                imageView.setImageResource(R.drawable.adam_asmaca_7);
                hata = 7;
                oyundurumu = false;
                Toast.makeText(this, "Oyun bitti, kaybettiniz!", Toast.LENGTH_LONG).show();
                kelimeHarfler.clear();
            }
        }
    }
}
