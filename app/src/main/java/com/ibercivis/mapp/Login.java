package com.ibercivis.mapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.ibercivis.mapp.clases.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity {

    ImageView logo;
    TextView logotext, desc;
    TextInputLayout usertext, passtext;
    Button btnentrar, btnsignup;
    TextInputEditText login_username_textview, login_password_textview;

    String error_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        /*-----Hooks-----*/

        btnentrar = findViewById(R.id.entrar_btn);
        btnsignup = findViewById(R.id.signup_btn);
        logo = findViewById(R.id.logologin);
        logotext = findViewById(R.id.bienvenidotxt);
        desc = findViewById(R.id.accedetxt);
        usertext = findViewById(R.id.username_edit);
        passtext = findViewById(R.id.password_edit);


        /*-----Button Functions-----*/

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_sign = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(logotext, "logo_text");
                pairs[2] = new Pair<View,String>(desc, "logo_desc");
                pairs[3] = new Pair<View,String>(usertext, "username_tran");
                pairs[4] = new Pair<View,String>(passtext, "password_tran");
                pairs[5] = new Pair<View,String>(btnentrar, "btn1_tran");
                pairs[6] = new Pair<View,String>(btnsignup, "btn2_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent_sign, options.toBundle());
            }
        });



    }

    public void loginRequest (View view) {
        final LinearLayout cargar = findViewById(R.id.cargando);

        login_username_textview = findViewById(R.id.username_edittext);
        login_password_textview = findViewById(R.id.password_edittext);
        cargar.setVisibility(View.VISIBLE);

        if(checkInputLogin()) {
            // Input data ok, so go with the request

            // Url for the webservice
            String url = getString(R.string.base_url) + "/logIn.php";

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        System.out.println(response.toString());

                        JSONObject responseJSON = new JSONObject(response);

                        if ((int) responseJSON.get("result") == 1) {
                            SessionManager session = new SessionManager(getApplicationContext());
                            session.setLogin(true, login_username_textview.getText().toString());
                            session.setKeys(responseJSON.getString("token"), responseJSON.getInt("idUser"));
                            cargar.setVisibility(View.GONE);
                            openMain();



                        } else {
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast;
                            CharSequence text;

                            text = "Error while login: " + responseJSON.get("message") + ".";
                            toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();

                            // Clean the text fields for new entries
                            login_username_textview.setText("");
                            login_password_textview.setText("");
                            cargar.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        cargar.setVisibility(View.GONE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast;
                    CharSequence text;
                    text = "Error while login: " + error.getLocalizedMessage() + ".";
                    toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    cargar.setVisibility(View.GONE);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> login_params = new HashMap<String, String>();
                    login_params.put("username", login_username_textview.getText().toString());
                    login_params.put("password", login_password_textview.getText().toString());

                    return login_params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(sr);
        }
        else {
            // Do nothing, error has been shown in a toast and views clean
            cargar.setVisibility(View.GONE);
        }
    }

    private void showError (CharSequence error) {
        int duration = Toast.LENGTH_LONG;
        Toast toast;

        toast = Toast.makeText(getApplicationContext(), error, duration);
        toast.show();
    }

    private boolean checkLength( String text, String fieldName, int min, int max ) {
        if ( text.length() > max || text.length() < min ) {
            error_check = error_check + "Length of " + fieldName + " must be between " +
                    min + " and " + max + ".\n";
            return false;
        } else {
            return true;
        }
    }

    private boolean checkRegexp(String text, Pattern regexp, String errorMessage) {
        if (!regexp.matcher(text).matches()) {
            error_check = error_check + errorMessage + "\n";
            return false;
        } else {
            return true;
        }
    }

    private boolean checkInputLogin () {

        error_check = "";
        boolean valid = true;

        // valid is evaluated in the second part to force the check function being called always, so all the errors are displayed at the same time (&& conditional evaluation)
        valid = checkLength( login_username_textview.getText().toString(), "username", 3, 16 ) && valid;
        valid = checkLength( login_password_textview.getText().toString(), "password", 5, 16 ) && valid;
//"/^[a-z]([0-9a-z_ ])+$/i"
        // In the regular expression for the username and password we do not use {3,16} (for instance),
        // to control the length through the regex, since it is most accurate to indicate the length error
        // separately, so it is not considered the length in the regex (it has been taken into account previously
        valid = checkRegexp( login_username_textview.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." ) && valid;
        valid = checkRegexp( login_password_textview.getText().toString(), Pattern.compile("^[0-9a-zA-Z]+$"), "Password field only allow : a-z 0-9") && valid;


        if (!error_check.equals("")){
            showError(error_check);
        }

        return valid;
    }

    /** Open MAIN Activity */
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
