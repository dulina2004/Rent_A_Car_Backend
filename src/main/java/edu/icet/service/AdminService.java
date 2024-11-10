package edu.icet.service;

import edu.icet.dto.BookACar;
import edu.icet.dto.Car;
import edu.icet.dto.CarDtoList;
import edu.icet.dto.SearchCar;

import java.util.List;

public interface AdminService {
    boolean postCar(Car car);
    List<Car>getAllCars();
    void deleteCar(Long id);
    Car getCarById(Long id);
    boolean updateCar(Long carId,Car car);
    List<BookACar>getBookings();
    boolean changeBookingStatus(Long bookingId,String status);
    CarDtoList searchCar(SearchCar searchCar);
}
