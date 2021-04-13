package p8499.client.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import p8499.client.entity.Employee
import p8499.client.repository.EmployeeRepository

@Service
class EmployeeService {
    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    fun get(emid: Long): Employee? =
        employeeRepository.findByIdOrNull(emid)


    fun add(employee: Employee) =
        employee
            .takeIf { it.emid == null }
            ?.also(employeeRepository::saveAndFlush)

    fun update(employee: Employee) =
        employee
            .takeIf { it.emid?.let(employeeRepository::existsById) ?: false }
            ?.also(employeeRepository::saveAndFlush)

    fun delete(emid: Long) =
        employeeRepository.deleteById(emid)

    fun query(specification: Specification<Employee>, pageable: Pageable) =
        employeeRepository.findAll(specification, pageable)
}