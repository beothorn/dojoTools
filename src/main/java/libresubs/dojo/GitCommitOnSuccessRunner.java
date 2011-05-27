package libresubs.dojo;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class GitCommitOnSuccessRunner extends BlockJUnit4ClassRunner{

	private final Class<?> klass;

	public GitCommitOnSuccessRunner(Class<?> klass) throws InitializationError {
		super(klass);
		this.klass = klass;
	}
	
	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
		TestClassGitRepository repository = new TestClassGitRepository(klass);
		GitCommitRunListener listener = new GitCommitRunListener(repository);
		notifier.addListener(listener);
	}
	

}
