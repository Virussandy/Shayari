package com.sandy.shayari.SecondFragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.sandy.shayari.DisplayShayari;
import com.sandy.shayari.R;
import com.squareup.picasso.Picasso;

public class DataAdapter extends FirebaseRecyclerAdapter<DataModel,DataAdapter.DataViewHolder>  {

    public DataAdapter(@NonNull FirebaseRecyclerOptions<DataModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DataViewHolder holder, int position, @NonNull DataModel model) {
        Picasso.get().load(model.getLogo_url()).into(holder.shayari_image);
        holder.shayari_text.setText(model.getLogo_text());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = getRef(holder.getAbsoluteAdapterPosition()).getKey();
                Intent intent = new Intent(v.getContext(), DisplayShayari.class);
                intent.putExtra("key",key);
                v.getContext().startActivity(intent);
            }
        });
    }


    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class DataViewHolder extends RecyclerView.ViewHolder{

        ImageView shayari_image;
        TextView shayari_text;
        public DataViewHolder(@NonNull View itemView){
            super(itemView);

            shayari_image = itemView.findViewById(R.id.shayari_image);
            shayari_text = itemView.findViewById(R.id.shayari_text);
        }
    }

}
