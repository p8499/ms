package p8499.client

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import p8499.client.repository.EmployeeRepository

@SpringBootTest
class ClientApplicationTests {
    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Test
    fun contextLoads() {
        val page = employeeRepository.findAll(
            { root, query, criteriaBuilder -> criteriaBuilder.like(root.get<String>("emname"), "%张三%") },
            PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "emid"))
        )
        print("页 = ${page.number} / ${page.totalPages}")
        print("页 = ${page.number * page.size} - ${page.number * page.size + page.numberOfElements} / ${page.totalElements}")
    }

}
