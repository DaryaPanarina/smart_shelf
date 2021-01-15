package com.embedded.shell.services

import com.embedded.shell.models.Logs
import com.embedded.shell.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class LogService(private val logsDAO: LogsDAO): BasicCrud<String, Logs> {
    override fun getAll(pageable: Pageable): Page<Logs> {
        return logsDAO.findAll(pageable);
    }

    override fun getById(id: String): Optional<Logs> {
        return logsDAO.findById(id);
    }

    override fun insert(obj: Logs): Logs {
        return logsDAO.insert(obj);
    }

    override fun deleteById(id: String): Optional<Logs> {
        return logsDAO.findById(id).apply {
            this.ifPresent {
                logsDAO.delete(it)
            }
        }
    }
}