package net.javaguides.springboot.repository;
// database နဲ့ချိတ်ဆက်ပေးမယ့် repository interface

import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Spring Data JPa internally provides @Repository Annotation so we need to add @Repository annotation to
// ... EmployeeRepository interface
//@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {// <== model နဲ့ သူ့ရဲ့ Id (model & it's Id)
}
