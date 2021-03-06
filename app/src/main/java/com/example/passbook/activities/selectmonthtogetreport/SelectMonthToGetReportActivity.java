package com.example.passbook.activities.selectmonthtogetreport;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.passbook.R;
import com.example.passbook.activities.form.FormHaveSubmitButtonActivity;
import com.example.passbook.adapters.FormAdapter;
import com.example.passbook.customviews.MonthYearPickerDialog;
import com.example.passbook.data.enums.DatePickerType;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.models.DateTimeModel;
import com.example.passbook.data.models.SpinnerModel;
import com.example.passbook.utils.Constant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectMonthToGetReportActivity extends FormHaveSubmitButtonActivity
        implements SelectMonthToGetReportContract.View {

    private SelectMonthToGetReportContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        title = getString(R.string.month_report);
        super.onCreate(savedInstanceState);
        presenter = new SelectMonthToGetReportPresenter(this);
    }

    @Override
    protected void initModelAndAdapter() {
        List<String> passBookTypes = new ArrayList<>();
        passBookTypes.add(PassBookType.THREE_MONTH.getText(this));
        passBookTypes.add(PassBookType.SIX_MONTH.getText(this));
        passBookTypes.add(PassBookType.INFINITE.getText(this));

        models = new ArrayList<>();
        models.add(new SpinnerModel(getResources().getString(R.string.passbook_type), null, "", passBookTypes));
        models.add(new DateTimeModel(getString(R.string.pick_up_month), new Date(), "", DatePickerType.MONTH_YEAR_ONLY));

        adapter = new FormAdapter(this, models, lst_input);
    }

    @Override
    protected void getDataFromViewAndCallPresenterHandle() {
        PassBookType passBookType = PassBookType.fromString((String) models.get(0).value, this);
        Date date = (Date) models.get(1).value;

        presenter.handleSubmit(passBookType, date);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
