package com.karumi.headerrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class HeaderRecyclerViewAdapterBuilder<VH extends RecyclerView.ViewHolder> {

    HeaderRecyclerViewAdapter<VH> adapter = new HeaderRecyclerViewAdapter<VH>() {

        @Override
        protected VH onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        protected VH onCreateItemViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        protected void onBindHeaderViewHolder(VH holder, int position) {

        }

        @Override
        protected void onBindItemViewHolder(VH holder, int position) {

        }

        @Override
        protected int getRealItemViewType(int position) {
            return 0;
        }

        @Override
        public int getRealItemCount() {
            return 0;
        }


    };


    public HeaderRecyclerViewAdapter<VH> build() {
        return adapter;
    }
}
