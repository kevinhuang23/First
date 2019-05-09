package callBack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;
import org.springframework.cglib.proxy.InvocationHandler;

public class SampleClassTest {
	@Test
	public void testFixedValue() throws Exception {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SampleClass.class);
		enhancer.setCallback(new FixedValue() {
			@Override
			public Object loadObject() throws Exception {
				return "Hello cglib!";
			}
		});
		SampleClass proxy = (SampleClass) enhancer.create();
		assertEquals("Hello cglib!", proxy.test("huang"));
	}

	@Test
	public void testInvocationHandler() throws Exception {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SampleClass.class);
		enhancer.setCallback(new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
					return "Hello cglib!";
				} else {
					throw new RuntimeException("Do not know what to do.");
				}
			}

		});
		SampleClass proxy = (SampleClass) enhancer.create();
		assertEquals("Hello cglib!", proxy.test(null));
//		assertNotEquals("Hello cglib!", proxy.toString());
	}
}
