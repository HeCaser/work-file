package com.example.xqtest.scrollabletable;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kelin on 16-11-18.
 */

/**
 * Base class for an Adapter
 * <p>
 * <p>Adapters provide a binding from an app-specific data set to views that are displayed
 * within a 2-dimensional {@link RecyclerView}.</p>
 */
public abstract class TableAdapter {

    /**
     * Returns the total number of items every row in the data set hold by the adapter.
     *
     * @return The total number of items every row in this adapter.
     */
    public abstract int getRowCount();

    /**
     * Returns the total number of items every column in the data set hold by the adapter.
     *
     * @return The total number of items every column in this adapter.
     */
    public abstract int getColumnCount();


    /**
     * Return the view type of the item at <code>row column</code> for the purposes
     * of view recycling.
     * <p>
     * <p>The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param row    row-position to query
     * @param column column-position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * <code>row column</code>. Type codes need not be contiguous.
     */
    public int getItemViewType(int row, int column) {
        return 0;
    }

    /**
     * @param holder ViewHolder
     * @param row    row-position to query
     * @param column column-position to query
     */
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column);

    /**
     * header的row固定为0
     */
    public abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int column);

    public boolean isFirstColumnViewHeight() {
        return false;
    }

    public void onBindRow(View rowLayout, int row) {

    }

    public void onBindFollowBackground(View rowLayout, int row) {

    }

    /**
     * @param parent ViewGroup
     * @param viewType item view type
     */
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    public int getItemViewWidth(int row, int column) {
        return -1;
    }

    /**
     * 行开始的index，默认-1
     */
    public int getRowStart(){
        return -1;
    }

    /**
     * 列开始的index，默认0
     */
    public int getColumnStart() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract boolean hasHeadRow();

}
