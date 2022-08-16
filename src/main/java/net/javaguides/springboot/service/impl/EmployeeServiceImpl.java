package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional // service မှာ ထည့်စရာမလိုတော့ဘူး JpaRepository မှာ ပါပြီးသားမလို့
public class EmployeeServiceImpl implements EmployeeService {

    // EmployeeServiceImpl class ထဲကို EmployeeRepository class ကို inject လုပ်မယ်
    private EmployeeRepository employeeRepository;

    //@Autowired // constructor တစ်ခုတည်းရှိတဲ့ အချိန်ကျရင် @Autowired မထည့်လည်း သိတယ်
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    // service layer ကနေ repository interface က တစ်ဆင့် employee ဆိုတဲ့ model ကို database ထဲကို save လိုက်တဲ့သဘော
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);

    }

    // service layer ကနေ repository interface က တစ်ဆင့် database ထဲက ကောင် တွေကို findAll() လုပ်လိုက်တာမျိုးပေါ့
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // service layer ကနေ repository interface က တစ်ဆင့် database ထဲက ကောင် တွေကို findById() လုပ်လိုက်တာမျိုးပေါ့
    // findById က Optional ကို return ပြန်တာပါ
    // တွေ့ရင် ပြန်ပေးမယ်
    // မတွေ့ရင် custom exception တက်မယ်
    // ResourceNotFoundException မှာ
    //    private String resourceName;
    //    private String fieldName;
    //    private Object fieldValue; ဆိုးပြီး field သုံးခုပါတယ်
    // lambda expression  နဲ့ orElseThrow() နဲ့လည်း သုံးလို့ရပါတယ်
    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }

        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id",id));

    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id",id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        // save existingEmployee to DB;
        employeeRepository.save(existingEmployee);


        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        // check whether a employee exist in a DB or not
         employeeRepository.findById(id).orElseThrow(()->
             new ResourceNotFoundException("Employee", "Id",id));

        employeeRepository.deleteById(id);
    }


}
