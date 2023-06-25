package net.javaguides.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.exception.EmailAlreadyExistsException;
import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.findByEmail(employeeDto.getEmail()).get() != null) {
            throw new EmailAlreadyExistsException("Email Already Exists for Employee");
        }

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).get();

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        return employeeDto;
    }


}
