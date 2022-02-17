package com.amigoscode.car;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
// Service annotation - for the business logic
//Only does if,exception and throws
@Service
public class CarService {

    private CarDAO carDAO;
// Qualifier allows us to call the implementation (faker or postgres)
    public CarService(@Qualifier("fake") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    /*METHOD FOR GETTING A CAR BY ID
    return the Car object
    Integer is what we're accepting as the id is an integer
     want to check if the id that is entered already exists within our list
     throw
     */

    //internal method for use
    public Car doesCarExist (Integer carId) {
        //goes to database to select a car
        Car car = carDAO.selectCarById(carId);
        if (car != null) {
            throw new IllegalStateException("Error, car does exist in database");
        }
        // we have to get to the database to get the id, we have to get to the next layer DAO so calling the carDAO then implementing the method within the class
        // argument we're passing is carId
        return car;
    }


    //registering a car / ADDING NEW CAR
    public void registerNewCar(Car car) {
        // business logic. check if reg number is valid and not take
        // if the car price is equal or less than zero, you throw an exception
        if (car.getPrice() <= 0) {
            throw new IllegalStateException("Car price cannot be 0 or less");
        }

        // variable result equal, calling on carDAO to implement method of inserting a car to the database/list and we're accepting a car object.
        int result = carDAO.insertCar(car);
// when we insert a car into the database we should get 1 but if we don't get it then throw an exception
        if (result != 1) {
            throw new IllegalStateException("Could not save car...");
        }
    }
    //METHOD TO GET A LIST OF CARS
    //ACCESS THE DAO TO IMPLEMENT SELECT ALL CAR METHOD
    public List<Car> getCars() {return carDAO.selectAllCars();}

    //METHOD TO GET CAR BY ID
// what do we want to do ? Return the car object
//method to selectCarbyId(integer id)
        public Car selectCarById(Integer id) {



//calling on the car object so we can try and implement the selectCarById method in the DAO. Naming the method cars as to not get it confused with the object car
            Car cars = carDAO.selectCarById(id);
// returns the placeholder cars
            return cars;
        }

     //DELETE CAR
    //looping through a list and deleting at a index
    //Aim is to remove/update so we dont need to return anything so method will be void
    public void deleteCar(Integer carId){
        // we need to check if the car exists in our list/database, so we can use our internal method
        Car car = doesCarExist(carId);
        // if statement to see if the car in the database/list doesnt equal to 1, then the car doesnt exist in the database so you cant delete it.
       // using the carDAO to check the database
        //throw an exception if it does not equal to one
        if (carDAO.deleteCar(carId) != 1){
          throw new IllegalStateException( " Sorry, the car could not be deleted. ") ;

        }
        //otherwise delete car
        carDAO.deleteCar(carId);
    }

    //UPDATE CAR
//Aim is to update so we dont need to return anything so method will be void
    public void updateCar(Integer carId, Car update){
        // we need to check if the car exists in our list/database, so we can use our internal method
        Car car = doesCarExist(carId);
        // create a variable called int result, then go into the carDAO then get the method passing it the int id and car to be updated above.
        int result = carDAO.updateCar(carId, update);
// using int result to check if its been successful updated in the database
        // 1 successful update
        //not 1 unsuccessful update - throw an exception
        if (result !=1){
            throw new IllegalStateException("Sorry, car could not be updated.");
        }
      //  otherwise update car
        carDAO.updateCar(carId,update);
    }


    }






