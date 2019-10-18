package com.example.mydiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>{

    List<DiaryStore> diaries;
    OnItemClickListener listener;

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_diary_layout, parent, false);
        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        holder.textTitle.setText(diaries.get(position).getTitle());
        holder.textContent.setText(diaries.get(position).getContent());
        holder.textDate.setText(diaries.get(position).getDatetime());
    }

    @Override
    public int getItemCount() {
        if (diaries == null) {
            return 0;
        }
        return diaries.size();
    }

    class DiaryViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        TextView textContent;
        TextView textDate;
        Button btnUpdate;
        Button btnDelete;

        public DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.edit_text_Title);
            textContent = itemView.findViewById(R.id.edit_text_Content);
            textDate = itemView.findViewById(R.id.edit_text_Date);
            btnUpdate = itemView.findViewById(R.id.btn_Upd);
            btnDelete = itemView.findViewById(R.id.btn_Del);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUpdateClick(getAdapterPosition());
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteClick(getAdapterPosition());
                }
            });
        }
    }

    interface OnItemClickListener {
        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }
}