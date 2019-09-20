package app.mobile.picoyplaca.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.mobile.picoyplaca.R;
import app.mobile.picoyplaca.model.Consultant;


public class ConsultantListAdapter extends BaseAdapter {

    private final List<Consultant> consultantList;
    private final Context context;

    public ConsultantListAdapter(List<Consultant> consultantList, Context context) {
        this.consultantList = consultantList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return consultantList.size();
    }

    @Override
    public Object getItem(int position) {
        return consultantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class ViewHolder {
        TextView tvDateRegister;
        TextView tvLicensePlate;
        TextView tvDateInput;
        TextView tvIsCounterversion;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Consultant consultant = consultantList.get(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_consultant, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvDateRegister = convertView.findViewById(R.id.tvDateRegister);
            viewHolder.tvLicensePlate = convertView.findViewById(R.id.tvLicensePlate);
            viewHolder.tvDateInput = convertView.findViewById(R.id.tvDateInput);
            viewHolder.tvIsCounterversion = convertView.findViewById(R.id.tvIsCounterversion);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvDateRegister.setText(consultant.getDateRegister());
        viewHolder.tvLicensePlate.setText(consultant.getLicensePlate());
        viewHolder.tvDateInput.setText(consultant.getDateConsultant());
        viewHolder.tvIsCounterversion.setText(consultant.isCounterversion() == 1 ? "SI" : "NO");

        return convertView;
    }


}
