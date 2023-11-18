package com.crud.project.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.project.Repository.AddressRepository;
import com.crud.project.dao.EmployeeDao;
import com.crud.project.dto.Address;
import com.crud.project.dto.Employeee;
import com.crud.project.helper.EmployeeHelper;
import com.crud.project.helper.Employeeehepler;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao dao;

	@Autowired
	AddressRepository addressRepository;

	public Employeee save(EmployeeHelper employe, String address) {
		Address add = new Address();
		add.setAddresss(address);
		addressRepository.save(add);
		Employeee employeee=new Employeee();
		employeee.setAge(employe.getAge());
		employeee.setEmail(employe.getEmail());
		employeee.setMobilenumber(employe.getMobilenumber());
		employeee.setAddress2(add);
		employeee.setCreatedOn(new Date());
		employeee.setIsDeleted(false);

		return dao.save(employeee);

	}

	public List<Employeee> getAllEmployees() {
		// TODO Auto-generated method stub

		return dao.getAllEployee();
	}

	public Employeee getEmployeeById(Long id) {
		// TODO Auto-generated method stub

		Employeee employeee = dao.getEMployeeByid(id);
		if (employeee == null) {
			return null;
		} else if(employeee.getIsDeleted()==false)
		{
			return employeee;
			
		}
		else
			return null;

	}

	public Employeee UpdateEmployee(Employeee existingEmployee, Employeeehepler updatedEmployee) {
		// Update the existing employee with the data from the request
//		Employeee existingEmployee=employees
		existingEmployee.setName(updatedEmployee.getName());
		existingEmployee.setMobilenumber(updatedEmployee.getMobilenumber());
//				existingEmployee.setAddress(updatedEmployee.getAddress());
		existingEmployee.setAge(updatedEmployee.getAge());
		existingEmployee.setEmail(updatedEmployee.getEmail());
		existingEmployee.setModifiedOn(new Date());

		// Save the updated employee
		Employeee updatedEmployeee = dao.save(existingEmployee);
		return updatedEmployeee;
	}
//	}

	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		Employeee employeee = getEmployeeById(id);
		employeee.setDeletedOn(new Date());
		employeee.setIsDeleted(true);
		dao.save(employeee);
	}

}
