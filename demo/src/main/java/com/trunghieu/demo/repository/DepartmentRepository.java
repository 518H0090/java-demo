package com.trunghieu.demo.repository;

import com.trunghieu.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    @Query("SELECT d FROM Department d WHERE d.code = :code")
    Optional<Department> findDepartmentsByCode(@Param("code") String code);
}
