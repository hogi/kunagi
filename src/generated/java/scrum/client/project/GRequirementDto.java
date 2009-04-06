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

public class GRequirementDto
            extends ilarkesto.gwt.client.AGwtEntityDto {

    public String projectId;
    public String sprintId;
    public Set<String> qualitysIds = new HashSet<String>();
    public java.lang.String label ;
    public java.lang.String description ;
    public java.lang.String testDescription ;
    public java.lang.Integer estimatedWork ;
    public boolean closed ;

}