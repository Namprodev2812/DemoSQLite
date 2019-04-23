package com.asterisk.nam.DemoSQLite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.ViewHolder> {

    private ItemRecyclerView mItemRecyclerView;
    private Context mContext;
    private List<Giohang> mGiohangs;

    public GiohangAdapter(Context context, List<Giohang> mGiohangs) {
        this.mContext = context;
        this.mGiohangs = mGiohangs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder myViewHolder, final int i) {
        myViewHolder.bindData(i);
        setOnItemClickListener((ItemRecyclerView) mContext);
        myViewHolder.setOnClickItem(i);
        myViewHolder.setOnLongClickItem(i);
    }

    @Override
    public int getItemCount() {
        return mGiohangs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewName;
        public TextView mTextViewPrice;
        public TextView mTextViewIndex;
        public View mView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.mView = itemView;
            mTextViewName = itemView.findViewById(R.id.tv_item_name);
            mTextViewPrice = itemView.findViewById(R.id.tv_item_price);
            mTextViewIndex = itemView.findViewById(R.id.tv_item_index);
        }

        public void bindData(int position) {
            Giohang mGiohang = mGiohangs.get(position);
            mTextViewName.setText("" + mGiohang.getmName());
            mTextViewPrice.setText("" + mGiohang.getMprice());
            mTextViewIndex.setText("" + mGiohang.getmIndex());
        }

        public void setOnClickItem(final int position) {
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemRecyclerView.onClick(v, position);
                }
            });
        }

        public void setOnLongClickItem(final int position) {
            mView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemRecyclerView.onLongClick(v, position);
                    return false;
                }
            });
        }
    }

    public void setOnItemClickListener(ItemRecyclerView itemClickListener) {
        this.mItemRecyclerView = itemClickListener;
    }

}
