package com.ibercivis.mapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.ibercivis.mapp.clases.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    TextInputEditText signup_username_textview, signup_email_textview, signup_password_textview;

    String error_check;

    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_login = findViewById(R.id.btn_back);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    public void signupRequest (View view) {
        final LinearLayout cargar = findViewById(R.id.cargando);

        cargar.setVisibility(View.VISIBLE);
        signup_username_textview =  findViewById(R.id.signup_user);
        signup_email_textview =  findViewById(R.id.signup_email);
        signup_password_textview = findViewById(R.id.signup_pass);


        if(checkInputSignup()) {
            // Input data ok, so go with the request

            // Url for the webservice
            String url = getString(R.string.base_url) + "/signUp.php";

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.getCache().clear();
            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        System.out.println(response.toString());

                        JSONObject responseJSON = new JSONObject(response);

                        if ((int) responseJSON.get("result") == 1){
                            SessionManager session = new SessionManager(getApplicationContext());
                            session.setLogin(true, signup_username_textview.getText().toString());
                            Toast toast;
                            cargar.setVisibility(View.GONE);

                            toast = Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG);
                            toast.show();
                            openLogin();


                        }
                        else {
                            showError("Error while signing up: " + responseJSON.get("message") + ".");

                            // Clean the text fields for new entries
                            signup_username_textview.setText("");
                            signup_email_textview.setText("");
                            signup_password_textview.setText("");

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
                }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> signup_params = new HashMap<String, String>();
                    signup_params.put("username", signup_username_textview.getText().toString());
                    signup_params.put("email", signup_email_textview.getText().toString());
                    signup_params.put("password", signup_password_textview.getText().toString());


                    return signup_params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Content-Type","application/x-www-form-urlencoded");
                    return params;
                }
            };
            sr.setShouldCache(false);
            queue.add(sr);
        }
        else {
            // Si llego aquí es ruina. nothing, error has been shown in a toast and views clean
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

    private boolean checkSelect(Spinner spinner, String errorMessage) {

        if (spinner.getSelectedItemPosition() == 0) {
            error_check = error_check + "You must select one of the options from the " + errorMessage + " drop-down.\n";
            return false;
        }
        return true;
    }

    private boolean checkInputSignup () {

        error_check = "";
        boolean valid = true;
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        // valid is evaluated in the second part to force the check function being called always, so all the errors are displayed at the same time (&& conditional evaluation)
        valid = checkLength( signup_username_textview.getText().toString(), "username", 3, 16 ) && valid;
        valid = checkLength( signup_email_textview.getText().toString(), "email", 6, 80 ) && valid;
        valid = checkLength( signup_password_textview.getText().toString(), "password", 5, 16 ) && valid;
//"/^[a-z]([0-9a-z_ ])+$/i"
        // In the regular expression for the username and password we do not use {3,16} (for instance),
        // to control the length through the regex, since it is most accurate to indicate the length error
        // separately, so it is not considered the length in the regex (it has been taken into account previously
        valid = checkRegexp( signup_username_textview.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." ) && valid;
        valid = checkRegexp( signup_email_textview.getText().toString(), Pattern.compile(emailRegex), "Wrong email address, eg. user@odourcollect.com" ) && valid;
        valid = checkRegexp( signup_password_textview.getText().toString(), Pattern.compile("^[0-9a-zA-Z]+$"), "Password field only allow : a-z 0-9") && valid;




        if (!error_check.equals("")){
            showError(error_check);
        }

        return valid;
    }

    public void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

}
