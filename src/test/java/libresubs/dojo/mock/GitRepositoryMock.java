package libresubs.dojo.mock;

import libresubs.dojo.GitRepository;

public class GitRepositoryMock implements GitRepository {

	private boolean hasCommit = false;

	public boolean hasCommit() {
		return hasCommit;
	}

	public void commit() {
		hasCommit = true;
	}

}
