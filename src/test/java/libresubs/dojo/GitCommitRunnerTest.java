package libresubs.dojo;

import junit.framework.Assert;
import libresubs.dojo.mock.GitRepositoryMock;

import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class GitCommitRunnerTest {

	@Test
	public void testCommitOnSuccess() throws Exception{
		GitRepositoryMock git = new GitRepositoryMock();
		RunListener listener = new GitCommitRunListener(git);
		Result allTestsHavePassed = new Result();
		listener.testRunFinished(allTestsHavePassed);
		
		Assert.assertTrue(git.hasCommit());
		
	}
	
}
