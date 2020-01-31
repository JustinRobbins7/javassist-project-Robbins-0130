package ex05;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import util.UtilMenu;

public class ExClassLoader extends ClassLoader {
	static final String SEP = File.separator;
	static String WORK_DIR = System.getProperty("user.dir");
	static String INPUT_DIR = WORK_DIR + SEP + "classfiles";
	private ClassPool pool;
	public String insertFieldName;
	
	public static void main(String[] args) throws Throwable {
		String[] input;

		do {
			System.out.println("===================================================================");
			System.out.println("EX05 - Please enter a class and a field name delimited by ,.        ");
			System.out.println("===================================================================");

			input = UtilMenu.getArguments();

		} while (input.length != 2);

		String appName = input[0];
		String fName = input[1];
		ExClassLoader loader = new ExClassLoader(fName);
		Class<?> c = loader.findClass("target." + appName);
		Method m = c.getDeclaredMethod("main", new Class[] { String[].class });
		m.invoke(null, new Object[] { input });
	}

	public ExClassLoader() throws NotFoundException {
		pool = ClassPool.getDefault();
		pool.insertClassPath(INPUT_DIR);
	}

	public ExClassLoader(String fName) throws NotFoundException  {
		pool = ClassPool.getDefault();
		pool.insertClassPath(INPUT_DIR);
		insertFieldName = fName;
	}
	/*
	 * Find a specified class, and modify the bytecode.
	 */
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			CtClass cc = pool.get(name);
			if (name.equals("target.ComponentApp") || name.equals("target.ServiceApp")) {
				CtField f = new CtField(CtClass.doubleType, insertFieldName, cc);
				f.setModifiers(Modifier.PUBLIC);
				cc.addField(f, CtField.Initializer.constant(0.0));
			}
			byte[] b = cc.toBytecode();
			return defineClass(name, b, 0, b.length);
		} catch (NotFoundException e) {
			throw new ClassNotFoundException();
		} catch (IOException e) {
			throw new ClassNotFoundException();
		} catch (CannotCompileException e) {
			throw new ClassNotFoundException();
		}
	}
}
