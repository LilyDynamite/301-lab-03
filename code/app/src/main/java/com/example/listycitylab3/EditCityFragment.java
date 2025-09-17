package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    interface EditCityDialogueListener{
        void editCity(City city);

        City getCurrCity();
    }

    private EditCityDialogueListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof EditCityDialogueListener)
        {
            listener = (EditCityDialogueListener) context;
        }
        else {
            throw new RuntimeException(context + " must implement EditCityDialogueListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);

        //view is our full file, so we can get the text from it
        EditText editCityText = view.findViewById(R.id.edit_text_city_text);
        editCityText.setText(listener.getCurrCity().getName());
        EditText editProvinceText = view.findViewById(R.id.edit_text_province_text);
        editProvinceText.setText(listener.getCurrCity().getProvince());


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", (dialog, which) -> {
                    String cityName = editCityText.getText().toString();
                    String provinceName = editProvinceText.getText().toString();
                    listener.editCity(new City(cityName, provinceName));
                })
                .create();
    }
}
