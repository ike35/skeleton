package dao;

//import api.TagResponse;
import generated.tables.records.TagsRecord;
import generated.tables.records.ReceiptsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.TAGS;
import static generated.Tables.RECEIPTS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public void insert(int receipt_id, String tag_name) {
        TagsRecord tagsRecord = dsl
                .insertInto(TAGS, TAGS.RECEIPT_ID, TAGS.TAG_NAME)
                .values(receipt_id, tag_name)
                .returning(TAGS.ID, TAGS.RECEIPT_ID, TAGS.TAG_NAME)
                .fetchOne();
        System.out.println("\n\n");
        System.out.println(tagsRecord);
        System.out.println("\n\n");
        checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert failed");

//        return tagsRecord.getReceiptId();
    }

    public List<ReceiptsRecord> getReceiptsFromTag(String tag_name) {
        List<TagsRecord> List_tagName = dsl.selectFrom(TAGS).where(TAGS.TAG_NAME.eq(tag_name)).fetch();
//        System.out.println("=======================here:  " + List_tagName.stream().map(x->x.getReceiptId()).collect(Collectors.toList()));
//        System.out.println("=======================here:  " + List_tagName.get(0).get("receipt_id").hashCode());
        Integer[] receiptsIDList = new Integer[List_tagName.size()];

        for (int i = 0 ; i < List_tagName.size() ; i ++) {
            receiptsIDList[i] = Integer.parseInt(List_tagName.get(i).get("receipt_id").toString());
//            System.out.println(receiptsIDList[i]);
        }
//        System.out.println("=======================here:  " + receiptsIDList);
        System.out.println(List_tagName + "\n\n");
        System.out.println(dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.in(receiptsIDList)).fetch());
        return dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.in(receiptsIDList)).fetch();
    }
}
