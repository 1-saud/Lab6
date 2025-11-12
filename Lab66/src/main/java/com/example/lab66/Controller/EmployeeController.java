package com.example.lab66.Controller;

import com.example.lab66.API.ApiResponse;
import com.example.lab66.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import javax.swing.text.Position;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping("get")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody @Valid Employee e , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(e);
        return ResponseEntity.status(200).body(new ApiResponse("the employee is added"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid Employee e,
                                            @PathVariable int index , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.set(index,e);
        return ResponseEntity.status(200).body(new ApiResponse("updated"));

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> DeleteEmployee (@PathVariable int index ){
        if (index < 0 || index >= employees.size()){
            return ResponseEntity.status(400).body(new ApiResponse("u entered a wrong index"));
        }
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("removed"));

    }


    @GetMapping("/search/{position}/")
    public ResponseEntity<?> SearchByPosition(@PathVariable String position){
        if (!position.equals("supervisor") && !position.equals("coordinator")){
            return ResponseEntity.status(400).body(new ApiResponse("u have entered wrong position"));

        }
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee e : employees){
            if (e.getPosition().equals(position)){
                result.add(e);
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("Search is finished"));
    }

    @GetMapping("/no-annual-leave")
    public ResponseEntity<?> getEmployeesWithNoAnnualLeave() {

        ArrayList<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getAnnualLeave() == 0) {
                result.add(e);
            }
        }

        return ResponseEntity.status(200).body(result);
    }

}
