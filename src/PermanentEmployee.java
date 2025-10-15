class PermanentEmployee extends Employee implements Payable {
    private double bonus;

    public PermanentEmployee(String ID, String name, double basicSalary, double bonus) {
        super(ID, name, basicSalary);
        this.bonus = bonus;
    }

    public double getBonus() { return bonus; }

    @Override
    double calculateTax() {
    	
        return 0.10 * (basicSalary + bonus);
    }

    @Override
    public double calculateNetSalary() {
    	
        return (basicSalary + bonus) - calculateTax();
    }

    @Override
    public void generatePayslip() {
        System.out.println("------------------------------------------------");
        System.out.println("Payslip for Permanent Employee");
        System.out.println("Employee ID   : " + getEmpId());
        System.out.println("Name          : " + getName());
        System.out.println("Basic Salary  : " + basicSalary);
        System.out.println("Bonus         : " + bonus);
        System.out.println("Tax (10%)     : " + calculateTax());
        System.out.println("Net Salary    : " + calculateNetSalary());
        System.out.println("------------------------------------------------");
    }
}