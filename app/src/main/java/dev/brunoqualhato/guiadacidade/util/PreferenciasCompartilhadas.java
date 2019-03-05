package dev.brunoqualhato.guiadacidade.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasCompartilhadas {
    private String PREFERENCIA = "qualhato.dev";
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public PreferenciasCompartilhadas(Context context) {
        this.mContext = context;
        this.mSharedPreferences = context.getSharedPreferences(PREFERENCIA, Context.MODE_PRIVATE);
        this.mEditor = mSharedPreferences.edit();
    }

    public void putString(String valor, String chave) {
        mEditor.putString(chave, valor);
        mEditor.commit();

    }

    public void putInt(int valor, String chave) {
        mEditor.putInt(chave, valor);
        mEditor.commit();

    }

    public void putBool(boolean valor, String chave) {
        mEditor.putBoolean(chave, valor);
        mEditor.commit();
    }

    public void putFloat(float valor, String chave) {
        mEditor.putFloat(chave, valor);
        mEditor.commit();
    }

    public String getString(String chave) {
        return mSharedPreferences.getString(chave, "");
    }

    public int getInt(String chave) {
        return mSharedPreferences.getInt(chave, 0);
    }

    public boolean getBool(String chave) {
        return mSharedPreferences.getBoolean(chave, false);
    }

    public float getFloat(String chave) {
        return mSharedPreferences.getFloat(chave, 0);
    }
}
