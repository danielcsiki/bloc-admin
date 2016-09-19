/**
 * 
 */
package ro.sci.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.sci.domain.Invoice;

/**
 * @author yellow
 *
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

}
