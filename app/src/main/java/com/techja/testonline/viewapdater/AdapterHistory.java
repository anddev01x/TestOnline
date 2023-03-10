package com.techja.testonline.viewapdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.testonline.R;
import com.techja.testonline.models.Result;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.Viewholider> {

    // context
    private Context context;
    // list
    private List<Result> list;

    // contructer
    public AdapterHistory(Context context, List<Result> list) {
        this.context = context;
        this.list = list;
    }

    // ánh xạ view
    @NonNull
    @Override
    public Viewholider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_history, parent, false);
        Viewholider viewHolder = new Viewholider(view);
        return viewHolder;
    }

    // gán dữ liêu
    @Override
    public void onBindViewHolder(@NonNull Viewholider holder, int position) {
        Result result = list.get(position);
        holder.txtDate.setText(result.getDate());
        holder.txtMon.setText(result.getMonHoc());
        holder.txtResult.setText(result.getResult() + " / 20");
    }

    // tra ve du lieu
    @Override
    public int getItemCount() {
        return list.size();
    }

    // ánh xạ layout
    class Viewholider extends RecyclerView.ViewHolder {
        TextView txtResult, txtMon, txtCap, txtDate;

        public Viewholider(@NonNull View itemView) {
            super(itemView);
            txtResult = itemView.findViewById(R.id.txtResult);
            txtMon = itemView.findViewById(R.id.txtMonHoc);
            txtCap = itemView.findViewById(R.id.txtCap);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
