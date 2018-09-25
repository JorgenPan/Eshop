import java.util.UUID;

import org.junit.Test;



public class TestUuid {

	
	@Test
	public void addItem() {
		
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(id);
		System.out.println("id³¤¶È"+id.length());

	}

}
