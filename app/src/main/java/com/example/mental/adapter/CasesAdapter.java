package com.example.mental.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.streamchatdemo.R;
import com.example.mental.data.patientsData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CasesAdapter extends ArrayList<patientsData> implements android.widget.ListAdapter  {
    ArrayList<patientsData> data;
    Context context;
    public CasesAdapter(Context context, int a, ArrayList<patientsData> data) {
        super(data);
        this.data = data;
        this.context = context;
    }
    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
        return true;
    }

    public void registerDataSetObserver(DataSetObserver observer) {
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {
        patientsData subjectData = data.get(position);

        ViewHolder holder = null;

        if(convertView == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.cases_structure, null);
            convertView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                }
            });

            holder = new ViewHolder();

            holder.title = (TextView)convertView.findViewById(R.id.patient_cases_name);
            holder.age = (TextView)convertView.findViewById(R.id.patient_cases_age);
            holder.email = (TextView)convertView.findViewById(R.id.patient_cases_email);
            holder.date = (TextView)convertView.findViewById(R.id.patient_cases_date);
            holder.telephone = (TextView)convertView.findViewById(R.id.patient_cases_telephone);
            holder.height = (TextView)convertView.findViewById(R.id.patient_cases_height);
            holder.weight = (TextView)convertView.findViewById(R.id.patient_cases_weight);
            holder.symptoms = (TextView)convertView.findViewById(R.id.patient_cases_symptoms);
            holder.sickness = (TextView)convertView.findViewById(R.id.patient_cases_sickness);
            holder.image = (ImageView) convertView.findViewById(R.id.patient_cases_Img);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(subjectData.getName());
        holder.age.setText(""+subjectData.getAge());
        holder.email.setText(subjectData.getEmail());
        holder.telephone.setText(subjectData.getTelephone());
        holder.height.setText(""+subjectData.getHeight());
        holder.weight.setText(""+subjectData.getWeight());
        holder.date.setText(subjectData.getDate());
        holder.sickness.setText(subjectData.getUniqueRecord());

        String[] symptomsArr = subjectData.getSymptoms().split("^");
        String symptomsAll = "";
        for(String symptom: symptomsArr){
            symptomsAll += Html.fromHtml(symptom + "<br>");
        }

        holder.symptoms.setText(symptomsAll);

        Picasso.with(context)
                .load("http://192.168.0.22/mentalImgs/" + subjectData.getImage())
                .into(holder.image);
        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView age;
        TextView email;
        TextView date;
        TextView telephone;
        TextView height;
        TextView weight;
        TextView symptoms;
        TextView sickness;
        ImageView image;
        Button chat;
    }
   /* @NonNull
    @NotNull

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }


    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

    }
*/

    public int getItemViewType(int position) {
        return position;
    }

    public int getViewTypeCount() {
        return data.size();
    }

    public boolean isEmpty() {
        return false;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}

