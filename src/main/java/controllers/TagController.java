package controllers;

import api.CreateTagRequest;
import api.TagResponse;
import dao.TagDao;
import generated.tables.records.TagsRecord;
import jdk.nashorn.internal.runtime.Debug;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/{tag}")
    public Integer toggleTag(CreateTagRequest tag, @PathParam("tag") String tagName) {
        // <your code here
//        List<TagsRecord> tagsRecords = tags.getAllTags();
        System.out.println("val = " + tagName);
//        return Integer.toString(tag.receipt_id);
//        return tagName;
        return tags.insert(tag.receipt_id, tagName);
//        return tag.receipt_id;
    }
}
