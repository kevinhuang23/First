package test;

import java.math.BigDecimal;

public class TestThird {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        BigDecimal first =new BigDecimal(1);
        BigDecimal second =new BigDecimal(3);
        BigDecimal third =new BigDecimal(4);
        
        String s="abcd";
        System.out.println(s.substring(3));
        System.out.println(s.substring(0, 3));
        System.out.println(s.subSequence(0, 3));
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//        System.out.println(first.add(second).compareTo(third)==0);
	}

}
