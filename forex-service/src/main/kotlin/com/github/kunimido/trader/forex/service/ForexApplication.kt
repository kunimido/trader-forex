package com.github.kunimido.trader.forex.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ForexApplication

fun main(args: Array<String>) {
    runApplication<ForexApplication>(*args)
}