package com.kmac_enterprises.chialphaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

/**
 * Created by Kelsey Maclin on 12/23/2015.
 */
public class SignUpActivity extends AppCompatActivity {

    Button register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        String emailIn = this.getIntent().getExtras().getString("email");
        String passwordIn = this.getIntent().getExtras().getString("password");

        final EditText fn_text = (EditText)findViewById(R.id.fn_editor);
        fn_text.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        final EditText ln_text = (EditText)findViewById(R.id.ln_editor);
        ln_text.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        final EditText email_text = (EditText)findViewById(R.id.email_editor);
        final EditText campus_text = (EditText)findViewById(R.id.campus_editor);
        final EditText password_text = (EditText)findViewById(R.id.password_editor);
        final EditText retype_password_text = (EditText)findViewById(R.id.retype_password_editor);

        if (emailIn.length() > 0) {
            email_text.setText(emailIn);
        }

        if (passwordIn.length() > 0) {
            password_text.setText(passwordIn);
        }

        register = (Button)findViewById(R.id.signUp_button);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean cancel = false;
                View focusView = null;

                fn_text.setError(null);
                ln_text.setError(null);
                email_text.setError(null);
                campus_text.setError(null);
                password_text.setError(null);
                retype_password_text.setError(null);

                if (TextUtils.isEmpty(fn_text.getText().toString())) {
                    Log.e("chialpha", "no first name");
                    fn_text.setError(getString(R.string.error_field_required));
                    focusView = fn_text;
                    cancel = true;
                }

                if (TextUtils.isEmpty(ln_text.getText().toString())) {
                    Log.e("chialpha", "no last name");
                    ln_text.setError(getString(R.string.error_field_required));
                    focusView = ln_text;
                    cancel = true;
                }

                if (TextUtils.isEmpty(campus_text.getText().toString())) {
                    Log.e("chialpha", "no campus name");
                    campus_text.setError(getString(R.string.error_field_required));
                    focusView = campus_text;
                    cancel = true;
                }

                if (!TextUtils.isEmpty(password_text.getText().toString()) && !isPasswordValid(password_text.getText().toString(),
                        retype_password_text.getText().toString()) && !TextUtils.isEmpty(retype_password_text.getText().toString())) {
                    Log.e("chialpha", "bad password");
                    password_text.setError(getString(R.string.error_invalid_password));
                    //retype_password_text.setError(getString(R.string.error_password_mismatch));
                    focusView = password_text;
                    cancel = true;
                }

                if (TextUtils.isEmpty(email_text.getText().toString())) {
                    Log.e("chialpha", "no email address");
                    email_text.setError(getString(R.string.error_field_required));
                    focusView = email_text;
                    cancel = true;
                }

                if (!isEmailValid(email_text)) {
                    Log.e("chialpha", "email address not valid");

                    focusView = email_text;
                    cancel = true;
                }

                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {

                    // Show a progress spinner, and kick off a background task to
                    // perform the user login attempt.
                    Person user = new User(fn_text.getText().toString(), ln_text.getText().toString(),
                            email_text.getText().toString(), campus_text.getText().toString());
                    Bridge.addMember(user, retype_password_text.getText().toString().hashCode());
                    Log.e("chialphaapp", "added member successfully");
                    Bridge.setCurrentUser(user);
                    Log.e("chialphaapp", "set current user successfully");
                    Intent afterSignUp = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(afterSignUp);
                    Log.e("chialphaapp", "started new activity successfully");
                    finish();
                }
            }
        });

    }

    private boolean isEmailValid(EditText email) {
        //TODO: Replace this with your own logic
        if (!email.getText().toString().contains("@")) {
            email.setError(getString(R.string.error_invalid_email));
            return false;
        }

        Person newPerson = new User(null, null, email.getText().toString(), null);
        if (Bridge.getUsers().containsKey(newPerson)) {
            email.setError(getString(R.string.error_email_in_system));
            Log.e("chialpha", "checks email");
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password, String retyped_password) {
        //TODO: Replace this with your own logic
        if (password.length() > 7 && password.equals(retyped_password)) {
            return true;
        }
        return false;
    }

    public void onClick(View v) {

    }
}
