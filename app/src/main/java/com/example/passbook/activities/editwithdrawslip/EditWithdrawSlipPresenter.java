package com.example.passbook.activities.editwithdrawslip;

import com.example.passbook.activities.form.FormPresenter;
import com.example.passbook.converters.DateConverter;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.PassBookRegulation;
import com.example.passbook.data.entitys.WithdrawalSlip;
import com.example.passbook.data.enums.PassBookType;
import com.example.passbook.data.enums.PassbookState;
import com.example.passbook.utils.Utils;

import java.util.Date;

public class EditWithdrawSlipPresenter extends FormPresenter implements EditWithdrawSlipContract.Presenter {
    private EditWithdrawSlipContract.View view;

    public EditWithdrawSlipPresenter(EditWithdrawSlipContract.View view) {
        super(view);

        this.view = view;
    }

    @Override
    protected boolean saveData(Object... objects) {
        WithdrawalSlip withdrawalSlip = (WithdrawalSlip) objects[0];

        float interest = getInterest(withdrawalSlip);
        view.notifyInterest(interest);

        int id = (int) appDatabase.transactionFormDAO().insertItem(withdrawalSlip);
        withdrawalSlip.Id = id;

        PassBook refPassBook = appDatabase.passBookDAO().getItem(withdrawalSlip.passBookId);
        refPassBook.amount -= withdrawalSlip.amount;

        if(refPassBook.amount <= 0) {
            refPassBook.passbookState = PassbookState.CLOSED;
        }

        appDatabase.passBookDAO().updateOrInsertItem(refPassBook);

        return false;
    }

    @Override
    protected boolean manualCheck(Object... objects) {
        WithdrawalSlip withdrawalSlip = (WithdrawalSlip) objects[0];
        int amount = withdrawalSlip.amount;
        boolean result = true;
        PassBook refPassbook = appDatabase.passBookDAO().getItem(withdrawalSlip.passBookId);

        if (refPassbook == null) {
            view.setPassbookIsNotExistError();
            result = false;
        } else if (withdrawalSlip.customerId != refPassbook.customerId) {
            view.setCustomerIdWrongError();
            result = false;
        } else if(refPassbook.passbookState == PassbookState.CLOSED) {
            view.setPassbookIsClosedError();
            result = false;

        } else if(!checkMinNumOfDates(refPassbook)) {
            view.setMinPeriodError();
            result = false;
        } else if(amount > refPassbook.amount) {
            view.setOverDepositError(refPassbook.amount);
            result = false;
        }

        return result;
    }

    private boolean checkMinNumOfDates(PassBook refPassbook) {
        boolean result = true;

        long period = Utils.subDates(refPassbook.creationDate, new Date());

        if(period < 15) {
            result = false;
        }

        return result;
    }

    //TODO: handle for each passbook type
    private float getInterest(WithdrawalSlip withdrawalSlip) {
        PassBook passBook = appDatabase.passBookDAO().getItem(withdrawalSlip.passBookId);
        Date iterate = passBook.creationDate;
        Date current = new Date();
        float interest = 0.0f;

        while (!iterate.after(current)) {
            long begin = DateConverter.dateToTimestamp(iterate);

            PassBookRegulation passBookRegulation = appDatabase
                    .passBookRegulationDAO()
                    .getItemForCalInterestRate(passBook.passBookType, begin);

            int period = (int) Utils.subDates(iterate, current);

            if(period >= passBookRegulation.term) {
                interest += passBookRegulation.interestRate* withdrawalSlip.amount;
                iterate = Utils.plusDates(iterate, passBookRegulation.term);
            } else {
                break;
            }
        }

        return interest;
    }
}