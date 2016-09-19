/**
 * 
 */
package ro.sci.services;

import java.util.List;

/**
 * @author luff
 *
 */
public interface CrudService<T> {

	List<T> listAll();

	T getById(Integer id);

	T save(T abstractModel);

	void delete(Integer id);

}
