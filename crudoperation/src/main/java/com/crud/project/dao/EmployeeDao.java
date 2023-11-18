package com.crud.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.project.Repository.AddressRepository;
import com.crud.project.Repository.EmployeeeRepository;
import com.crud.project.dto.Address;
import com.crud.project.dto.Employeee;

@Repository
public class EmployeeDao {
	
	@Autowired
	EmployeeeRepository employeeeRepository;
	
	@Autowired
	AddressRepository addressRepository;


	public Employeee save(Employeee employeee) {
		// TODO Auto-generated method stub

		return employeeeRepository.save(employeee);
	}


	public List<Employeee> getAllEployee() {
		
		// TODO Auto-generated method stub
		List<Employeee> employeees=employeeeRepository.findAll();
		return employeees;
	}

	public Employeee getEMployeeByid(Long id) {
		
		Optional<Employeee> employeee= employeeeRepository.findById(id);
		if(employeee.isPresent())
		{
			Employeee employeee2=employeee.get();
			
			if(employeee2.getIsDeleted()==false)
			return employeee2;
			else
				return null;
		}
		else
		{
			return null;
		}

	
	}

	public void delete(Employeee employeee) {
		// TODO Auto-generated method stub
		employeeeRepository.delete(employeee);
	}

}
