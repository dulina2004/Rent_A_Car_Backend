package edu.icet.controller;
import edu.icet.dto.BookACar;
import edu.icet.dto.Car;
import edu.icet.dto.SearchCar;
import edu.icet.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/car")
    public ResponseEntity<?>postCar(@ModelAttribute Car car){
        System.out.println(car);
        boolean success=adminService.postCar(car);
        if (success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @GetMapping("/cars")
    public ResponseEntity<?>getAllCars(){
        //System.out.println(ResponseEntity.ok(adminService.getAllCars()));
        return ResponseEntity.ok(adminService.getAllCars());
    }
    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        adminService.deleteCar(id);
        return ResponseEntity.ok(null);
    }
    @GetMapping("/car/{id}")
    public ResponseEntity<Car>getCarById(@PathVariable Long id){
        Car car =adminService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/car/{carId}")
    public ResponseEntity<Void> updateCar(@PathVariable Long carId,@ModelAttribute Car car){
        try{
            boolean success=adminService.updateCar(carId,car);
            if (success) return  ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/car/bookings")
    public  ResponseEntity<List<BookACar>> getBookings(){
        return ResponseEntity.ok(adminService.getBookings());

    }
    @GetMapping("/car/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId ,@PathVariable String status){
        boolean success = adminService.changeBookingStatus(bookingId,status);
        if (success) return  ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchCar searchCar){
        return ResponseEntity.ok(adminService.searchCar(searchCar));
    }
}
