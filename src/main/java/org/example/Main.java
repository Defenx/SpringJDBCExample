package org.example;

import org.example.repository.HumanRepository;
import org.example.service.DealFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example");

        var humanRepository = applicationContext.getBean(HumanRepository.class);
        var dealFacade = applicationContext.getBean(DealFacade.class);

        Scanner scanner = new Scanner(System.in);

        var seller = humanRepository.findById(2L).orElseThrow();
        var buyer = humanRepository.findById(1L).orElseThrow();

        System.out.printf("seller: %s%n", seller);
        System.out.printf("buyer: %s%n", buyer);

        System.out.println("Введите id машины для покупки: ");
        var carId = scanner.nextLong();
        
        var car = seller.getCars().stream().filter(c -> c.getId().equals(carId)).findFirst().orElseThrow();

        dealFacade.deal(seller, buyer, car);

        humanRepository.findAll().forEach(System.out::println);
    }
}
