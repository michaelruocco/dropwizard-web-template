package uk.co.mruoc.health;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.tweak.HandleCallback;
import org.skife.jdbi.v2.util.IntegerColumnMapper;

public class Database {

    private final DBI dbi;

    public Database(DBI dbi) {
        this.dbi = dbi;
    }

    public boolean customerTableExists() {
        int count = dbi.withHandle(new CustomerTableCountCallback());
        return count == 1;
    }

    private static class CustomerTableCountCallback implements HandleCallback<Integer> {

        private static final String QUERY = "SELECT COUNT(*) FROM information_schema.tables " +
                "WHERE table_schema = :schema " +
                "AND table_name = :table;";

        @Override
        public Integer withHandle(Handle handle) throws Exception {
            return handle.createQuery(QUERY)
                    .bind(":schema", "web_template")
                    .bind(":table", "customer")
                    .map(IntegerColumnMapper.PRIMITIVE)
                    .first();
        }

    }

}
