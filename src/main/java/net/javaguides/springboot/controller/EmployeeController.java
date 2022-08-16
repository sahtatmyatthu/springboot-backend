package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController မှာ @Controller ကော @ResponseBody ကောပါပြီးသား
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    // EmployeeController class ထဲကို EmployeeService class ကို inject လုပ်မယ်

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // build create employee REST API

    // @PostMapping က post request မို့လို့ပေါ့
    // ResponseEntity => complete response ပြန်ပေးနိုင်ဖို့အတွက်
    // @RequestBody annotations allows to retrieve the request's body and automatically convert to Java Object
    // request body ကကောင်ကို java object အနေနဲ့ ပြောင်းပေးမယ် ဆိုတဲ့သဘော
    //  return မှာ request body ကကောင်ကို java object အနေနဲ့ ပြောင်းလို့ရလာတဲ့ကောင်ကို inject လုပ်ထားတဲ့ EmployeeService ရဲ့ saveEmployee
    // ဆီကို ရောက်သွားမယ် အဲ့ကနေမှ database ထဲကို save သွားတာမျိုးပေါ့
    // post method ဖြစ်တဲ့အတွက် HttpStatus.CREATED ပေါ့
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){

        return  new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // build get all employees REST API

    // @GetMapping က get request မို့လို့ပေါ့
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //  build get employee by id REST API

    // @GetMapping က get request မို့လို့ပေါ့
    // http://localhost:8080/api/employees/1
    // PathVariable နဲ့ သုံးထားတဲ့အတွက် @PathVariable ကိုသုံး
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    // build update employee REST API

    //@PutMapping က put request မို့လို့ပေါ့
    //http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public  ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                    @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id), HttpStatus.OK);

    }

    // build delete employee REST API
    //http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){

        // delete employee from DB
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
    }

}
