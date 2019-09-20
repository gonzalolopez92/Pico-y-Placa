package app.mobile.picoyplaca.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;
import java.util.Objects;

import app.mobile.picoyplaca.R;
import app.mobile.picoyplaca.controller.Controller;
import app.mobile.picoyplaca.model.Consultant;
import app.mobile.picoyplaca.view.adapters.ConsultantListAdapter;

public class ConsultantsActivity extends AppCompatActivity {

    private Context context;
    private Controller controller;
    private List<Consultant> consultantList;
    private ListView lvConsultants;
    private TextView tvEmptyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultants_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        lvConsultants = findViewById(R.id.lvConsultants);
        tvEmptyList = findViewById(R.id.tvEmptyList);

        context = ConsultantsActivity.this;
        controller = new Controller(context);

        new GetConsultants().execute();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    private class GetConsultants extends AsyncTask<Void, Void, Void> {

        private ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle(getString(R.string.app_name));
            dialog.setCancelable(false);
            dialog.setIcon(R.mipmap.ic_launcher);
            dialog.setMessage(getString(R.string.wait_a_moment));
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                consultantList = controller.getConsultants();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (consultantList.size() == 0) {
                tvEmptyList.setVisibility(View.VISIBLE);
            } else {
                lvConsultants.setAdapter(new ConsultantListAdapter(consultantList, context));
            }
            dialog.dismiss();
        }
    }
}
