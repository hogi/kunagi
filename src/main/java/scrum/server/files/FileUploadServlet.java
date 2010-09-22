package scrum.server.files;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import ilarkesto.base.PermissionDeniedException;
import ilarkesto.core.logging.Log;
import ilarkesto.io.IO;
import ilarkesto.webapp.Servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import scrum.server.GwtConversation;
import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.project.Project;

public class FileUploadServlet extends UploadAction {

	private static final Log log = Log.get(FileUploadServlet.class);

	@Override
	public String executeAction(HttpServletRequest req, List<FileItem> sessionFiles) throws UploadActionException {
		sessionFiles = new ArrayList<FileItem>(sessionFiles);
		String projectId = null;
		log.debug("file upload", Servlet.toString(req, "    "));
		log.debug("fields:", sessionFiles.size());
		for (FileItem item : new ArrayList<FileItem>(sessionFiles)) {
			String fieldName = item.getFieldName();
			if (item.isFormField()) {
				log.debug("   ", fieldName, "->", item.getString());
				sessionFiles.remove(item);
				if (fieldName.equals("projectId")) projectId = item.getString();
			} else {
				log.debug("   ", fieldName, "-> [file]");
			}
		}
		if (projectId == null) throw new RuntimeException("projectId == null");
		if (sessionFiles.size() != 1) throw new IllegalStateException("sessionFiles.size() == " + sessionFiles.size());

		ScrumWebApplication webApp = ScrumWebApplication.get();
		WebSession session = (WebSession) webApp.getWebSession(req);

		Project project = (Project) ScrumWebApplication.get().getDaoService().getById(projectId);
		if (!project.isVisibleFor(session.getUser())) throw new PermissionDeniedException();

		FileItem item = sessionFiles.get(0);
		try {
			String filename = getFilename(item.getName());
			java.io.File f = new java.io.File(project.getFileRepositoryPath() + "/" + filename);
			int count = 0;
			while (f.exists()) {
				count++;
				f = new java.io.File(project.getFileRepositoryPath() + "/" + insertSuffix(filename, count));
			}
			IO.copyDataToFile(item.getInputStream(), f);

			File file = webApp.getFileDao().postFile(f, project);
			webApp.getTransactionService().commit();
			for (GwtConversation conversation : webApp.getConversationsByProject(project, null)) {
				conversation.sendToClient(file);
			}
			return file.getReference();
		} catch (Exception e) {
			log.error(e);
			throw new UploadActionException(e.getMessage());
		} finally {
			removeSessionFileItems(req);
		}
	}

	private String insertSuffix(String name, int count) {
		int idx = name.lastIndexOf('.');
		if (idx > 0) return name.substring(0, idx) + "#" + count + name.substring(idx);
		return name + "#" + count;
	}

	private String getFilename(String name) {
		if (name == null) return "unnamed.bin";
		name = name.replace('\\', '/');
		int idx = name.lastIndexOf('/');
		if (idx >= 0) return name.substring(idx + 1);
		return name;
	}

}
