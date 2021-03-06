package com.Cds.Api;

import com.apiutils.APIUtils;
import com.backendutils.Env;
import com.backendutils.MongoUtils;
import com.backendutils.PostgresUtils;
import com.configuration.LoggerInitialization;
import com.configuration.api.Configuration;
import com.jayway.restassured.path.json.config.JsonPathConfig;
import com.jayway.restassured.response.Response;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

public class T1_Sprint_14 extends Configuration {

    Logger logger = LoggerInitialization.setupLogger("T1_Sprint_14");
    MongoUtils mongoUtils = new MongoUtils();
    APIUtils apiUtils = new APIUtils();

    @DataProvider(name="Fisc6354")
    public Object[][] getDataFor6354(){
        MongoCollection<Document> collection = mongoUtils
                .connectToMongoDatabase(META)
                .getDatabase("xrefdb")
                .getCollection("metadataFields");

        BasicDBObject query = new BasicDBObject();
        query.put("dataSourceId", "cds");
        String mongoResponse = null;
        MongoCursor<Document> cursor = collection.find(query).iterator();
        while (cursor.hasNext()) {
            mongoResponse = cursor.next().toJson();
        }

        String uri = baseURI + "/v1/metadata/fields?filter[source]=cds";
        String apiResponse = apiUtils.getResponse(uri, AuthrztionValue, XappClintIDvalue, acceptValue, contentValue).asString();


        return new Object[][] {
                {"FC_2Y_BPS_CDS","2Y CDS (bps)","2Y CDS (bps)","2Y CDS in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_BPS_CDS_PD","2Y CDS (bps) PD","2Y CDS (bps) PD","2Y CDS Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_2Y_DD_BPS_CDS","2Y CDS d/d (bps)","2Y CDS d/d (bps)","2Y CDS Day on Day change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_DD_BPS_CDS_PD","2Y CDS d/d (bps) PD","2Y CDS d/d (bps) PD","2Y CDS Probability of Default Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_2Y_DD_PERCENT_CDS","2Y CDS d/d (%)","2Y CDS d/d (%)","2Y CDS Day on Day percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_DD_PERCENT_CDS_PD","2Y CDS d/d (%) PD","2Y CDS d/d (%) PD","2Y CDS Probability of Default Day on Day change percent change","base", apiResponse, mongoResponse},
                {"FC_2Y_MM_BPS_CDS","2Y CDS m/m (bps)","2Y CDS m/m (bps)","2Y CDS Month on Month change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_MM_BPS_CDS_PD","2Y CDS m/m (bps) PD","2Y CDS m/m (bps) PD","2Y CDS Probability of Default Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_2Y_MM_PERCENT_CDS","2Y CDS m/m (%)","2Y CDS m/m (%)","2Y CDS Month on Month percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_MM_PERCENT_CDS_PD","2Y CDS m/m (%) PD","2Y CDS m/m (%) PD","2Y CDS Probability of Default Month on Month change percent change","base", apiResponse, mongoResponse},
                {"FC_2Y_QQ_BPS_CDS","2Y CDS q/q (bps)","2Y CDS q/q (bps)","2Y CDS Quarter on Quarter change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_QQ_BPS_CDS_PD","2Y CDS q/q (bps) PD","2Y CDS q/q (bps) PD","2Y CDS Probability of Default Quarter on Quarter change in basis points","base", apiResponse, mongoResponse},
                {"FC_2Y_QQ_PERCENT_CDS","2Y CDS q/q (%)","2Y CDS q/q (%)","2Y CDS Quarter on Quarter percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_QQ_PERCENT_CDS_PD","2Y CDS q/q (%) PD","2Y CDS q/q (%) PD","2Y CDS Probability of Default Quarter on Quarter change percent change","base", apiResponse, mongoResponse},
                {"FC_2Y_SS_BPS_CDS","2Y CDS hy/hy (bps)","2Y CDS hy/hy (bps)","2Y CDS Quarter on Half-Year change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_SS_BPS_CDS_PD","2Y CDS hy/hy (bps) PD","2Y CDS hy/hy (bps) PD","2Y CDS Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_2Y_SS_PERCENT_CDS","2Y CDS hy/hy (%)","2Y CDS hy/hy (%)","2Y CDS Quarter on Half-Year percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_SS_PERCENT_CDS_PD","2Y CDS hy/hy (%) PD","2Y CDS hy/hy (%) PD","2Y CDS Probability of Default Half-Year on Half-Year change percent change","base", apiResponse, mongoResponse},
                {"FC_2Y_WW_BPS_CDS","2Y CDS w/w (bps)","2Y CDS w/w (bps)","2Y CDS Week on Week change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_WW_BPS_CDS_PD","2Y CDS w/w (bps) PD","2Y CDS w/w (bps) PD","2Y CDS Probability of Default Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_2Y_WW_PERCENT_CDS","2Y CDS w/w (%)","2Y CDS w/w (%)","2Y CDS Week on Week percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_WW_PERCENT_CDS_PD","2Y CDS w/w (%) PD","2Y CDS w/w (%) PD","2Y CDS Probability of Default Week on Week change percent change","base", apiResponse, mongoResponse},
                {"FC_2Y_YY_BPS_CDS","2Y CDS y/y (bps)","2Y CDS y/y (bps)","2Y CDS Year on Year change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_YY_BPS_CDS_PD","2Y CDS y/y (bps) PD","2Y CDS y/y (bps) PD","2Y CDS Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_2Y_YY_PERCENT_CDS","2Y CDS y/y (%)","2Y CDS y/y (%)","2Y CDS Year on Year percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_2Y_YY_PERCENT_CDS_PD","2Y CDS y/y (%) PD","2Y CDS y/y (%) PD","2Y CDS Probability of Default Year on Year change percent change","base", apiResponse, mongoResponse},
                {"FC_4Y_BPS_CDS","4Y CDS (bps)","4Y CDS (bps)","4Y CDS in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_BPS_CDS_PD","4Y CDS (bps) PD","4Y CDS (bps) PD","4Y CDS Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_4Y_DD_BPS_CDS","4Y CDS d/d (bps)","4Y CDS d/d (bps)","4Y CDS Day on Day change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_DD_BPS_CDS_PD","4Y CDS d/d (bps) PD","4Y CDS d/d (bps) PD","4Y CDS Probability of Default Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_4Y_DD_PERCENT_CDS","4Y CDS d/d (%)","4Y CDS d/d (%)","4Y CDS Day on Day percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_DD_PERCENT_CDS_PD","4Y CDS d/d (%) PD","4Y CDS d/d (%) PD","4Y CDS Probability of Default Day on Day change percent change","base", apiResponse, mongoResponse},
                {"FC_4Y_MM_BPS_CDS","4Y CDS m/m (bps)","4Y CDS m/m (bps)","4Y CDS Month on Month change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_MM_BPS_CDS_PD","4Y CDS m/m (bps) PD","4Y CDS m/m (bps) PD","4Y CDS Probability of Default Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_4Y_MM_PERCENT_CDS","4Y CDS m/m (%)","4Y CDS m/m (%)","4Y CDS Month on Month percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_MM_PERCENT_CDS_PD","4Y CDS m/m (%) PD","4Y CDS m/m (%) PD","4Y CDS Probability of Default Month on Month change percent change","base", apiResponse, mongoResponse},
                {"FC_4Y_QQ_BPS_CDS","4Y CDS q/q (bps)","4Y CDS q/q (bps)","4Y CDS Quarter on Quarter change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_QQ_BPS_CDS_PD","4Y CDS q/q (bps) PD","4Y CDS q/q (bps) PD","4Y CDS Probability of Default Quarter on Quarter change in basis points","base", apiResponse, mongoResponse},
                {"FC_4Y_QQ_PERCENT_CDS","4Y CDS q/q (%)","4Y CDS q/q (%)","4Y CDS Quarter on Quarter percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_QQ_PERCENT_CDS_PD","4Y CDS q/q (%) PD","4Y CDS q/q (%) PD","4Y CDS Probability of Default Quarter on Quarter change percent change","base", apiResponse, mongoResponse},
                {"FC_4Y_SS_BPS_CDS","4Y CDS hy/hy (bps)","4Y CDS hy/hy (bps)","4Y CDS Quarter on Half-Year change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_SS_BPS_CDS_PD","4Y CDS hy/hy (bps) PD","4Y CDS hy/hy (bps) PD","4Y CDS Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_4Y_SS_PERCENT_CDS","4Y CDS hy/hy (%)","4Y CDS hy/hy (%)","4Y CDS Quarter on Half-Year percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_SS_PERCENT_CDS_PD","4Y CDS hy/hy (%) PD","4Y CDS hy/hy (%) PD","4Y CDS Probability of Default Half-Year on Half-Year change percent change","base", apiResponse, mongoResponse},
                {"FC_4Y_WW_BPS_CDS","4Y CDS w/w (bps)","4Y CDS w/w (bps)","4Y CDS Week on Week change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_WW_BPS_CDS_PD","4Y CDS w/w (bps) PD","4Y CDS w/w (bps) PD","4Y CDS Probability of Default Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_4Y_WW_PERCENT_CDS","4Y CDS w/w (%)","4Y CDS w/w (%)","4Y CDS Week on Week percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_WW_PERCENT_CDS_PD","4Y CDS w/w (%) PD","4Y CDS w/w (%) PD","4Y CDS Probability of Default Week on Week change percent change","base", apiResponse, mongoResponse},
                {"FC_4Y_YY_BPS_CDS","4Y CDS y/y (bps)","4Y CDS y/y (bps)","4Y CDS Year on Year change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_YY_BPS_CDS_PD","4Y CDS y/y (bps) PD","4Y CDS y/y (bps) PD","4Y CDS Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_4Y_YY_PERCENT_CDS","4Y CDS y/y (%)","4Y CDS y/y (%)","4Y CDS Year on Year percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_4Y_YY_PERCENT_CDS_PD","4Y CDS y/y (%) PD","4Y CDS y/y (%) PD","4Y CDS Probability of Default Year on Year change percent change","base", apiResponse, mongoResponse},
                {"FC_6M_BPS_CDS","6M CDS (bps) ","6M CDS (bps)","6M CDS in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_DD_BPS_CDS","6M CDS d/d (bps)","6M CDS d/d (bps)","6M CDS Day on Day change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_DD_PERCENT_CDS","6M CDS d/d (%)","6M CDS d/d (%)","6M CDS Day on Day percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_MM_BPS_CDS","6M CDS m/m (bps)","6M CDS m/m (bps)","6M CDS Month on Month change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_MM_PERCENT_CDS","6M CDS m/m (%)","6M CDS m/m (%)","6M CDS Month on Month percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_QQ_BPS_CDS","6M CDS q/q (bps)","6M CDS q/q (bps)","6M CDS Quarter on Quarter change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_QQ_PERCENT_CDS","6M CDS q/q (%)","6M CDS q/q (%)","6M CDS Quarter on Quarter percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_SS_BPS_CDS","6M CDS hy/hy (bps)","6M CDS hy/hy (bps)","6M CDS Quarter on Half-Year change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_SS_PERCENT_CDS","6M CDS hy/hy (%)","6M CDS hy/hy (%)","6M CDS Quarter on Half-Year percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_WW_BPS_CDS","6M CDS w/w (bps)","6M CDS w/w (bps)","6M CDS Week on Week change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_WW_PERCENT_CDS","6M CDS w/w (%)","6M CDS w/w (%)","6M CDS Week on Week percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_YY_BPS_CDS","6M CDS y/y (bps)","6M CDS y/y (bps)","6M CDS Year on Year change in basis points","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_6M_YY_PERCENT_CDS","6M CDS y/y (%)","6M CDS y/y (%)","6M CDS Year on Year percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_2Y_BPS_CDS","2Y CDS Risk Benchmark (bps)","2Y CDS Risk Benchmark (bps)","2Y CDS Risk Benchmark in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_4Y_BPS_CDS","4Y CDS Risk Benchmark (bps)","4Y CDS Risk Benchmark (bps) PD","4Y CDS Risk Benchmark in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_6M_BPS_CDS","6M CDS Risk Benchmark (bps)","6M CDS Risk Benchmark (bps)","6M CDS Risk Benchmark in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_BPS_CDS","2Y CDS Risk Benchmark (bps) PD","2Y CDS Risk Benchmark (bps) PD","2Y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_DD_BPS_CDS","2Y CDS Risk Benchmark d/d (bps) PD","2Y CDS Risk Benchmark d/d (bps) PD","2Y CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_DD_PERCENT_CDS","2Y CDS Risk Benchmark d/d (%) PD","2Y CDS Risk Benchmark d/d (%) PD","2Y CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_MM_BPS_CDS","2Y CDS Risk Benchmark m/m (bps) PD","2Y CDS Risk Benchmark m/m (bps) PD","2Y CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_MM_PERCENT_CDS","2Y CDS Risk Benchmark m/m (%) PD","2Y CDS Risk Benchmark m/m (%) PD","2Y CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_QQ_BPS_CDS","2Y CDS Risk Benchmark q/q (bps) PD","2Y CDS Risk Benchmark q/q (bps) PD","2Y CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_QQ_PERCENT_CDS","2Y CDS Risk Benchmark q/q (%) PD","2Y CDS Risk Benchmark q/q (%) PD","2Y CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_SS_BPS_CDS","2Y CDS Risk Benchmark hy/hy (bps) PD","2Y CDS Risk Benchmark hy/hy (bps) PD","2Y CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_SS_PERCENT_CDS","2Y CDS Risk Benchmark hy/hy (%) PD","2Y CDS Risk Benchmark hy/hy (%) PD","2Y CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_WW_BPS_CDS","2Y CDS Risk Benchmark w/w (bps) PD","2Y CDS Risk Benchmark w/w (bps) PD","2Y CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_WW_PERCENT_CDS","2Y CDS Risk Benchmark w/w (%) PD","2Y CDS Risk Benchmark w/w (%) PD","2Y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_YY_BPS_CDS","2Y CDS Risk Benchmark y/y (bps) PD","2Y CDS Risk Benchmark y/y (bps) PD","2Y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_YY_PERCENT_CDS","2Y CDS Risk Benchmark y/y (%) PD","2Y CDS Risk Benchmark y/y (%) PD","2Y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_BPS_CDS","4Y CDS Risk Benchmark (bps) PD","4Y CDS Risk Benchmark (bps)","4Y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_DD_BPS_CDS","4Y CDS Risk Benchmark d/d (bps) PD","4Y CDS Risk Benchmark d/d (bps)","4Y CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_DD_PERCENT_CDS","4Y CDS Risk Benchmark d/d (%) PD","4Y CDS Risk Benchmark d/d (%)","4Y CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_MM_BPS_CDS","4Y CDS Risk Benchmark m/m (bps) PD","4Y CDS Risk Benchmark m/m (bps)","4Y CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_MM_PERCENT_CDS","4Y CDS Risk Benchmark m/m (%) PD","4Y CDS Risk Benchmark m/m (%)","4Y CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_QQ_BPS_CDS","4Y CDS Risk Benchmark q/q (bps) PD","4Y CDS Risk Benchmark q/q (bps)","4Y CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_QQ_PERCENT_CDS","4Y CDS Risk Benchmark q/q (%) PD","4Y CDS Risk Benchmark q/q (%)","4Y CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_SS_BPS_CDS","4Y CDS Risk Benchmark hy/hy (bps) PD","4Y CDS Risk Benchmark hy/hy (bps)","4Y CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_SS_PERCENT_CDS","4Y CDS Risk Benchmark hy/hy (%) PD","4Y CDS Risk Benchmark hy/hy (%)","4Y CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_WW_BPS_CDS","4Y CDS Risk Benchmark w/w (bps) PD","4Y CDS Risk Benchmark w/w (bps)","4Y CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_WW_PERCENT_CDS","4Y CDS Risk Benchmark w/w (%) PD","4Y CDS Risk Benchmark w/w (%)","4Y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_YY_BPS_CDS","4Y CDS Risk Benchmark y/y (bps) PD","4Y CDS Risk Benchmark y/y (bps)","4Y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_YY_PERCENT_CDS","4Y CDS Risk Benchmark y/y (%) PD","4Y CDS Risk Benchmark y/y (%)","4Y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_DD_BPS_CDS","2Y CDS Risk Benchmark d/d (bps)","2Y CDS Risk Benchmark d/d (bps)","2Y CDS Risk Benchmar Spread Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_DD_PERCENT_CDS","2Y CDS Risk Benchmark d/d (%)","2Y CDS Risk Benchmark d/d (%)","2Y CDS Risk Benchmark Spread Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_MM_BPS_CDS","2Y CDS Risk Benchmark m/m (bps)","2Y CDS Risk Benchmark m/m (bps)","2Y CDS Risk Benchmark Spread Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_MM_PERCENT_CDS","2Y CDS Risk Benchmark m/m (%)","2Y CDS Risk Benchmark m/m (%)","2Y CDS Risk Benchmark Spread Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_QQ_BPS_CDS","2Y CDS Risk Benchmark q/q (bps)","2Y CDS Risk Benchmark q/q (bps)","2Y CDS Risk Benchmark Spread Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_QQ_PERCENT_CDS","2Y CDS Risk Benchmark q/q (%)","2Y CDS Risk Benchmark q/q (%)","2Y CDS Risk Benchmark Spread Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_SS_BPS_CDS","2Y CDS Risk Benchmark hy/hy (bps)","2Y CDS Risk Benchmark hy/hy (bps)","2Y CDS Risk Benchmark Spread Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_SS_PERCENT_CDS","2Y CDS Risk Benchmark hy/hy (%)","2Y CDS Risk Benchmark hy/hy (%)","2Y CDS Risk Benchmark Spread Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_WW_BPS_CDS","2Y CDS Risk Benchmark w/w (bps)","2Y CDS Risk Benchmark w/w (bps)","2Y CDS Risk Benchmark Spread Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_WW_PERCENT_CDS","2Y CDS Risk Benchmark w/w (%)","2Y CDS Risk Benchmark w/w (%)","2Y CDS Risk Benchmark Spread Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_YY_BPS_CDS","2Y CDS Risk Benchmark y/y (bps)","2Y CDS Risk Benchmark y/y (bps)","2Y CDS Risk Benchmark Spread Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_YY_PERCENT_CDS","2Y CDS Risk Benchmark y/y (%)","2Y CDS Risk Benchmark y/y (%)","2Y CDS Risk Benchmark Spread Year on Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_DD_BPS_CDS","4Y CDS Risk Benchmark d/d (bps)","4Y CDS Risk Benchmark d/d (bps)","4Y CDS Risk Benchmar Spread Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_DD_PERCENT_CDS","4Y CDS Risk Benchmark d/d (%)","4Y CDS Risk Benchmark d/d (%)","4Y CDS Risk Benchmark Spread Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_MM_BPS_CDS","4Y CDS Risk Benchmark m/m (bps)","4Y CDS Risk Benchmark m/m (bps)","4Y CDS Risk Benchmark Spread Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_MM_PERCENT_CDS","4Y CDS Risk Benchmark m/m (%)","4Y CDS Risk Benchmark m/m (%)","4Y CDS Risk Benchmark Spread Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_QQ_BPS_CDS","4Y CDS Risk Benchmark q/q (bps)","4Y CDS Risk Benchmark q/q (bps)","4Y CDS Risk Benchmark Spread Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_QQ_PERCENT_CDS","4Y CDS Risk Benchmark q/q (%)","4Y CDS Risk Benchmark q/q (%)","4Y CDS Risk Benchmark Spread Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_SS_BPS_CDS","4Y CDS Risk Benchmark hy/hy (bps)","4Y CDS Risk Benchmark hy/hy (bps)","4Y CDS Risk Benchmark Spread Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_SS_PERCENT_CDS","4Y CDS Risk Benchmark hy/hy (%)","4Y CDS Risk Benchmark hy/hy (%)","4Y CDS Risk Benchmark Spread Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_WW_BPS_CDS","4Y CDS Risk Benchmark w/w (bps)","4Y CDS Risk Benchmark w/w (bps)","4Y CDS Risk Benchmark Spread Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_WW_PERCENT_CDS","4Y CDS Risk Benchmark w/w (%)","4Y CDS Risk Benchmark w/w (%)","4Y CDS Risk Benchmark Spread Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_YY_BPS_CDS","4Y CDS Risk Benchmark y/y (bps)","4Y CDS Risk Benchmark y/y (bps)","4Y CDS Risk Benchmark Spread Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_YY_PERCENT_CDS","4Y CDS Risk Benchmark y/y (%)","4Y CDS Risk Benchmark y/y (%)","4Y CDS Risk Benchmark Spread Year on Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_DD_BPS_CDS","6M CDS Risk Benchmark d/d (bps)","6M CDS Risk Benchmark d/d (bps)","6M CDS Risk Benchmar Spread Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_DD_PERCENT_CDS","6M CDS Risk Benchmark d/d (%)","6M CDS Risk Benchmark d/d (%)","6M CDS Risk Benchmark Spread Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_MM_BPS_CDS","6M CDS Risk Benchmark m/m (bps)","6M CDS Risk Benchmark m/m (bps)","6M CDS Risk Benchmark Spread Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_MM_PERCENT_CDS","6M CDS Risk Benchmark m/m (%)","6M CDS Risk Benchmark m/m (%)","6M CDS Risk Benchmark Spread Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_QQ_BPS_CDS","6M CDS Risk Benchmark q/q (bps)","6M CDS Risk Benchmark q/q (bps)","6M CDS Risk Benchmark Spread Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_QQ_PERCENT_CDS","6M CDS Risk Benchmark q/q (%)","6M CDS Risk Benchmark q/q (%)","6M CDS Risk Benchmark Spread Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_SS_BPS_CDS","6M CDS Risk Benchmark hy/hy (bps)","6M CDS Risk Benchmark hy/hy (bps)","6M CDS Risk Benchmark Spread Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_SS_PERCENT_CDS","6M CDS Risk Benchmark hy/hy (%)","6M CDS Risk Benchmark hy/hy (%)","6M CDS Risk Benchmark Spread Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_WW_BPS_CDS","6M CDS Risk Benchmark w/w (bps)","6M CDS Risk Benchmark w/w (bps)","6M CDS Risk Benchmark Spread Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_WW_PERCENT_CDS","6M CDS Risk Benchmark w/w (%)","6M CDS Risk Benchmark w/w (%)","6M CDS Risk Benchmark Spread Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_YY_BPS_CDS","6M CDS Risk Benchmark y/y (bps)","6M CDS Risk Benchmark y/y (bps)","6M CDS Risk Benchmark Spread Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_YY_PERCENT_CDS","6M CDS Risk Benchmark y/y (%)","6M CDS Risk Benchmark y/y (%)","6M CDS Risk Benchmark Spread Year on Year percentage change","base", apiResponse, mongoResponse},
                //changes
                {"FC_1Y_SS_PERCENT_CDS","1y CDS hy/hy (bps) %","1y CDS hy/hy (%)","1y CDS Quarter on Half-Year percentage change","creditDefaultSwaps", apiResponse, mongoResponse},
                {"FC_IMPLIED_CREDIT_SCORE_YY_CDS_SPOT","CDS ICS y/y Spot","CDS ICS y/y Spot","CDS Implied Credit Score Year on Year Spot","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_10Y_BPS_CDS","10y CDS Risk Benchmark (bps) PD","10y CDS Risk Benchmark (bps) PD","10y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_10Y_WW_PERCENT_CDS","10y CDS Risk Benchmark w/w (%) PD","10y CDS Risk Benchmark w/w (%) PD","10y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_10Y_YY_BPS_CDS","10y CDS Risk Benchmark y/y (bps) PD","10y CDS Risk Benchmark y/y (bps) PD","10y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_10Y_YY_PERCENT_CDS","10y CDS Risk Benchmark y/y (%) PD","10y CDS Risk Benchmark y/y (%) PD","10y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_BPS_CDS","1y CDS Risk Benchmark (bps) PD","1y CDS Risk Benchmark (bps) PD","1y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_DD_BPS_CDS","1y CDS Risk Benchmark d/d (bps) PD","1y CDS Risk Benchmark d/d (bps) PD","1y CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_DD_PERCENT_CDS","1y CDS Risk Benchmark d/d (%) PD","1y CDS Risk Benchmark d/d (%) PD","1y CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_MM_BPS_CDS","1y CDS Risk Benchmark m/m (bps) PD","1y CDS Risk Benchmark m/m (bps) PD","1y CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_MM_PERCENT_CDS","1y CDS Risk Benchmark m/m (%) PD","1y CDS Risk Benchmark m/m (%) PD","1y CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_QQ_BPS_CDS","1y CDS Risk Benchmark q/q (bps) PD","1y CDS Risk Benchmark q/q (bps) PD","1y CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_QQ_PERCENT_CDS","1y CDS Risk Benchmark q/q (%) PD","1y CDS Risk Benchmark q/q (%) PD","1y CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_SS_BPS_CDS","1y CDS Risk Benchmark hy/hy (bps) PD","1y CDS Risk Benchmark hy/hy (bps) PD","1y CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_SS_PERCENT_CDS","1y CDS Risk Benchmark hy/hy (%) PD","1y CDS Risk Benchmark hy/hy (%) PD","1y CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_WW_BPS_CDS","1y CDS Risk Benchmark w/w (bps) PD","1y CDS Risk Benchmark w/w (bps) PD","1y CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_WW_PERCENT_CDS","1y CDS Risk Benchmark w/w (%) PD","1y CDS Risk Benchmark w/w (%) PD","1y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_YY_BPS_CDS","1y CDS Risk Benchmark y/y (bps) PD","1y CDS Risk Benchmark y/y (bps) PD","1y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_1Y_YY_PERCENT_CDS","1y CDS Risk Benchmark y/y (%) PD","1y CDS Risk Benchmark y/y (%) PD","1y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_BPS_CDS","3y CDS Risk Benchmark (bps) PD","3y CDS Risk Benchmark (bps) PD","3y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_DD_BPS_CDS","3y CDS Risk Benchmark d/d (bps) PD","3y CDS Risk Benchmark d/d (bps) PD","3y CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_DD_PERCENT_CDS","3y CDS Risk Benchmark d/d (%) PD","3y CDS Risk Benchmark d/d (%) PD","3y CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_MM_BPS_CDS","3y CDS Risk Benchmark m/m (bps) PD","3y CDS Risk Benchmark m/m (bps) PD","3y CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_MM_PERCENT_CDS","3y CDS Risk Benchmark m/m (%) PD","3y CDS Risk Benchmark m/m (%) PD","3y CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_QQ_BPS_CDS","3y CDS Risk Benchmark q/q (bps) PD","3y CDS Risk Benchmark q/q (bps) PD","3y CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_QQ_PERCENT_CDS","3y CDS Risk Benchmark q/q (%) PD","3y CDS Risk Benchmark q/q (%) PD","3y CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_SS_BPS_CDS","3y CDS Risk Benchmark hy/hy (bps) PD","3y CDS Risk Benchmark hy/hy (bps) PD","3y CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_SS_PERCENT_CDS","3y CDS Risk Benchmark hy/hy (%) PD","3y CDS Risk Benchmark hy/hy (%) PD","3y CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_WW_BPS_CDS","3y CDS Risk Benchmark w/w (bps) PD","3y CDS Risk Benchmark w/w (bps) PD","3y CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_WW_PERCENT_CDS","3y CDS Risk Benchmark w/w (%) PD","3y CDS Risk Benchmark w/w (%) PD","3y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_YY_BPS_CDS","3y CDS Risk Benchmark y/y (bps) PD","3y CDS Risk Benchmark y/y (bps) PD","3y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_3Y_YY_PERCENT_CDS","3y CDS Risk Benchmark y/y (%) PD","3y CDS Risk Benchmark y/y (%) PD","3y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_BPS_CDS","5y CDS Risk Benchmark (bps) PD","5y CDS Risk Benchmark (bps) PD","5y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_DD_BPS_CDS","5y CDS Risk Benchmark d/d (bps) PD","5y CDS Risk Benchmark d/d (bps) PD","5y CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_DD_PERCENT_CDS","5y CDS Risk Benchmark d/d (%) PD","5y CDS Risk Benchmark d/d (%) PD","5y CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_MM_BPS_CDS","5y CDS Risk Benchmark m/m (bps) PD","5y CDS Risk Benchmark m/m (bps) PD","5y CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_MM_PERCENT_CDS","5y CDS Risk Benchmark m/m (%) PD","5y CDS Risk Benchmark m/m (%) PD","5y CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_QQ_BPS_CDS","5y CDS Risk Benchmark q/q (bps) PD","5y CDS Risk Benchmark q/q (bps) PD","5y CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_QQ_PERCENT_CDS","5y CDS Risk Benchmark q/q (%) PD","5y CDS Risk Benchmark q/q (%) PD","5y CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_SS_BPS_CDS","5y CDS Risk Benchmark hy/hy (bps) PD","5y CDS Risk Benchmark hy/hy (bps) PD","5y CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_SS_PERCENT_CDS","5y CDS Risk Benchmark hy/hy (%) PD","5y CDS Risk Benchmark hy/hy (%) PD","5y CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_WW_BPS_CDS","5y CDS Risk Benchmark w/w (bps) PD","5y CDS Risk Benchmark w/w (bps) PD","5y CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_WW_PERCENT_CDS","5y CDS Risk Benchmark w/w (%) PD","5y CDS Risk Benchmark w/w (%) PD","5y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_YY_BPS_CDS","5y CDS Risk Benchmark y/y (bps) PD","5y CDS Risk Benchmark y/y (bps) PD","5y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_5Y_YY_PERCENT_CDS","5y CDS Risk Benchmark y/y (%) PD","5y CDS Risk Benchmark y/y (%) PD","5y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_BPS_CDS","7y CDS Risk Benchmark (bps) PD","7y CDS Risk Benchmark (bps) PD","7y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_DD_BPS_CDS","7y CDS Risk Benchmark d/d (bps) PD","7y CDS Risk Benchmark d/d (bps) PD","7y CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_DD_PERCENT_CDS","7y CDS Risk Benchmark d/d (%) PD","7y CDS Risk Benchmark d/d (%) PD","7y CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_MM_BPS_CDS","7y CDS Risk Benchmark m/m (bps) PD","7y CDS Risk Benchmark m/m (bps) PD","7y CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_MM_PERCENT_CDS","7y CDS Risk Benchmark m/m (%) PD","7y CDS Risk Benchmark m/m (%) PD","7y CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_QQ_BPS_CDS","7y CDS Risk Benchmark q/q (bps) PD","7y CDS Risk Benchmark q/q (bps) PD","7y CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_QQ_PERCENT_CDS","7y CDS Risk Benchmark q/q (%) PD","7y CDS Risk Benchmark q/q (%) PD","7y CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_SS_BPS_CDS","7y CDS Risk Benchmark hy/hy (bps) PD","7y CDS Risk Benchmark hy/hy (bps) PD","7y CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_SS_PERCENT_CDS","7y CDS Risk Benchmark hy/hy (%) PD","7y CDS Risk Benchmark hy/hy (%) PD","7y CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_WW_BPS_CDS","7y CDS Risk Benchmark w/w (bps) PD","7y CDS Risk Benchmark w/w (bps) PD","7y CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_WW_PERCENT_CDS","7y CDS Risk Benchmark w/w (%) PD","7y CDS Risk Benchmark w/w (%) PD","7y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_YY_BPS_CDS","7y CDS Risk Benchmark y/y (bps) PD","7y CDS Risk Benchmark y/y (bps) PD","7y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse, mongoResponse},
                {"FC_RISK_BENCHMARK_PD_7Y_YY_PERCENT_CDS","7y CDS Risk Benchmark y/y (%) PD","7y CDS Risk Benchmark y/y (%) PD","7y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse, mongoResponse},
        };
    }

    @Test(dataProvider = "Fisc6354")
    public void Fisc6354_MONGO_cdsMetadataEnhacements(String fitchFieldId, String displayName, String fitchFieldDesc, String fieldDefinition, String permissionsRequired, String apiResponse, String mongoResponse){
        try {
            Assert.assertTrue(mongoResponse.contains("\"fitchFieldId\": \"" + fitchFieldId + "\""));
            Assert.assertTrue(mongoResponse.contains("\"displayName\": \"" + displayName + "\""));
            Assert.assertTrue(mongoResponse.contains("\"fieldDefinition\": \"" + fieldDefinition + "\""));
            Assert.assertEquals(StringUtils.countMatches(mongoResponse, "\"fitchFieldId\": \"" + fitchFieldId + "\""), 1);
            Assert.assertEquals(StringUtils.countMatches(mongoResponse, "\"displayName\": \"" + displayName + "\""), 1);
            Assert.assertEquals(StringUtils.countMatches(mongoResponse, "\"fieldDefinition\": \"" + fieldDefinition + "\""), 1);
            logger.info("FISC 6354 MONGO DATA PASSED! Tested FITCHFIELDID: " + fitchFieldId + " DISPLAYNAME: " + displayName + " FITCHFIELDDESC " + fitchFieldDesc + " FIELDDEFINITION " + fieldDefinition + " PERMISSION " + permissionsRequired);
        } catch (AssertionError err){
            logger.error("FISC 6354 MONGO DATA FAILED! Tested FITCHFIELDID: "  + fitchFieldId + " ERROR: " + err);
            Assert.fail();
        }
    }

    @Test(dataProvider = "Fisc6354")
    public void Fisc6354_API_cdsMetadataEnhacements(String fitchFieldId, String displayName, String fitchFieldDesc, String fieldDefinition, String permissionsRequired, String apiResponse, String mongoResponse){
        try {
            System.out.println(apiResponse);
            Assert.assertTrue(apiResponse.contains("\"id\":\"" + fitchFieldId + "\""));
            Assert.assertTrue(apiResponse.contains("\"displayName\":\"" + displayName + "\""));
            Assert.assertTrue(apiResponse.contains("\"fitchFieldDesc\":\"" + fitchFieldDesc + "\""));
            Assert.assertTrue(apiResponse.contains("\"fieldDefinition\":\"" + fieldDefinition + "\""));
            /*Assert.assertEquals(StringUtils.countMatches(apiResponse, "\"id\":\"" + fitchFieldId + "\""), 1);
            Assert.assertEquals(StringUtils.countMatches(apiResponse, "\"displayName\":\"" + displayName + "\""), 1);
            Assert.assertEquals(StringUtils.countMatches(apiResponse, "\"fitchFieldDesc\":\"" + fitchFieldDesc + "\""), 1);
            Assert.assertEquals(StringUtils.countMatches(apiResponse, "\"fieldDefinition\":\"" + fieldDefinition + "\""), 1);*/
            logger.info("FISC 6354 API RESOURCEFUL ENDPOINT DATA PASSED! Tested FITCHFIELDID: " + fitchFieldId + " DISPLAYNAME: " + displayName + " FITCHFIELDDESC " + fitchFieldDesc + " FIELDDEFINITION " + fieldDefinition + " PERMISSION " + permissionsRequired);
        } catch (AssertionError err){
            logger.error("FISC 6354 API RESOURCEFUL ENDPOINT DATA FAILED! Tested FITCHFIELDID: "  + fitchFieldId + " ERROR: " + err);
            Assert.fail();
        }
    }

    @DataProvider(name="Fisc6373")
    public Object[][] getDataFor6373() throws IOException {

        String apiResponse = apiUtils.postToDataAggregator("6373.json", AuthrztionValue, XappClintIDvalue, dataPostUrl).asString();
        return new Object[][] {
                {"FC_2Y_BPS_CDS","2Y CDS (bps)","2Y CDS (bps)","2Y CDS in basis points","creditDefaultSwaps", apiResponse},
                {"FC_2Y_BPS_CDS_PD","2Y CDS (bps) PD","2Y CDS (bps) PD","2Y CDS Probability of Default in basis points","base", apiResponse},
                {"FC_2Y_DD_BPS_CDS","2Y CDS d/d (bps)","2Y CDS d/d (bps)","2Y CDS Day on Day change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_2Y_DD_BPS_CDS_PD","2Y CDS d/d (bps) PD","2Y CDS d/d (bps) PD","2Y CDS Probability of Default Day on Day change in basis points","base", apiResponse},
                {"FC_2Y_DD_PERCENT_CDS","2Y CDS d/d (%)","2Y CDS d/d (%)","2Y CDS Day on Day percentage change","creditDefaultSwaps", apiResponse},
                {"FC_2Y_DD_PERCENT_CDS_PD","2Y CDS d/d (%) PD","2Y CDS d/d (%) PD","2Y CDS Probability of Default Day on Day change percent change","base", apiResponse},
                {"FC_2Y_MM_BPS_CDS","2Y CDS m/m (bps)","2Y CDS m/m (bps)","2Y CDS Month on Month change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_2Y_MM_BPS_CDS_PD","2Y CDS m/m (bps) PD","2Y CDS m/m (bps) PD","2Y CDS Probability of Default Month on Month change in basis points","base", apiResponse},
                {"FC_2Y_MM_PERCENT_CDS","2Y CDS m/m (%)","2Y CDS m/m (%)","2Y CDS Month on Month percentage change","creditDefaultSwaps", apiResponse},
                {"FC_2Y_MM_PERCENT_CDS_PD","2Y CDS m/m (%) PD","2Y CDS m/m (%) PD","2Y CDS Probability of Default Month on Month change percent change","base", apiResponse},
                {"FC_2Y_QQ_BPS_CDS","2Y CDS q/q (bps)","2Y CDS q/q (bps)","2Y CDS Quarter on Quarter change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_2Y_QQ_BPS_CDS_PD","2Y CDS q/q (bps) PD","2Y CDS q/q (bps) PD","2Y CDS Probability of Default Quarter on Quarter change in basis points","base", apiResponse},
                {"FC_2Y_QQ_PERCENT_CDS","2Y CDS q/q (%)","2Y CDS q/q (%)","2Y CDS Quarter on Quarter percentage change","creditDefaultSwaps", apiResponse},
                {"FC_2Y_QQ_PERCENT_CDS_PD","2Y CDS q/q (%) PD","2Y CDS q/q (%) PD","2Y CDS Probability of Default Quarter on Quarter change percent change","base", apiResponse},
                {"FC_2Y_SS_BPS_CDS","2Y CDS hy/hy (bps)","2Y CDS hy/hy (bps)","2Y CDS Quarter on Half-Year change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_2Y_SS_BPS_CDS_PD","2Y CDS hy/hy (bps) PD","2Y CDS hy/hy (bps) PD","2Y CDS Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse},
                {"FC_2Y_SS_PERCENT_CDS","2Y CDS hy/hy (%)","2Y CDS hy/hy (%)","2Y CDS Quarter on Half-Year percentage change","creditDefaultSwaps", apiResponse},
                {"FC_2Y_SS_PERCENT_CDS_PD","2Y CDS hy/hy (%) PD","2Y CDS hy/hy (%) PD","2Y CDS Probability of Default Half-Year on Half-Year change percent change","base", apiResponse},
                {"FC_2Y_WW_BPS_CDS","2Y CDS w/w (bps)","2Y CDS w/w (bps)","2Y CDS Week on Week change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_2Y_WW_BPS_CDS_PD","2Y CDS w/w (bps) PD","2Y CDS w/w (bps) PD","2Y CDS Probability of Default Week on Week change in basis points","base", apiResponse},
                {"FC_2Y_WW_PERCENT_CDS","2Y CDS w/w (%)","2Y CDS w/w (%)","2Y CDS Week on Week percentage change","creditDefaultSwaps", apiResponse},
                {"FC_2Y_WW_PERCENT_CDS_PD","2Y CDS w/w (%) PD","2Y CDS w/w (%) PD","2Y CDS Probability of Default Week on Week change percent change","base", apiResponse},
                {"FC_2Y_YY_BPS_CDS","2Y CDS y/y (bps)","2Y CDS y/y (bps)","2Y CDS Year on Year change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_2Y_YY_BPS_CDS_PD","2Y CDS y/y (bps) PD","2Y CDS y/y (bps) PD","2Y CDS Probability of Default Year on Year change in basis points","base", apiResponse},
                {"FC_2Y_YY_PERCENT_CDS","2Y CDS y/y (%)","2Y CDS y/y (%)","2Y CDS Year on Year percentage change","creditDefaultSwaps", apiResponse},
                {"FC_2Y_YY_PERCENT_CDS_PD","2Y CDS y/y (%) PD","2Y CDS y/y (%) PD","2Y CDS Probability of Default Year on Year change percent change","base", apiResponse},
                {"FC_4Y_BPS_CDS","4Y CDS (bps)","4Y CDS (bps)","4Y CDS in basis points","creditDefaultSwaps", apiResponse},
                {"FC_4Y_BPS_CDS_PD","4Y CDS (bps) PD","4Y CDS (bps) PD","4Y CDS Probability of Default in basis points","base", apiResponse},
                {"FC_4Y_DD_BPS_CDS","4Y CDS d/d (bps)","4Y CDS d/d (bps)","4Y CDS Day on Day change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_4Y_DD_BPS_CDS_PD","4Y CDS d/d (bps) PD","4Y CDS d/d (bps) PD","4Y CDS Probability of Default Day on Day change in basis points","base", apiResponse},
                {"FC_4Y_DD_PERCENT_CDS","4Y CDS d/d (%)","4Y CDS d/d (%)","4Y CDS Day on Day percentage change","creditDefaultSwaps", apiResponse},
                {"FC_4Y_DD_PERCENT_CDS_PD","4Y CDS d/d (%) PD","4Y CDS d/d (%) PD","4Y CDS Probability of Default Day on Day change percent change","base", apiResponse},
                {"FC_4Y_MM_BPS_CDS","4Y CDS m/m (bps)","4Y CDS m/m (bps)","4Y CDS Month on Month change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_4Y_MM_BPS_CDS_PD","4Y CDS m/m (bps) PD","4Y CDS m/m (bps) PD","4Y CDS Probability of Default Month on Month change in basis points","base", apiResponse},
                {"FC_4Y_MM_PERCENT_CDS","4Y CDS m/m (%)","4Y CDS m/m (%)","4Y CDS Month on Month percentage change","creditDefaultSwaps", apiResponse},
                {"FC_4Y_MM_PERCENT_CDS_PD","4Y CDS m/m (%) PD","4Y CDS m/m (%) PD","4Y CDS Probability of Default Month on Month change percent change","base", apiResponse},
                {"FC_4Y_QQ_BPS_CDS","4Y CDS q/q (bps)","4Y CDS q/q (bps)","4Y CDS Quarter on Quarter change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_4Y_QQ_BPS_CDS_PD","4Y CDS q/q (bps) PD","4Y CDS q/q (bps) PD","4Y CDS Probability of Default Quarter on Quarter change in basis points","base", apiResponse},
                {"FC_4Y_QQ_PERCENT_CDS","4Y CDS q/q (%)","4Y CDS q/q (%)","4Y CDS Quarter on Quarter percentage change","creditDefaultSwaps", apiResponse},
                {"FC_4Y_QQ_PERCENT_CDS_PD","4Y CDS q/q (%) PD","4Y CDS q/q (%) PD","4Y CDS Probability of Default Quarter on Quarter change percent change","base", apiResponse},
                {"FC_4Y_SS_BPS_CDS","4Y CDS hy/hy (bps)","4Y CDS hy/hy (bps)","4Y CDS Quarter on Half-Year change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_4Y_SS_BPS_CDS_PD","4Y CDS hy/hy (bps) PD","4Y CDS hy/hy (bps) PD","4Y CDS Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse},
                {"FC_4Y_SS_PERCENT_CDS","4Y CDS hy/hy (%)","4Y CDS hy/hy (%)","4Y CDS Quarter on Half-Year percentage change","creditDefaultSwaps", apiResponse},
                {"FC_4Y_SS_PERCENT_CDS_PD","4Y CDS hy/hy (%) PD","4Y CDS hy/hy (%) PD","4Y CDS Probability of Default Half-Year on Half-Year change percent change","base", apiResponse},
                {"FC_4Y_WW_BPS_CDS","4Y CDS w/w (bps)","4Y CDS w/w (bps)","4Y CDS Week on Week change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_4Y_WW_BPS_CDS_PD","4Y CDS w/w (bps) PD","4Y CDS w/w (bps) PD","4Y CDS Probability of Default Week on Week change in basis points","base", apiResponse},
                {"FC_4Y_WW_PERCENT_CDS","4Y CDS w/w (%)","4Y CDS w/w (%)","4Y CDS Week on Week percentage change","creditDefaultSwaps", apiResponse},
                {"FC_4Y_WW_PERCENT_CDS_PD","4Y CDS w/w (%) PD","4Y CDS w/w (%) PD","4Y CDS Probability of Default Week on Week change percent change","base", apiResponse},
                {"FC_4Y_YY_BPS_CDS","4Y CDS y/y (bps)","4Y CDS y/y (bps)","4Y CDS Year on Year change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_4Y_YY_BPS_CDS_PD","4Y CDS y/y (bps) PD","4Y CDS y/y (bps) PD","4Y CDS Probability of Default Year on Year change in basis points","base", apiResponse},
                {"FC_4Y_YY_PERCENT_CDS","4Y CDS y/y (%)","4Y CDS y/y (%)","4Y CDS Year on Year percentage change","creditDefaultSwaps", apiResponse},
                {"FC_4Y_YY_PERCENT_CDS_PD","4Y CDS y/y (%) PD","4Y CDS y/y (%) PD","4Y CDS Probability of Default Year on Year change percent change","base", apiResponse},
                {"FC_6M_BPS_CDS","6M CDS (bps) ","6M CDS (bps)","6M CDS in basis points","creditDefaultSwaps", apiResponse},
                {"FC_6M_DD_BPS_CDS","6M CDS d/d (bps)","6M CDS d/d (bps)","6M CDS Day on Day change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_6M_DD_PERCENT_CDS","6M CDS d/d (%)","6M CDS d/d (%)","6M CDS Day on Day percentage change","creditDefaultSwaps", apiResponse},
                {"FC_6M_MM_BPS_CDS","6M CDS m/m (bps)","6M CDS m/m (bps)","6M CDS Month on Month change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_6M_MM_PERCENT_CDS","6M CDS m/m (%)","6M CDS m/m (%)","6M CDS Month on Month percentage change","creditDefaultSwaps", apiResponse},
                {"FC_6M_QQ_BPS_CDS","6M CDS q/q (bps)","6M CDS q/q (bps)","6M CDS Quarter on Quarter change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_6M_QQ_PERCENT_CDS","6M CDS q/q (%)","6M CDS q/q (%)","6M CDS Quarter on Quarter percentage change","creditDefaultSwaps", apiResponse},
                {"FC_6M_SS_BPS_CDS","6M CDS hy/hy (bps)","6M CDS hy/hy (bps)","6M CDS Quarter on Half-Year change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_6M_SS_PERCENT_CDS","6M CDS hy/hy (%)","6M CDS hy/hy (%)","6M CDS Quarter on Half-Year percentage change","creditDefaultSwaps", apiResponse},
                {"FC_6M_WW_BPS_CDS","6M CDS w/w (bps)","6M CDS w/w (bps)","6M CDS Week on Week change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_6M_WW_PERCENT_CDS","6M CDS w/w (%)","6M CDS w/w (%)","6M CDS Week on Week percentage change","creditDefaultSwaps", apiResponse},
                {"FC_6M_YY_BPS_CDS","6M CDS y/y (bps)","6M CDS y/y (bps)","6M CDS Year on Year change in basis points","creditDefaultSwaps", apiResponse},
                {"FC_6M_YY_PERCENT_CDS","6M CDS y/y (%)","6M CDS y/y (%)","6M CDS Year on Year percentage change","creditDefaultSwaps", apiResponse},
                {"FC_RISK_BENCHMARK_2Y_BPS_CDS","2Y CDS Risk Benchmark (bps)","2Y CDS Risk Benchmark (bps)","2Y CDS Risk Benchmark in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_4Y_BPS_CDS","4Y CDS Risk Benchmark (bps)","4Y CDS Risk Benchmark (bps)","4Y CDS Risk Benchmark in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_6M_BPS_CDS","6M CDS Risk Benchmark (bps)","6M CDS Risk Benchmark (bps)","6M CDS Risk Benchmark in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_BPS_CDS","2Y CDS Risk Benchmark (bps) PD","2Y CDS Risk Benchmark (bps) PD","2Y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_DD_BPS_CDS","2Y CDS Risk Benchmark d/d (bps) PD","2Y CDS Risk Benchmark d/d (bps) PD","2Y CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_DD_PERCENT_CDS","2Y CDS Risk Benchmark d/d (%) PD","2Y CDS Risk Benchmark d/d (%) PD","2Y CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_MM_BPS_CDS","2Y CDS Risk Benchmark m/m (bps) PD","2Y CDS Risk Benchmark m/m (bps) PD","2Y CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_MM_PERCENT_CDS","2Y CDS Risk Benchmark m/m (%) PD","2Y CDS Risk Benchmark m/m (%) PD","2Y CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_QQ_BPS_CDS","2Y CDS Risk Benchmark q/q (bps) PD","2Y CDS Risk Benchmark q/q (bps) PD","2Y CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_QQ_PERCENT_CDS","2Y CDS Risk Benchmark q/q (%) PD","2Y CDS Risk Benchmark q/q (%) PD","2Y CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_SS_BPS_CDS","2Y CDS Risk Benchmark hy/hy (bps) PD","2Y CDS Risk Benchmark hy/hy (bps) PD","2Y CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_SS_PERCENT_CDS","2Y CDS Risk Benchmark hy/hy (%) PD","2Y CDS Risk Benchmark hy/hy (%) PD","2Y CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_WW_BPS_CDS","2Y CDS Risk Benchmark w/w (bps) PD","2Y CDS Risk Benchmark w/w (bps) PD","2Y CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_WW_PERCENT_CDS","2Y CDS Risk Benchmark w/w (%) PD","2Y CDS Risk Benchmark w/w (%) PD","2Y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_YY_BPS_CDS","2Y CDS Risk Benchmark y/y (bps) PD","2Y CDS Risk Benchmark y/y (bps) PD","2Y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_2Y_YY_PERCENT_CDS","2Y CDS Risk Benchmark y/y (%) PD","2Y CDS Risk Benchmark y/y (%) PD","2Y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_BPS_CDS","4Y CDS Risk Benchmark (bps) PD","4Y CDS Risk Benchmark (bps)","4Y CDS Risk Benchmark Probability of Default in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_DD_BPS_CDS","4Y CDS Risk Benchmark d/d (bps) PD","4Y CDS Risk Benchmark d/d (bps)","4Y CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_DD_PERCENT_CDS","4Y CDS Risk Benchmark d/d (%) PD","4Y CDS Risk Benchmark d/d (%)","4Y CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_MM_BPS_CDS","4Y CDS Risk Benchmark m/m (bps) PD","4Y CDS Risk Benchmark m/m (bps)","4Y CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_MM_PERCENT_CDS","4Y CDS Risk Benchmark m/m (%) PD","4Y CDS Risk Benchmark m/m (%)","4Y CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_QQ_BPS_CDS","4Y CDS Risk Benchmark q/q (bps) PD","4Y CDS Risk Benchmark q/q (bps)","4Y CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_QQ_PERCENT_CDS","4Y CDS Risk Benchmark q/q (%) PD","4Y CDS Risk Benchmark q/q (%)","4Y CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_SS_BPS_CDS","4Y CDS Risk Benchmark hy/hy (bps) PD","4Y CDS Risk Benchmark hy/hy (bps)","4Y CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_SS_PERCENT_CDS","4Y CDS Risk Benchmark hy/hy (%) PD","4Y CDS Risk Benchmark hy/hy (%)","4Y CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_WW_BPS_CDS","4Y CDS Risk Benchmark w/w (bps) PD","4Y CDS Risk Benchmark w/w (bps)","4Y CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_WW_PERCENT_CDS","4Y CDS Risk Benchmark w/w (%) PD","4Y CDS Risk Benchmark w/w (%)","4Y CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_YY_BPS_CDS","4Y CDS Risk Benchmark y/y (bps) PD","4Y CDS Risk Benchmark y/y (bps)","4Y CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_4Y_YY_PERCENT_CDS","4Y CDS Risk Benchmark y/y (%) PD","4Y CDS Risk Benchmark y/y (%)","4Y CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_DD_BPS_CDS","6M CDS Risk Benchmark d/d (bps) PD","6M CDS Risk Benchmark d/d (bps) PD","6M CDS Risk Benchmark Probability of Default Day on Day change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_DD_PERCENT_CDS","6M CDS Risk Benchmark d/d (%) PD","6M CDS Risk Benchmark d/d (%) PD","6M CDS Risk Benchmark Probability of Default Day on Day percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_MM_BPS_CDS","6M CDS Risk Benchmark m/m (bps) PD","6M CDS Risk Benchmark m/m (bps) PD","6M CDS Risk Benchmark Probability of Default Month on Month change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_MM_PERCENT_CDS","6M CDS Risk Benchmark m/m (%) PD","6M CDS Risk Benchmark m/m (%) PD","6M CDS Risk Benchmark Probability of Default Month on Month percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_QQ_BPS_CDS","6M CDS Risk Benchmark q/q (bps) PD","6M CDS Risk Benchmark q/q (bps) PD","6M CDS Risk Benchmark Probability of Default Quater on Quater change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_QQ_PERCENT_CDS","6M CDS Risk Benchmark q/q (%) PD","6M CDS Risk Benchmark q/q (%) PD","6M CDS Risk Benchmark Probability of Default Quater on Quater percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_SS_BPS_CDS","6M CDS Risk Benchmark hy/hy (bps) PD","6M CDS Risk Benchmark hy/hy (bps) PD","6M CDS Risk Benchmark Probability of Default Half-Year on Half-Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_SS_PERCENT_CDS","6M CDS Risk Benchmark hy/hy (%) PD","6M CDS Risk Benchmark hy/hy (%) PD","6M CDS Risk Benchmark Probability of Default Half-Year on Healf-Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_WW_BPS_CDS","6M CDS Risk Benchmark w/w (bps) PD","6M CDS Risk Benchmark w/w (bps) PD","6M CDS Risk Benchmark Probability of Default Week on Week change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_WW_PERCENT_CDS","6M CDS Risk Benchmark w/w (%) PD","6M CDS Risk Benchmark w/w (%) PD","6M CDS Risk Benchmark Probability of Default Week on Week percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_YY_BPS_CDS","6M CDS Risk Benchmark y/y (bps) PD","6M CDS Risk Benchmark y/y (bps) PD","6M CDS Risk Benchmark Probability of Default Year on Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_PD_6M_YY_PERCENT_CDS","6M CDS Risk Benchmark y/y (%) PD","6M CDS Risk Benchmark y/y (%) PD","6M CDS Risk Benchmark Probability of Default Year on Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_DD_BPS_CDS","2Y CDS Risk Benchmark d/d (bps)","2Y CDS Risk Benchmark d/d (bps)","2Y CDS Risk Benchmar Spread Day on Day change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_DD_PERCENT_CDS","2Y CDS Risk Benchmark d/d (%)","2Y CDS Risk Benchmark d/d (%)","2Y CDS Risk Benchmark Spread Day on Day percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_MM_BPS_CDS","2Y CDS Risk Benchmark m/m (bps)","2Y CDS Risk Benchmark m/m (bps)","2Y CDS Risk Benchmark Spread Month on Month change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_MM_PERCENT_CDS","2Y CDS Risk Benchmark m/m (%)","2Y CDS Risk Benchmark m/m (%)","2Y CDS Risk Benchmark Spread Month on Month percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_QQ_BPS_CDS","2Y CDS Risk Benchmark q/q (bps)","2Y CDS Risk Benchmark q/q (bps)","2Y CDS Risk Benchmark Spread Quater on Quater change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_QQ_PERCENT_CDS","2Y CDS Risk Benchmark q/q (%)","2Y CDS Risk Benchmark q/q (%)","2Y CDS Risk Benchmark Spread Quater on Quater percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_SS_BPS_CDS","2Y CDS Risk Benchmark hy/hy (bps)","2Y CDS Risk Benchmark hy/hy (bps)","2Y CDS Risk Benchmark Spread Half-Year on Half-Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_SS_PERCENT_CDS","2Y CDS Risk Benchmark hy/hy (%)","2Y CDS Risk Benchmark hy/hy (%)","2Y CDS Risk Benchmark Spread Half-Year on Healf-Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_WW_BPS_CDS","2Y CDS Risk Benchmark w/w (bps)","2Y CDS Risk Benchmark w/w (bps)","2Y CDS Risk Benchmark Spread Week on Week change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_WW_PERCENT_CDS","2Y CDS Risk Benchmark w/w (%)","2Y CDS Risk Benchmark w/w (%)","2Y CDS Risk Benchmark Spread Week on Week percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_YY_BPS_CDS","2Y CDS Risk Benchmark y/y (bps)","2Y CDS Risk Benchmark y/y (bps)","2Y CDS Risk Benchmark Spread Year on Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_2Y_YY_PERCENT_CDS","2Y CDS Risk Benchmark y/y (%)","2Y CDS Risk Benchmark y/y (%)","2Y CDS Risk Benchmark Spread Year on Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_DD_BPS_CDS","4Y CDS Risk Benchmark d/d (bps)","4Y CDS Risk Benchmark d/d (bps)","4Y CDS Risk Benchmar Spread Day on Day change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_DD_PERCENT_CDS","4Y CDS Risk Benchmark d/d (%)","4Y CDS Risk Benchmark d/d (%)","4Y CDS Risk Benchmark Spread Day on Day percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_MM_BPS_CDS","4Y CDS Risk Benchmark m/m (bps)","4Y CDS Risk Benchmark m/m (bps)","4Y CDS Risk Benchmark Spread Month on Month change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_MM_PERCENT_CDS","4Y CDS Risk Benchmark m/m (%)","4Y CDS Risk Benchmark m/m (%)","4Y CDS Risk Benchmark Spread Month on Month percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_QQ_BPS_CDS","4Y CDS Risk Benchmark q/q (bps)","4Y CDS Risk Benchmark q/q (bps)","4Y CDS Risk Benchmark Spread Quater on Quater change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_QQ_PERCENT_CDS","4Y CDS Risk Benchmark q/q (%)","4Y CDS Risk Benchmark q/q (%)","4Y CDS Risk Benchmark Spread Quater on Quater percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_SS_BPS_CDS","4Y CDS Risk Benchmark hy/hy (bps)","4Y CDS Risk Benchmark hy/hy (bps)","4Y CDS Risk Benchmark Spread Half-Year on Half-Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_SS_PERCENT_CDS","4Y CDS Risk Benchmark hy/hy (%)","4Y CDS Risk Benchmark hy/hy (%)","4Y CDS Risk Benchmark Spread Half-Year on Healf-Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_WW_BPS_CDS","4Y CDS Risk Benchmark w/w (bps)","4Y CDS Risk Benchmark w/w (bps)","4Y CDS Risk Benchmark Spread Week on Week change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_WW_PERCENT_CDS","4Y CDS Risk Benchmark w/w (%)","4Y CDS Risk Benchmark w/w (%)","4Y CDS Risk Benchmark Spread Week on Week percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_YY_BPS_CDS","4Y CDS Risk Benchmark y/y (bps)","4Y CDS Risk Benchmark y/y (bps)","4Y CDS Risk Benchmark Spread Year on Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_4Y_YY_PERCENT_CDS","4Y CDS Risk Benchmark y/y (%)","4Y CDS Risk Benchmark y/y (%)","4Y CDS Risk Benchmark Spread Year on Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_DD_BPS_CDS","6M CDS Risk Benchmark d/d (bps)","6M CDS Risk Benchmark d/d (bps)","6M CDS Risk Benchmar Spread Day on Day change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_DD_PERCENT_CDS","6M CDS Risk Benchmark d/d (%)","6M CDS Risk Benchmark d/d (%)","6M CDS Risk Benchmark Spread Day on Day percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_MM_BPS_CDS","6M CDS Risk Benchmark m/m (bps)","6M CDS Risk Benchmark m/m (bps)","6M CDS Risk Benchmark Spread Month on Month change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_MM_PERCENT_CDS","6M CDS Risk Benchmark m/m (%)","6M CDS Risk Benchmark m/m (%)","6M CDS Risk Benchmark Spread Month on Month percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_QQ_BPS_CDS","6M CDS Risk Benchmark q/q (bps)","6M CDS Risk Benchmark q/q (bps)","6M CDS Risk Benchmark Spread Quater on Quater change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_QQ_PERCENT_CDS","6M CDS Risk Benchmark q/q (%)","6M CDS Risk Benchmark q/q (%)","6M CDS Risk Benchmark Spread Quater on Quater percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_SS_BPS_CDS","6M CDS Risk Benchmark hy/hy (bps)","6M CDS Risk Benchmark hy/hy (bps)","6M CDS Risk Benchmark Spread Half-Year on Half-Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_SS_PERCENT_CDS","6M CDS Risk Benchmark hy/hy (%)","6M CDS Risk Benchmark hy/hy (%)","6M CDS Risk Benchmark Spread Half-Year on Healf-Year percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_WW_BPS_CDS","6M CDS Risk Benchmark w/w (bps)","6M CDS Risk Benchmark w/w (bps)","6M CDS Risk Benchmark Spread Week on Week change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_WW_PERCENT_CDS","6M CDS Risk Benchmark w/w (%)","6M CDS Risk Benchmark w/w (%)","6M CDS Risk Benchmark Spread Week on Week percentage change","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_YY_BPS_CDS","6M CDS Risk Benchmark y/y (bps)","6M CDS Risk Benchmark y/y (bps)","6M CDS Risk Benchmark Spread Year on Year change in basis points","base", apiResponse},
                {"FC_RISK_BENCHMARK_SPREADS_6M_YY_PERCENT_CDS","6M CDS Risk Benchmark y/y (%)","6M CDS Risk Benchmark y/y (%)","6M CDS Risk Benchmark Spread Year on Year percentage change","base", apiResponse},
        };
    }

    @Test(dataProvider = "Fisc6373", enabled = false)
    public void Fisc6373_cdsEnhacementsInDataAggregator_ValidatingPresenceOfAttributes(String fitchFieldId, String displayName, String fitchFieldDesc, String fieldDefinition, String permissionsRequired, String apiResponse){
        try {
            Assert.assertTrue(apiResponse.contains("\"fitchFieldId\":\"" + fitchFieldId + "\""));
            logger.info("FISC 6373 API DATA AGGREGATOR PASSED! Tested FITCHFIELDID: " + fitchFieldId + " DISPLAYNAME: " + displayName + " FITCHFIELDDESC " + fitchFieldDesc + " FIELDDEFINITION " + fieldDefinition + " PERMISSION " + permissionsRequired);
        } catch (AssertionError err){
            logger.error("FISC 6373 API DATA AGGREGATOR FAILED! Tested FITCHFIELDID: "  + fitchFieldId + " ERROR: " + err);
            Assert.fail();
        }
    }

    @Test
    public void Fisc6373_cdsEnhacementsInDataAggregator_ValidatingValues() throws IOException {
        MongoCollection<Document> collection = mongoUtils
                .connectToMongoDatabase(CAL)
                .getDatabase("cds-data")
                .getCollection("cdsEntities");

        AggregateIterable<Document> mongoValues = collection.aggregate(
                Arrays.asList(
                        new Document()
                                .append("$match", new Document()
                                        .append("FC_AGENT_ID", 114281L)
                                ),
                        new Document()
                                .append("$project", new Document()
                                        .append("FC_2Y_BPS_CDS", 1.0)
                                        .append("FC_2Y_BPS_CDS_PD", 1.0)
                                        .append("FC_2Y_DD_BPS_CDS", 1.0)
                                        .append("FC_2Y_DD_BPS_CDS_PD", 1.0)
                                        .append("FC_2Y_DD_PERCENT_CDS", 1.0)
                                        .append("FC_2Y_DD_PERCENT_CDS_PD", 1.0)
                                        .append("FC_2Y_MM_BPS_CDS", 1.0)
                                        .append("FC_2Y_MM_BPS_CDS_PD", 1.0)
                                        .append("FC_2Y_MM_PERCENT_CDS", 1.0)
                                        .append("FC_2Y_MM_PERCENT_CDS_PD", 1.0)
                                        .append("FC_2Y_QQ_BPS_CDS", 1.0)
                                        .append("FC_2Y_QQ_BPS_CDS_PD", 1.0)
                                        .append("FC_2Y_QQ_PERCENT_CDS", 1.0)
                                        .append("FC_2Y_QQ_PERCENT_CDS_PD", 1.0)
                                        .append("FC_2Y_SS_BPS_CDS", 1.0)
                                        .append("FC_2Y_SS_BPS_CDS_PD", 1.0)
                                        .append("FC_2Y_SS_PERCENT_CDS", 1.0)
                                        .append("FC_2Y_SS_PERCENT_CDS_PD", 1.0)
                                        .append("FC_2Y_WW_BPS_CDS", 1.0)
                                        .append("FC_2Y_WW_BPS_CDS_PD", 1.0)
                                        .append("FC_2Y_WW_PERCENT_CDS", 1.0)
                                        .append("FC_2Y_WW_PERCENT_CDS_PD", 1.0)
                                        .append("FC_2Y_YY_BPS_CDS", 1.0)
                                        .append("FC_2Y_YY_BPS_CDS_PD", 1.0)
                                        .append("FC_2Y_YY_PERCENT_CDS", 1.0)
                                        .append("FC_2Y_YY_PERCENT_CDS_PD", 1.0)
                                        .append("FC_4Y_BPS_CDS", 1.0)
                                        .append("FC_4Y_BPS_CDS_PD", 1.0)
                                        .append("FC_4Y_DD_BPS_CDS", 1.0)
                                        .append("FC_4Y_DD_BPS_CDS_PD", 1.0)
                                        .append("FC_4Y_DD_PERCENT_CDS", 1.0)
                                        .append("FC_4Y_DD_PERCENT_CDS_PD", 1.0)
                                        .append("FC_4Y_MM_BPS_CDS", 1.0)
                                        .append("FC_4Y_MM_BPS_CDS_PD", 1.0)
                                        .append("FC_4Y_MM_PERCENT_CDS", 1.0)
                                        .append("FC_4Y_MM_PERCENT_CDS_PD", 1.0)
                                        .append("FC_4Y_QQ_BPS_CDS", 1.0)
                                        .append("FC_4Y_QQ_BPS_CDS_PD", 1.0)
                                        .append("FC_4Y_QQ_PERCENT_CDS", 1.0)
                                        .append("FC_4Y_QQ_PERCENT_CDS_PD", 1.0)
                                        .append("FC_4Y_SS_BPS_CDS", 1.0)
                                        .append("FC_4Y_SS_BPS_CDS_PD", 1.0)
                                        .append("FC_4Y_SS_PERCENT_CDS", 1.0)
                                        .append("FC_4Y_SS_PERCENT_CDS_PD", 1.0)
                                        .append("FC_4Y_WW_BPS_CDS", 1.0)
                                        .append("FC_4Y_WW_BPS_CDS_PD", 1.0)
                                        .append("FC_4Y_WW_PERCENT_CDS", 1.0)
                                        .append("FC_4Y_WW_PERCENT_CDS_PD", 1.0)
                                        .append("FC_4Y_YY_BPS_CDS", 1.0)
                                        .append("FC_4Y_YY_BPS_CDS_PD", 1.0)
                                        .append("FC_4Y_YY_PERCENT_CDS", 1.0)
                                        .append("FC_4Y_YY_PERCENT_CDS_PD", 1.0)
                                        .append("FC_6M_BPS_CDS", 1.0)
                                        .append("FC_6M_DD_BPS_CDS", 1.0)
                                        .append("FC_6M_DD_PERCENT_CDS", 1.0)
                                        .append("FC_6M_MM_BPS_CDS", 1.0)
                                        .append("FC_6M_MM_PERCENT_CDS", 1.0)
                                        .append("FC_6M_QQ_BPS_CDS", 1.0)
                                        .append("FC_6M_QQ_PERCENT_CDS", 1.0)
                                        .append("FC_6M_SS_BPS_CDS", 1.0)
                                        .append("FC_6M_SS_PERCENT_CDS", 1.0)
                                        .append("FC_6M_WW_BPS_CDS", 1.0)
                                        .append("FC_6M_WW_PERCENT_CDS", 1.0)
                                        .append("FC_6M_YY_BPS_CDS", 1.0)
                                        .append("FC_6M_YY_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_2Y_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_4Y_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_6M_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_DD_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_DD_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_MM_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_MM_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_QQ_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_QQ_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_SS_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_SS_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_WW_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_WW_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_YY_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_2Y_YY_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_DD_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_DD_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_MM_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_MM_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_QQ_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_QQ_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_SS_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_SS_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_WW_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_WW_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_YY_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_PD_4Y_YY_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_DD_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_DD_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_MM_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_MM_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_QQ_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_QQ_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_SS_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_SS_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_WW_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_WW_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_YY_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_2Y_YY_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_DD_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_DD_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_MM_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_MM_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_QQ_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_QQ_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_SS_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_SS_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_WW_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_WW_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_YY_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_4Y_YY_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_DD_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_DD_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_MM_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_MM_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_QQ_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_QQ_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_SS_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_SS_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_WW_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_WW_PERCENT_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_YY_BPS_CDS", 1.0)
                                        .append("FC_RISK_BENCHMARK_SPREADS_6M_YY_PERCENT_CDS", 1.0)
                                ),
                        new Document()
                                .append("$sort", new Document()
                                        .append("_id", -1.0)
                                ),
                        new Document()
                                .append("$limit", 1.0)
                )
        );

        Response res = apiUtils.postToDataAggregator("6373.json", AuthrztionValue, XappClintIDvalue, dataPostUrl);

        ArrayList<String> values = res.path("data.attributes.entities[0].values");
        int numberOfValues = values.size();

        HashMap<String, String> apiValues = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.0000");

        for (Document mongoValue: mongoValues){
            System.out.println(mongoValue.toJson());
        }

        for (Document mongoValue : mongoValues) {
            System.out.println(res.asString());
            for (int i = 0; i < numberOfValues; i++) {
                String apiFitchFieldId = res.path("data.attributes.entities[0].values[" + i + "].fitchFieldId");
                String apiValue = df.format(res.path("data.attributes.entities[0].values[" + i + "].values[0].value[0]"));
                apiValues.put(apiFitchFieldId, apiValue);
                float apiValue_float = Float.parseFloat(apiValue);
                float mongoValue_float = Float.parseFloat(df.format(mongoValue.get(apiFitchFieldId)));
                System.out.println(apiFitchFieldId + "     " + apiValue_float + "     " + mongoValue_float);
                try {
                    Assert.assertEquals(apiValue_float, mongoValue_float, 0.01);
                    logger.info("FISC 6373 API VALUES PASSED FITCHFIELDID " + apiFitchFieldId);
                } catch (AssertionError err) {
                    logger.error("FISC 6373 API VALUES FAILED! FITCHFIELDID " + apiFitchFieldId);
                    Assert.fail();
                }
            }
        }


    }

    @DataProvider(name="Fisc6373_baseUser")
    public Object[][] getDataFor6373_baseUser() throws IOException {

        String apiResponse = apiUtils.postToDataAggregatorBaseUser("6373_baseUserFields.json", XappClintIDvalue, dataPostUrl).asString();
        return new Object[][] {
                {"FC_2Y_BPS_CDS",apiResponse},
                {"FC_2Y_DD_BPS_CDS",apiResponse},
                {"FC_2Y_DD_PERCENT_CDS",apiResponse},
                {"FC_2Y_MM_BPS_CDS",apiResponse},
                {"FC_2Y_MM_PERCENT_CDS",apiResponse},
                {"FC_2Y_QQ_BPS_CDS",apiResponse},
                {"FC_2Y_QQ_PERCENT_CDS",apiResponse},
                {"FC_2Y_SS_BPS_CDS",apiResponse},
                {"FC_2Y_SS_PERCENT_CDS",apiResponse},
                {"FC_2Y_WW_BPS_CDS",apiResponse},
                {"FC_2Y_WW_PERCENT_CDS",apiResponse},
                {"FC_2Y_YY_BPS_CDS",apiResponse},
                {"FC_2Y_YY_PERCENT_CDS",apiResponse},
                {"FC_4Y_BPS_CDS",apiResponse},
                {"FC_4Y_DD_BPS_CDS",apiResponse},
                {"FC_4Y_DD_PERCENT_CDS",apiResponse},
                {"FC_4Y_MM_BPS_CDS",apiResponse},
                {"FC_4Y_MM_PERCENT_CDS",apiResponse},
                {"FC_4Y_QQ_BPS_CDS",apiResponse},
                {"FC_4Y_QQ_PERCENT_CDS",apiResponse},
                {"FC_4Y_SS_BPS_CDS",apiResponse},
                {"FC_4Y_SS_PERCENT_CDS",apiResponse},
                {"FC_4Y_WW_BPS_CDS",apiResponse},
                {"FC_4Y_WW_PERCENT_CDS",apiResponse},
                {"FC_4Y_YY_BPS_CDS",apiResponse},
                {"FC_4Y_YY_PERCENT_CDS",apiResponse},
                {"FC_6M_YY_PERCENT_CDS", apiResponse},
                {"FC_6M_BPS_CDS",apiResponse},
                {"FC_6M_DD_BPS_CDS",apiResponse},
                {"FC_6M_DD_PERCENT_CDS",apiResponse},
                {"FC_6M_MM_BPS_CDS",apiResponse},
                {"FC_6M_MM_PERCENT_CDS",apiResponse},
                {"FC_6M_QQ_BPS_CDS",apiResponse},
                {"FC_6M_QQ_PERCENT_CDS",apiResponse},
                {"FC_6M_SS_BPS_CDS",apiResponse},
                {"FC_6M_SS_PERCENT_CDS",apiResponse},
                {"FC_6M_WW_BPS_CDS",apiResponse},
                {"FC_6M_WW_PERCENT_CDS",apiResponse},
                {"FC_6M_YY_BPS_CDS",apiResponse},
                {"FC_6M_YY_PERCENT_CDS", apiResponse},
        };
    }

    @Test(dataProvider = "Fisc6373_baseUser")
    public void Fisc6373_cdsEnhacementsInDataAggregator_BaseUser_ValidatingValues(String fitchFieldId, String apiResponse){
        try {
            Assert.assertTrue(apiResponse.contains("\"fitchFieldId\":\"" + fitchFieldId + "\",\"type\":\"numerical\",\"auditTrail\":false,\"isRestricted\":true"));
            logger.info("FISC 6373 API DATA AGGREGATOR BASE USER PASSED FITCHFIELDID " + fitchFieldId);
        } catch (AssertionError err) {
            logger.error("FISC 6373 API DATA AGGREGATOR BASE USER PASSED FITCHFIELDID " + fitchFieldId + " ERROR " + err);
            Assert.fail();
        }
    }

    @Test(enabled=false)
    public void Fisc7288_HistoryFilesFor6MCDSDataToUpstreamAndMongo(){
        //Done by Max
        Assert.assertTrue(true);
    }

    @Test(enabled=false)
    public void Fisc7891_IssueDataInPostgresMasterDatabase(){

    }
}
