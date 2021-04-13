package p8499.client.entity

import java.sql.Time
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "RECORD", schema = "USERNAME")
class Record {
    @get:Column(name = "REID")
    @get:Id
    var reid: Long? = null

    @get:Column(name = "REQTY")
    @get:Basic
    var reqty: Long? = null

    @get:Column(name = "RECREATED")
    @get:Basic
    var recreated: Time? = null

    @get:JoinColumn(name = "REIMID", referencedColumnName = "IMID")
    @get:ManyToOne
    var itemsByReimid: Item? = null

    @get:JoinColumn(name = "REEMID", referencedColumnName = "EMID")
    @get:ManyToOne
    var employeesByReemid: Employee? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val record = other as Record
        return reid == record.reid && reqty == record.reqty && recreated == record.recreated
    }

    override fun hashCode(): Int {
        return Objects.hash(reid, reqty, recreated)
    }
}