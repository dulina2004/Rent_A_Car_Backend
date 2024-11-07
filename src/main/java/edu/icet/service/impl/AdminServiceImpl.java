package edu.icet.service.impl;

import edu.icet.dto.Car;
import edu.icet.entity.CarEntity;
import edu.icet.repository.CarRepository;
import edu.icet.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CarRepository carRepository;

    @Override
    public boolean postCar(Car car) {
        try {
            CarEntity carEntity=new CarEntity();
            carEntity.setName(car.getName());
            carEntity.setBrand(car.getBrand());
            carEntity.setColour(car.getColour());
             carEntity.setPrice(car.getPrice());
            carEntity.setYear(car.getYear());
            carEntity.setType(car.getType());
            carEntity.setDescription(car.getDescription());
            carEntity.setTransmission(car.getTransmission());
            carEntity.setImage(car.getImage().getBytes());
            carRepository.save(carEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll().stream().map(CarEntity::getCar).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car getCarById(Long id) {
        Optional<CarEntity> optionalCar = carRepository.findById(id);
        return optionalCar.map(CarEntity::getCar).orElse(null);
    }

    @Override
    public boolean updateCar(Long carId, Car car){
        Optional<CarEntity> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()){
            CarEntity existingCar =optionalCar.get();
            try {
                if (car.getImage()!=null)
                    existingCar.setImage(car.getImage().getBytes());
            } catch (Exception e) {
                System.out.println("imge not set");
            }

            existingCar.setPrice(car.getPrice());
            existingCar.setYear(car.getYear());
            existingCar.setType(car.getType());
            existingCar.setDescription(car.getDescription());
            existingCar.setTransmission(car.getTransmission());
            existingCar.setColour(car.getColour());
            existingCar.setName(car.getName());
            existingCar.setBrand(car.getBrand());

            carRepository.save(existingCar);
            return true;
        }
        return false;

    }
}
