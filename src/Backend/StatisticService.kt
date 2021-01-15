package com.embedded.shell.services

import com.embedded.shell.models.ImageStatistic
import com.embedded.shell.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class StatisticService(private val statisticDAO: ImageStatisticDAO): BasicCrud<String, ImageStatistic> {
    override fun getAll(pageable: Pageable): Page<ImageStatistic> {
        return statisticDAO.findAll(pageable);
    }

    override fun getById(id: String): Optional<ImageStatistic> {
        return statisticDAO.findById(id);
    }

    override fun insert(obj: ImageStatistic): ImageStatistic {
        return statisticDAO.insert(obj);
    }

    override fun deleteById(id: String): Optional<ImageStatistic> {
        return statisticDAO.findById(id).apply {
            this.ifPresent {
                statisticDAO.delete(it)
            }
        }
    }
}