package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    /**
     * Create a new address
     * @param address The address object to save
     * @return The saved address
     */
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    /**
     * Retrieve all addresses
     * @return List of all addresses
     */
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    /**
     * Retrieve an address by Id
     * @param id the address ID
     * @return The address object if found
     */
    public Address findById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    /**
     * Update an existing address.
     *
     * @param id The address ID.
     * @param updatedAddress The updated address object.
     * @return The updated address if found.
     */
    public Address updateAddress(Integer id, Address updatedAddress) {
        return addressRepository.findById(id)
                .map(existingAddress -> {
                    existingAddress.setPostalCode(updatedAddress.getPostalCode());
                    return addressRepository.save(existingAddress);
                })
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
    }

    /**
     * Delete an address by ID.
     *
     * @param id The address ID to delete.
     */
    public void deleteAddress(Integer id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found with ID: " + id);
        }
        addressRepository.deleteById(id);
    }

}
