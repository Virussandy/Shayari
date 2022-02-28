package com.sandy.shayari.FirstFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sandy.shayari.R;
import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    private List<String> random_list;

    public DataAdapter(List<String> random_list) {
        this.random_list = random_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View)LayoutInflater.from(parent.getContext()).inflate(R.layout.data_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String list = random_list.get(position);
        holder.shayari_text.setText((CharSequence) list);
    }

    @Override
    public int getItemCount() {
        if(random_list != null){
            return random_list.size();
        }else{
           return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         public final TextView shayari_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        shayari_text = itemView.findViewById(R.id.shayari_text);
        }
    }
}
