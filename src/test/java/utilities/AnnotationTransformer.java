package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

//This class is being used to implement the interface called "IAnnotationTransformer" and will modify the annotation values during run time.
public class AnnotationTransformer implements IAnnotationTransformer{

	//This method will be invoked by TestNG to give you a chance to modify a TestNG annotation read from your test classes. You can change the values you need by calling any of the setters on the ITest interface. Note that only one of the three parameters testClass, testConstructor and testMethod will be non-null.
	@Override
	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
		// TODO Auto-generated method stub
		arg0.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
