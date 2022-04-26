package com;

import model.Employee;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Employee")
public class Employeeservice {
	Employee EmployeeObj = new Employee();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployee() {
		return EmployeeObj.readEmployee();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("name") String Name, 
			@FormParam("age") String age,
			@FormParam("address") String address) {
		String output = EmployeeObj.insertEmployee(Name, age, address);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateEmployee(String EmployeeData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(EmployeeData).getAsJsonObject();

		// Read the values from the JSON object
		String Id = ProObject.get("id").getAsString();
		String name = ProObject.get("name").getAsString();
		String age = ProObject.get("age").getAsString();
		String address = ProObject.get("address").getAsString();

		String output = EmployeeObj.updateEmployee(Id, name, age, address);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String EmployeeData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(EmployeeData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String Id = doc.select("id").text();
		String output = EmployeeObj.deleteEmployee(Id);
		return output;
	}
}
