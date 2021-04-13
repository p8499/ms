package p8499.client

interface FilterExpr {
    fun toStringPostgresql(): String
    fun toStringOracle(): String

    companion object {
        const val OP = "op"
        const val DATA = "data"
    }
}

class FilterOperandExpr(op: String, data: String) {
    companion object {
        const val OP_FIELD = "field"
        const val OP_DATEFIELD = "datefield"
        const val OP_DATETIMEFIELD = "datetimefield"
        const val OP_BEAN = "bean"
        const val OP_STRING = "string"
        const val OP_NUMBER = "number"
        const val OP_DATE = "date"
        const val OP_DATETIME = "datetime"
    }
}