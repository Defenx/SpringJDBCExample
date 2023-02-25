package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.repository.CarRepository;
import org.example.repository.HumanRepository;
import org.example.service.DealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final HumanRepository humanRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deal(Long sellerId, Long buyerId, Long carId) {
        var seller = humanRepository.findById(sellerId).orElseThrow();
        var buyer = humanRepository.findById(buyerId).orElseThrow();

        var car = seller.getCars().stream().filter(e -> e.getId().equals(carId)).findFirst().orElseThrow();
        var cost = car.getCost();
        var sellerBalance = seller.getBalance();
        var buyerBalance = buyer.getBalance();

        if (buyerBalance.compareTo(cost) < 0) {
            throw new RuntimeException("Not enough money");
        }

        seller.setBalance(sellerBalance.add(cost));
        seller.getCars().remove(car);
        buyer.setBalance(buyerBalance.subtract(cost));
        buyer.getCars().add(car);
        car.setHumanId(buyerId);

        humanRepository.save(seller);
        humanRepository.save(buyer);
    }
}
