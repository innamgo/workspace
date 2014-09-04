package com.jejuair.cosmicray.test;

import java.io.IOException;

import org.junit.Test;

import com.jejuair.cosmicray.core.Cari6mController;

public class CariTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cari6mController cariCon=new Cari6mController();
		
		try {
			cariCon.doCalculating();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test()
	{
		Cari6mController cariCon=new Cari6mController();
		
		try {
			cariCon.doCalculating();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
