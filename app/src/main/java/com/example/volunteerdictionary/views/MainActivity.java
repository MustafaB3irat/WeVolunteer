package com.example.volunteerdictionary.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.volunteerdictionary.R;
import com.example.volunteerdictionary.adapters.CountryAdapter;
import com.example.volunteerdictionary.adapters.VolunteerCardAdapter;
import com.example.volunteerdictionary.databinding.ActivityMainBinding;
import com.example.volunteerdictionary.interfaces.Volunteers;
import com.example.volunteerdictionary.models.pojo.Country;
import com.example.volunteerdictionary.models.pojo.Volunteer;
import com.example.volunteerdictionary.presenters.VolunteersPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Volunteers.VolunteersView {




    public ActivityMainBinding activityMainBinding;
    private Volunteers.VolunteersPresenter volunteersPresenter;
    private Bundle inputData = new Bundle();
    final Calendar myCalendar = Calendar.getInstance();
    String countryName = "";
    private VolunteerCardAdapter volunteerCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        volunteersPresenter = new VolunteersPresenter(this);

        if ( savedInstanceState!= null && !savedInstanceState.isEmpty()){
            activityMainBinding.volunteerName.setText(savedInstanceState.getString("volunteerName"));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        activityMainBinding.volunteersRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        //loading progress bar
        activityMainBinding.mainView.setVisibility(View.INVISIBLE);

        activityMainBinding.progresBar.setVisibility(View.VISIBLE);
        activityMainBinding.loadingLayout.setVisibility(View.VISIBLE);

        addVolunteerOnClickListener();


        displayVolunteers();
        setDatePicker();

        volunteersPresenter.getCountries();


        activityMainBinding.volunteerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                inputData.putString("volunteerName" , s.toString());

            }
        });

    }

    @Override
    public void displayVolunteers() {

        List<Volunteer> volunteers = volunteersPresenter.getVolunteers();


        if (volunteers == null || volunteers.size() == 0) {

            volunteers = new ArrayList<>();
            volunteers.add(new Volunteer("Add Volunteers now!", "", "", "", "", "", "", ""));

        }

        volunteerCardAdapter = new VolunteerCardAdapter(volunteers);
        activityMainBinding.volunteersRecyclerView.setAdapter(volunteerCardAdapter);

    }





    @Override
    public void addVolunteerOnClickListener() {

        activityMainBinding.addVolunteer.setOnClickListener(e -> {

            RadioButton gender = findViewById(activityMainBinding.gender.getCheckedRadioButtonId());
            String isActive = !activityMainBinding.isActive.getTextOff().toString().equals("") ? "Not active" : "Active";


            if (validate()) {

                boolean addStatus = volunteersPresenter.addVolunteer(activityMainBinding.volunteerName.getText().toString(),
                        activityMainBinding.volunteerPhone.getText().toString(), activityMainBinding.country.getSelectedItem().toString(),
                        gender.getText().toString(), activityMainBinding.volunteerWork.getText().toString(), activityMainBinding.volunteerHours.getText().toString(),
                        activityMainBinding.volunteerDOB.getText().toString(), isActive);
                if (addStatus) {
                    Toast.makeText(this, "Added Volunteer Successfully!", Toast.LENGTH_SHORT).show();
                    displayVolunteers();

                } else
                    Toast.makeText(this, "Sorry, an error happened. Try Again later!", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(this, "All Fields Are Required!", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void setDatePicker() {


        DatePickerDialog.OnDateSetListener datePicker = (datePicker1, year, monthOfYear, dayOfMonth) -> {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        activityMainBinding.volunteerDOB.setOnClickListener(e -> {
            new DatePickerDialog(this, datePicker, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    @Override
    public boolean validate() {

        return (activityMainBinding.country.getSelectedItem() != null &&
                !TextUtils.isEmpty(activityMainBinding.volunteerDOB.getText().toString()) &&
                !TextUtils.isEmpty(activityMainBinding.volunteerHours.getText().toString()) &&
                !TextUtils.isEmpty(activityMainBinding.volunteerName.getText().toString()) &&
                !TextUtils.isEmpty(activityMainBinding.volunteerPhone.getText().toString()) &&
                activityMainBinding.gender.getCheckedRadioButtonId() != -1);

    }

    @Override
    public void getCountries(List<Country> countries) {

        CountryAdapter mAdapter = new CountryAdapter(this, countries);

        activityMainBinding.country.setAdapter(mAdapter);

        activityMainBinding.progresBar.setVisibility(View.INVISIBLE);
        activityMainBinding.loadingLayout.setVisibility(View.INVISIBLE);
        activityMainBinding.mainView.setVisibility(View.VISIBLE);

    }

    @Override
    public void initOnCountrySelectedListener() {
        activityMainBinding.country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Country country = (Country) adapterView.getItemAtPosition(i);

                countryName = country.getName();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        activityMainBinding.volunteerDOB.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState = inputData;
    }
}
