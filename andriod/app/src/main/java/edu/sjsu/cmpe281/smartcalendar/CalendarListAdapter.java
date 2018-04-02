package edu.sjsu.cmpe281.smartcalendar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CalendarListAdapter extends RecyclerView.Adapter <CalendarListAdapter.CalendarViewHolder>{
    private List<CalendarEvent> entryList;

    public CalendarListAdapter(List<CalendarEvent> entryList) {
        this.entryList = entryList;
        Collections.sort(this.entryList, new DateComparator());
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);

        return new CalendarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        CalendarEvent entry = entryList.get(position);
        holder.vName.setText(entry.name);
        holder.vStartDate.setText("Start Time: " + DateUtil.FormatDateView(entry.startTime));
        holder.vEndDate.setText("End Time: " + DateUtil.FormatDateView(entry.endTime));
        holder.vmymessage.setText("Message: " + entry.mymessage);
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vStartDate;
        protected TextView vEndDate;
        protected TextView vmymessage;

        public CalendarViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.eventName);
            vStartDate = (TextView) v.findViewById(R.id.eventStartDate);
            vEndDate = (TextView) v.findViewById(R.id.eventEndDate);
            vmymessage = (TextView) v.findViewById(R.id.eventmymessage);

        }
    }

    public void addItem(CalendarEvent event) {
        entryList.add(event);
        Collections.sort(entryList, new DateComparator());
        notifyDataSetChanged();
    }

    public class DateComparator implements Comparator<CalendarEvent> {
        @Override
        public int compare(CalendarEvent e1, CalendarEvent e2) {
            return e1.startTime.compareTo(e2.startTime);
        }
    }
}
