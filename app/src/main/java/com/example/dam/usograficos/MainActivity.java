package com.example.dam.usograficos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        requestWindowFeature(
//                Window.FEATURE_NO_TITLE);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new Vista(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.Circulo) {
            Vista.circulo();
            return true;
        }
        if (id == R.id.linea) {
            Vista.linea();
            return true;
        }
        if (id == R.id.rectangulo) {
            Vista.rectangulo();
            return true;
        }
        if(id == R.id.linearecta){
            Vista.lineaRecta();
            return true;
        }
        if (id == R.id.solobordes){
            Vista.soloBordes();
        }

        return super.onOptionsItemSelected(item);
    }
}
