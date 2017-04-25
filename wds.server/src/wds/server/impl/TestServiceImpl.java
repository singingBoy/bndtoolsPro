package wds.server.impl;

import javax.persistence.EntityManager;

import org.amdatu.jta.ManagedTransactional;
import org.amdatu.jta.Transactional;
import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Property;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import wds.commons.apis.JpaBasePersistenceService;
import wds.server.api.TestService;

@Component(provides = ManagedTransactional.class, properties = @Property(name = "persistent", value = "true"))
@Transactional
public class TestServiceImpl extends JpaBasePersistenceService implements TestService, ManagedTransactional {

	@ServiceDependency(filter = "(osgi.unit.name=wds.ManagePU)")
    private EntityManager entityManager;

	@Override
	public Class<?>[] getManagedInterfaces() {
		return new Class[] { TestService.class };
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	
}
