package com.svsos.api.jpa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.svsos.core.spring.Profiles;

/**
 * 实现带事务回滚的JPA测试
 * 
 * @author ranfi
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class JpaTest extends AbstractTransactionalJUnit4SpringContextTests {

	static {
		// 设定Spring的profile
		Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);
	}

}
