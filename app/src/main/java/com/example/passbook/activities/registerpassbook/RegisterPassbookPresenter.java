package com.example.passbook.activities.registerpassbook;

import com.example.passbook.activities.form.FormPresenter;
import com.example.passbook.daos.PassBookDAO;
import com.example.passbook.data.entitys.BankRegulation;
import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.DepositSlip;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.TransactionForm;

import java.util.Date;
import java.util.Random;

public class RegisterPassbookPresenter extends FormPresenter implements RegisterPassbookContract.Presenter {
    private RegisterPassbookContract.View view;

    public RegisterPassbookPresenter(RegisterPassbookContract.View anotherView) {
        super(anotherView);
        this.view = anotherView;
    }

    @Override
    protected boolean saveData(Object... objects) {
        Customer customer = (Customer) objects[0];
        PassBook passBook = (PassBook) objects[1];

        switch (view.getViewMode()) {
            case EDIT:
                updatePassbook(passBook, customer);
                break;

            case CREATE_NEW:
            default:
                addNewPassbook(passBook, customer);
                break;
        }

        return true;
    }

    private void addNewPassbook(PassBook passBook, Customer customer) {
        long cusId = appDatabase.customerDAO().insertItem(customer);

        passBook.customerId = (int) cusId;
        PassBookDAO passBookDAO = appDatabase.passBookDAO();
        int passBookId = (int) passBookDAO.insertItem(passBook);

        DepositSlip depositSlip = new DepositSlip();
        depositSlip.passBookId = passBookId;
        depositSlip.customerId = (int) cusId;
        depositSlip.amount = passBook.amount;
        depositSlip.transactionDateTime = new Date();

        appDatabase.transactionFormDAO().insertItem(depositSlip);
    }

    private void updatePassbook(PassBook passBook, Customer customer) {
        appDatabase.customerDAO().updateOrInsertItem(customer);

        PassBookDAO passBookDAO = appDatabase.passBookDAO();
        passBookDAO.updateOrInsertItem(passBook);
    }

    @Override
    protected boolean manualCheck(Object... objects) {
        PassBook passBook = (PassBook) objects[1];

        boolean result = true;

        if (appDatabase.passBookDAO().getItem(passBook.Id) != null) {
            view.setPassBookIsExistedError();
            result = false;
        }

        BankRegulation bankRegulation = appDatabase.bankRegulationDAO().getItem(1);
        int minDepositAmount = bankRegulation != null? bankRegulation.minDepositAmount: 100000;

        if(passBook.amount < minDepositAmount) {
            view.setAmountIsSmallThanRegulationError(minDepositAmount);
            result = false;
        }

        return result;
    }

    @Override
    public int getNextPassbookId() {
        int nextId = 0;
        PassBook passBook = null;

        Random random = new Random();
        do {
            nextId = 0;
            passBook = null;

            nextId = random.nextInt();
            passBook = appDatabase.passBookDAO().getItem(nextId);
        }while (passBook != null ||  nextId < 0);

        return nextId;
    }
}
