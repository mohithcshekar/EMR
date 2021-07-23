package com.example.emr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.myViewHolder> {
    Context context;
    ArrayList name_col,id_col,mob_col,consid_col,refby,remarks_col,medMor,medAft,medNight;
    customAdapter(Context context,ArrayList name_col,ArrayList id_col,ArrayList mob_col,ArrayList consid_col,ArrayList refby, ArrayList remarks_col, ArrayList medMor,ArrayList medAft,ArrayList medNight ){
        this.context=context;
        this.name_col=name_col;
        this.id_col=id_col;
        this.mob_col=mob_col;
        this.consid_col=consid_col;
        this.refby=refby;
        this.remarks_col=remarks_col;
        this.medMor=medMor;
        this.medAft=medAft;
        this.medNight=medNight;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.nameS.setText(String.valueOf(name_col.get(position)));
        holder.idS.setText(String.valueOf(id_col.get(position)));
        holder.phS.setText(String.valueOf(mob_col.get(position)));
        holder.cidS.setText(String.valueOf(consid_col.get(position)));
        holder.refS.setText(String.valueOf(refby.get(position)));
        holder.reS.setText(String.valueOf(remarks_col.get(position)));

        holder.morS.setText(String.valueOf(medMor.get(position)));
        holder.aftS.setText(String.valueOf(medAft.get(position)));
        holder.nightS.setText(String.valueOf(medNight.get(position)));


    }

    @Override
    public int getItemCount() {
        return id_col.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView nameS,idS,cidS,phS,reS,refS,morS,aftS,nightS;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            nameS = itemView.findViewById(R.id.nameS);
            idS = itemView.findViewById(R.id.idS);
            cidS = itemView.findViewById(R.id.cidS);
            phS = itemView.findViewById(R.id.phS);
            refS=itemView.findViewById(R.id.refS);
            reS = itemView.findViewById(R.id.reS);
            morS = itemView.findViewById(R.id.morS);
            aftS = itemView.findViewById(R.id.aftS);
            nightS = itemView.findViewById(R.id.nightS);

        }
    }
}
