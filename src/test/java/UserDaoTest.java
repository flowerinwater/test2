import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.mongodb.dao.IUserDao;
import com.ssm.mongodb.entity.User;

/**
 * @author <a href="http://blog.csdn.net/java2000_wl">java2000_wl</a>
 * @version <b>1.0</b>
 */
public class UserDaoTest {
	
	/**
	 * 新增 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testInsert() {
		getUserDao().insert(new User(null, "testUser", 21));
	}
	
	/**
	 * 批量新增 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testInsertAll() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			list.add(new User(null, "testUser" + i, 21 + i));
		}
		getUserDao().insertAll(list);
	}
	
	/**
	 * 根据主键删除 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDeleteById() {
		String id = "5058184ec85607e42c4bfad8";
		getUserDao().deleteById(id);
	}
	
	/**
	 * 条件删除 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDelete() {
		//删除年龄大于25的
		getUserDao().delete(new User(null, null, 25));
	}
	
	/**
	 * 删除全部
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDeleteAll() {
		getUserDao().deleteAll();
	}
	
	/**
	 * 修改  根据id修改
	 * <br>------------------------------<br>
	 */
	@Test
	public void testUpdateById() {
		getUserDao().updateById(new User("50581c08c856346f02e9842c", "张三", 100));
	}
	
	/**
	 * 修改多个
	 * <br>------------------------------<br>
	 */
	@Test
	public void update() {
		//修改年龄大于29岁的 姓名为“王五“
		User criteriaUser = new User(null, null, 29);
		User user = new User(null, "王五", 39);
		getUserDao().update(criteriaUser, user);
	}
	
	/**
	 * 按主键查询, 如果不存在 返回null
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFindById() {
		User user = getUserDao().findById("50581c08c856346f02e98425");
		print(user);
	}
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFindAll() {
		List<User> list = getUserDao().findAll();
		print(list);
	}
	
	/**
	 * 按条件查询
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFind() {
		//查询25岁以上的, 分页
		User criteriaUser = new User(null, null, 20);
		List<User> list = getUserDao().find(criteriaUser, 1, 10);
		print(list);
	}
	
	/**
	 * 查询出来后  修改
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFindAndModify() {
		User criteriaUser = new User("50581c08c856346f02e9842d", null, 0);
		User updateUser = new User(null, "张三", 100);
		updateUser = getUserDao().findAndModify(criteriaUser, updateUser);
		print(updateUser);
	}
	
	/**
	 * 查询出来后 删除
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFindAndRemove() {
		User criteriaUser = new User("50581c08c856346f02e9842d", null, 0);
		criteriaUser = getUserDao().findAndRemove(criteriaUser);
		print(criteriaUser);
	}
	
	/**
	 * count
	 * <br>------------------------------<br>
	 */
	@Test
	public void testCount() {
		User criteriaUser = new User(null, "test", 0);
		long count = getUserDao().count(criteriaUser);
		print(count);
	}
	
	public void print(Object object) {
		if (object == null || !(object instanceof Collection)) {
			System.out.println(object);
			return;
		}
		List<?> list = (List<?>) object;
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
	
	public IUserDao getUserDao() {
		String configLocations = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		IUserDao userDao = applicationContext.getBean("userDao", IUserDao.class);
		return userDao;
	}
}