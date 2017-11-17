package com.rramos.loginservice.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rramos.loginservice.R;
import com.rramos.loginservice.adapters.DenunciasAdapter;
import com.rramos.loginservice.models.Denuncia;
import com.rramos.loginservice.models.Usuario;
import com.rramos.loginservice.models.Usuario;
import com.rramos.loginservice.services.ApiService;
import com.rramos.loginservice.services.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText usernameInput;
    private EditText passwordInput;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = (EditText)findViewById(R.id.username_input);
        passwordInput = (EditText)findViewById(R.id.password_input);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // username remember
        String username = sharedPreferences.getString("username", null);
        if(username != null){
            usernameInput.setText(username);
            passwordInput.requestFocus();
        }
        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goListDenunciaActivity();
        }

    }

    public void callLogin(View view) {
        //Obtengo los datos de las cajas de sesion
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Usuario> call = null;
        call = service.loginUsuario(username,password);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);


                    if (response.isSuccessful()) {

                        Usuario usuario = response.body();
                        Log.d(TAG, "Usuario " + usuario.toString());
                        // Save to SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        boolean success = editor
                                .putString("id", String.valueOf(usuario.getId()))
                                .putString("username", usuario.getUsername())
                                .putString("email", usuario.getEmail())
                                .putBoolean("islogged", true)
                                .commit();
                        goListDenunciaActivity();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        Toast.makeText(MainActivity.this, "Usuario y contraseña incorrecta", Toast.LENGTH_LONG).show();

                        return;
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


    public void registerUser(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
        startActivity(intent);
    }


    private void goListDenunciaActivity(){
        Intent intent = new Intent(MainActivity.this, ListaDenunciaActivity.class);
        startActivity(intent);
        finish();
    }
}
