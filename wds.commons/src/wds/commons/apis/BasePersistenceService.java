package wds.commons.apis;

import java.io.Serializable;
import java.util.List;

public interface BasePersistenceService {
	
	<T extends Serializable > T persist(T object);
	
	<T extends Serializable > void remove(Class<T> clazz, String id);

	<T extends Serializable > T merge(T object);

	/**
	 * 根据ID查询，如果找到则返回实体类，否则返回null
	 * @param clazz 实体类型
	 * @param id 查询id
	 * @return 实体类
	 */
	<T extends Serializable > T find(Class<T> clazz, String id);
	
	<T extends Serializable > List<T> query(Class<T> clazz, QueryParams params);

	int count(Class<? extends Serializable> clazz, QueryParams params);
	
}
