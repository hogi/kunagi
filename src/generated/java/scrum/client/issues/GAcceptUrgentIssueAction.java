// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.issues;

import java.util.*;

public abstract class GAcceptUrgentIssueAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.issues.Issue issue;

    public GAcceptUrgentIssueAction(scrum.client.issues.Issue issue) {
        this.issue = issue;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

}