package com.example.app1;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class LoginFragment extends Fragment {




    public LoginFragment() {
        // Required empty public constructor
    }
    private EditText email2,pasword2;
    private Button login2,signup2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        login2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((RegisterActivity)getActivity()).setFragment(new LoginFragment());


            }
                                  }
        );
    }
    private void init(View view){
        email2 = view.findViewById(R.id.email2);
        pasword2 = view.findViewById(R.id.pasword2);
        login2 = view.findViewById(R.id.login2);
        signup2 = view.findViewById(R.id.signup2);
    }
}