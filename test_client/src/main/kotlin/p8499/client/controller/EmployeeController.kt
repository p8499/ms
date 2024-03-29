package p8499.client.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import p8499.client.entity.Employee
import p8499.client.service.EmployeeService
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
@RequestMapping(path = ["/api/employee"])
class EmployeeController {
    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping(path = ["/{emid}"])
    fun get(
        session: HttpSession,
        request: HttpServletRequest,
        response: HttpServletResponse,
        @PathVariable emid: Long,
        @RequestParam(required = false, defaultValue = "") mask: String
    ): Employee? = employeeService.get(emid)

    @PostMapping
    fun add(
        session: HttpSession,
        request: HttpServletRequest,
        response: HttpServletResponse,
        @RequestBody employee: Employee
    ): Employee? = employeeService.add(employee)

    @PutMapping(path = ["/{emid}"])
    fun update(
        session: HttpSession,
        request: HttpServletRequest,
        response: HttpServletResponse,
        @PathVariable emid: Long,
        @RequestBody employee: Employee,
        @RequestParam(required = false, defaultValue = "") mask: String
    ): Employee? = employeeService.update(employee.also { it.emid = emid })


    @DeleteMapping(path = ["/{emid}"])
    fun delete(
        session: HttpSession,
        request: HttpServletRequest,
        response: HttpServletResponse,
        @PathVariable emid: Long
    ) = employeeService.delete(emid)

    @GetMapping
    fun query(
        session: HttpSession,
        request: HttpServletRequest,
        response: HttpServletResponse,
        @RequestHeader(required = false, defaultValue = "items: 0-99") range: String,
    ): List<Employee> {
        val matcher = Pattern.compile("[0-9]+").matcher(range)
        val start = matcher.takeIf { it.find() }?.group()?.toInt() ?: 0
        val end = matcher.takeIf { it.find() }?.group()?.toInt() ?: 99
        val pageSize = end - start + 1
        val pageNumber = pageSize.takeIf { it > 0 }?.let { Math.ceil(((start + 1).toDouble() / it)).toInt() - 1 } ?: 0
        val page = employeeService.query(
            { root, query, criteriaBuilder -> criteriaBuilder.and() },
            PageRequest.of(pageNumber, pageSize)
        )
        response.setHeader(
            "Content-Range",
            "items ${page.number * page.size} - ${page.number * page.size + page.numberOfElements - 1} / ${page.totalElements}"
        )
        return page.content
    }
}