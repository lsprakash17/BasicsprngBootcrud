package com.crud.project.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.project.dto.Employeee;
import com.crud.project.helper.EmployeeHelper;
import com.crud.project.helper.Employeeehepler;
import com.crud.project.helper.ResponseStructure;
import com.crud.project.service.EmployeeService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class maincontroler {
	@Autowired
	EmployeeService emplservice;

	@PostMapping("/save")
	public ResponseStructure<Employeee> save(@RequestBody EmployeeHelper employeee,@RequestParam String address) {
		ResponseStructure<Employeee> responseStructure = new ResponseStructure<>();
		if (employeee == null) {
			responseStructure.setMessage("please enter all data");
			responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
			responseStructure.setData(null);
			return responseStructure;
		} else {
			Employeee employe = emplservice.save(employeee,address);
			responseStructure.setMessage("data Saved Succesfully");
			responseStructure.setStatus(HttpStatus.ACCEPTED.value());
			responseStructure.setData(employe);
			return responseStructure;

		}

	}

	@GetMapping("/employees")
	public ResponseStructure<List<Employeee>> getAllEmployees() {
		ResponseStructure<List<Employeee>> responseStructure = new ResponseStructure<>();

		List<Employeee> employeeList = emplservice.getAllEmployees();

		if (employeeList.isEmpty()) {
			responseStructure.setMessage("No employees found");
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setData(null);
		} else {
			responseStructure.setMessage("List of employees");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(employeeList);
		}

		return responseStructure;
	}

	@GetMapping("/employees/ids")
	public ResponseStructure<Employeee> getEmployeeById(@RequestParam Long id) {
		ResponseStructure<Employeee> responseStructure = new ResponseStructure<>();

		if (id == null) {
			responseStructure.setMessage("Please provide a valid employee ID");
			responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
			responseStructure.setData(null);
			return responseStructure;
		}

		Employeee employee = emplservice.getEmployeeById(id);

		if (employee != null) {
			responseStructure.setMessage("Employee found");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(employee);

		} else {
			responseStructure.setMessage("Employee not found with ID: " + id);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setData(null);
		}

		return responseStructure;
	}

	@PutMapping("/employees/id")
	public ResponseStructure<Employeee> updateEmployee(@RequestParam Long id, @RequestBody Employeeehepler updatedEmployee) {
		ResponseStructure<Employeee> responseStructure = new ResponseStructure<>();

		if (id == null || updatedEmployee == null) {
			responseStructure.setMessage("Please provide a valid employee ID and update data");
			responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
			responseStructure.setData(null);
			return responseStructure;
		}

		Employeee existingEmployee = emplservice.getEmployeeById(id);

		if (existingEmployee == null) {
			responseStructure.setMessage("Employee not found with ID: " + id);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setData(null);
			return responseStructure;
		}
		Employeee existingEmploye = emplservice.UpdateEmployee(existingEmployee,updatedEmployee);


		responseStructure.setMessage("Employee updated successfully");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setData(existingEmploye);

		return responseStructure;
	}
	
	@DeleteMapping("/employees/id")
	public ResponseStructure<String> deleteEmployee(@RequestParam Long id) {
	    ResponseStructure<String> responseStructure = new ResponseStructure<>();

	    if (id == null) {
	        responseStructure.setMessage("Please provide a valid employee ID");
	        responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
	        responseStructure.setData(null);
	        return responseStructure;
	    }

	    Employeee employee = emplservice.getEmployeeById(id);

	    if (employee != null) {
	        // Call the service method to delete the employee
	        emplservice.deleteEmployee(id);

	        responseStructure.setMessage("Employee deleted successfully");
	        responseStructure.setStatus(HttpStatus.OK.value());
	        responseStructure.setData("Employee with ID " + id + " deleted");
	    } else {
	        responseStructure.setMessage("Employee not found with ID: " + id);
	        responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
	        responseStructure.setData(null);
	    }

	    return responseStructure;
	}

	
	

}
