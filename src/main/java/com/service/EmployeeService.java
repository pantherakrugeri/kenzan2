package com.service;

import com.model.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


@Path("/rest/employee")
public class EmployeeService {
    @GET
    @Path("/search/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployee(@PathParam("employeeId") Integer employeeId){
        return "I search for an employee by this id: " + employeeId;
    }

    @GET
    @Path("/search/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() throws Exception{
        return "I return all employees";
    }

    @POST
    @Path("/create")
    @Consumes({"application/json", "application/x-www-form-urlencoded"})
    public String createEmployee(){
        return "I create an employee";
    }

    @PUT
    @Path("/update/{employeeId}")
    @Consumes({"application/json", "application/x-www-form-urlencoded"})
    public String updateEmployee(@PathParam("employeeId") Integer employeeId) {
        return "I update the employee by this id: " + employeeId;
    }

    @DELETE
    @Path("/delete/{employeeId}/{status}")
    @Consumes({"application/json", "application/x-www-form-urlencoded"})
    public String setEmployeeStatus(@PathParam("employeeId") Integer employeeId, @HeaderParam("authorization") String authorization) {
        return "I inactivate/activate the employee by this id: " + employeeId;
    }

    @GET
    @Path("/initialize/")
    @Produces(MediaType.APPLICATION_JSON)
    public Object initializeEmployees() throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        ArrayList employees = new ArrayList();

        Employee employee = new Employee(1, "Stymie", "S", "Rascal", df.parse("02-01-1980"), df.parse("06-03-2018"));
        Employee employee2 = new Employee(2, "Buckwheat", "B", "Rascal", df.parse("03-01-1970"), df.parse("01-01-2018"));
        Employee employee3 = new Employee(3, "Chubby", "C", "Rascal", df.parse("03-01-1970"), df.parse("01-01-2018"));
        Employee employee4 = new Employee(4, "Spanky", "S", "Rascal", df.parse("03-01-1970"), df.parse("01-01-2018"));
        Employee employee5 = new Employee(5, "Alfalfa", "A", "Rascal", df.parse("03-01-1970"), df.parse("01-01-2018"));

        employees.add(employee);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);

        return this.encodeJson(employees);
    }

    private String encodeJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            // Convert object to JSON string
            jsonInString = mapper.writeValueAsString(obj);
            System.out.println("employees:" + jsonInString);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }
}
