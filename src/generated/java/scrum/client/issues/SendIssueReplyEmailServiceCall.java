// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.issues;

public class SendIssueReplyEmailServiceCall extends scrum.client.core.AServiceCall {

    private String issueId;

    private String from;

    private String to;

    private String subject;

    private String text;

    public  SendIssueReplyEmailServiceCall(String issueId, String from, String to, String subject, String text) {
        this.issueId = issueId;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().sendIssueReplyEmail(serviceCaller.getConversationNumber(), issueId, from, to, subject, text, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "SendIssueReplyEmail";
    }

}

