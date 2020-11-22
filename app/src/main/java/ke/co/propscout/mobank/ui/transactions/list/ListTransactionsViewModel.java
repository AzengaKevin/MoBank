package ke.co.propscout.mobank.ui.transactions.list;

import java.util.List;

import ke.co.propscout.mobank.data.models.Transaction;
import ke.co.propscout.mobank.data.repositories.TransactionRepository;

public class ListTransactionsViewModel implements TransactionRepository.TransactionCrudTaskListener {
    @Override
    public void onTransactionRetrieved(Transaction transaction) {

    }

    @Override
    public void onTransactionsRetrieved(List<Transaction> transactions) {

    }

    @Override
    public void onException(Exception exception) {

    }
}
