package com.prestamos.microCustomer.services.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamos.microCustomer.exception.DuplicateResourceException;
import com.prestamos.microCustomer.exception.ResourceNotFoundException;
import com.prestamos.microCustomer.models.Client;
import com.prestamos.microCustomer.repository.ClientRepository;
import com.prestamos.microCustomer.services.CustomerService;
@Service
public class CustomerServiceImple implements CustomerService {

	@Autowired
	private ClientRepository clientRepository;
	@Override
	public Client createClient(Client client) {
		if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
			throw new DuplicateResourceException("Email ya está en uso");
		}
		if (clientRepository.findByCedule(client.getCedule()).isPresent()) {
			throw new DuplicateResourceException("Cédula ya está en uso");
		}
		return clientRepository.save(client);
	}

	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClientById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
	}

	public Client updateClient(Client clientDetails) {
		Client client = getClientById(clientDetails.getId());

		client.setFirstName(clientDetails.getFirstName());
		client.setLastName(clientDetails.getLastName());
		client.setPhoneNumber(clientDetails.getPhoneNumber());
		client.setAssignedEmployeeId(clientDetails.getAssignedEmployeeId());

		if (!client.getEmail().equals(clientDetails.getEmail())) {
			if (clientRepository.findByEmail(clientDetails.getEmail()).isPresent()) {
				throw new DuplicateResourceException("Email ya está en uso");
			}
			client.setEmail(clientDetails.getEmail());
		}

		if (!client.getCedule().equals(clientDetails.getCedule())) {
			if (clientRepository.findByCedule(clientDetails.getCedule()).isPresent()) {
				throw new DuplicateResourceException("Cédula ya está en uso");
			}
			client.setCedule(clientDetails.getCedule());
		}

		return clientRepository.save(client);
	}

	public void deleteClient(Long id) {
		Client client = getClientById(id);
		clientRepository.delete(client);
	}
	
	@Override
	public List<Client> getClientsByEmployeeId(Long employeeId) {
		return clientRepository.findByAssignedEmployeeId(employeeId);
	}

	@Override
	public Optional<Client> findByEmail(String email) {
		// TODO Auto-generated method stub
		return clientRepository.findByEmail(email);
	}

	@Override
	public Optional<Client> findByCedule(String cedule) {
		// TODO Auto-generated method stub
		return clientRepository.findByCedule(cedule);
	}

	@Override
	public List<Client> findByAssignedEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub
		return clientRepository.findByAssignedEmployeeId(employeeId);
	}
}
