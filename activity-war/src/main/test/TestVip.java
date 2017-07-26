package src.main.test;


import org.junit.Test;

import com.power.api.VipService;
import com.power.service.impl.VipServiceImpl;

import src.main.test.BaseTest;

public class TestVip extends BaseTest{

	
	 @Test
	public void testVipService(){
		 VipServiceImpl vip = new VipServiceImpl();
		 
		 vip.AddVip(3L);
		
	}
	
}
