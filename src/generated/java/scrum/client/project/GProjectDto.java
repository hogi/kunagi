// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityDtoGenerator










package scrum.client.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public class GProjectDto
            extends ilarkesto.gwt.client.AGwtEntityDto {

    public java.lang.String label ;
    public java.lang.String description ;
    public ilarkesto.gwt.client.Date begin ;
    public ilarkesto.gwt.client.Date end ;
    public Set<String> adminsIds = new HashSet<String>();
    public String productOwnerId;
    public String scrumMasterId;
    public Set<String> teamMembersIds = new HashSet<String>();
    public String currentSprintId;
    public String nextSprintId;

}