package com.viaplay.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viaplay.R;
import com.viaplay.databinding.SectionListItemBinding;
import com.viaplay.livedata.SectionPreview;

import java.util.ArrayList;
import java.util.List;

public class ViaplayٰRecyclerViewAdapter extends RecyclerView.Adapter<ViaplayٰRecyclerViewAdapter.ViaplayViewHolder> {

    private List<SectionPreview> items = new ArrayList<>();
    private Context context;
    private SectionListItemClickListener itemClickListener = new SectionListItemClickListener();

    public ViaplayٰRecyclerViewAdapter(List<SectionPreview> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public SectionPreview getItem(int position){
        return items.get(position);
    }

    @NonNull
    @Override
    public ViaplayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        SectionListItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.section_list_item, viewGroup, false);
        ViaplayViewHolder viewHolder = new ViaplayViewHolder(itemBinding.getRoot(), itemBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViaplayViewHolder viaplayViewHolder, int i) {
        SectionPreview section = items.get(i);
        viaplayViewHolder.getBinding().setItem(section);
        viaplayViewHolder.getBinding().setListener(itemClickListener);
        viaplayViewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViaplayViewHolder extends RecyclerView.ViewHolder{
        private SectionListItemBinding binding;

        public SectionListItemBinding getBinding() {
            return binding;
        }

        public ViaplayViewHolder(@NonNull View itemView, SectionListItemBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }



}
