/**
 * 
 */
package ro.sci.dataloader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ro.sci.domain.Apartment;
import ro.sci.domain.Charge;
import ro.sci.domain.ChargeItem;
import ro.sci.domain.Expense;
import ro.sci.domain.Finance;
import ro.sci.domain.Invoice;
import ro.sci.domain.InvoiceItem;
import ro.sci.domain.User;
import ro.sci.domain.security.Role;
import ro.sci.services.ExpenseService;
import ro.sci.services.InvoiceService;
import ro.sci.services.RoleService;
import ro.sci.services.UserService;

/**
 * @author luff
 *
 */
@Component
public class JpaDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private ExpenseService expenseService;
	private UserService userService;
	private RoleService roleService;
	private InvoiceService invoiceService;

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		loadExpenses();
		loadUsersWithApartments();
		loadCharges();
		loadInvoices();
		loadRoles();
		assignUsersToDefaultRole();
		assignUsersToAdminRole();
	}

	private void loadExpenses() {

		Expense expense1 = new Expense();
		expense1.setItem("apa rece");
		expense1.setUnitReference("index contor");
		expense1.setUnitPrice(2.87f);
		expenseService.save(expense1);

		Expense expense2 = new Expense();
		expense2.setItem("apa pluviala");
		expense2.setUnitReference("apartament");
		expense2.setUnitPrice(3.28f);
		expenseService.save(expense2);

		Expense expense3 = new Expense();
		expense3.setItem("salubritate");
		expense3.setUnitReference("rezident");
		expense3.setUnitPrice(6.88f);
		expenseService.save(expense3);

		Expense expense4 = new Expense();
		expense4.setItem("servicii administrative");
		expense4.setUnitReference("apartament");
		expense4.setUnitPrice(11.00f);
		expenseService.save(expense4);

	}

	private void loadUsersWithApartments() {

		User user1 = new User();
		user1.setUsername("usernameone");
		user1.setPassword("pwone");

		Apartment apartment1 = new Apartment();
		apartment1.setNr(9);
		apartment1.setNrResidents(2);
		apartment1.setFirstName("e");
		apartment1.setLastName("r");
		apartment1.setEmail("er@yahoo.com");
		apartment1.setPhoneNumber("0749135694");
		apartment1.setAddress("rasinari 3");

		user1.setApartment(apartment1);
		userService.save(user1);

		User user2 = new User();
		user2.setUsername("usernametwo");
		user2.setPassword("pwtwo");

		Apartment apartment2 = new Apartment();
		apartment2.setNr(10);
		apartment2.setNrResidents(3);
		apartment2.setFirstName("s");
		apartment2.setLastName("t");
		apartment2.setEmail("st@yahoo.com");
		apartment2.setPhoneNumber("0744634782");
		apartment2.setAddress("rasinari 3");

		user2.setApartment(apartment2);
		userService.save(user2);

		User user3 = new User();
		user3.setUsername("usernamethree");
		user3.setPassword("pwthree");

		Apartment apartment3 = new Apartment();
		apartment3.setNr(11);
		apartment3.setNrResidents(4);
		apartment3.setFirstName("c");
		apartment3.setLastName("b");
		apartment3.setEmail("cb@yahoo.com");
		apartment3.setPhoneNumber("0752159384");
		apartment3.setAddress("rasinari 3");

		user3.setApartment(apartment3);
		userService.save(user3);

	}

	private void loadCharges() {

		List<User> users = userService.listAll();
		List<Expense> expenses = expenseService.listAll();

		User user = users.get(0);
		Charge charge = new Charge();
		user.setCharge(charge);

		try {
			charge.setChargeDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/09/2016"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ChargeItem chargeItem1 = new ChargeItem();
		chargeItem1.setExpense(expenses.get(0));
		chargeItem1.setAmount(14);
		chargeItem1.setCost(chargeItem1.getCost());
		user.getCharge().addChargeItem(chargeItem1);

		ChargeItem chargeItem2 = new ChargeItem();
		chargeItem2.setExpense(expenses.get(1));
		chargeItem2.setAmount(1);
		chargeItem2.setCost(chargeItem2.getCost());
		user.getCharge().addChargeItem(chargeItem2);

		user.getCharge().getChargeSum();

		Finance finance = new Finance();
		user.setFinance(finance);

		try {
			finance.setChargeDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/09/2016"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		finance.setChargeSum(finance.getChargeSum(charge));
		finance.setPayment(100f);
		finance.setBalance(finance.getBalance(charge));
		user.getFinance().addCharge(charge);

		userService.save(user);

	}

	private void loadInvoices() {

		List<User> users = userService.listAll();
		List<Expense> expenses = expenseService.listAll();

		users.forEach(user -> {
			Invoice invoice = new Invoice();
			invoice.setApartment(user.getApartment());

			expenses.forEach(expense -> {
				InvoiceItem invoiceItem = new InvoiceItem();
				invoiceItem.setExpense(expense);
				invoice.addInvoiceItem(invoiceItem);
			});
			invoiceService.save(invoice);
		});
	}

	private void loadRoles() {
		Role role = new Role();
		role.setRole("OWNER");
		roleService.save(role);

		Role adminRole = new Role();
		adminRole.setRole("ADMIN");
		roleService.save(adminRole);
	}

	private void assignUsersToDefaultRole() {
		List<Role> roles = roleService.listAll();
		List<User> users = userService.listAll();

		roles.forEach(role -> {
			if (role.getRole().equalsIgnoreCase("OWNER")) {
				users.forEach(user -> {
					user.addRole(role);
					userService.save(user);
				});
			}
		});
	}

	private void assignUsersToAdminRole() {
		List<Role> roles = (List<Role>) roleService.listAll();
		List<User> users = (List<User>) userService.listAll();

		roles.forEach(role -> {
			if (role.getRole().equalsIgnoreCase("ADMIN")) {
				users.forEach(user -> {
					if (user.getUsername().equals("usernameone")) {
						user.addRole(role);
						userService.save(user);
					}
				});
			}
		});
	}

	// private void loadCharges() {
	//
	// List<User> users = userService.listAll();
	// List<Expense> expenses = expenseService.listAll();
	//
	// users.forEach(user -> {
	// Charge charge = new Charge();
	// user.setCharge(charge);
	//
	// expenses.forEach(expense -> {
	// ChargeItem chargeItem = new ChargeItem();
	// chargeItem.setExpense(expense);
	// user.getCharge().addChargeItem(chargeItem);
	// });
	// userService.save(user);
	// });
	// }

	// private void loadInvoices() {
	//
	// List<User> users = userService.listAll();
	// List<Expense> expenses = expenseService.listAll();
	//
	// users.forEach(user -> {
	// Invoice invoice = new Invoice();
	// invoice.setApartment(user.getApartment());
	// InvoiceItem invoiceItem = new InvoiceItem();
	// invoiceItem.setExpense(expenses.get(0));
	// invoice.addInvoiceItem(invoiceItem);
	// invoiceService.save(invoice);
	// });
	// }

}
