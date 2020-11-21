package ke.co.propscout.mobank.ui.transactions.add.fragments.customer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ke.co.propscout.mobank.data.models.Customer;
import ke.co.propscout.mobank.data.repositories.CustomerRepository;

public class CustomerViewModel extends ViewModel implements CustomerRepository.CustomerCrudTaskLister {

    private String uid;

    private MutableLiveData<Customer> _customer = new MutableLiveData<>();
    private MutableLiveData<List<Customer>> _customers = new MutableLiveData<>();
    private MutableLiveData<Exception> _exception = new MutableLiveData<>();

    private CustomerRepository customerRepository;

    public CustomerViewModel(String uid) {
        this.uid = uid;
        customerRepository = new CustomerRepository(this);
    }

    public void createCustomer(Customer customer) {
        customer.withOwnerId(uid);
        customerRepository.createCustomer(customer);
    }

    public LiveData<Customer> getCustomer() {
        return _customer;
    }

    public LiveData<Exception> getException() {
        return _exception;
    }

    @Override
    public void onCustomerRetrieved(Customer customer) {
        _customer.postValue(customer);
    }

    @Override
    public void onCustomersRetrieved(List<Customer> customers) {
        _customers.postValue(customers);
    }

    @Override
    public void onException(Exception exception) {
        _exception.postValue(exception);
    }

}
