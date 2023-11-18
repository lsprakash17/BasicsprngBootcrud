package com.crud.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.project.dto.Employeee;

public interface EmployeeeRepository extends JpaRepository<Employeee,Long>
{

}
