/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.headerrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView.Adapter extension created to add header capability support and a generic List of
 * items really useful most of the cases. You should extend from this class and override
 * onCreateViewHolder to create your ViewHolder instances and onBindViewHolder methods to draw your
 * user interface as you wish.
 * <p>
 * The usage of List<T> items member is not mandatory. If you are going to provide your custom
 * implementation remember to override getItemCount method.
 */
public abstract class HeaderRecyclerViewAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    protected static final int view_TYPE_HEADER = -2;

    boolean showHeader;
    List items = Collections.EMPTY_LIST;

    /**
     * Invokes onCreateHeaderViewHolder, onCreateItemViewHolder or onCreateFooterViewHolder methods
     * based on the view type param.
     */
    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH viewHolder;
        if (isHeaderType(viewType)) {
            viewHolder = onCreateHeaderViewHolder(parent, viewType);
        } else {
            viewHolder = onCreateItemViewHolder(parent, viewType);
        }
        return viewHolder;
    }

    /**
     * If you don't need header feature, you can bypass overriding this method.
     */
    protected VH onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);


    /**
     * Invokes onBindHeaderViewHolder, onBindItemViewHolder or onBindFooterViewHOlder methods based
     * on the position param.
     */
    @Override
    public final void onBindViewHolder(VH holder, int position) {

        boolean isHeader = isHeaderPosition(position);

        if (isHeader) {
            onBindHeaderViewHolder(holder, position);
        } else {

            if(showHeader){
                onBindItemViewHolder(holder, position - 1); // account for off by one
            }else{
                onBindItemViewHolder(holder, position);
            }
        }
    }

    /**
     * If you don't need header feature, you can bypass overriding this method.
     */
    protected void onBindHeaderViewHolder(VH holder, int position) {
    }

    protected abstract void onBindItemViewHolder(VH holder, int position);

    /**
     * Invokes onHeaderViewRecycled, onItemViewRecycled or onFooterViewRecycled methods based
     * on the holder.getAdapterPosition()
     */
    @Override
    public final void onViewRecycled(VH holder) {
        int position = holder.getAdapterPosition();

        if (isHeaderPosition(position)) {
            onHeaderViewRecycled(holder);
        } else {
            onItemViewRecycled(holder);
        }
    }

    protected void onHeaderViewRecycled(VH holder) {
    }

    protected void onItemViewRecycled(VH holder) {
    }

    /**
     * Returns the type associated to an item given a position passed as arguments. If the position
     * is related to a header item returns the constant view_TYPE_HEADER or TYPE_FOOTER if the position is
     * related to the footer, if not, returns TYPE_ITEM.
     * <p>
     * If your application has to support different types override this method and provide your
     * implementation. Remember that view_TYPE_HEADER, TYPE_ITEM and TYPE_FOOTER are internal constants
     * can be used to identify an item given a position, try to use different values in your
     * application.
     */
    @Override
    public int getItemViewType(int position) {

        if (isHeaderPosition(position)) {
            return view_TYPE_HEADER;
        }

        if(showHeader) // compoensate for off by 1
            position--;

        return getRealItemViewType(position);
    }

    protected abstract int getRealItemViewType(int position);

    /**
     * Returns the items list size if there is no a header configured or the size taking into account
     * that if a header or a footer is configured the number of items returned is going to include
     * this elements.
     */
    @Override
    public int getItemCount() {

        int size = getRealItemCount();

        if (showHeader) {
            size++;
        }

        return size;
    }


    public abstract int getRealItemCount();

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    /**
     * Returns true if the position type parameter passed as argument is equals to 0 and the adapter
     * has a not null header already configured.
     */
    public boolean isHeaderPosition(int position) {
        return showHeader && position == 0;
    }

    /**
     * Returns true if the view type parameter passed as argument is equals to view_TYPE_HEADER.
     */
    protected boolean isHeaderType(int viewType) {
        return viewType == view_TYPE_HEADER;
    }




}
