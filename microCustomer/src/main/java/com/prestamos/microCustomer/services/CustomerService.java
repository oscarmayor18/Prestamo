package com.prestamos.microCustomer.services;

import java.util.List;
import java.util.Optional;

import com.prestamos.microCustomer.models.Client;

public interface CustomerService {
	Client createClient(Client client);

	Client updateClient(Client client);

	void deleteClient(Long id);

	List<Client> getAllClients();

	Optional<Client> findByEmail(String email);

	Optional<Client> findByCedule(String cedule);

	List<Client> findByAssignedEmployeeId(Long employeeId);

	Client getClientById(Long id);
	
	List<Client> getClientsByEmployeeId(Long employeeId);


}
