package libresubs.dojo;

import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

class GitCommitRunListener extends RunListener {

	private final GitRepository git;

	public GitCommitRunListener(GitRepository git) {
		this.git = git;
	}
	
	@Override
	public void testRunFinished(Result result) throws Exception {
		super.testRunFinished(result);
		if (result.getFailureCount() == 0)
			git.commit();
	}

}
