package p8499.client.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ITEM", schema = "USERNAME")
class Item {
    @get:Column(name = "IMID")
    @get:Id
    var imid: Long? = null

    @get:Column(name = "IMNAME")
    @get:Basic
    var imname: String? = null

    @get:Column(name = "IMPRICE")
    @get:Basic
    var imprice: Long? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val item = other as Item
        return imid == item.imid && imname == item.imname && imprice == item.imprice
    }

    override fun hashCode(): Int {
        return Objects.hash(imid, imname, imprice)
    }
}