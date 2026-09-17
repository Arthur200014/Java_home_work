package ru.itis.hw44.car;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Page<Car> findAll(int page, int size, String sort) {
        PageRequest request = PageRequest.of(page, size, Sort.by(sort));
        return carRepository.findAll(request);
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    public Car add(CarRequest request) {
        Car car = new Car();
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
        return carRepository.save(car);
    }
}
