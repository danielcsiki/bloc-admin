/**
 * 
 */
package ro.sci.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ro.sci.domain.Expense;
import ro.sci.services.ExpenseService;

/**
 * @author luff
 *
 */
public class ExpenseControllerTest {

	@Mock
	private ExpenseService expenseService;

	@InjectMocks
	private ExpenseController expenseController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testListExpenses() throws Exception {

		List<Expense> expenses = new ArrayList<>();
		expenses.add(new Expense());
		expenses.add(new Expense());

		when(expenseService.listAll()).thenReturn((List) expenses);

		mockMvc.perform(get("/expense/list")).andExpect(status().isOk()).andExpect(view().name("expense/list"))
				.andExpect(model().attribute("expenses", hasSize(2)));

	}

	@Test
	public void testGetExpense() throws Exception {
		Integer id = 1;

		when(expenseService.getById(id)).thenReturn(new Expense());

		mockMvc.perform(get("/expense/show/1")).andExpect(status().isOk()).andExpect(view().name("expense/show"))
				.andExpect(model().attribute("expense", instanceOf(Expense.class)));
	}

	@Test
	public void testEditExpense() throws Exception {
		Integer id = 1;

		when(expenseService.getById(id)).thenReturn(new Expense());

		mockMvc.perform(get("/expense/edit/1")).andExpect(status().isOk()).andExpect(view().name("expense/expenseform"))
				.andExpect(model().attribute("expense", instanceOf(Expense.class)));
	}

	@Test
	public void testAddExpense() throws Exception {

		// should not invoke service
		verifyZeroInteractions(expenseService);

		mockMvc.perform(get("/expense/add")).andExpect(status().isOk()).andExpect(view().name("expense/expenseform"))
				.andExpect(model().attribute("expense", instanceOf(Expense.class)));
	}

	@Test
	public void testSaveExpense() throws Exception {

		Integer id = 1;
		String item = "salubritate";
		String calcReference = "rezident";
		Integer calcQuantum = 4;
		Float pricePerCalcQuantum = (float) 6.88;

		Expense expense = new Expense();
		expense.setId(id);
		expense.setItem(item);
		expense.setCalcReference(calcReference);
		expense.setCalcQuantum(calcQuantum);
		expense.setPricePerCalcQuantum(pricePerCalcQuantum);

		when(expenseService.save(Matchers.<Expense> any())).thenReturn(expense);

		mockMvc.perform(post("/expense").param("id", "1").param("item", "salubritate")
				.param("calcReference", "rezident").param("calcQuantum", "4").param("pricePerCalcQuantum", "6.88"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/expense/show/1"))
				.andExpect(model().attribute("expense", instanceOf(Expense.class)))
				.andExpect(model().attribute("expense", hasProperty("id", is(id))))
				.andExpect(model().attribute("expense", hasProperty("item", is(item))))
				.andExpect(model().attribute("expense", hasProperty("calcReference", is(calcReference))))
				.andExpect(model().attribute("expense", hasProperty("calcQuantum", is(calcQuantum))))
				.andExpect(model().attribute("expense", hasProperty("pricePerCalcQuantum", is(pricePerCalcQuantum))));

		// verify properties of bound object
		ArgumentCaptor<Expense> boundExpense = ArgumentCaptor.forClass(Expense.class);
		verify(expenseService).save(boundExpense.capture());

		assertEquals(id, boundExpense.getValue().getId());
		assertEquals(item, boundExpense.getValue().getItem());
		assertEquals(calcReference, boundExpense.getValue().getCalcReference());
		assertEquals(calcQuantum, boundExpense.getValue().getCalcQuantum());
		assertEquals(pricePerCalcQuantum, boundExpense.getValue().getPricePerCalcQuantum());
	}

	@Test
	public void testDeleteExpense() throws Exception {
		Integer id = 1;

		mockMvc.perform(get("/expense/delete/1")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/expense/list"));

		verify(expenseService, times(1)).delete(id);
	}

}
