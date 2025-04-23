package com.example.PS40753_TranHoang_SD19308.repository;

import com.example.PS40753_TranHoang_SD19308.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
