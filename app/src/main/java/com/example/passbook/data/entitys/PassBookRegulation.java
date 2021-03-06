package com.example.passbook.data.entitys;

import androidx.room.Entity;

import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.utils.Constant;

import java.util.ConcurrentModificationException;
import java.util.Date;

@Entity(tableName = Constant.PASSBOOK_REGULATION_TABLE)
public class PassBookRegulation extends BaseEntity {
    public static final float MIN_INTEREST_RATE = 0.0015f;

    public PassBookType passBookType;
    public Date creationRegulationDateTime;
    public int term;
    public float interestRate;
}
