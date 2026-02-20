package br.com.ehc.math;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class SimpleMath {

    public Double somar(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtrair(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiplicar(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public Double dividir(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public Double media(Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo) / 2;
    }

    public Double raizQuadrada(Double number) {
        return Math.sqrt(number);
    }
}
