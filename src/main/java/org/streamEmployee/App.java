package org.streamEmployee;

import java.lang.reflect.Type;
import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        //Question1 -> How many male and female employees are there in the organization?
        Map<String,Long>noOfMaleAndFemaleEmployee = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println("The no of male and female employees are"+" "+noOfMaleAndFemaleEmployee+" ");

        //Question2->  Print the name of all departments in the organization?
        employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
        System.out.println(" ");

        //Question3-> What is the average age of male and female employees?
        Map<String,Double>averageAgeOfMaleAndFemaleEmployee=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingInt(Employee::getAge)));
        System.out.println("The Average age of male and female employees are "+" "+averageAgeOfMaleAndFemaleEmployee+" ");

        //Question4->  Get the details of highest paid employee in the organization?
        Double highestPaidEmployee = employeeList.stream()
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder())
                .orElse(Double.valueOf(0));
        System.out.println("The highest paid employee salary is "+" "+highestPaidEmployee+" ");

        //Question5-> Get the names of all employees who have joined after 2015?
        employeeList.stream().filter(e->e.getYearOfJoining()>2015).map(Employee::getName).forEach(System.out::println);
        System.out.println(" ");

        //Question6-> Count the number of employees in each department?
        Map<String,Long>empCount=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        Set<Map.Entry<String,Long>>entrySet=empCount.entrySet();
        for(Map.Entry<String,Long>entry:entrySet){
            System.out.println(entry.getKey()+" "+entry.getValue()+"   ");

        }
        System.out.println("   ");
       //Question7-> What is the average salary of each department?
        Map<String,Double>avgSalary=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        Set<Map.Entry<String,Double>> entrySe =avgSalary.entrySet();
        for(Map.Entry<String,Double>entry:entrySe){
            System.out.println(entry.getKey()+" "+entry.getValue()+" ");
        }
        System.out.println("   ");

       //Question8-> Get the details of youngest male employee in the product development department?
        Optional<Employee> youngestMaleEmployeeInProductDevelopmentWrapper=
                employeeList.stream()
                        .filter(e -> e.getGender()=="Male" && e.getDepartment()=="Product Development")
                        .min(Comparator.comparingInt(Employee::getAge));

        Employee youngestMaleEmployeeInProductDevelopment = youngestMaleEmployeeInProductDevelopmentWrapper.get();

        System.out.println("Details Of Youngest Male Employee In Product Development");

        System.out.println("----------------------------------------------");

        System.out.println("ID : "+youngestMaleEmployeeInProductDevelopment.getId());

        System.out.println("Name : "+youngestMaleEmployeeInProductDevelopment.getName());

        System.out.println("Age : "+youngestMaleEmployeeInProductDevelopment.getAge());

        System.out.println("Year Of Joinging : "+youngestMaleEmployeeInProductDevelopment.getYearOfJoining());

        System.out.println("Salary : "+youngestMaleEmployeeInProductDevelopment.getSalary());

        //Question9-> Who has the most working experience in the organization?
        Optional<Employee> seniorMostEmployeeWrapper=
                employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();

        Employee seniorMostEmployee = seniorMostEmployeeWrapper.get();

        System.out.println("Senior Most Employee Details :");

        System.out.println("----------------------------");

        System.out.println("ID : "+seniorMostEmployee.getId());

        System.out.println("Name : "+seniorMostEmployee.getName());

        System.out.println("Age : "+seniorMostEmployee.getAge());

        System.out.println("Gender : "+seniorMostEmployee.getGender());

        System.out.println("Age : "+seniorMostEmployee.getDepartment());

        System.out.println("Year Of Joinging : "+seniorMostEmployee.getYearOfJoining());

        System.out.println("Salary : "+seniorMostEmployee.getSalary());

        //Question10->  How many male and female employees are there in the sales and marketing team?
        Map<String, Long> countMaleFemaleEmployeesInSalesMarketing=
                employeeList.stream()
                        .filter(e -> e.getDepartment()=="Sales And Marketing")
                        .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        System.out.println(countMaleFemaleEmployeesInSalesMarketing);

        //Question11->  What is the average salary of male and female employees?
         Map<String,Double>maleAndFemaleSalary=employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
         System.out.println("the salary of male and female employees"+" "+maleAndFemaleSalary+" ");

        //Question12-> List down the names of all employees in each department?
        Map<String,List<Employee>>employeeListByDepartment=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        Set<Map.Entry<String,List<Employee>>> employee=employeeListByDepartment.entrySet();
        for(Map.Entry<String,List<Employee>>entry:employee){
               System.out.println("Employees in "+" "+entry.getKey()+" ");
               List<Employee>list=entry.getValue();
               for(Employee e:list){
                   System.out.println(e.getName());

               }
               System.out.println("   ");

        }

       //Question13->  What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics employeeSalaryStatistics= employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        Double averageSalary= employeeSalaryStatistics.getAverage();
        Double totalSalary =  employeeSalaryStatistics.getSum();
        System.out.println("the average salary"+" "+averageSalary+" "+"The total Salary is "+totalSalary+" ");

       //Question14-> Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean,List<Employee>>partitioningEmployeeByAge=employeeList.stream().collect(Collectors.partitioningBy(e->e.getAge()>25));
        Set<Map.Entry<Boolean,List<Employee>>>partition=partitioningEmployeeByAge.entrySet();
        for(Map.Entry<Boolean,List<Employee>>entry:partition){
            System.out.println("-----------------------");
            if(entry.getKey()){
                   System.out.println("Employees age greater than age 25 are ");
            }
            else{
                System.out.println("Employees age less than age 25 are ");
            }
            List<Employee>e=entry.getValue();
            for(Employee er:e){
                System.out.println(er.getName());
            }
            System.out.println("--------------------------");
            System.out.println("        ");
        }

       //Question15->  Who is the oldest employee in the organization? What is his age and which department he belongs to?

    }
}
