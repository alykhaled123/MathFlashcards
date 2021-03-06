package com.alykhaled.mathflashcards;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<ListItem> mLists;
    private LayoutInflater layoutInflater;
    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick (int position, ArrayList<ListItem> t);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public ListAdapter(Context mContext, ArrayList<ListItem> mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        ListItem list = mLists.get(position);
        holder.mListName.setText(list.getList_name());
        Picasso.get().load(list.getList_pic()).fit().into(holder.mListImage);

    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mListName;
        public ImageView mListImage;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mListName = itemView.findViewById(R.id.listText);
            mListImage = itemView.findViewById(R.id.list_back);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position,mLists);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(itemView.getContext(), "upload failed: ", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }
}
