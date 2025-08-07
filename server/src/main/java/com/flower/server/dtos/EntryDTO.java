package com.flower.server.dtos;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
public class EntryDTO {

    String id;

    String formId;

    ObjectNode values;

    // TODO: see how userId can be integrated here, maybe we can as we are building
    //  this for orgs internal purposes rather than online anonymous survey forms
    //  Even if it is for survey, maybe we can still ask for login.

}
