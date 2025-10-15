class ContractEmployee extends Employee implements Payable {
    private int contractDuration;

    public ContractEmployee(String ID, String name, double basicSalary, int contractDuration) {
        super(ID, name, basicSalary);
        this.contractDuration = contractDuration;
    }

    public int getContractDuration() {
    	return contractDuration; 
    	
    }

    @Override
    double calculateTax() {
    	
        return 0.05 * basicSalary;
    }

    @Override
    public double calculateNetSalary() {
    	
        return basicSalary - calculateTax();
    }

    @Override
    public void generatePayslip() {
        System.out.println("------------------------------------------------");
        System.out.println("Payslip for Contract Employee");
        System.out.println("Employee ID   : " + getEmpId());
        System.out.println("Name          : " + getName());
        System.out.println("Basic Salary  : " + basicSalary);
        System.out.println("Contract (mo) : " + contractDuration);
        System.out.println("Tax (5%)      : " + calculateTax());
        System.out.println("Net Salary    : " + calculateNetSalary());
        System.out.println("------------------------------------------------");
    }
}