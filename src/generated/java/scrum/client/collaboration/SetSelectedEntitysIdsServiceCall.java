// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.collaboration;

public class SetSelectedEntitysIdsServiceCall extends scrum.client.core.AServiceCall {

    private java.util.Set ids;

    public  SetSelectedEntitysIdsServiceCall(java.util.Set ids) {
        this.ids = ids;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().setSelectedEntitysIds(serviceCaller.getConversationNumber(), ids, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "SetSelectedEntitysIds";
    }

}

