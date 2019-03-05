package dev.brunoqualhato.guiadacidade.atividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CircularBorderDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mancj.materialsearchbar.MaterialSearchBar;

import de.hdodenhof.circleimageview.CircleImageView;
import dev.brunoqualhato.guiadacidade.R;
import dev.brunoqualhato.guiadacidade.util.Alertas;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MaterialSearchBar.OnSearchActionListener {

    private DrawerLayout drawer;
    private MaterialSearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        searchBar = findViewById(R.id.searchBar);
        searchBar.setCardViewElevation(15);
        searchBar.setMaxSuggestionCount(0);
        searchBar.setOnSearchActionListener(this);
        searchBar.inflateMenu(R.menu.main);
        searchBar.getMenu().setOnMenuItemClickListener(this::onOptionsItemSelected);

        //Log.d("LOG_TAG", getClass().getSimpleName() + ": text " + searchBar.getText());

        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        View hView = navigationView.getHeaderView(0);
        TextView nome = hView.findViewById(R.id.nav_nome);
        nome.setText(currentUser.getDisplayName());
        TextView email = hView.findViewById(R.id.nav_email);
        email.setText(currentUser.getEmail());

        CircleImageView foto = hView.findViewById(R.id.imageViewFoto);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(foto);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_config) {
            if (item.isChecked()){
                item.setChecked(false);
                Alertas oAlertas = new Alertas();
                oAlertas.exemplo_simples(this,"Atenção","Digite o nome de uma cidade manualmente");
            }else{
                item.setChecked(true);
            }

           return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_restaurantes) {

        } else if (id == R.id.nav_enterterimento) {

        } else if (id == R.id.nav_hotel) {

        } else if (id == R.id.nav_compras) {

        } else if (id == R.id.nav_frete) {

        } else if (id == R.id.nav_taxi) {

        } else if (id == R.id.nav_vendas) {

        } else if (id == R.id.nav_alimentos) {

        } else if (id == R.id.nav_doacoes) {

        } else if (id == R.id.nav_educacao) {

        } else if (id == R.id.nav_contato) {

        } else if (id == R.id.nav_config) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                drawer.openDrawer(Gravity.START);
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                searchBar.disableSearch();

                break;
        }
    }
}