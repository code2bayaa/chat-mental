package com.example.mental.doctor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.streamchatdemo.R;
import com.example.mental.adapter.CustomAdapter;
import com.example.mental.api.patientsAPI;
import com.example.mental.data.patientsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public  class patientsDoctor extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ListView list;
    private TextView mentalPatients;
    private ArrayList<String> emailArr,recordArr;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patients_doctor, container, false);

        list = (ListView) rootView.findViewById(R.id.profile_patients_list);
        mentalPatients = (TextView) rootView.findViewById(R.id.mental_patients);

        teleportData();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String patientE = emailArr.get(position);
                String recordE = recordArr.get(position);

                SharedPreferences pref = getActivity().getSharedPreferences("patient", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("patient", patientE);
                edit.putString("record", recordE);
                edit.commit();

                Toast.makeText(getContext(),"Please wait...",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getContext(), patientsRecords.class));

            }
        });

        return rootView;
    }

    private void teleportData(){
        String permit = "patients";
        SharedPreferences pref = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user = pref.getString("user", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(patientsAPI.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        patientsAPI api = retrofit.create(patientsAPI.class);
        Call<String> call = api.getStrings(permit,user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //progressBar.setVisibility(View.GONE);
                //mentalPatients.setText(response.body());

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        try {
                            JSONObject data = new JSONObject(response.body());
                            String message = data.getString("message");
                            if(message.equals("Success!")) {
                                JSONArray patients = data.getJSONArray("data");
                                //JSONArray patients = data.optJSONArray("data");

                                emailArr = new ArrayList<String>();
                                recordArr = new ArrayList<String>();
                                ArrayList<patientsData> arrayList = new ArrayList<patientsData>();

                                for (int i = 0; i < patients.length(); i++) {

                                    JSONObject jsonObject = patients.getJSONObject(i);

                                    String name = jsonObject.optString("name");
                                    String telephone = jsonObject.optString("telephone");
                                    int age = Integer.parseInt(jsonObject.optString("age"));
                                    String image = jsonObject.optString("image");
                                    String email = jsonObject.optString("email");
                                    String symptoms = jsonObject.optString("symptoms");
                                    float height = Float.parseFloat(jsonObject.optString("height"));
                                    float weight = Float.parseFloat(jsonObject.optString("weight"));
                                    String uniqueRecord = jsonObject.optString("uniqueRecord");
                                    String date = jsonObject.optString("date");

                                    emailArr.add(email);
                                    recordArr.add(uniqueRecord);


                                    arrayList.add(new patientsData(name,telephone,age,image,email,symptoms,height,weight,uniqueRecord,date));
                                }

                                if(arrayList.size() > 0) {
                                    plotValues(arrayList);
                                }else {
                                    Toast.makeText(getContext(), "No Sick Patients Available", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getContext(), "No response from the server", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getContext(), "Response not successful "+response.toString(), Toast.LENGTH_SHORT).show();

                }
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error occurred during upload", Toast.LENGTH_SHORT).show();

            }
        });


    }

/*
    private View.OnClickListener treatClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            View parentRow = (View) v.getParent();

            ListView listView = (ListView) parentRow.getParent();

            final int position = listView.getPositionForView(parentRow);


        }

    };



           JSONObject jsonRootObject = new JSONObject(strJson);
         JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");
         for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int id = Integer.parseInt(jsonObject.optString("ID"));
            String name = jsonObject.optString("Name");
            float salary = Float.parseFloat(jsonObject.optString("Salary"));
            data.append("Employee ").append(i).append(" : \n ID= ")
               .append(id).append(" \n " + "Name= ")
               .append(name).append(" \n Salary= ")
               .append(salary).append(" \n\n ");
         }

             JSONObject json = new JSONObject(content);
    JSONArray jArray = json.getJSONArray("rows");
                JSONObject json_data = null;
                for (int i = 0; i < jArray.length(); i++) {
                    json_data = jArray.getJSONObject(i);
                    String fname = json_data.getString("Fname");
    String lname = json_data.getString("Lname");
                    HashMap<String, String>map=new HashMap<String, String>();
                    map.put("Fname",Fname);
                    map.put("LName", Lname);
                    alist.add(map);
                }
     */

    private void plotValues(ArrayList<patientsData> data){

      CustomAdapter customAdapter = new CustomAdapter(getContext(), R.layout.patients_structure, data);
      //ListAdapter customAdapter = new ListAdapter(this, R.layout.fragment_profile_doctor, data);

      list.setAdapter(customAdapter);
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}