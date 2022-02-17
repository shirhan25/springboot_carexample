package com.amigoscode.car;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//Api layer

// Restcontroller - for the api
@RestController
public class CarController {
//property of the api
    private CarService carService;
// constructor for the property
    public CarController(CarService carService) {
        this.carService = carService;
    }
//path - what you add to the end of the local host
    // http://localhost:8080/path
    //http://localhost:8080/cars
    //Post http request - adds a new resource
    @PostMapping(path = "cars")
    //Calling CarService to access the business logic/method related to registerNewCar/add the car
    //Service class just checks the logic ( verification step)
    public void addCar(@RequestBody Car car) {
        carService.registerNewCar(car);
    }

   //http://localhost:8080/cars
    //Get http request - get existing resource
    @GetMapping(path = "cars")
    //Calling the car Service and using the getcar method
    public List<Car> getCars() {
        return carService.getCars();
    }

//http://localhost:8080/cars/idnumber
//Get http request - get existing resource
    //calling the car Service and using selectCarByID method.
    @GetMapping(path = "cars/{id}")
    public Car getCarById(@PathVariable("id") Integer carId) {
        return carService.selectCarById(carId);
    }

    //http://localhost:8080/cars/idnumber
    //delete http - request updates existing resource
    //im not returning anything so i dont need a return
    // you can use get http request to see if it has deleted.
    //in the curly brackets we're conecting the api layer(car controller) with the service class (car service) to get access to the method
    @DeleteMapping(path = "cars/{id}")
    public void deleteCarById(@PathVariable("id") Integer carId) {
        carService.deleteCar(carId);
    }


    //http://localhost:8080/cars/idnumber
    //put http - request updates existing resource
    //im not returning anything so i dont need a return
    // you can use get http request to see if it has updated.
   // in the curly brackets we're conecting the api layer(car controller) with the service class(car service to get access to the method
    @PutMapping(path = "cars/{id}")
    public void updateCar(@PathVariable("id") Integer carId, @RequestBody Car update) {
        carService.updateCar(carId,update);
    }
}
