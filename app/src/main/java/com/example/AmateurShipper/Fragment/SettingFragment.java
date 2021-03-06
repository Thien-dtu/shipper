package com.example.AmateurShipper.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.AmateurShipper.Activity.LoginActivity;
import com.example.AmateurShipper.R;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.AmateurShipper.Activity.LoginActivity.MyPREFERENCES;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ScrollView layoutSetting;
    CardView signOut,manageAccount;
    ImageView back;

    private FirebaseAuth mAuth;
    SharedPreferences sharedpreferences;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        signOut = view.findViewById(R.id.card_sign_out);
        manageAccount = view.findViewById(R.id.card_manage_acc);
        back = view.findViewById(R.id.btn_back);
        layoutSetting = view.findViewById(R.id.layout_setting);

        mAuth = FirebaseAuth.getInstance();
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        manageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToManageAccount();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToProfile();
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogOut();
            }
        });
        return view;
    }

    public void sigOut(){
        mAuth.signOut();
        clearData();
        Intent intent_toLogin = new Intent(getActivity(), LoginActivity.class);
        intent_toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_toLogin);
    }
    private void clearData() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }
    public void dialogLogOut(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("????ng Xu???t!");
        dialog.setMessage("B???n th???c s??? mu???n ????ng xu???t?");
        dialog.setPositiveButton("C??", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sigOut();
            }
        });
        dialog.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = dialog.create();
        al.show();
    }


    public void goToManageAccount(){
        layoutSetting.setVisibility(View.GONE);
        ManageAccountFragment tabManageAcc= new ManageAccountFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.setting_frame,tabManageAcc);
        fragmentTransaction.commit();
    }
    public void backToProfile(){
        layoutSetting.setVisibility(View.GONE);
        EditProfileFragment tabProfile= new EditProfileFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.setting_frame,tabProfile);
        fragmentTransaction.commit();
    }

}