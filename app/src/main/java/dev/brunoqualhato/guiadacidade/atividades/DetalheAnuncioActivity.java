package dev.brunoqualhato.guiadacidade.atividades;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import dev.brunoqualhato.guiadacidade.R;

public class DetalheAnuncioActivity extends AppCompatActivity implements RatingDialogListener, OnMapReadyCallback {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    View rootLayout;

    private int revealX;
    private int revealY;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_anuncio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        FloatingActionButton fabTelefone = findViewById(R.id.floatingActionButtonTelefone);
        fabTelefone.setOnClickListener(view -> {

        });

        FloatingActionButton fabAvaliar = findViewById(R.id.floatingActionButtonAvaliar);

        fabAvaliar.setOnClickListener(view -> showDialog());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        final Intent intent = getIntent();

        rootLayout = findViewById(R.id.root_layout);

        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);


            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }
    }

    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(400);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        unRevealActivity();
    }

    protected void unRevealActivity() {
        float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                rootLayout, revealX, revealY, finalRadius, 0);

        circularReveal.setDuration(400);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                rootLayout.setVisibility(View.INVISIBLE);
                finish();
            }
        });


        circularReveal.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                unRevealActivity();
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    public void onNegativeButtonClicked() {
        Log.e("NEGATIVE", "Botao negativo");
    }

    @Override
    public void onNeutralButtonClicked() {
        Log.e("NEUTRAL", "Botao NEUTRAL");
    }

    @Override
    public void onPositiveButtonClicked(int i, @NotNull String s) {
        Log.e("POSITIVE", "Botao POSiTIVE: " + i + " : " + s);
    }

    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("enviar")
                .setNegativeButtonText("cancelar")
                .setNeutralButtonText("depois")
                .setNoteDescriptions(Arrays.asList("Muito Ruim", "Ruim", "Bom", "Muito Bom", "Excelente !!!"))
                .setDefaultRating(2)
                .setTitle("Qualifique esse Lugar")
                .setDescription("Por favor, selecione algumas estrelas e dê sua opinião")
                .setCommentInputEnabled(true)
                .setDefaultComment("Este lugar é muito legal !")
                .setStarColor(R.color.primaryColor)
                .setNoteDescriptionTextColor(R.color.primaryTextColor)
                .setTitleTextColor(R.color.primaryTextColor)
                .setDescriptionTextColor(R.color.searchBarTextColor)
                .setHint("Por favor, escreva seu comentário aqui ...")
                .setHintTextColor(R.color.searchBarHintColor)
                .setCommentTextColor(R.color.searchBarTextColor)
                .setCommentBackgroundColor(R.color.primaryLightColor)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .create(this)
                .show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.setMinZoomPreference(200);

    }
}