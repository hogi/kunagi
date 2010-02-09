// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtActionGenerator










package scrum.client.issues;

import java.util.*;

public abstract class GAcceptIssueAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.issues.Issue issue;

    public GAcceptIssueAction(scrum.client.issues.Issue issue) {
        this.issue = issue;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

}