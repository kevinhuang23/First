package Jdbc;

public interface customerService {
	void addCustomer(customer cs) ;
    void searchByName(String name);
    void ExchangeCustomer(customer cs1,customer cs2);
}
