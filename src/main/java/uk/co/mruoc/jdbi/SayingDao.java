package uk.co.mruoc.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import uk.co.mruoc.api.Saying;

@RegisterMapper(SayingMapper.class)
public interface SayingDao {

    @SqlUpdate("insert into saying (id, content) values (:id, :content)")
    void insert(@BindBean Saying saying);

    @SqlQuery("select id, content from saying where id = :id")
    Saying getSaying(@Bind("id") long id);

    void close();

}