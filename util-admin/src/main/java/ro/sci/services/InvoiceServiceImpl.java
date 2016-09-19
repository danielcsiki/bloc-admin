/**
 * 
 */
package ro.sci.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.domain.Invoice;
import ro.sci.repositories.InvoiceRepository;

/**
 * @author yellow
 *
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

	private InvoiceRepository invoiceRepository;

	@Autowired
	public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#listAll()
	 */
	@Override
	public List<Invoice> listAll() {
		List<Invoice> invoices = new ArrayList<>();
		invoiceRepository.findAll().forEach(invoices::add);
		return invoices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#getById(java.lang.Integer)
	 */
	@Override
	public Invoice getById(Integer id) {
		return invoiceRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#save(java.lang.Object)
	 */
	@Override
	public Invoice save(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		invoiceRepository.delete(id);
	}

}
