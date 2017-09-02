package controllers;

import api.CreateTagRequest;
import api.ReceiptResponse;
import api.TagResponse;
import dao.ReceiptDao;
import dao.TagDao;
import generated.tables.records.TagsRecord;
import generated.tables.records.ReceiptsRecord;
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
//    final ReceiptDao receipts;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/{tag}")
    public void toggleTag(CreateTagRequest tag, @PathParam("tag") String tagName) {
        // <your code here
//        List<TagsRecord> tagsRecords = tags.getAllTags();
        System.out.println("val = " + tagName);
//        return Integer.toString(tag.receipt_id);
//        return tagName;
        tags.insert(tag.receipt_id, tagName);
//        return tag.receipt_id;
    }

    @GET
    @Path("/{tag}")
    public List<ReceiptResponse> getReceiptsFromTag (@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = tags.getReceiptsFromTag(tagName);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
//        return tags.getReceiptsFromTag(tagName);
    }
}
