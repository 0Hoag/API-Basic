package com.example.PS40753_TranHoang_SD19308.repository;

import com.example.PS40753_TranHoang_SD19308.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
