package libresubs.dojo;

import java.io.File;
import java.util.Arrays;

public class GitRepositoryForClass implements GitRepository {

	private final Class<?> testClass;

	public GitRepositoryForClass(Class<?> testClass){
		this.testClass = testClass;
	}
	
	public void commit() {
		File repositoryDirectory = determineGitRepositoryForTestClass();
		commit(repositoryDirectory);
	}

	private void commit(File repositoryDirectory) {
		new GitRepoHandler(repositoryDirectory).commitAllWithUserMessage("dojo", "All tests are passing:) ");
	}

	private File determineGitRepositoryForTestClass() {
		File testClassParentDir = new File(testClass.getResource("/").getFile());
		File repositoryDirectory = findGitRepositoryOrNull(testClassParentDir);
		
		if (repositoryDirectory == null){
			throw new IllegalStateException("Git repository not found for base dir: " + testClassParentDir);
		}
		return repositoryDirectory;
	}

	private File findGitRepositoryOrNull(File dir) {
		
		if (isGitRepository(dir)){
			return dir;
		}
		
		File parent = dir.getParentFile();
		if (parent == null){
			return null;
		}
		
		return findGitRepositoryOrNull(parent);
	}

	private boolean isGitRepository(File dir) {
		
		String[] list = dir.list();
		Arrays.sort(list);
		return Arrays.binarySearch(list, ".git") != -1;
	}

}
