package com.crud.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.project.dto.Address;

public interface AddressRepository extends JpaRepository<Address,Integer>
{

}
