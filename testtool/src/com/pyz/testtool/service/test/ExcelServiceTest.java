package com.pyz.testtool.service.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pyz.testtool.entity.Student;
import com.pyz.testtool.service.ExcelService;

public class ExcelServiceTest {

	@Test
	public void test() {
		try {
			new ExcelService().handle(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
