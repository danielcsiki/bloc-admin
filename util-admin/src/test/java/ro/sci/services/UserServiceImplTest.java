/**
 * 
 */
package ro.sci.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sci.config.JpaIntegrationConfig;
import ro.sci.domain.Apartment;
import ro.sci.domain.Charge;
import ro.sci.domain.ChargeItem;
import ro.sci.domain.Expense;
import ro.sci.domain.User;

/**
 * @author yellow
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
public class UserServiceImplTest {

	private UserService userService;
	private ExpenseService expenseService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@Test
	public void testSaveUser() throws Exception {

		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		User savedUser = userService.save(user);

		assert savedUser.getId() != null;
		assert savedUser.getEncryptedPassword() != null;

	}

	@Test
	public void testSaveUserWithApartment() throws Exception {

		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		user.setApartment(new Apartment());

		User savedUser = userService.save(user);

		assert savedUser.getId() != null;
		assert savedUser.getVersion() != null;
		assert savedUser.getEncryptedPassword() != null;
		assert savedUser.getApartment() != null;
		assert savedUser.getApartment().getId() != null;

	}

	@Test
	public void testSaveUserWithCharge() throws Exception {

		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		user.setCharge(new Charge());

		User savedUser = userService.save(user);

		assert savedUser.getId() != null;
		assert savedUser.getVersion() != null;
		assert savedUser.getCharge() != null;
		assert savedUser.getCharge().getId() != null;
	}

	@Test
	public void testSaveUserWithChargeWithChargeItems() throws Exception {

		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		user.setCharge(new Charge());

		// see JpaDataLoader...
		List<Expense> storedExpenses = expenseService.listAll();

		ChargeItem chargeItemOne = new ChargeItem();
		chargeItemOne.setExpense(storedExpenses.get(0));
		user.getCharge().addChargeItem(chargeItemOne);

		ChargeItem chargeItemTwo = new ChargeItem();
		chargeItemTwo.setExpense(storedExpenses.get(1));
		user.getCharge().addChargeItem(chargeItemTwo);

		User savedUser = userService.save(user);

		assert savedUser.getId() != null;
		assert savedUser.getVersion() != null;
		assert savedUser.getCharge() != null;
		assert savedUser.getCharge().getId() != null;
		assert savedUser.getCharge().getChargeItems().size() == 2;

		savedUser.getCharge().removeChargeItem(savedUser.getCharge().getChargeItems().get(0));

		userService.save(savedUser);

		assert savedUser.getCharge().getChargeItems().size() == 1;
	}

}
