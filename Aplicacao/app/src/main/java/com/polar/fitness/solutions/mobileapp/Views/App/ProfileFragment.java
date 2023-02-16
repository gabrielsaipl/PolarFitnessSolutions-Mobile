package com.polar.fitness.solutions.mobileapp.Views.App;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.polar.fitness.solutions.mobileapp.Models.SingletonGestorUsers;
import com.polar.fitness.solutions.mobileapp.Models.User;
import com.polar.fitness.solutions.mobileapp.R;
import com.polar.fitness.solutions.mobileapp.databinding.FragmentProfileBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    int SELECT_PICTURE = 200;
    Uri selectedImageUri;
    ImageView ProfilePic;
    EditText etUsername, etEmail, etRua, etCodPostal, etLocalidade, etTelefone, etNIF;
    Spinner spGenero;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
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

        SharedPreferences sh = Objects.requireNonNull(getContext()).getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String s1 = sh.getString("img","");
        loadImageFromStorage(s1);

        binding.editProfilePictureButton.setOnClickListener(editProfilePictureButton -> {imageChooser();});

        binding.btGuardarImagem.setOnClickListener(btGuardarImagem -> {

            if (null != selectedImageUri) {
                try {
                    //get the bitmap and save the path in shared preferences
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(this.getActivity()).getContentResolver(), selectedImageUri);
                    String path = saveToInternalStorage(bitmap);
                    SharedPreferences.Editor myEdit = sh.edit();
                    myEdit.putString("img", path);
                    myEdit.apply();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Toast.makeText(getContext(), "Imagem Guardada", Toast.LENGTH_SHORT).show();
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

        binding.btEditar.setOnClickListener(btEditar ->{
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String rua = etRua.getText().toString();
            String codigoPostal = etCodPostal.getText().toString();
            String localidade = etLocalidade.getText().toString();
            String telefone = etTelefone.getText().toString();
            String NIF = etNIF.getText().toString();
            String genero = spGenero.getSelectedItem().toString();
            SingletonGestorUsers.getInstance(getContext()).AtualizarDadosClienteAPI(username, email, rua, codigoPostal, localidade, telefone,NIF, genero,getContext());
            assert getFragmentManager() != null;
            FragmentTransaction tr = getFragmentManager().beginTransaction();
            tr.replace(R.id.fragment_container, ProfileFragment.this);
            tr.commit();
            SharedPreferences.Editor myEdit = sh.edit();
            myEdit.putString("etUsername", username);
            myEdit.putString("email", email);
            myEdit.apply();
            Toast.makeText(getContext(), "Dados alterados com sucesso", Toast.LENGTH_SHORT).show();
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                //allways throws filenotfoundexeption

                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    // and download image to the device
                    /*try {
                        //get the bitmap and save the path in shared preferences
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(this.getActivity()).getContentResolver(), selectedImageUri);
                        String path = saveToInternalStorage(bitmap);
                        SharedPreferences sh = Objects.requireNonNull(getContext()).getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sh.edit();
                        myEdit.putString("img", path);
                        myEdit.apply();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }*/
                    binding.profilePicture.setImageURI(selectedImageUri);

                }
            }
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory,"profile.png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public void loadImageFromStorage(String path)
    {

        try {
            File f = new File(path, "profile.png");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            binding.profilePicture.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
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
        if (!selec1.equals(genero)) {spGenero.setSelection(2);}
    }
}