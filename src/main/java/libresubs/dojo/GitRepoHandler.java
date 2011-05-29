package libresubs.dojo;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;

public class GitRepoHandler {
	
	private final Git git;
	private Repository repo;
	
	public GitRepoHandler(final File parentDir) {
		final File gitFolder = new File(parentDir,".git");
		
		try {
			repo = new RepositoryBuilder().setGitDir(gitFolder).build();
		} catch (final IOException e) {
			throw new RuntimeException("Error loading git repo", e);
		}
		
		git = new Git(repo);
		
	}
	
	public void commitAllWithUserMessage(String user, String message){
		addAll();
		commitWith(user, user, message);
	}

	private void addAll() {
		final String allFilesPattern = ".";
		addFile(allFilesPattern);
	}

	private void commitWith(final String commiterName, final String commiterEmail,
			final String commitMessage) {
		try{
			final CommitCommand commitCommand = git.commit();
			commitCommand.setAll(true).setCommitter(commiterName, commiterEmail).setMessage(commitMessage).call();
		} catch (final Exception e) {
			throw new RuntimeException("Error commiting file", e);
		}
	}

	private void addFile(final String filePattern) {
		repo.getRepositoryState().values();
		final AddCommand addCommand = git.add();
		addCommand.addFilepattern(filePattern);
		try {
			DirCache result = addCommand.call();
		} catch (final Exception e) {
			throw new RuntimeException("Error while adding all repository to initial commit.", e);
		}
	}
}
