package solo.practice.ems.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.practice.ems.dto.EmployeeDto;

import solo.practice.ems.entity.Employee;
import solo.practice.ems.services.EmployeeServices;

import java.util.List;

//this class will capable to handle http request

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    //Build Add Employee REST API
    //@RequestBody - will extract the JSON from the HTTP request and it will convert JSON into java object.

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployeeHandler(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<EmployeeDto>(employeeServices.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
         return new ResponseEntity<EmployeeDto>(employeeServices.getEmployeeById(employeeId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return new ResponseEntity<List<EmployeeDto>>(employeeServices.getAllEmployee(),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeHandler(@PathVariable("id") Long employeeId, @RequestBody  EmployeeDto updatedEmployee){
         return new ResponseEntity<EmployeeDto>(employeeServices.updateEmployee(employeeId, updatedEmployee), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<EmployeeDto> deleteEmployeeHandler(@PathVariable("id") Long employeeId){
        return new ResponseEntity<EmployeeDto>(employeeServices.deleteEmployee(employeeId),HttpStatus.OK);
    }

}
