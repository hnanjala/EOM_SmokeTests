package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//This class has implemented 'IRetryAnalyzer' interface and is being used to determine the number of retries to perform based on the failure statuys.

public class RetryAnalyzer implements IRetryAnalyzer{
int counter=1;
int RetryCount=4;
	@Override
	public boolean retry(ITestResult arg0) {
		// TODO Auto-generated method stub
		if(counter<RetryCount)
		{
			counter++;
			return true;
		}
		return false;
	}

}
