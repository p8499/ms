package p8499.client.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "EMPLOYEE", schema = "USERNAME")
class Employee(
    @get:Id
    @get:SequenceGenerator(name = "EMPLOYEE_EMID", allocationSize = 1)
    @get:GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_EMID")
    var emid: Long? = null,

    @get:Basic
    var emstatus: Long? = null,

    @get:Basic
    var emgender: String? = null,

    @get:Basic
    var emname: String? = null,

    @get:Basic
    @get:JsonFormat(pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    var embirthday: Date? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val employee = other as Employee
        return emid == employee.emid && emstatus == employee.emstatus && emgender == employee.emgender && emname == employee.emname && embirthday == employee.embirthday
    }

    override fun hashCode(): Int {
        return Objects.hash(emid, emstatus, emgender, emname, embirthday)
    }
}