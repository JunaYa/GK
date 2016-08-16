package gank.sin.me.gk.data.remote;

import java.util.List;

import gank.sin.me.gk.data.model.Gank;
import gank.sin.me.gk.data.model.History;
import gank.sin.me.gk.data.model.Search;
import gank.sin.me.gk.data.model.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sin on 2016/8/5.
 */
public interface GankApi {

    /**
     * @param query listView
     * @param type  all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     * @param page
     * @return
     */
    @GET("search/query/{query}/category/{type}/count/30/page/{page}")
    Observable<Result<List<Search>>> getSearch(
            @Path("query") String query,
            @Path("type") String type
            , @Path("page") int page);


    /**
     * 获取某几日干货网站数据:
     * @param page  取第 page 页数据
     * @return
     */
    @GET("history/content/10/{page}")
    Observable<Result<History>> getSomeDaysHistory(@Path("page") int page);


    /**
     * 获取特定日期网站数据:
     * @param date  history date
     * @return
     */
    @GET("history/content/day/{date}")
    Observable<Result<History>> getSpecifiedHistory(@Path("date") String date);

    /**
     * 获取发过干货日期接口:
     * @return
     */
    @GET("day/history")
    Observable<Result<List<String>>> getHistoryDates();

    @GET("data/{type}/10/{page}")
    Observable<Result<List<Gank>>> getGank(@Path("type") String type, @Path("page") int page);
}
