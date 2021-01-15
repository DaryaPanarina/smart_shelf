package com.embedded.shell.services

import com.embedded.shell.models.Price
import com.embedded.shell.util.BasicCrud
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class PriceService(private val priceDAO: PriceDAO): BasicCrud<String, Price> {
    override fun getAll(pageable: Pageable): Page<Price> {
        return priceDAO.findAll(pageable);
    }

    override fun getById(id: String): Optional<Price> {
        return priceDAO.findById(id);
    }

    override fun insert(obj: Price): Price {
        return priceDAO.insert(obj);
    }

    override fun deleteById(id: String): Optional<Price> {
        return priceDAO.findById(id).apply {
            this.ifPresent {
                priceDAO.delete(it)
            }
        }
    }

    fun setNewPrice(newCurrency: Int, newValue: Double) {
        var prices: MutableList<Price> = priceDAO.findAll();
        if (prices.isEmpty()) {
            val price: Price = Price(currency = newCurrency, value = newValue);
            prices.add(price);
        } else {
            prices.forEach { price ->
                price.currency = newCurrency
                price.value = newValue
            }
        }
        priceDAO.saveAll(prices);
    }

    fun getFirst() : Price? {
        var prices: List<Price> = priceDAO.findAll();
        return prices.firstOrNull();
    }
}
