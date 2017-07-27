package test;


import org.junit.Test;

import com.power.service.impl.IVipServiceImpl;

import test.BaseTest;


public class TestVip extends BaseTest{


	 @Test
	public void testVipService(){
		 IVipServiceImpl vip = new IVipServiceImpl();

		 vip.AddVip(3L);

	}

}
