package com.amigoscode.car;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fake")
public class FakeCarDataAccessService implements CarDAO {
// creating a list of cars and naming it database
    private List<Car> db;

    public FakeCarDataAccessService() {
        this.db = new ArrayList<>();
    }

//creating the methods for the methods its override from the interface.

    @Override

    //say if the car id already exists, we need a way checking
    // taking the integer  id
    // loop to check the list of cars, putting in t

    public Car selectCarById(Integer id) {
        for (Car car:db) {
            // using the getter to get the id associated with the car, comparing to the id we entered in (id)
            if(car.getId() == id ) {
                // return the placeholder, a car from the list.
                return car;
            }
        }
        //no car exists with that id
return null;
    }

    @Override
    public List<Car> selectAllCars() {
        return db;
    }

    @Override
    public int insertCar(Car car) {
        db.add(car);
        //number of rows affected in the database
        return 1;
    }

    @Override
    public int deleteCar(Integer carId) {
        // writing out the method to delete a car
        // car to be deleted equals any id we enter
        Car carToBeDeleted = selectCarById(carId);
       //getting the list and deleting from the list using the remove method. Parameters is the car above.
        db.remove(carToBeDeleted);
        //if its successfully deleted from the database/list we want it to return 1./number of rows affected in the database
        return 1;
    }

    @Override
    public int updateCar(Integer carId, Car update) {
        // car to be updated equals any id we enter
        Car carToBeUpdated = selectCarById(carId);
        //getting the list and changing an element (car) in the list, set method.
        //we're setting each of the properties of the car.
        //db.setbrand??
        //carTobeUpdated is accessing the set Brand method, (getting the update brand above and get the brand we already have)
        carToBeUpdated.setBrand(update.getBrand());
        carToBeUpdated.setPrice(update.getPrice());
        carToBeUpdated.setRegNumber(update.getRegNumber());
    // if it's successful updating from the database/list, we want it to return 1/ number of rows affected in the database
        return 1;




    }
}
