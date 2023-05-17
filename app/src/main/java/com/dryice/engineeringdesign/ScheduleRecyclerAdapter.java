package com.dryice.engineeringdesign;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class ScheduleRecyclerAdapter extends RecyclerView.Adapter<ScheduleRecyclerAdapter.ViewHolder> {

    private List<Schedule> scheduleList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ScheduleRecyclerAdapter(List<Schedule> list) {
        scheduleList = list;
    }

    //===== [Click 이벤트 구현을 위해 추가된 코드] ==========================
    // OnItemClickListener 인터페이스 선언
    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    // OnItemClickListener 참조 변수 선언
    private ScheduleRecyclerAdapter.OnItemClickListener itemClickListener;

    // OnItemClickListener 전달 메소드
    public void setOnItemClickListener (ScheduleRecyclerAdapter.OnItemClickListener listener) {
        itemClickListener = listener;
    }
    //=====================================================================

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.schedule_item, parent, false);
        ScheduleRecyclerAdapter.ViewHolder vh = new ScheduleRecyclerAdapter.ViewHolder(view);

        //===== [Click 이벤트 구현을 위해 추가된 코드] =====================
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = vh.getAdapterPosition();
//                Context context = view.getContext();
//                Intent intent = new Intent(context,BookListActivity.class);
//                ((MainActivity)context).startActivity(intent);
            }
        });
        //==================================================================

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule item = scheduleList.get(position);
        holder.name.setText(item.name);
        holder.importance.setText(item.importance);
        //Log.v("test","list: "+str);

        String date = dateFormat.format(item.deadline);
        holder.deadline.setText(date);
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView deadline;
        TextView importance;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name_text);
            deadline = itemView.findViewById(R.id.item_deadline_text);
            importance = itemView.findViewById(R.id.item_importance_text);

        }
    }
}
