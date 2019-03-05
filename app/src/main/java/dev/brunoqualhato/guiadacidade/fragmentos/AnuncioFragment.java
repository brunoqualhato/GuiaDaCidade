package dev.brunoqualhato.guiadacidade.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import dev.brunoqualhato.guiadacidade.R;
import dev.brunoqualhato.guiadacidade.adapters.AnuncioAdapter;
import dev.brunoqualhato.guiadacidade.atividades.DetalheAnuncioActivity;
import dev.brunoqualhato.guiadacidade.model.MAnuncio;

public class AnuncioFragment extends Fragment  implements AnuncioAdapter.ItemClickListener {

    AnuncioAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anuncio, container, false);


        ArrayList<MAnuncio> animalNames = new ArrayList<MAnuncio>();
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));
        animalNames.add(new MAnuncio("nome","descricao","Qualificação: 5.0"));



        RecyclerView recyclerView = view.findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AnuncioAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position).getNome() + " on row number " + position, Toast.LENGTH_SHORT).show();
       startActivity(new Intent(getActivity(),DetalheAnuncioActivity.class));
    }

    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);
        Intent intent = new Intent(getActivity(), DetalheAnuncioActivity.class);
        intent.putExtra(DetalheAnuncioActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(DetalheAnuncioActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }
}
