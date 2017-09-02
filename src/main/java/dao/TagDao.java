package dao;

//import api.TagResponse;
import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public int insert(int receipt_id, String tag_name) {
        TagsRecord tagsRecord = dsl
                .insertInto(TAGS, TAGS.RECEIPT_ID, TAGS.TAG_NAME)
                .values(receipt_id, tag_name)
                .returning(TAGS.ID, TAGS.RECEIPT_ID, TAGS.TAG_NAME)
                .fetchOne();
        System.out.println("\n\n");
        System.out.println(tagsRecord);
        System.out.println("\n\n");
        checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert failed");

        return tagsRecord.getReceiptId();
    }

    public List<TagsRecord> getAllTags() {
        return dsl.selectFrom(TAGS).fetch();
    }
}
