package p8499.client.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import p8499.client.entity.Record

@Repository
interface RecordRepository : JpaRepository<Record, Long>
