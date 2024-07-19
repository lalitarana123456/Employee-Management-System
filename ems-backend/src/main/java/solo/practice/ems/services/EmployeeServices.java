package solo.practice.ems.services;

import solo.practice.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeServices {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee );

    EmployeeDto deleteEmployee(Long employeeId);

}
