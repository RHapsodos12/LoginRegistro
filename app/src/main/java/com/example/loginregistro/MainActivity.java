package com.example.loginregistro;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    //CLASE RegistroUsuario.java

    RequestQueue rq;
    JsonRequest jrq;

    Button mBtnRegistrar;
    EditText mTxtUser, mTxtPwd, mTxtNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnRegistrar =  findViewById(R.id.btnRegistrarReg);
        mTxtUser = findViewById(R.id.txtUserReg);
        mTxtPwd = findViewById(R.id.txtPwdReg);
        mTxtNames = findViewById(R.id.txtNamesReg);

        rq = Volley.newRequestQueue(getApplicationContext());

        mBtnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Intent instancia = new Intent(getApplicationContext(), user_registrado.class);
                //startActivity(instancia);
                nuevo_registro();
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Error al registrar"+error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "Registrado Correctamente", Toast.LENGTH_SHORT).show();
        limpiar_cajas();

        Intent instancia = new Intent(getApplicationContext(), user_registrado.class);
        startActivity(instancia);
    }

    void nuevo_registro () {

        String url="http://172.20.97.201/htdocs/login/registrar.php?names="+mTxtNames.getText().toString()+"&user="+mTxtUser.getText().toString()+"&pwd="+mTxtPwd.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);

    }

    void limpiar_cajas() {
        mTxtNames.setText("");
        mTxtPwd.setText("");
        mTxtUser.setText("");
    }
    //NUEVO PROYECTO: BUSCAR LOGIN
    //distic.com.mx/login/buscar.php?
}
