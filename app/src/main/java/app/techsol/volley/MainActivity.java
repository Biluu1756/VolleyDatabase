package app.techsol.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import app.techsol.volley.R;

public class MainActivity extends AppCompatActivity {
    EditText mNameEt, mPasswordET;
    String mNameStr, mPasswordStr;
    Button mSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Restration();
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNameEt.getText().toString().isEmpty()){
                    mNameEt.setError("Please enter your name");
                } else{
                    StringRequest stringRequest=new StringRequest(1, "https://www.google.com", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> map=new HashMap<>();

                            map.put("username", mNameStr);
                            map.put("password", mPasswordStr);
                            return map;
                        }
                    };
                    RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);



                }


            }
        });
    }

    void Restration(){
        mNameEt=findViewById(R.id.NameET);
        mPasswordET=findViewById(R.id.PasswordET);
        mSignupBtn=findViewById(R.id.SignupBtn);
    }
    void EdtTxtToStr(){
        mNameStr=mNameEt.getText().toString();
        mPasswordStr=mPasswordET.getText().toString();
    }

}
