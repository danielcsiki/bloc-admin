/**
 * 
 */
package ro.sci.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.domain.security.Role;
import ro.sci.repositories.RoleRepository;

/**
 * @author luff
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#listAll()
	 */
	@Override
	public List<Role> listAll() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);
		return roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#getById(java.lang.Integer)
	 */
	@Override
	public Role getById(Integer id) {
		return roleRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#save(java.lang.Object)
	 */
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		roleRepository.delete(id);
	}

}
