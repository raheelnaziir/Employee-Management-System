
import java.util.*;

public class EmployeeSystem {
	
	static Scanner sc = new Scanner(System.in);
    static ArrayList<Payable> employees = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  int choice;
	        do {
	            System.out.println("\nWelcome to Employee Payroll Management System");
	            System.out.println("1. Add Employee");
	            System.out.println("2. View All Employees");
	            System.out.println("3. Search Employee");
	            System.out.println("4. Highest Net Salary");
	            System.out.println("5. Average Salary");
	            System.out.println("6. Generate Payslip");
	            System.out.println("7. Exit");
	            System.out.print("Choose an option: ");
	            choice = sc.nextInt();
	            sc.nextLine();

	            switch (choice) {
	                case 1 : addEmployee();
	                	break;
	                case 2 : viewAll();
	                	break;
	                case 3 : searchEmployee();
	                	break;	
	                case 4 : highestSalary();
	                break;
	                case 5 : averageSalary();
	                	break;
	                case 6 : payslip();
	                	break;
	                case 7 : exitSummary();
	                	break;
	                default : System.out.println("Invalid option! Try again.");
	            }
	        } while (choice != 7);

	}
	
    static void addEmployee() {
    	
        if (employees.size() >= 5) {
            System.out.println("Maximum 5 employees allowed!");
            return;
        }

        System.out.print("Enter Employee Type (Permanent/Contract): ");
        String type = sc.nextLine().toLowerCase();

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        for (Payable p : employees) {
            if (((Employee) p).getEmpId().equals(id)) {
                System.out.println("Employee ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Basic Salary: ");
        double salary = sc.nextDouble();

        if (salary <= 0) {
            System.out.println("Salary must be positive!");
            return;
        }

        if (type.equals("permanent")) {
            System.out.print("Enter Bonus: ");
            double bonus = sc.nextDouble();
            if (bonus < 0) {
                System.out.println("Bonus must be positive!");
                return;
            }
            employees.add(new PermanentEmployee(id, name, salary, bonus));
        } else if (type.equals("contract")) {
            System.out.print("Enter Contract Duration (months): ");
            int duration = sc.nextInt();
            employees.add(new ContractEmployee(id, name, salary, duration));
        } else {
            System.out.println("Invalid employee type!");
            return;
        }

        System.out.println("Employee added successfully!");
    }

    static void viewAll() {
    	
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-12s %-12s %-12s %-12s\n",
                "ID", "Name", "Type", "Salary", "Bonus/Dur", "Net Salary");
        System.out.println("--------------------------------------------------------------");

        for (Payable p : employees) {
            Employee e = (Employee) p;
            String type, extra;
            if (e instanceof PermanentEmployee pe) {
                type = "Permanent";
                extra = String.valueOf(pe.getBonus());
            } else if (e instanceof ContractEmployee ce) {
                type = "Contract";
                extra = ce.getContractDuration() + " mo";
            } else {
                type = "-";
                extra = "-";
            }
            System.out.printf("%-10s %-10s %-12s %-12.2f %-12s %-12.2f\n",
                    e.getEmpId(), e.getName(), type, e.getBasicSalary(), extra, p.calculateNetSalary());
        }
        System.out.println("--------------------------------------------------------------");
    }

    static void searchEmployee() {
    	
        System.out.print("Enter Employee ID to search: ");
        String id = sc.nextLine();

        for (Payable p : employees) {
            Employee e = (Employee) p;
            if (e.getEmpId().equals(id)) {
                p.generatePayslip();
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    static void highestSalary() {
    	
        if (employees.isEmpty()) {
            System.out.println("No employees available!");
            return;
        }

        Payable highest = employees.get(0);
        for (Payable p : employees) {
            if (p.calculateNetSalary() > highest.calculateNetSalary()) {
                highest = p;
            }
        }
        System.out.println("Employee with Highest Net Salary:");
        highest.generatePayslip();
    }

    static void averageSalary() {
    	
        if (employees.isEmpty()) {
            System.out.println("No employees available!");
            return;
        }

        double total = 0;
        for (Payable p : employees) {
            total += p.calculateNetSalary();
        }
        System.out.println("Average Net Salary: " + (total / employees.size()));
    }

    static void payslip() {
    	
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine();

        for (Payable p : employees) {
            Employee e = (Employee) p;
            if (e.getEmpId().equals(id)) {
                p.generatePayslip();
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    static void exitSummary() {
    	
        System.out.println("\nExiting program...");
        System.out.println("Total Employees: " + employees.size());
        if (!employees.isEmpty()) averageSalary();
        System.out.println("Goodbye!");
    }

}
