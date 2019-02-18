package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;


public interface LiveMapper {

    @Select("select  * from STARCLOUDPORTAL.SCE_COMMON_USER ")
    @ResultType(List.class)
    List<Map> getAllInfo();


    @InsertProvider(type = LiveDynaSqlProvider.class,
            method = "addLive")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addLive( Map< String, Object > map );


    @Update("update  train_live  set status=#{status} where room_id=#{room_id}")
    int updateLiveStatus( @Param( "room_id" ) String room_id, @Param( "status" ) String status );


    @SelectProvider(type = LiveDynaSqlProvider.class,
            method = "selectLive")
    @ResultType(List.class)
//    "select * from train_live"
    List<Map<String, Object>> selectAllLive( Map< String, Object > map );

    @Update({
            "<script>",
            "<foreach item='value' index='key' collection='map' separator=';'>",
            "update  train_live ",
            "set audience_num=audience_num + #{value} where room_id=#{key}",
            "</foreach>",
            "</script>"
    })
    int updateWatchPeople( Map map );


    @UpdateProvider(type = LiveDynaSqlProvider.class,
            method = "editLive" )
    int editLive( Map< String, Object > map );


    @Update("update train_live  set  operation_status='0' where id =#{id}")
    int repealLive( Map< String, Object > map );

    @Update("update train_live  set  is_display='0' where id =#{id}")
    int delLive( Map< String, Object > map );


    @Update("update  train_live set audience_num=audience_num + 1 where id=#{id}")
    Integer updateWatchNum( @Param( "id" ) String id );

    @Update("update  train_live set replay_url=#{replay_url} where id=#{id}")
    int replayUpload( Map map );

    @Update("UPDATE train_live SET replay_url=#{video_url}" +
            "WHERE  room_id=#{channel_id}" +
            " AND  date_sub(live_start, interval 1 hour) < FROM_UNIXTIME(#{start_time})" +
            " AND  date_add(live_end, interval 1 hour) > FROM_UNIXTIME(#{end_time})")
    int updateLiveReplay( Map map );


    @Insert("insert into train_replay_record(live_id,replay_url)  VALUES (#{channel_id},#{video_url})  ")
    int insertReplayRecord( Map map );


    class LiveDynaSqlProvider {
        public String addLive( final Map< String, Object > map ) {
            return new SQL() {{
                INSERT_INTO( "train_live" );
                if ( map.get( "id" ) != null ) {
                    VALUES( "id", "#{id}" );
                }
                if ( map.get( "room_title" ) != null ) {
                    VALUES( "room_title", "#{room_title}" );
                }
                if ( map.get( "room_desc" ) != null ) {
                    VALUES( "room_desc", "#{room_desc}" );
                }
                if ( map.get( "lecture_desc" ) != null ) {
                    VALUES( "lecture_desc", "#{lecture_desc}" );
                }
                if ( map.get( "room_id" ) != null ) {
                    VALUES( "room_id", "#{room_id}" );
                }
                if ( map.get( "push_url" ) != null ) {
                    VALUES( "push_url", "#{push_url}" );
                }
                if ( map.get( "pull_url" ) != null ) {
                    VALUES( "pull_url", "#{pull_url}" );
                }
                if ( map.get( "pull_rtmp_url" ) != null ) {
                    VALUES( "pull_rtmp_url", "#{pull_rtmp_url}" );
                }
                if ( map.get( "live_type" ) != null ) {
                    VALUES( "live_type", "#{live_type}" );
                }
                if ( map.get( "spec_id" ) != null ) {
                    VALUES( "spec_id", "#{spec_id}" );
                }
                if ( map.get( "lecture_name" ) != null ) {
                    VALUES( "lecture_name", "#{lecture_name}" );
                }
                if ( map.get( "live_start" ) != null ) {
                    VALUES( "live_start", "#{live_start}" );
                }
                if ( map.get( "live_end" ) != null ) {
                    VALUES( "live_end", "#{live_end}" );
                }
                if ( map.get( "user_id" ) != null ) {
                    VALUES( "user_id", "#{user_id}" );
                }
                if ( map.get( "img_url" ) != null ) {
                    VALUES( "img_url", "#{img_url}" );
                }
            }}.toString();
        }


    }
}
