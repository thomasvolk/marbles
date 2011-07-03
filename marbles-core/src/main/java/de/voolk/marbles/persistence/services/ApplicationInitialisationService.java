package de.voolk.marbles.persistence.services;

import de.voolk.marbles.app.AppConfig;
import de.voolk.marbles.persistence.beans.Role;
import de.voolk.marbles.persistence.beans.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
@Lazy(false)
public class ApplicationInitialisationService implements IApplicationInitialisationService {
	private static  final Log LOG = LogFactory.getLog(ApplicationInitialisationService.class);
	@Autowired 
	private IAuthentificationService authentificationService;
	@Autowired
	private AppConfig config;
	
	@PostConstruct
	@Transactional
	public void init() {
		LOG.info("###### - INIT Marbles Database - ######");
		importAuthentificationData(config.getDefaultUsers());
		LOG.info("###### -          Done         - ######");
	}

	@Override
	public void importAuthentificationData(Collection<User> users) {
		for(User user: users) {
			Set<String> rolesFromDatabase = new HashSet<String>();
			Iterator<Role> rolesIter = user.getRoles().iterator();
			while(rolesIter.hasNext()) {
				Role role = rolesIter.next();
				Role dbRole = authentificationService.findRoleByName(role.getName());
				if(dbRole == null) {
					dbRole = authentificationService.createRole(role.getName());
				}
				rolesIter.remove();
				rolesFromDatabase.add(dbRole.getName());
			}
			User dbUser = authentificationService.findUserByName(user.getName());
			if(dbUser == null) {				
				authentificationService.persistUser(user);
			}
			else {
				user = dbUser;
			}
			if(!rolesFromDatabase.isEmpty()) {
				authentificationService.setRolesToUser(user.getId(), 
					rolesFromDatabase.toArray(new String[]{}));
			}
		}
	}
}