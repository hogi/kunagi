package scrum.client;

import ilarkesto.gwt.client.ADataTransferObject;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataTransferObject extends ADataTransferObject implements Serializable, IsSerializable {

	public ApplicationInfo applicationInfo;
	public UsersStatusData usersStatus;

}
