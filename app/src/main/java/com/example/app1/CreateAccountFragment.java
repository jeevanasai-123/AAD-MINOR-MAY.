package com.example.app1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class CreateAccountFragment extends Fragment {


    public CreateAccountFragment() {
        // Required empty public constructor
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile( "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
    private EditText email,name,password,confirmPassword;
    private Button CreateAccount,login;
    private FirebaseAuth firebaseAuth;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RegisterActivity)getActivity()).setFragment(new LoginFragment());

            }
        });
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setError(null);
                name.setError(null);
                password.setError(null);
                confirmPassword.setError(null);
              if(email.getText().toString().isEmpty()){
                  email.setError("Email needed");
                  return;
              }
              if(name.getText().toString().isEmpty()){
                  name.setError("Name needed");
                  return;
              }
              if(password.getText().toString().isEmpty()){
                  password.setError("Password needed");
                  return;
              }
              if(confirmPassword.getText().toString().isEmpty()){
                  confirmPassword.setError("Password needed");
                  return;
              }
              if(VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText().toString()).find()){
                email.setError("Email address is not correct");
                return;
              }
              if(password.getText().toString().equals((confirmPassword.getText().toString()))){
                  confirmPassword.setError("Does not match with the password");
                  return;
              }



            }
        });

    }
    private void init(View view){
        email = view.findViewById(R.id.email);
        name = view.findViewById(R.id.Name);
        password = view.findViewById(R.id.Password);
        confirmPassword = view.findViewById(R.id.ConfirmPassword);
        CreateAccount = view.findViewById(R.id.Signup);
        login = view.findViewById(R.id.Login);
    }
    private  void createAccount() {
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ((RegisterActivity) getActivity()).setFragment(new LoginFragment());

                }else {
                    String error = task.getException().getMessage();
                    Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
                }

            }


        });
    }
}



