
public abstract class Employee {
	
	private String ID;
    private String name;
    protected double basicSalary;

    public Employee(String ID, String name, double basicSalary) {
        this.ID = ID;
        this.name = name;
        this.basicSalary = basicSalary;
    }

    // Getters and Setters
    public String getEmpId() { 
    	return ID;
    	
    }
    public String getName() {
    	return name; 
    	
    }
    public double getBasicSalary() {
    	return basicSalary; 
    	
    }

    public void setName(String name) {
    	this.name = name; 
    	
    }
    public void setBasicSalary(double basicSalary) {
    	this.basicSalary = basicSalary; 
    	
    }

    
    abstract double calculateTax();

}
