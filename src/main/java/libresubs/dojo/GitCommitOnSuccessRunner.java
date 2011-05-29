package libresubs.dojo;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class GitCommitOnSuccessRunner extends BlockJUnit4ClassRunner{

	private final Class<?> klass;

	public GitCommitOnSuccessRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
		this.klass = testClass;
	}
	
	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
		GitRepositoryForClass repository = new GitRepositoryForClass(klass);
		GitCommitRunListener listener = new GitCommitRunListener(repository);
		notifier.addListener(listener);
	}
	

}
