package solo.practice.ems.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solo.practice.ems.dto.EmployeeDto;
import solo.practice.ems.entity.Employee;
import solo.practice.ems.exception.ResourceNotFoundException;
import solo.practice.ems.mapper.EmployeeMapper;
import solo.practice.ems.repository.EmployeeRepository;
import solo.practice.ems.services.EmployeeServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//tell spring container to create spring bean of this class
@Service
public class EmployeeServiceImpl implements EmployeeServices {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee is not exist with the given id :" + employeeId));



        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> allEmployee = employeeRepository.findAll();
        return allEmployee.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
         Employee employee = employeeRepository.findById(employeeId)
                 .orElseThrow(()->new ResourceNotFoundException("Employee is not exist with the given id :" + employeeId));


         employee.setEmail(updatedEmployee.getEmail());
         employee.setFirstName(updatedEmployee.getFirstName());
         employee.setLastName(updatedEmployee.getLastName());

         employeeRepository.save(employee);

         return EmployeeMapper.mapToEmployeeDto(employee);


    }

    @Override
    public EmployeeDto deleteEmployee(Long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id: " + employeeId));

        employeeRepository.delete(existingEmployee);

        return EmployeeMapper.mapToEmployeeDto(existingEmployee);
    }
}
