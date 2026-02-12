package br.com.ehc.controllers;

import br.com.ehc.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Olá %s, %s, %s!!!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(value = "lastName", defaultValue = "[Não definido]") String lastName,
            @RequestParam(value = "idade", defaultValue = "0") String idade) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name, lastName, idade));
    }
}
