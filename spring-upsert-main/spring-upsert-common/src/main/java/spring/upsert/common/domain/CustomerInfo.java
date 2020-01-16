package spring.upsert.common.domain;

public class CustomerInfo extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String name;
	private Integer customerType;

	public CustomerInfo() {
		super();
	}

	public CustomerInfo(Long id, String status) {
		super(id, status);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "CustomerInfo [firstName=" + firstName + ", lastName=" + lastName + ", name=" + name + ", customerType="
				+ customerType + ", toString()=" + super.toString() + "]";
	}

}
