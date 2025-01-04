package com.example.appcontroller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class planningListAdapter extends BaseAdapter  {
    private List<planning> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public planningListAdapter(Context aContext, List<planning> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.planning, null);
            holder = new ViewHolder();
            holder.nombinomeView = (TextView) convertView.findViewById(R.id.binome);
            holder.bus = (TextView) convertView.findViewById(R.id.bus);
            holder.matriculebinomeView = (TextView) convertView.findViewById(R.id.matriculebinome);
            holder.station = (TextView) convertView.findViewById(R.id.station);
            holder.heureView = (TextView) convertView.findViewById(R.id.heure);
            holder.ligneView = (TextView) convertView.findViewById(R.id.ligne);
            holder.ligneView.setPaintFlags(holder.ligneView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.flagView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            holder.numeroView=(TextView) convertView.findViewById(R.id.numero);
            holder.imagetlfView=(ImageView)convertView.findViewById(R.id.imageView_numtlf);
            holder.rapportView=(TextView)convertView.findViewById(R.id.Rapport);
            holder.cliqueView=(ImageView)convertView.findViewById(R.id.clique);
            holder.rapportView.setPaintFlags(holder.ligneView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.mapView=(ImageView)convertView.findViewById(R.id.map);
            holder.cliqueiconView=(ImageView)convertView.findViewById(R.id.cliqueicon);


            View finalConvertView = convertView;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalConvertView.setBackgroundColor(Color.LTGRAY);


                }
            });

            holder.rapportView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String matriculebinome=(String) holder.matriculebinomeView.getText();
                    String heure =(String) holder.heureView.getText();
                    SharedPreferences.Editor myEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                    myEdit.putString("matriculebinome", matriculebinome );
                    myEdit.putString("heure", heure );
                    myEdit.apply();
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                    String s2 = sh.getString("codeqr", "");
                    if (!s2.equals("")) {
                        Intent l = new Intent(v.getContext(), Rapport.class);
                        context.startActivity(l);
                        SharedPreferences.Editor Edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                        Edit.putString("codeqr", "");
                        Edit.apply();

                    } else {
                        Toast.makeText(context, "Vous devez scanner votre code QR  !!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            holder.ligneView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String ligne = (String) holder.ligneView.getText();

                    SharedPreferences.Editor myEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                    myEdit.putString("ligne", ligne );
                    myEdit.apply();
                    Toast.makeText(context, " Bienvenue ", Toast.LENGTH_LONG).show();
                    Intent OsmdroidMap = new Intent(v.getContext(),OsmdroidMap.class);
                    context.startActivity(OsmdroidMap);
                }
            });



            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        planning Planning =this.listData.get(position);
        holder.nombinomeView.setText( Planning.getNombinome());
        holder.bus.setText(Planning.getBus());
        holder.matriculebinomeView.setText(Planning.getMatriculebinome());
        holder.station.setText( Planning.getStation());
        holder.heureView.setText( Planning.getHeure());
        holder.ligneView.setText( Planning.getLigne());
        holder.flagView.setImageResource(R.drawable.tache);
        holder.numeroView.setText(Planning.getNumero());
        holder.imagetlfView.setImageResource(R.drawable.imagetlf);
        holder.rapportView.setText("Rapport");
        holder.cliqueView.setImageResource(R.drawable.clique);
        holder.mapView.setImageResource(R.drawable.map);
        holder.cliqueiconView.setImageResource(R.drawable.clickicon);





        return convertView;
    }




    static class ViewHolder {

        TextView nombinomeView;
        TextView bus;
        TextView matriculebinomeView;
        TextView station;
        TextView heureView;
        TextView ligneView;
        ImageView flagView;
        TextView numeroView;
        ImageView imagetlfView;
        TextView  rapportView;
        ImageView cliqueView ;
        ImageView mapView;
        ImageView cliqueiconView;

    }


    }

