package com.chinatown254.notes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chinatown254.notes.R;
import com.chinatown254.notes.database.NoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.viewHolder> {
    private final List<NoteEntity> mNotes;
    private final Context mContext;

    public NotesAdapter(List<NoteEntity> mNotes, Context mContext) {
        this.mNotes = mNotes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_list_item, parent,false);
        return new  viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final NoteEntity note = mNotes.get(position);
        holder.mTextView.setText(note.getText());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.note_text)
        TextView mTextView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
