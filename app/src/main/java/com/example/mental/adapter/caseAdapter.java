package com.example.mental.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.streamchatdemo.R;
import com.example.mental.data.patientsData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class caseAdapter extends ArrayAdapter<patientsData> {

    ArrayList<patientsData> data = new ArrayList<>();
    Context context;
    public caseAdapter(Context c, int textViewResourceId, ArrayList<patientsData> objects) {
        super(c, textViewResourceId, objects);
        this.data = objects;
        this.context = c;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        patientsData subjectData = data.get(position);

        //CasesAdapter.ViewHolder holder = null;
/*
        if(convertView == null) {

            //holder = new CasesAdapter.ViewHolder();

           // convertView.setTag(holder);
        }else{
           // holder = (CasesAdapter.ViewHolder) convertView.getTag();
        }

*/
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.cases_structure, null);

        convertView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            }
        });

        TextView title = convertView.findViewById(R.id.patient_cases_name);
        TextView age = convertView.findViewById(R.id.patient_cases_age);
        TextView email = convertView.findViewById(R.id.patient_cases_email);
        TextView date = convertView.findViewById(R.id.patient_cases_date);
        TextView telephone = convertView.findViewById(R.id.patient_cases_telephone);
        TextView height = convertView.findViewById(R.id.patient_cases_height);
        TextView weight = convertView.findViewById(R.id.patient_cases_weight);
        TextView symptoms = convertView.findViewById(R.id.patient_cases_symptoms);
        TextView sickness = convertView.findViewById(R.id.patient_cases_sickness);
        ImageView image = convertView.findViewById(R.id.patient_cases_Img);

        title.setText(subjectData.getName()+""+getCount());
        age.setText(""+subjectData.getAge());
        email.setText(subjectData.getEmail());
        telephone.setText(subjectData.getTelephone());
        height.setText(""+subjectData.getHeight());
        weight.setText(""+subjectData.getWeight());
        date.setText(subjectData.getDate());
        sickness.setText(subjectData.getUniqueRecord());

        String[] symptomsArr = subjectData.getSymptoms().split("\\^");
        String symptomsAll = "";
        for(String symptom: symptomsArr){
            symptomsAll += Html.fromHtml(symptom + "<br>");
        }

        symptoms.setText(symptomsAll);

        Picasso.with(context)
                .load("http://192.168.0.22/mentalImgs/" + subjectData.getImage())
                .into(image);
        return convertView;
    }

}