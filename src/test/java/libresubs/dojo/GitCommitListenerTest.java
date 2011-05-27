package libresubs.dojo;

import junit.framework.Assert;
import libresubs.dojo.mock.GitRepositoryMock;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

@RunWith(GitCommitOnSuccessRunner.class)//
public class GitCommitListenerTest {

	GitRepositoryMock git = new GitRepositoryMock();
	RunListener listener = new GitCommitRunListener(git);	

	@Test
	public void testCommitOnSuccess() throws Exception{
		Result allTestsHavePassed = new Result();
		listener.testRunFinished(allTestsHavePassed);
		
		Assert.assertTrue(git.hasCommit());
	}
	
	@Test
	public void testWillNotCommitOnFailure() throws Exception{
		Result resultWithFailedTests = createFailedResult();
		listener.testRunFinished(resultWithFailedTests);
		
		Assert.assertFalse(git.hasCommit());
		
	}

	private Result createFailedResult() throws Exception {
		
		Result result = new Result();
		
		RunListener resultListener = result.createListener();
		
		Description description = null;
		Throwable throwable = null;
		Failure failure = new Failure(description, throwable);
		resultListener.testFailure(failure);
		
		return result;
	}
	
}
