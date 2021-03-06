package com.example.passbook.data;

import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.DepositSlip;
import com.example.passbook.data.entitys.MonthlyPassBook;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.TimelessPassBook;
import com.example.passbook.data.entitys.TransactionForm;
import com.example.passbook.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HardCode {
    public static class HardCodePassbook{
        public static List<PassBook> getPassBooks() {
            List<PassBook> result  = new ArrayList<>();

            PassBook passBook1 = MonthlyPassBook.createInstance(100000, 3);
            PassBook passBook2 = MonthlyPassBook.createInstance(100000, 3);
            PassBook passBook7 = MonthlyPassBook.createInstance(100000, 3);
            PassBook passBook8 = MonthlyPassBook.createInstance(100000, 3);
            PassBook passBook9 = MonthlyPassBook.createInstance(100000, 6);
            PassBook passBook3 = MonthlyPassBook.createInstance(100000, 6);
            PassBook passBook4 = MonthlyPassBook.createInstance(100000, 6);
            PassBook passBook5 = new TimelessPassBook();
            PassBook passBook6 = new TimelessPassBook();

            passBook1.Id = 1011;
            passBook2.Id = 1012;
            passBook3.Id = 1013;
            passBook4.Id = 1014;
            passBook5.Id = 1015;
            passBook6.Id = 1016;
            passBook7.Id = 1017;
            passBook8.Id = 1018;
            passBook9.Id = 1019;

            passBook1.customerId = 1;
            passBook2.customerId = 2;
            passBook3.customerId = 3;
            passBook4.customerId = 4;
            passBook5.customerId = 5;
            passBook6.customerId = 6;
            passBook7.customerId = 6;
            passBook8.customerId = 6;
            passBook9.customerId = 6;

            passBook1.creationPassBookDate = Utils.parseDate("2021-02-01");
            passBook2.creationPassBookDate = new Date();
            passBook3.creationPassBookDate = new Date();
            passBook4.creationPassBookDate = new Date();
            passBook5.creationPassBookDate = new Date();
            passBook6.creationPassBookDate = new Date();
            passBook7.creationPassBookDate = new Date();
            passBook8.creationPassBookDate = new Date();
            passBook9.creationPassBookDate = Utils.getNextDate(new Date());

//            passBook1.amount = 0;
//            passBook2.amount = 0;
//            passBook3.amount = 0;
//            passBook4.amount = 0;
//            passBook5.amount = 0;
//            passBook6.amount = 1523;
//            passBook6.amount = 1523;
//            passBook6.amount = 1523;
//            passBook6.amount = 1523;

            result.add(passBook1);
            result.add(passBook2);
            result.add(passBook3);
            result.add(passBook4);
            result.add(passBook5);
            result.add(passBook6);
            result.add(passBook7);
            result.add(passBook8);
            result.add(passBook9);

            return result;
        }
    }

    public static class HardCodeCustomer{
        public static List<Customer> getCustomers() {
            List<Customer> result = new ArrayList<>();

            Customer customer1 = new Customer();
            Customer customer2 = new Customer();
            Customer customer3 = new Customer();
            Customer customer4 = new Customer();
            Customer customer5 = new Customer();
            Customer customer6 = new Customer();

            customer1.Id = 1;
            customer2.Id = 2;
            customer3.Id = 3;
            customer4.Id = 4;
            customer5.Id = 5;
            customer6.Id = 6;

            customer1.fullName = "Shaquille Watt";
            customer2.fullName = "Arwel Pittman";
            customer3.fullName = "Cavan Clements";
            customer4.fullName = "Raja Bone";
            customer5.fullName = "Aditya Barnett";
            customer6.fullName = "Danni Swan";

            customer1.address = "4040  Hickory Street";
            customer2.address = "5595 Vernon Hall Rd";
            customer3.address = "321 Earl Slate Rd";
            customer4.address = "2582 Walworth Rd";
            customer5.address = "91 Bruce St";
            customer6.address = "4185 Poplar Corner Rd";

            customer1.identifyNumber = "711466083";
            customer2.identifyNumber = "394806492";
            customer3.identifyNumber = "713868735";
            customer4.identifyNumber = "969289032";
            customer5.identifyNumber = "638355264";
            customer6.identifyNumber = "452601592";

            result.add(customer1);
            result.add(customer2);
            result.add(customer3);
            result.add(customer4);
            result.add(customer5);
            result.add(customer6);

            return result;
        }
    }

    public static class HardCodeTransactionForm {
        public static List<TransactionForm> getTransactionForms() {
            List<TransactionForm> result = new ArrayList<>();

            TransactionForm transactionForm1 = new DepositSlip();
            TransactionForm transactionForm2 = new DepositSlip();
            TransactionForm transactionForm3 = new DepositSlip();
            TransactionForm transactionForm4 = new DepositSlip();
            TransactionForm transactionForm5 = new DepositSlip();
            TransactionForm transactionForm6 = new DepositSlip();

            transactionForm1.Id = 1;
            transactionForm2.Id = 2;
            transactionForm3.Id = 3;
            transactionForm4.Id = 4;
            transactionForm5.Id = 5;
            transactionForm6.Id = 6;

            transactionForm1.passBookId = 1011;
            transactionForm2.passBookId = 1012;
            transactionForm3.passBookId = 1013;
            transactionForm4.passBookId = 1014;
            transactionForm5.passBookId = 1015;
            transactionForm6.passBookId = 1016;

            transactionForm1.customerId = 1;
            transactionForm2.customerId = 2;
            transactionForm3.customerId = 3;
            transactionForm4.customerId = 4;
            transactionForm5.customerId = 5;
            transactionForm6.customerId = 6;

            transactionForm1.transactionDateTime = new Date();
            transactionForm2.transactionDateTime = new Date();
            transactionForm3.transactionDateTime = new Date();
            transactionForm4.transactionDateTime = new Date();
            transactionForm5.transactionDateTime = new Date();
            transactionForm6.transactionDateTime = new Date();

            transactionForm1.amount = 100000;
            transactionForm2.amount = 200000;
            transactionForm3.amount = 300000;
            transactionForm4.amount = 400000;
            transactionForm5.amount = 500000;
            transactionForm6.amount = 600000;

            result.add(transactionForm1);
            result.add(transactionForm2);
            result.add(transactionForm3);
            result.add(transactionForm4);
            result.add(transactionForm5);
            result.add(transactionForm6);

            return result;
        }
    }
}
