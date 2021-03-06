package com.example.passbook.data.models;

import com.example.passbook.data.enums.DatePickerType;
import com.example.passbook.utils.FormItemType;

import java.util.Date;

public class DateTimeModel extends BaseFormModel {
    public DatePickerType datePickerType;

    public DateTimeModel(String title, Object value, String hint, DatePickerType datePickerType) {
        super(title, value, hint);

        this.datePickerType = datePickerType;

        if(value == null) {
            this.value = new Date();
        }

        this.formItemType = FormItemType.DateTime;
    }
}
