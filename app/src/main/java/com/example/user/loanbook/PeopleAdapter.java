package com.example.user.loanbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    Context context;
    public List<People> myList;

    public PeopleAdapter(Context context, List<People> myList) {
        this.context = context;
        this.myList = myList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.peopleview, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final People people = myList.get(position);
        holder.mPeoplesId.setText(String.valueOf(people.getId()));
        holder.mPeoplesName.setText(people.getName());
        holder.mPeoplesNumber.setText(people.getNumber());

    }


    @Override
    public int getItemCount() {
        return myList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mPeoplesName, mPeoplesNumber, mPeoplesId;

        public ViewHolder(View itemView) {
            super(itemView);
            mPeoplesId = (TextView) itemView.findViewById(R.id.tv_peoples_id);
            mPeoplesName = (TextView) itemView.findViewById(R.id.tv_people_name);
            mPeoplesNumber = (TextView) itemView.findViewById(R.id.tv_peoples_number);
        }
    }
}
