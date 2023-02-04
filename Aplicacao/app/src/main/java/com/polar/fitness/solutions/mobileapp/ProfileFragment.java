package com.polar.fitness.solutions.mobileapp;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.User;
import com.polar.fitness.solutions.mobileapp.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    int SELECT_PICTURE = 200;

    EditText etUsername, etEmail, etRua, etCodPostal, etLocalidade, etTelefone, etNIF;
    Spinner spGenero;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment with bind
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.editProfilePictureButton.setOnClickListener(editProfilePictureButton -> {
            imageChooser();
        });
        binding.btEditar.setOnClickListener(btEditar ->{
            Toast.makeText(getContext(), "ola", Toast.LENGTH_SHORT).show();
        });
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etRua = (EditText) view.findViewById(R.id.etRua);
        etCodPostal = (EditText) view.findViewById(R.id.etCondigoPostal);
        etLocalidade = (EditText) view.findViewById(R.id.etLocalidade);
        etTelefone = (EditText) view.findViewById(R.id.etTelefone);
        etNIF = (EditText) view.findViewById(R.id.etNIF);
        spGenero = (Spinner) view.findViewById(R.id.spinnerGenero);
        dados();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    binding.profilePicture.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public void dados() {
        //aceder ao nome do utilizador
        ArrayList<User> users = SingletonGestorUsers.getInstance(getContext()).getUserBD();
        User user = users.get(0);
        //atribuir os valores nas textviews
        String username = user.getUsername();
        String email = user.getEmail();
        String street = user.getStreet();
        String zip_code = user.getZip_code();
        String area = user.getArea();
        String phone_number = String.valueOf(user.getPhone_number());
        String nif = String.valueOf(user.getNif());
        String genero = user.getGender();
        etUsername.setText(username);
        etEmail.setText(email);
        etRua.setText(street);
        etCodPostal.setText(zip_code);
        etLocalidade.setText(area);
        etTelefone.setText(phone_number);
        etNIF.setText(nif);
        spinnerSelection(genero);
    }

    public void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void spinnerSelection(String genero){
        spGenero.setSelection(0);
        String selec = spGenero.getSelectedItem().toString();
        if (!selec.equals(genero)) {spGenero.setSelection(1);}
        String selec1 = spGenero.getSelectedItem().toString();
        if (!selec.equals(genero)) {spGenero.setSelection(2);}
    }
}