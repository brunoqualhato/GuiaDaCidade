package dev.brunoqualhato.guiadacidade.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Alertas {
    private AlertDialog alerta;

    public void exemplo_simples(Context context, String titulo, String mensagem) {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //define o titulo
        builder.setTitle(titulo);
        //define a mensagem
        builder.setMessage(mensagem);
        LinearLayout layout = new LinearLayout(context);
        EditText cidade = new EditText(context);

        layout.addView(cidade);
        layout.setGravity(Gravity.CENTER);
        cidade.setWidth(820);
        cidade.setHint("Cidade");
        builder.setView(layout);
        //define um botão como positivo
        builder.setPositiveButton("Positivo", (arg0, arg1) -> Toast.makeText(context, "positivo=" + arg1, Toast.LENGTH_SHORT).show());
        //define um botão como negativo.
        builder.setNegativeButton("Negativo", (arg0, arg1) -> Toast.makeText(context, "negativo=" + arg1, Toast.LENGTH_SHORT).show());
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

}
